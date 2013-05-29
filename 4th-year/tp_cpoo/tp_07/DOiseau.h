#pragma once

#include <string>
#include "dvivant.h"
#include "sonore.h"

class DOiseau : public DVivant, public Sonore
{
	static int nbOiseau;
public:
	DOiseau(string id, string son, string classe, string race) : Sonore(son), DVivant(classe,race)
	{
		_id = id;
	nbOiseau++;
	};
	virtual void affiche() const
	{
		DVivant::affiche();
		cout << "Il fait un son : " << _son << std::endl;
	};
	~DOiseau()
	{
		nbOiseau--;
	}
};

int DOiseau::nbOiseau = 0;