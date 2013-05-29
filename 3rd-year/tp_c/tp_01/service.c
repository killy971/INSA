/* % Gestion de configuration
 * Creation : 
 * Auteur : 
 * Modifications : 
 * Derniere modification : 
 *
 */

/*
 * %Module
 *   Nom            : service
 *   Nom du fichier : service.c
 *                  
 *
 *   Fonctions de ce module :
 *  		void ecrire(int t[], int taille)
 *		     affiche le tableau d'entiers t[0..taille-1]
 * 
 *		void lire(int t[], int taille)
 *                     lit le tableau d'entiers t[0..taille-1]
 *
 *              void init_alea( int t[], int taille) 
 *                   initialise le tableau de facon aleatoire
 *
 *              void echanger(int *x, int *y)
 *                    echange le contenu des 2 variables en parametres
 *
 *           
 */ 
 
/* 
 * %Fichiers include systeme
 */
#include "stdio.h"
#include <time.h>
#include <stdlib.h>
/* 
 * %Fichiers include de l'application
*/
#include "service.h" 

/*
 * %Fonction globale
 * Nom : lire
 * Definition : lit le tableau d'entiers t[0..taille-1]
 * Argument : int t[],int taille  ; le tableau t est modifie
 * Resultat : void
 * Variables globales utilisees 
 * Fonctions appelees : scanf,printf
 * Fonctions appelantes : main(princ.c)
 * Erreur detectee : 
 * Commentaires :  
 * Creation              : 
 * Auteur/Origine        : 
 * Modifications         :
 * Derniere modification : 
 *		     
 */

void lire(int t[], int taille)
{
    int i;
    printf("entrez %d entiers : \n",taille);
    for(i=0;i<taille;i++){
        printf("%d :\n",i);
        scanf("%d",&t[i]);
    }
} /* fin LIRE */
	
/*
 * %Fonction globale
 * Nom : ecrire 
 * Definition : affiche le tableau d'entiers t[0..taille-1]
 * Argument : int t[],int taille
 * Resultat : void
 * Variables globales utilisees 
 * Fonctions appelées : printf
 * Fonctions appelantes : main(princ.c)
 * Erreur detectee : 
 * Commentaires :  
 * Creation              : 
 * Auteur/Origine        : 
 * Modifications         :
 * Derniere modification :
 *		     
 */

void ecrire(int t[], int taille)
{
    int i;
    for(i=0;i<taille;i++){
        printf("%d, ",t[i]);
    }
} /* fin ECRIRE */
	
	
/*
 * %Fonction globale
 * Nom : init_alea 
 * Definition : initialise t[0..taille-1] de facon aleatoire
 * Argument : int t[],int taille
 * Resultat : void
 * Variables globales utilisees 
 * Fonctions appelées : rand(C library)
 * Fonctions appelantes : main(princ.c)
 * Erreur detectee : 
 * Commentaires : 
 * Creation              : 
 * Auteur/Origine        : 
 * Modifications         :
 * Derniere modification :
 *		     
 */

void init_alea( int t[], int taille)

{ 
    int i;
    
    srand(time(NULL));
    for(i=0;i<taille;i++){
      t[i]=rand();
    }    
}/* init_alea */

/*
 * %Fonction globale
 * Nom : echanger 
 * Definition : permute le contenu des 2 variables
 * Argument : int *x ,int *y
 * Resultat : void
 * Variables globales utilisees 
 * Fonctions appelees : 
 * Fonctions appelantes :  tri_rap,  tri_naif
 * Erreur detectee : 
 * Commentaires : 
 * Creation              : 
 * Auteur/Origine        : 
 * Modifications         :
 * Derniere modification :
 *		     
 */
 
void echanger(int *x, int *y)
{
    int n;
    n=*x;
    *x=*y;
    *y=n;
}/*  ECHANGER */
