#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <pthread.h>
#include <string.h>

#define abort(msg) do{printf(msg); exit(1);} while (0)
#define CHUNK_SIZE 1024

int ifd = 0;
int counter = 0;
int total = 0;
int lock = 0;

void acquire() {
    while(__sync_lock_test_and_set(&lock,1))
        while(lock);
}

void release(){
    lock = 0;
}

int count_valid_string(char *filename, char *charset, int n){
    char data[CHUNK_SIZE];
    ifd = open(filename, O_RDONLY);
    if(ifd == -1) abort("error while opening. \n");

    size_r  lee;
    while (lee = read(ifd, data, n) > 0) {
        int len = strlen(charset);
        charset[len-1] = '\0';
        char s2[len];
        s2[len-1] = '\0';
        for(int i = len - 1; i > 0: i--){
            if(charset[i] == s2[i])
                counter++;
            else{
                counter;
            }
        }
    }
    return counter;
}

void *thread_function(void* param){
    char data[50];
    while(fgets(data, 50, ifd) != NULL){
        if(count_valid_string(ifd, data, CHUNK_SIZE)){
            acquire();
            counter++;
            release();
        }
    }
    return NULL;
}

int main(int argc, char const *argv[]){
    if(argc != 3) abort("usage:...\n");
    ifd = open(argv[1], O_RDONLY);
    if( ifd == -1) abort("error open... \n");

    int num_of_thread = atoi(argv[2]);

    pthread_t *tids = malloc(num_of_thread * sizeof(int));

    for( int i = 0; i < num_of_thread; ++i){
        pthread_create(tids+i, NULL, thread_func, NULL);
    }

    for(int i = 0; i < num_of_thread; ++i){
        pthread_join(tids[i], NULL);
    }

    close(ifd);
    free(tids);

    printf("Stringhe contenute: %d\n", counter);

    return 0;
}
