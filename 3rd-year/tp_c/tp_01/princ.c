/* % Gestion de configuration
 * Creation : 
 * Auteur : 
 * Modifications : 
 * Derniere modification
 *
 */

/*
 * %Module
 *   Nom            : princ
 *   Nom du fichier : princ.c
 *   Role du module : Initialiser un tableau et le trier                     
 *   Fonctions de ce module :
 *               main : 
 */ 
 
/* 
 * %Fichiers include systeme
 */

/* 
 * %Fichiers include de l'application
 */
#include <stdio.h>
#include "service.h" 
#include "trirap.h"
#include "trinaif.h"


/* 
 * % Prototype des fonctions externes appelees dans ce module
 *		void ecrire(int t[], int taille)
 *		     affiche le tableau d'entiers t[0..taille-1]
 *		void lire(int t[], int taille)
 *		     lit le tableau d'entiers t[0..taille-1]
 * 		     en suivant l'algoritme du tri en tas
 *		void tri_rap(int t[], int taille)
 *		     trie le tableau d'entiers t[0..taille-1]		
 * 		     en suivant l'algoritme du tri rapide
 *		void tri_naif(int t[], int taille)
 *		     trie le tableau d'entiers t[0..taille-1]		
 * 		     en suivant l'algoritme du tri naif
 *
 */

/*
 * %Fonction 
 * Nom :main
 * Definition : comparaison de methodes de tri d'un tableau 
 * Argument :       
 * Resultat : int      
 * Variables globales utilisees : néant
 * Fonctions appelees : lire(service.c), ecrire(service.c)
 *			tri_rap(trirap.c),
 *			tri_naif(trinaif.c) 
 * Fonctions appelantes : 
 * Erreur detectee :  
 * Commentaires :  
 * Creation              : 
 * Auteur/Origine        : 
 * Modifications         :
 * Derniere modification :
 *		     
 */

/*---------------------------------------------------------------
 *  ce module permet de tester successivement : 
 *  1) les fonctions lire, ecrire et echanger de service.c
 *  2) les fonctions de tri naif et tri rapide
 *  3) la fonction init-alea de service.c
 *  4) les complexites du tri naif et du tri rapide
 *------------------------------------------------------------------
 */

#define TAILLE 6	/* nombre d'elements du tableau*/
#define TAILLE2 65535
int tab[TAILLE] ;	/* tableau tab[0..taille-1] */
int tab3[TAILLE2];
int tab2[TAILLE2] ;

main()
{
int x=3;
int y=8;
/*
lire(tab,TAILLE);
ecrire(tab,TAILLE);	
echanger(&x,&y);
printf("Avant : x=3, y=8. Après : x=%d, y=%d \n",x,y);

tri_naif(tab,TAILLE);
printf("Après trinaif : \n",x,y);
ecrire(tab,TAILLE);	
*/
printf("\n ------------------------------ \n");

init_alea(tab3,TAILLE2);
// printf("apres initialisation : \n");
// ecrire(tab3,TAILLE2);
//lire(tab3,TAILLE2);
tri_rap(tab3,TAILLE2);
 // ecrire(tab3,TAILLE2);

 init_alea(tab2,TAILLE2);
 tri_naif(tab2,TAILLE2);
/*
init_alea(tab2,524288);
printf("Initialisation aleatoire\n");
tri_rap(tab2,524288);
ecrire(tab2,524288);	
 */

printf("\n ------------------------------ \n");
/*
int t1[2^10];
init_alea(t1,2^10);
*/
}/*main*/

/*
1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Avant : x=3, y=8. Après : x=8, y=3

7, 1, 5, 4, 8, 3, Avant : x=3, y=8. Après : x=8, y=3
Après trinaif :
1, 3, 4, 5, 7, 8,



*/
