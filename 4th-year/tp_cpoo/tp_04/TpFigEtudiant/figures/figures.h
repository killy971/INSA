#ifndef FIGURESH
#define FIGURESH

#include "../TpFig.h"

/*-------------------------------------------------------*/

/**
 *
 */
class Figure
{
protected:
	int _posX;
	int _posY;
public:
	Figure();
	Figure(int x, int y):_posX(x), _posY(y){};
	~Figure();
	virtual void dessiner(CDC* pDC)= 0;
 	void effacer(CDC* pDC);
	void deplacer(CDC* pDC, int xx, int yy);
};

/*-------------------------------------------------------*/

/**
 *
 */
class Point : public Figure
{
	int _x;
	int _y;
public:
	Point(int x, int y);
	virtual void dessiner(CDC* pDC);
};

/*-------------------------------------------------------*/

/**
 *
 */
class Segment : public Figure
{
	int _x1;
	int _y1;
	int _x2;
	int _y2;
public :
	Segment() : Figure(0,0), _x1(0), _y1(0), _x2(0), _y2(0) {};
	Segment(int posX, int posY, int x2, int y2);
	virtual void dessiner(CDC* pDC);
};

/*-------------------------------------------------------*/

/**
 *
 */
class Cercle : public Figure
{
	int _x;
	int _y;
	int _rayon;
public :
	Cercle();
	Cercle(int x, int y, int rayon) : Figure(x,y),
	                                  _x(0),
	                                  _y(0),
	                                  _rayon(rayon) {};
	virtual void dessiner(CDC* pDC);
};

/*-------------------------------------------------------*/

/**
 *
 */
class MonRectangle : public Figure
{
public:
	Segment _seg1;
	Segment _seg2;
	Segment _seg3;
	Segment _seg4;
public :
	MonRectangle() : Figure(0,0),
	                 _seg1(0,0,0,0),
	                 _seg2(0,0,0,0),
	                 _seg3(0,0,0,0),
	                 _seg4(0,0,0,0){};
	MonRectangle(int x, int y, int longueur, int largeur);
	virtual void dessiner(CDC* pDC);
};

/*-------------------------------------------------------*/

/**
 *
 */
class Carre : public MonRectangle
{
public:
	Carre();
	Carre(int x, int y, int cote) : MonRectangle(x,y,cote,cote) {};
};

#endif