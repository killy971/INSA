#pragma once

#include <string.h>

class Sonore
{
protected :
	string _son;
public :
	Sonore(string s) : _son(s) {};
	void son()
	{
		cout << "Son : " << _son << std::endl;
	}
};