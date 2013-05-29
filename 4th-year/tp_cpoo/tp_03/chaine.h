#ifndef CHAINE_H
#define CHAINE_H

#include <iostream>
using namespace std;

/**
 * On a choisi de conserver la longueur 
 * de la chaine dans l'attribut "longueur"
 */
class chaine
{
	//la longueur de la chaine
	int longueur;
	//le tableau de caractere de la chaine
	char* tab;
public:
	chaine(void);
	chaine(const char* c);
	chaine(const chaine& c);
	chaine(const char c);
	chaine& operator=(const chaine& c);
	friend bool operator==(const chaine& c1, const chaine& c2);
	friend bool operator<(const chaine& c1, const chaine& c2);
	friend bool operator>(const chaine& c1, const chaine& c2);
	friend chaine operator+(const chaine& c1, const chaine& c2);
	friend chaine operator-(const chaine& c1, const chaine& c2);
	friend ostream& operator<<(ostream& o, const chaine& c);
	char operator[](int i);
	chaine& operator+=(const chaine& c);
	chaine& operator-=(const chaine& c);
	chaine sous_chaine(const char deb, const char fin) const;
	chaine sous_chaine(const int ind1, const int ind2) const;
	~chaine(void);
};

#endif
