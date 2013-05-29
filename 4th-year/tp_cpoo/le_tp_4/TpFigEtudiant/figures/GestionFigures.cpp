/* classe GestionFigures
	creation et destruction des figures utilisées par 
	l'application MFC
*/

#include "stdafx.h"
#include "GestionFigures.h"


GestionFigures::GestionFigures()
{
	Cercle *c1 = new Cercle(10,10,5);
	Carre  *c2 = new Carre(20,20,10);
	MonRectangle *r1 = new MonRectangle(40,20,10,30);

	this->ensFig.push_back(c1);
	this->ensFig.push_back(c2);
	this->ensFig.push_back(r1);

}

//destruction de l'ensemble des figures
GestionFigures::~GestionFigures()
{

}

