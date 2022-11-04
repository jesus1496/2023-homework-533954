//include che servono per le diverse syscall
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

void main () {
	int res, status; //questo alloca una variabile nello stack
	printf("I'm a process and I'm goind to create a child\n");
	res = fork();
	if(res<0) printf("I cannot create a child");
	else if(res == 0) {
	  //eseguito dal processo child
	  sleep(5);
	  printf("I'm the child!\n");
	  exit(0);
	}
	else{
	  //eseguito da processo parent
	  printf("I'm now a parent and I'll wait for my child to die... \n");
	  wait(&status); //viene passato un puntatore 
	  //la macro ci dice se il processo child terminato ha terminato invocando
	  //la exit esplicitamente(gli 8 bit meno significativi)
	  printf("My child has invoked exit? %d\n", WIFEXITED(status));
	  // la macro WEXITSTATUS ottiene il codice di terminazione dalla
	  // variabile status
	  printf("My child has invoked exit(%d)\n", WEXITSTATUS(status));
	}
	printf("My child is dead, so it's my time to die\n");
	exit(0);
}
