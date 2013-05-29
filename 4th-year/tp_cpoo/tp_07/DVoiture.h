#pragma once

#include "dmachine.h"
#include "sonore.h"

class DVoiture : public DMachine, public Sonore
{
	static int nbVoiture;
public :
	DVoiture(string id, string son, string fabriquant, int _puissance) : Sonore(son), DMachine(fabriquant, _puissance)
	{
		_id = id;
		nbVoiture++;
	};
	virtual void affiche() const
	{
		DMachine::affiche();
		cout << "Emet le son : " << _son << std::endl;
	};
	~DVoiture()
	{
		nbVoiture--;
	}
};

int DVoiture::nbVoiture = 0;