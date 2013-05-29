#include <stdio.h>
#include <stdlib.h>

const int taille=4;

int main(){

    int i;
    int n=0;
    int temp=0;
    
    printf("entrez %d entiers : \n",taille);
    for(i=0;i<taille;i++){
        printf("%d :\n",i);
        scanf("%d",&temp);
        n+=temp;
    }
    
    printf("moyenne des %d entiers : %f",taille,(float)((float)n/taille));
}
/*
0 :
1
1 :
3
2 :
5
3 :
7
moyenne des 4 entiers : 4.000000
*/
