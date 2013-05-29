#include "stdafx.h"
#include "figures.h"

MonRectangle::MonRectangle(int x, int y, int longueur, int hauteur) : Figure(x,y)
{
	_seg1 = Segment(x, y+hauteur/2, -longueur/2, 0);
	_seg2 = Segment(x-longueur/2, y, 0, hauteur/2);
	_seg3 = Segment(x, y-hauteur/2, -longueur/2, 0);
	_seg4 = Segment(x+longueur/2, y, 0, hauteur/2);
}

void MonRectangle::dessiner(CDC* pDC)
{
	_seg1.dessiner(pDC);
	_seg2.dessiner(pDC);
	_seg3.dessiner(pDC);
	_seg4.dessiner(pDC);
}