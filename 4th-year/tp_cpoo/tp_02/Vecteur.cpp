#include "stdafx.h"
using namespace std;
#include ".\vecteur.h"
#include <math.h>

Vecteur::Vecteur(float x, float y, float z)
{
	_x = x;
	_y = y;
	_z = z;
}

Vecteur::Vecteur()
{
	//
}

ostream& operator<<(ostream& o,const Vecteur& v)
{
	return o << v._x << " " << v._y << " " << v._z << endl;
}
istream& operator>>(istream& i,Vecteur& v)
{
	return i >> v._x >> v._y >> v._z;
}

Vecteur operator+(const Vecteur& v1, const Vecteur& v2)
{
	return Vecteur(v1._x+v2._x,v1._y+v2._y,v1._z+v2._z);
}

Vecteur Vecteur::operator-(const Vecteur& v) const
{
	return Vecteur(_x-v._x,_y-v._y,_z-v._z);
}

float operator*(const Vecteur& v1, const Vecteur& v2)
{
	return v1._x*v2._x+v1._y*v2._y+v1._z*v2._z;
}

Vecteur operator^(const Vecteur& v1, const Vecteur& v2)
{
	float x = v1._y*v2._z - v1._z*v2._y;
	float y = v1._z*v2._x - v1._x*v2._z;
	float z = v1._x*v2._y - v1._y*v2._x;
	return Vecteur(x,y,z);
}
bool operator==(const Vecteur& v1, const Vecteur& v2)
{
	return (v1._x==v2._x) && (v1._y==v2._y) && (v1._z==v2._z);
}

Vecteur& Vecteur::operator++(void)
{
	_x++;
	_y++;
	_z++;
	return (*this);
}

Vecteur Vecteur::operator++(int n)
{
	Vecteur temp = Vecteur(_x,_y,_z);
	_x++;
	_y++;
	_z++;
	return temp;
}

Vecteur Vecteur::operator*(float c) const
{
	float x = _x * c;
	float y = _y * c;
	float z = _z * c;
	return Vecteur (x,y,z);
}

Vecteur operator*(float c, const Vecteur& v)
{
	float x = v._x * c;
	float y = v._y * c;
	float z = v._z * c;
	return Vecteur (x,y,z);
}

float Vecteur::x()
{
	return _x;
}

float Vecteur::y()
{
	return _y;
}

float Vecteur::z()
{
	return _z;
}

float Vecteur::abs()
{
	return sqrt(this->_x*this->_x + sqrt(this->_y*this->_y + this->_y*this->_y));
}