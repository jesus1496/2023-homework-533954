#include <stdlib.h>
#include <stdio.h>

#ifndef VERSION
#define VERSION 0
#endif

int x;
int y = 15;


void stampa(int len, int *values){
  // stampa ciascun intero seguito da uno spazio
  for(int j=0;j<len;j++) printf("%d ", values[j]);
  // stampa il carattere di nuova linea
  printf("\n");
}


int main(int argc, char *argv[],char **envp){
  int *values;
  int i;
  // controllo che il numero di parametri sia corretto
  if(argc != 2){ //perche' 2?
   printf("Error! correct usage: ./a.out #num_integers\n");
   return -1;
  }
  printf("num parameters:%d\n", argc); // stampa il valore di argc
  for(i=0;i<argc;i++) printf("parametro %d: %s\n", i, argv[i]); // stampa tutti i parametri

  y = atoi(argv[1]); // ascii to integer: converte la stringa in intero
  values = malloc(sizeof(int)*y); // alloca sufficiente memoria per 'y' interi
  
  for(i=0;i<y;i++) values[i] = i; // memorizza i primi 'y' interi incluso lo 0
  stampa(y, values);              // invoca la funzione stampa


 #if VERSION >= 2
  printf("Address of 'stampa'        : %p\n", &stampa);
  printf("Address of 'main'          : %p\n", &main);
  printf("Address of 'y'             : %p\n", &y);
  printf("Address of 'x'             : %p\n", &x);
  printf("Address of 'values' buffer : %p\n", values);
  printf("Address of 'i'             : %p\n", &i);
  printf("Address of 'values'        : %p\n", &values);
  printf("Address of 'argv' buffer   : %p\n", argv);
  printf("Address of 'envp' 		  : %p\n", envp);
 #endif

 i = 0;
 #if VERSION <= 2	
   while(0)
 #elif VERSION ==3
   while(1)
 #elif VERSION >= 4
   while(i<58)
 #endif
  {
	//stampa l'indirizo di values ed allocato nello STACK
	printf("Address: value of 'values[%i]' %p:%p\n", i,(&values)+i, *((&values)+i));
	i++;
  }

 #if VERSION == 5
   i=0
   while(1)
    {
	//stampa i valori dell'array argv e la posizione e termina in un
	//errore, 
	 printf("Address:value of 'argv[%i]' %p:%s\n", i, (argv)+i, *((argv)+i));
	 i++;
    }
#endif

free(values);
return 0;
}
