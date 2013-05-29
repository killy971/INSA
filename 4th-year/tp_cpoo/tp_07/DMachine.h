#pragma once

#include "document.h"

class DMachine : public Document
{
	string _fabriquant;
	int _puissance;
public :
	DMachine();
	DMachine(string f, int p) : _fabriquant(f), _puissance(p) {};
	virtual void affiche() const
	{
		cout << "Une machine de marque : " << _fabriquant << "et de puissance : " << _puissance << std::endl;
	};
};