#include "StdAfx.h"
#include ".\chaine.h"

/**
 * 
 */
chaine::chaine(void)
{
	longueur = 0;
	tab = new char[longueur];
}

/**
 * 
 */
chaine::chaine(const char* c)
{
	longueur = 0;
	while(c[longueur] != '\0')
	{
		longueur++;
	}
	tab = new char[longueur];
	for (int i=0 ; i<longueur ; i++)
	{
		tab[i] = c[i];
	}
}
/*
 * ce constructeur avec un caractère permet la conversion implicite lors par exemple de 
 * l'éxécution de chaine1 = 'c'; on a pas a redéfinir l'opérateur d'affectation
 */
chaine::chaine(const char c)
{
	longueur = 1;
	
	tab = new char[longueur];	
	tab[0] = c;	
}

/**
 * 
 */
chaine::chaine(const chaine& c)
{
	longueur = c.longueur;
	tab = new char[longueur];
	for (int i=0 ; i<longueur ; i++)
	{
		tab[i] = c.tab[i];
	}
}

/**
 * 
 */
chaine& chaine::operator=(const chaine& c)
{
	// si il y a une ligne du type ch=ch;
	if (this == &c) return *this;
	// on detruit l'objet appelant
	delete this->tab;
	longueur = c.longueur;
	this->tab = new char[longueur];
	for (int i=0 ; i<longueur ; i++)
	{
		this->tab[i] = c.tab[i];
	}
	return *this;
}

/**
 * 
 * 
 * Explications : compare caractere a caractere les
 *                2 chaines passees en parametre
 * 
 */
bool operator==(const chaine& c1, const chaine& c2)
{
	if (c1.longueur == c2.longueur)
	{
		for (int i=0 ; i<c1.longueur ; i++)
		{
			if (c1.tab[i] != c2.tab[i])
			{
				return false;
			}
		}
		return true;
	}
	else
	{
		return false;
	}
}

/**
 * 
 */
bool operator<(const chaine& c1, const chaine& c2)
{
	int l;
	if (c1.longueur < c2.longueur)
	{
		l = c1.longueur;
	}
	else
	{
		l = c2.longueur;
	}

	for (int i=0 ; i<l ; i++)
	{
		if (c1.tab[i] > c2.tab[i])
		{
			return false;
		}
		if (c1.tab[i] < c2.tab[i])
		{
			return true;
		}
	}
	return c1.longueur < c2.longueur;
}

/**
 * 
 */
bool operator>(const chaine& c1, const chaine& c2)
{
	int l;
	if (c2.longueur < c1.longueur)
	{
		l = c2.longueur;
	}
	else
	{
		l = c1.longueur;
	}

	for (int i=0 ; i<l ; i++)
	{
		if (c2.tab[i] > c1.tab[i])
		{
			return false;
		}
		if (c2.tab[i] < c1.tab[i])
		{
			return true;
		}
	}
	return c2.longueur < c1.longueur;
}

/**
 * 
 */
chaine operator+(const chaine& c1, const chaine& c2)
{
	chaine ch;
	ch.longueur = c1.longueur+c2.longueur;
	ch.tab = new char[ch.longueur];
	for (int i=0 ; i<c1.longueur ; i++)
	{
		ch.tab[i] = c1.tab[i];
	}
	for (int i=0 ; i<c2.longueur ; i++)
	{
		ch.tab[i+c1.longueur] = c2.tab[i];
	}
	return ch;
}

/**
 * 
 */
char chaine::operator[](int i)
{
	return this->tab[i];
}

/**
 * 
 */
chaine& chaine::operator+=(const chaine &c)
{
	chaine tmp = *this;
	this->longueur += c.longueur;	
	this->tab = new char[longueur];
	for (int i=0 ; i<tmp.longueur ; i++)
	{
		this->tab[i] = tmp.tab[i];
	}
	for (int i=0 ; i<c.longueur ; i++)
	{
		this->tab[i+tmp.longueur] = c.tab[i];
	}
	return *this;
}

/**
 * 
 */
chaine& chaine::operator-=(const chaine &c)
{
	if(c.longueur >= this->longueur )
	{
		return *this;
	}

	chaine tmp = *this;

	int deb = this->longueur - c.longueur;

	if (this->sous_chaine(deb,this->longueur-1) == c)
	{
		chaine tmp = this->sous_chaine(0,deb-1);
		this->longueur = tmp.longueur;
		this->tab = new char[this->longueur];
		for (int i=0 ; i<this->longueur ; i++)
		{
			this->tab[i] = tmp[i];
		}
		return *this;
	}
	else
	{
		return *this;
	}
}

/**
 * 
 */
chaine chaine::sous_chaine(const char deb, const char fin) const
{
	int i;
	bool trouveDeb = false;
	chaine ret = "";
	for (i=0; (i<this->longueur && !trouveDeb);i++)
	{
		if((*this).tab[i] == deb)
		{
			trouveDeb = true;
		}
	}
	if (trouveDeb)
	{
		for (int j=i-1; (j<this->longueur);j++)
		{
			if ((*this).tab[j] == fin){
				
				return ret+fin;
			}
			else
			{
				
				ret += (*this).tab[j];
				
			}

		}
	}
	return ret;		
}

/**
 * 
 */
chaine chaine::sous_chaine(const int ind1, const int ind2) const
{
	chaine ch;
	if ((ind1>ind2) || (ind2>=this->longueur) || (ind1<0))
	{
		return ch;
	}
	else
	{
		for (int i=ind1 ; i<=ind2 ; i++)
		{
			ch += (*this).tab[i];
		}
		return ch;
	}

}

/**
 * 
 */
chaine operator-(const chaine& ch1,const chaine& ch2)
{
	chaine ch;

	if(ch2.longueur >= ch1.longueur)
	{
		return chaine(ch1);
	}

	int deb = ch1.longueur - (ch1.longueur - ch2.longueur);
	bool isEnd = true;
	for (int i=deb ; (i<ch1.longueur && isEnd) ; i++)
	{
		isEnd = (ch1.tab[i+1] == ch2.tab[i-deb]);
	
		ch += ch1.tab[i];
	}
	if (isEnd)
	{
		return ch1.sous_chaine(0,deb);
	}
	else
	{
		return chaine(ch1);
	}
}

/**
 * 
 */
ostream& operator<<(ostream& o, const chaine& c)
{
	for (int i=0 ; i<c.longueur ; i++)
	{
		o << c.tab[i];
	}
	return o;
}

/**
 * 
 */
chaine::~chaine(void)
{
	delete tab;
}
