#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <pthread.h>
#include <string.h>

#define abort(msg) do{printf(msg);exit(1);} while(0)

FILE* ifp = 0;
int lock = 0;
int counter = 0;

void acquire(){
    while(__sync_lock_test_and_set(&lock,1))
              while(lock);
}

void release() {
    lock = 0;
}

int palindroma(char *s1){
    int len = strlen(s1);
    s1[len-1] = '\0';
    char s2[len];
    s2[len-1] = '\0';

    for(int i=0; i<len; i++)
          s2[len-1-i] = s1[i];
    return(strcmp(s1,s2) == 0);
}

void *thread_function(void *param){
    char data[30];
    FILE *ifd;
    while(fgets(data,30,ifd) != NULL){
        if(palindroma(data)){
            acquire();
            counter++;
            release();
        }
    }
}

int main(int argc, char const *argv[]){
    if(argc != 3) abort("use...");

    FILE* ifd = fopen(argv[1],"r");
    if(ifd == NULL) abort("open error\n");

    int num_of_threads = atoi(argv[2]);

    pthread_t *tids = malloc(num_of_threads*sizeof(pthread_t));

    for(int i = 0; i < num_of_threads; ++i){
          pthread_create(tids+i, NULL, thread_function, NULL);
    }

    for(int i = 0; i < num_of_threads; ++i){
          pthread_join(tids[i], NULL);
    }
    printf("Palindrome: %d\n",counter);

    return 0;
}
