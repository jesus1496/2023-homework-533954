#include <stdio.h>
int main()
{
	int var = -1;
	int *var_ptr = &var;

	printf("La variabile 'var' ha indirizzo %p e valore %d\n",&var, var); 
	printf("La variabile 'var_ptr' ha indirizzo %p e valore %p. "
		"Il contenuto all'indirzzo referenziato è %d \n", &var_ptr, var_ptr, *var_ptr);
	
	printf("Taglia di un int     %ld\n", sizeof(int));
	printf("Decimale con segno   %d\n", var);
	printf("Decimale unsigned    %u\n", var);
	printf("Intero ottale        %o\n", var);
	printf("Intero esadecimale   %x\n", var);

	printf("Valore decimale con segno %d\n", -1);

	printf("Valore decimale con segno %d\n", 4294967295U);

	printf("Valore decimale con segno %d\n", 037777777777);

	printf("Valore decimale con segno %d\n", 0xffffffff);

	
}
