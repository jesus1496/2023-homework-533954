#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <pthread.h>

#define about(msg) do{printf(msg);exit(1);}while(0)

FILE* ifd = 0;
volatile int flag = 0;
volatile int lock = 0;
char *tofind = 'a';

void acquire(){
    while (__sync_lock_test_and_set(&lock, 1))
        while(lock);
}

void release(){
    lock = 0;
}

void* thread_function(void* param){

    char *data = (char*)malloc(sizeof(char));

    while ((fgets(data,1,ifd) != NULL) && !flag) {
        data[strlen(data)-1] = '\0';
        for(int i = 0, i < strlen(data); i++){
            if(data[i] == *tofind){
                acquire();
                counter++;
                release();
            }
            else{
                counter;
            }
        }
    }
}

int main(int argc, char const *argv[]) {
    if( argc != 3 ) abort("use...");

    ifd = open(argv[1], O_RDONLY)
    if( ifd == NULL ) abort("error open \n");

    int num_of_thread = atoi(argv[2]);

    pthread_t *tids = malloc(num_of_thread* sizeof(pthread_t));

    for(int i = 0; i < num_of_thread; i++){
        pthread_create(tids + i, NULL, thread_function, NULL);
    }

    for(int i = 0, i < num_of_thread; i++){
        pthread_join(tids[i], NULL);
    }

    close (ifd);
    free(tids);

    printf("Quanti file hanno la 'a':%d", counter);
}
