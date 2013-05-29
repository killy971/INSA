#include "stdafx.h"
#include <iostream>
using namespace std;
#include "Carte.h"


_tmain(int argc, _TCHAR* argv[])
//***************************************
{
		 // Variables de travail
 char propr;
 int i;
 Couleur coul;
 Valeur  haut;
 Carte* Nt; 	// Pointeurs pour naviguer dans les paquets
 Carte* St;  
 Carte* Nec; 
 Carte* Sec; 
 Carte* Ns; 
 Carte* Ss; 
 char p[52];     	// pour inverser la creation des listes, on lit 52 char.


				// Initialisation des paquets/ des cartes
				// selon la suite des N et S prevonant 
				// de l'entree standarde cin 
				// (on suppose la redirection de stdin)
				//=====================================
 for ( i=0; i < 52; i++)
   cin >> p[i];			// lecture

 for ( int icoul=3; icoul >= 0; icoul--)
    for ( int ihaut=12; ihaut >= 0; ihaut--)
      {
      propr = p[icoul*13 + ihaut];

      if (propr != 'N' && propr != 'S')
	{
	cerr << "Erreur dans l'initialisation  \n"; exit(1);
	}
        coul = (Couleur)icoul;		// conversions de int vers enum
        haut = (Valeur)(ihaut+1);
      	new Carte(coul, haut, propr);   // attention : le constructeur
					// doit enchainer la Carte avec
					// la bonne liste
      }
					//affichages des paquets 
  cout << " Les paquets de depart sont :" << endl;
  Carte::afficherN();   
  Carte::afficherS();   
  cout << endl;

			// Boucle principal :
			// le jeu est termine, si une des listes est vide
			//===============================================
 while (Carte::getNTete() != NULL && Carte::getSTete() != NULL)
   {
    Nt = Carte::getNTete(); 		// Les premieres cartes sont comparees
    St = Carte::getSTete();
    Nt->afficher();
    St->afficher();
    cout << endl; 
    while ( Nt != NULL && St != NULL && Nt->egale(*St) )
      {					// egalite -> bataille
      cout << "** bataille ** ";
       Nt = Nt->suc(); 
       if (Nt != NULL)
         Nt = Nt->suc(); 		// avancer deux cartes pour N
       St = St->suc(); 
       if (St != NULL)
         St = St->suc(); 		// avancer deux cartes pour S
      }
    if (Nt == NULL)			// N n'a pas assez de carte
       {				// S gagne
       cout << "  N : plus de Carte \n";
       while (Carte::getNTete() != NULL)
	  {
    	  Nec = Carte::getNTete();
	  Nec->changerProp(); 
	  }
       }
    else
       if (St == NULL)
	  {			// S n'a pas assez de carte
          cout << "  S : plus de Carte \n";
          while (Carte::getSTete() != NULL)
	    {
    	    Sec = Carte::getSTete();
	    Sec->changerProp(); 
	    }
	  }
       else
 	  if ( Nt->supAbs( (*St) ) )	// comparaison des dernieres
					// cartes
					// S a perdu cette bataille
	     {
	     Ss = St->suc(); 		// premiere Carte non gagnee
             while (Carte::getSTete() != Ss)   
	       {
    	       Sec = Carte::getSTete();
	       Sec->changerProp();      // on met les cartes en question 
    	       Nec = Carte::getNTete(); // a la fin du paquet N
	       Nec->passerDerriere(); 
	       cout << " N gagne : ";
	       Sec->afficher();
	       cout << ' '; 
	       Nec->afficher();
	       cout << endl;
	       }
             }
          else
					 // N a perdu cette bataille
	     {
	     Ns = Nt->suc(); 		// premiere carte non gagnee 
             while (Carte::getNTete() != Ns)  
	       {
    	       Nec = Carte::getNTete();
	       Nec->changerProp();     // on met les cartes en question
    	       Sec = Carte::getSTete(); // a la fin du paquet S
	       Sec->passerDerriere(); 
	       cout << " S gagne : " ;
	       Nec->afficher();
	       cout << ' '; 
	       Sec->afficher();
	       cout << endl;
	       }
	     }
   }
   cout << " >>>>>>>>>>>>>le gagnant de ce jeu est : ";
   if (Carte::getNTete() == NULL)
     {					// N a perdu les jeux
     cout << 'S' << endl;
     while (Carte::getSTete() != NULL)
	delete Carte::getSTete();	// destruction du paquet de S
     }
   else
     {					// S a perdu les jeux
     cout << 'N' << endl;
     while (Carte::getNTete() != NULL)
	delete Carte::getNTete();	// destruction du paquet de N
     }
} 
