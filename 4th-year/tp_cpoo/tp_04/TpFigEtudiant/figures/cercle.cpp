#include "stdafx.h"
#include "figures.h"

void Cercle::dessiner(CDC* pDC)
{
	pDC->Ellipse(_posX-_rayon, _posY+_rayon, _posX+_rayon, _posY-_rayon);
}