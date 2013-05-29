#pragma once

#include "document.h"
#include "sonore.h"
#include <vector>

class BandeSon
{
	vector<Document*> _documents;
public:
	void ajouter(Document *d)
	{
		this->_documents.push_back(d);
	}
	void inventaire()
	{
		vector<Document*>::iterator it;
		for (it=_documents.begin() ; it!=_documents.end() ; it++)
		{
			Sonore *s = dynamic_cast<Sonore*> (*it);
			s->son();
		}
	}
};