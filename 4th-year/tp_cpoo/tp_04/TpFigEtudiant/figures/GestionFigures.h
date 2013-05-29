/* classe GestionFigures
	Creation et destruction des figures utilis�es par
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

	//vecteur contenant l'ensemble des figures � g�rer
	vector<Figure*> ensFig;

	//cr�ation des figures et stockage dans ensFig
	GestionFigures();

	//destruction de l'ensemble des figures
  	~GestionFigures();

};

#endif