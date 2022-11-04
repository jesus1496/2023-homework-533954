#include <stdio.h>

#ifndef  ITERATIONS  /*se ITERATIONS non è definito, possiamo definirlo*/

#define ITERATIONS 5
#endif
 
void main(){
	int i;
	
	for(i=0;i<ITERATIONS;i++)
		printf("%d\n", i);

}
