//=======================================================================
// Nom      : 	main.cpp
//
// But      : 	Tester les algèbres de chemin
//
// Création : 	06/10/2004
//=======================================================================

#include <iostream>
#include <limits.h>
#include "matrice.h"
#include "Min.h"
#include "Mini.h"
#include "Plus.h"
#include "Plusi.h"

void main(){
	const unsigned dim=4;

	matrice<int, Min<int>, Plus<int>, INT_MAX, 0> M(dim,dim);

	// Donner la matrice d'incidences de l'entree std
	cin >> M;  
	// cout << M;

	matrice<int, Min<int>, Plus<int>, INT_MAX, 0> D0(dim,1);
	D0.init(100);
	D0.setElement(0,0,0);
	//D0[0][0] = 0;
	// cout << D0;
	matrice<int, Min<int>, Plus<int>, INT_MAX, 0> D1(dim,1);
	D1.init(100);
	D1.setElement(0,0,0);
	//D1[0][0] = 0;
	// cout << D1;
	for (int i = 1; i < dim; i++) {
		cout << "-------" << endl;
		// cout << M*D1;
		D1 = M * D1 ;
	}

    cout << D1;
}
