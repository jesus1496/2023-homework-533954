#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <pthread.h>

//child_func: è un puntatore ad un funzione 

void* child_func(void *par){
	*((int*)par) = 1;
	sleep(10);
	printf("I'm the child!\n");
	pthread_exit(par);
}
	
int main(){
	pthread_t ctid;  //pthread_t: un tipo di thread che sarebbe un intero
	int res, *status_ptr, status_val;
		status_ptr = &status_val;
	printf("I'm a thread in a process. " 
			"I'm going to create a thread\n");
	res = pthread_create(&ctid, NULL, child_func, &status_val);
	//il primo parametro è un puntatore ad una area di memoria e ci dice dove il parent thread
	//può accedere per ottenere il pthread_t ossia il thread id del child
	//il secondo deve contenere certi attributi che vogliamo che il nuovo thread contenga
	//dobbiamo tenere in mente che il thread inizia con una funzione e quindi questa viene messa come terzo parametro
	if(res != 0) printf("I cannot create a child");
	else{
		printf("I'm now a parent thread. " 
				"I'll wait for my child %lu thread to die...\n",ctid); 
		pthread_join(ctid, (void**)&status_ptr); //ctid:variabile su cui pthread_create andara a scrivere il thread appena creato
		//pthread_join di solito comprende un wait pid
		//con il join aspettiamo quel thread che termina l'esecuzione, l'unico perche abbiamo passato thread id come unico parametro
		printf("My child has completed...%d\n",*status_ptr);
	}	
	printf("My child is dead, so it's my time to die\n");
	return 0;
}