/*In questo programma vengono creati molteplici thread che manipolano un array condiviso. 
Nello specifico, ciascun thread inverte ripetutamente la posizione di ciascuna entry 
nell’array, la cui dimensione è un numero pari. A seguire, la funzione stress_test 
implementa tale operazione:*/

void* stress_test(void *arg){
    long my_ops = 0;
    int i = 0;
    //sincronizzazione che blocca un thread fintanto che N thread non 
    //invocano la medesima funzione sul medesimo oggetto inizializzato a N
    pthread_barrier_wait(&ptbarrier);

    while(!stop){
        acquire();
        for(i=0;i<ARRAY_LEN/2;i++){
            shared[i] ^= shared[ARRAY_LEN-1-i];
            shared[ARRAY_LEN-1-i] ^= shared[i];
            shared[i] ^= shared[ARRAY_LEN-1-i];
        }
        release();
        my_ops+=1;
    }

    //incrementa un contatore globale ops del numero di operazioni 
    //complessivamente eseguite dai thread
    __sync_fetch_and_add(&ops, my_ops);
    return NULL;
}