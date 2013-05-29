#include "stdafx.h"
#include "figures.h"

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