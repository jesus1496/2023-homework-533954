#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <limits.h>
#include <pthread.h>

#define abort(msg) do{printf(msg);exit(1);} while(0)

//volatile short... os read every time this variable
//from memory and not from cache
volatile short min = SHRT_MAX;
volatile short max = SHRT_MIN;

int ifd;//global file descriptor

#define NUMBERS 32768 //2^15 short inside the file
#define FILESIZE NUMBERS*sizeof(short)

#define SIZE_MEM 512 // max byte of mem allocable for every thread
#define SIZE_DATA 512/sizeof(short) // max num of short readable

void* thread_function() {
      short local_min = SHRT_MAX;
      short local_max = SHRT_MIN;
      short *data = malloc(SIZE_DATA* sizeof(short));
      int size_r; //numero di byte letti

      while((size_r = read(ifd, data, SIZE_MEM)) > 0){
          for(int i = 0; i < (size_r / sizeof(short)); i++){
                if(data[i] < local_min) local_min = data[i];
                if(data[i] > local_max) local_max = data[i];
          }
      }

       short global_min;
       do global_min = min;
       while((global_min > local_min) && !__sync_bool_compare_and_swap(&min, global_min, local_min));

       short global_max;
       do global_max = max;
       while((global_max < local_max) && !__sync_bool_compare_and_swap(&max, global_max, local_max));

      return NULL;
      //pthread_exit(param); si usa nel caso in cui dovessi ritornare un
      //                     parametro al thread padre
}

int main(int argc, char const *argv[]){

    //checkin comand line arguments
    if(argc != 3) abort("use: exe2 <thread_number> <filename>\n");
    char num_of_threads = atoi(argv[1]);

    //try open the input file, 2nd commline argument
    ifd = open(argv[2], O_RDONLY);
    if(ifd == -1) abort("open error...\n");

    //allocate memory for thread ids
    pthread_t *tids = malloc(num_of_threads*sizeof(pthread_t));

    //create threads
    for(int i=0; i<num_of_threads; i++){
        pthread_create(tids+i, NULL, thread_function, NULL);
    }
    //join every threads
    for(int i=0; i<num_of_threads; i++){
        pthread_join(*(tids+i), NULL);
    }

    //close file descriptor and free the memory of tids
    close(ifd);
    free(tids);

    printf("max: %d\n", max);
    printf("min: %d\n", min);

    return 0;
}
