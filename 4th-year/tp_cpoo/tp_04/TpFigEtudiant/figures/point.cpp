#include "stdafx.h"
#include "figures.h"

Point::Point(int x, int y) : Figure(x,y), _x(0), _y(0) {};

void Point::dessiner(CDC* pDC)
{
	pDC->MoveTo(_x,_y);
	pDC->LineTo(_x,_y);
}