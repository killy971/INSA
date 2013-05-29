#include <iostream>
using namespace std;

class Vecteur
{
	float _x;
	float _y;
	float _z;

public:
	Vecteur(float x, float y, float z);
	Vecteur();

	friend ostream& operator<<(ostream& o,const Vecteur& v);
	friend istream& operator>>(istream& i,Vecteur& v);
	
	friend Vecteur operator+(const Vecteur& v1, const Vecteur& v2);
	Vecteur operator-(const Vecteur& v) const;
	friend float operator*(const Vecteur& v1, const Vecteur& v2);
	friend Vecteur operator^(const Vecteur& v1, const Vecteur& v2);
	friend bool operator==(const Vecteur& v1, const Vecteur& v2);
	Vecteur& operator++(void);
	Vecteur operator++(int n);

	Vecteur operator*(float c) const;
	friend Vecteur operator*(float c, const Vecteur& v);

	float x();
	float y();
	float z();
	
	float abs();


};
