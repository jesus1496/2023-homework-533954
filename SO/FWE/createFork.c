#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
	int id = fork();
	if( id != 0){
		fork();
	}
	printf("Hello world\n");
	//printf("Hello world from id: %id\n", id);
	//if (id  == 0) {
	//	printf("Hello from child process\n");
	//}else{
	//	 printf("Hello from process parent\n");
	//}
	return 0;
}