#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define FILENAME "log.txt"
#define STDOUT 1

#define abort(msg) do{printf(msg);exit(1);} while(0)

int main() {
    int ofd;

    /*opend output and file check errors*/
    ofd = open(FILENAME, O_CREAT | O_WRONLY | O_TRUNC, 0660);
    if (ofd == -1) abort("output creation error\n");

    close(STDOUT);   /*close standard output*/
    ofd = dup(ofd);  /*duplicated file creator*/
    if( ofd == -1 ) abort("dup failed\n");

    execlp("ls","ls",NULL); /*run 'ls' */
}
