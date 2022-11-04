#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
   char comando[256];
   pid_t pid; int status;

   while(1)
   {
     printf("Digitare un comando: ");
     int res = scanf("%s",comando);
     if(res == EOF) continue;
     pid = fork();
     if ( pid == -1 ) {
       printf("Errore nella fork.\n");
       exit(1);
     }
	//se entra nell'if vuol dire che il pezzo di codice lo esegue il child.
	//il child sostituisce l'immagine del processo di se stesso con una 
	//programma execlp
     if ( pid == 0 )
	   //il primo parametro è un filename
	   //comando è un puntatore che punta ad una stringa che è il filename
	   //argv[0] è il nome del programma per quello il secondo parametro è
	   //anche comando		
        execlp(comando, comando, NULL);
     else wait(&status);
   }
   return 0;
}