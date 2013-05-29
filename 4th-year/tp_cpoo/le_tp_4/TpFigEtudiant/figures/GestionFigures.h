/* classe GestionFigures
	Creation et destruction des figures utilisées par
	l'application MFC


*/

#ifndef GESTIONFIGURES_H
#define GESTIONFIGURES_H

#include "figures.h"

#include <vector>
using namespace std;

class GestionFigures
{
public :

	//vecteur contenant l'ensemble des figures à gérer
	vector<Figure*> ensFig;

	//création des figures et stockage dans ensFig
	GestionFigures();

	//destruction de l'ensemble des figures
  	~GestionFigures();

};

#endif