#pragma once

#include "document.h"

class DVivant : public Document
{
	string _classe;
	string _race;
public :
	DVivant();
	DVivant(string c, string r) : _classe(c), _race(r) {};
	virtual void affiche() const
	{
		cout << "Un etre vivant de classe : " << _classe << " et de race : " << _race << std::endl;
	};
};