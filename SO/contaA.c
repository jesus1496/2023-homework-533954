#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdbool.h>

/*
Programma per cercare un carattere nel file passato come parametro,
con N thread passati come parametro.
Ogni thread cerca il carattere dato
*/

char* buffer;
int count = 0;
int nt; //numero dei thread

size_t  filesize;

pthread_mutex_t mutex;

void* worker(void *param){
    int id = *(int*)param;
    off_t slice = filesize/nt; //il mio chuck
    off_t remain = filesize%nt;//size rimanente

    off_t mySlice = slice;

    //bite dall'inizio del thread
    int i;

    for(i=0;i<=mySlice;i++){
        size_t inizio = slice*id;

        if(buffer[inizio]=='a'){
            count++;
        }else{
            i++;
        }
    }
}



void cerca(int num_thread){
    int i;
    int *ar = (int*)malloc(num_thread*sizeof(int));
    pthread_t* tids = malloc(num_thread*sizeof(pthread_t));


    for(i=0;i<num_thread;i++){
        ar[i]=i;
        pthread_create(tids+i,NULL,worker,ar+i);
    }
    for(i=0;i<num_thread;i++){
        pthread_join(tids[i], NULL);
    }


}


int main(int argc,char *argv[]){
    if(argc !=3){
        printf("Devi inserire 2 pramentri!\n");
        exit(0);
    }
    int ifd;
    int size_r=0;

    ifd = open(argv[1],O_RDONLY);
    nt = atoi(argv[2]);


    filesize = lseek(ifd,0,SEEK_END);
    lseek(ifd,0,SEEK_SET);

    buffer = (char*)malloc(filesize+1);


    //leggo tutto il file
     while(size_r < filesize){
        size_t currLetto = read(ifd,buffer+size_r,filesize);
        size_r += currLetto;
    }
    cerca(nt);

    printf("Trovato %d caratteri \n",count);

    free(buffer);
    close(ifd);

}
