
/* % Gestion de configuration
 * Creation : 
 * Auteur : 
 * Modifications : 
 * Derniere modification : 
 *
 */

/*
 * %Module
 *   Nom            : trirap
 *   Nom du fichier : trirap.c
 *                  
 *
 *   Fonctions de ce module :
 * globale
 *  	prototype : void tri_rap (int t[], int taille)
 *		     trie le tableau d'entiers t[0..taille-1]
 *                   selon la methode du tri rapide 
 * locales a tri_rap
 *       prototype :  int partitionner (int p , int r )
 *                       delivre la place de t[p]
 *                    void tri_rapide(int p, int r )  
 *                       procedure recursive locale : tri 
 *                          entre p et r  
 */
 
/* 
 * %Fichiers include de l'application
*/
#include "service.h" 
#include "trirap.h"
#include <time.h>

typedef enum {false=0,true=1} bool;
/*
 * %Fonction globale
 * Nom : tri_rap
 * Definition : trie le tableau d'entiers t[0..taille-1]
 * Argument : int t[],int taille  ; le tableau t est modifie
 * Resultat : void
 * Variables globales utilisees 
 * Fonctions appelees : echanger 
 * Fonctions appelantes : main(princ.c)
 * Erreur detectee : 
 * Commentaires :  
 * Creation              : 
 * Auteur/Origine        : 
 * Modifications         :
 * Derniere modification :
 *		     
 */

void tri_rap(int t[],int taille)
{
    clock_t d=clock();
    //  les fonctions sont locales ==> evite de passer le tableau en parametre   
	int partitionner(int p,int r)
	{
  	  int x=t[p];
  	  int i=p-1;
  	  int j=r+1;
  	  
  	  while(true){
	    
  	   while(t[j]>x){j--;}
           while(t[i]<x){i++;}  

	   

           if(i<j){echanger(&t[i],&t[j]);}
           else{return j;}
          }       
	}// partitionner 


	void tri_rapide( int i, int j)
	{
	  int q;
	  
	  if(i<j){
	     q=partitionner(i,j);   
	     tri_rapide(i,q);
	     tri_rapide(q+1,j);

	  }   
	}// tri_rapide 

	tri_rapide( 1,taille-2);
 
	printf("Duree du tri_rap : %e \n",(double)(clock()-d));
}/* tri_rap */

