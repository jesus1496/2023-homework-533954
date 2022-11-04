/*
	Si scriva un programma C tale che il main thread legge ciclicamente da standard input una stringa P
	che corrisponde ad un percorso di file. Per ciascun percorso P viene creato un nuovo thread, il quale
	legge il file al percorso P cercando il carattere ‘a’.
	Qualora il main thread riceve un percorso non valido o un numero di percorsi pari a N (a piacere) il
	programma stampa su standard output il numero di file che contengono il carattere ‘a’.
	Nota: la lettura da standard input del main thread dovrà essere concorrente alla lettura dei file da
	parte dei thread child.
*/

#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>
#include<string.h>
#include<pthread.h>

#define abort(msg) do{printf(msg); exit(1);} while(0)

#define BUFFER_SIZE 256
#define N_PERCORSI 5

void* func(void* param) {
	int ifd = (*(int*) param);

	char buffer[BUFFER_SIZE];

	ssize_t r = 0;

	while((r = read(ifd, buffer, BUFFER_SIZE*sizeof(char))) > 0) {
		if(strstr(buffer, "a")) {
			pthread_exit((void*)1);
		}
	}

	return 0;
}

int main(int argc, char *argv[]) {

	if(argc != 1) abort("usage: esame\n");

	int contatore_a = 0;

	int percorsi = N_PERCORSI; //scelto a piacere

	int threads = 0;

	pthread_t *tids = malloc(percorsi * sizeof(pthread_t));

	int *ifds = malloc(percorsi * sizeof(int));

	for(int i = 0; i < percorsi; i++) {
		printf("Inserisci un percorso: ");
		char percorso[25];
		scanf("%s", percorso);

		ifds[i] = open(percorso, O_RDONLY);
		if(ifds[i] == -1) {
			break;
		}

		pthread_create(tids + i, NULL, func, ifds + i);
		threads++;
	}

	for(int i = 0; i < threads; i++) {
		void *ritorno;
		pthread_join(tids[i], &ritorno);
		contatore_a = contatore_a + (intptr_t) ritorno;
		close(ifds[i]);
	}

	free(tids);

	printf("Numero di file che contengono il carattere 'a': %d\n", contatore_a);

	return 0;
}
