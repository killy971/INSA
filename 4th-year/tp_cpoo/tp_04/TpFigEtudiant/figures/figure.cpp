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


