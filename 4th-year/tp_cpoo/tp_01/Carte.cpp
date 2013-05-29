#include "stdafx.h"
#include <iostream>
using namespace std;
#include "Carte.h"

Carte* Carte::Nt = NULL;
Carte* Carte::St = NULL;
Carte* Carte::Nq = NULL;
Carte* Carte::Sq = NULL;

Couleur Carte::getCouleur()
{
	return this->_c;
}
Valeur Carte::getValeur()
{
	return this->_v;
}
char Carte::getProp()
{
	return this->_p;
}
Carte::Carte(Couleur c,Valeur v, char prop)
{
	this->_c = c;
	this->_v = v;
	this->_p = prop;

	if (prop == 'N')
	{
		if (Carte::Nt == NULL)
		{
			Carte::Nt = this;
			Carte::Nq = this;
			this->_suivant = NULL;
		}
		else
		{
			this->_suivant = Carte::Nt;
			Carte::Nt = this;
		}
	}
	else
	{
		if (Carte::St == NULL)
		{
			Carte::St = this;
			Carte::Sq = this;
			this->_suivant = NULL;
		}
		else
		{
			this->_suivant = Carte::St;
			Carte::St = this;
		}
	}
}

Carte::~Carte()
{
	//
}


Carte* Carte::suc()
{
	return this->_suivant; 
}
void Carte::afficherN()
{
	Carte* ec=Carte::Nt;
	while(ec!=Carte::Nq)
	{
		ec->afficher();
		ec = ec->_suivant;
	}
	Nq->afficher();
}
void Carte::afficherS()
{
	Carte* ec=Carte::Nt;
	while(ec!=Carte::Nq)
	{
		ec->afficher();
		ec = ec->_suivant;
	}
	Nq->afficher();
}
void Carte::afficher()
{
	cout << "hauteur:" << this->_v << " couleur:" << this->_c << std::endl;
}
void Carte::changerProp()
{
	if(this->_p=='N')
	{
		Carte::Nt = Carte::Nt->_suivant;
		this->_p ='S';
		Carte::Sq->_suivant = this;
		Carte::Sq = this;
		this->_suivant = NULL;
	}
	else
	{
		Carte::St = Carte::St->_suivant;
		this->_p ='N';
		Carte::Nq->_suivant = this;
		Carte::Nq = this;
		this->_suivant = NULL;
	}
}
bool Carte::supAbs(const Carte &c)const
{
	return ((int)this->_v > (int)c._v); 
}
bool Carte::egale(const Carte &c)const
{
	return ((int)this->_v == (int)c._v); 
}
void Carte::passerDerriere()
{
	if (this->_p == 'N')
	{
		Carte::Nt = Carte::Nt->_suivant;
		Carte::Nq->_suivant = this;
		Carte::Nq = this;
		this->_suivant = NULL;
	}
	else
	{
		Carte::St = Carte::St->_suivant;
		Carte::Sq->_suivant = this;
		Carte::Sq = this;
		this->_suivant = NULL;
	}
}
Carte* Carte::getNTete()
{
	return Carte::Nt;
}
Carte* Carte::getSTete()
{
	return Carte::St;
}
