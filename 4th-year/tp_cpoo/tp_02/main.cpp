#include "stdafx.h"
#include <iostream>
using namespace std;
#include "Vecteur.h"

 main()
  {
  Vecteur v1;
  cin >> v1; 
  Vecteur v2(1.0,2.0,3.5);
  cout << "Les deux vecteurs sont : ";
  cout << v1 << "  " << v2 << endl;

  cout << "la projection de v1 sur x  : ";
  cout << v1.x() << endl;
  cout << "la projection de v1 sur y  : ";
  cout << v1.y() << endl;
  cout << "la projection de v1 sur z  : ";
  cout << v1.z() << endl;
  cout << "sa valeur absolute  : ";
  cout << v1.abs() << endl;


  cout << "la somme  des deux vecteur : ";
  cout << v1+v2 << endl;
  cout << "leur difference  : ";
  cout << v1-v2 << endl;
  cout << "leur produit scalaire  : ";
  cout << v1*v2 << endl;
  cout << "leur produit vectoriel  : ";
  cout << (v1^v2) << endl;
  cout << "le double de v1  : ";
  cout << 2*v1 << endl;
  cout << "pi fois v2  : ";
  cout << v2*3.1417 << endl;
  cout << "le resultat du test d'egalite  : ";
  cout << (v1==v2) << endl;
  Vecteur v3 = Vecteur(0,0,0);
  v3 = v2;
  cout << (v3++ == ++v2) << endl;

// pour controler la coherence de votre solution, enlever le commentaire de
// la ligne suivante :

// cout << v1 + 2 ;

// On doit avoir un message d'erreur.

  } 
