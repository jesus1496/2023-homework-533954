#include <stdio.h>
#include <stdlib.h>
#include <wait.h>
#include <unistd.h>

void main(int argc, char **argv){
	pid_t pid;
	int status, result;
	
	pid = fork();
	if(pid == -1) {
		printf("errore nella chiamata fork()\n");
	     exit(EXIT_FAILURE);
	}

	if(pid == 0){
		sleep(5);
		printf("processo figlio\n");
		exit(0);
	}
	else {
		printf("processo padre, attesa terminazione figlio\n");
		result = wait(&status);
		if(result == -1) printf("errore nella chiamata wait()\n");
	}
}