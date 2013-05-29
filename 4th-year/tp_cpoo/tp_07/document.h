#pragma once

#include <string>

class Document
{
protected :
	string _id;
public :
	virtual ~Document() {}
	virtual void affiche() const = 0;
};