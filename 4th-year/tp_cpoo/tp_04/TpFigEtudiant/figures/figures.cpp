#include "stdafx.h"
#include "figures.h"

void Figure::effacer(CDC* pDC)
{
	CPen penWhite;
	if (penWhite.CreatePen(PS_SOLID, 1, RGB(255,255,255)))
	{
		CPen* pOldPen = pDC->SelectObject(&penWhite);
		dessiner(pDC);
		pDC->SelectObject(pOldPen);
	}
}

Figure::~Figure()
{

}
void Figure::deplacer(CDC* pDC, int xx, int yy)
{
	effacer(pDC);
	_posX += xx;
	_posY += yy;
	dessiner(pDC);
}

/*------------------------------------------*/

Point::Point(int x, int y) : Figure(x,y), _x(0), _y(0) {};

void Point::dessiner(CDC* pDC)
{
	pDC->MoveTo(_x,_y);
	pDC->LineTo(_x,_y);
}


/*------------------------------------------*/

void Cercle::dessiner(CDC* pDC)
{
	pDC->Ellipse(_posX-_rayon, _posY+_rayon, _posX+_rayon, _posY-_rayon);
}

/*------------------------------------------*/

/**
 * @param posX  position x du centre du segment
 * @param posY  position y du centre du segment
 * @param x2    position x de l'un des 2 deux points
 *              diametralement oppose au centre du segment
 * @param y2    position y de l'un des 2 deux points (meme point
 *              que pour x2) diametralement oppose au centre du segment
 */
Segment::Segment(int posX, int posY, int x2, int y2) : Figure(posX,posY),
                                                   _x1(posX+x2),
												   _y1(posY+y2),
												   _x2(posX-x2),
												   _y2(posY-y2) {};

void Segment::dessiner(CDC* pDC)
{
	pDC->MoveTo(_x1,_y1);
	pDC->LineTo(_x2,_y2);
}

/*------------------------------------------*/

MonRectangle::MonRectangle(int x, int y,
                           int longueur, int hauteur) : Figure(x,y)
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