#ifndef CLASS_MATRICE
#define CLASS_MATRICE

template <class T, class OpPlus, class OpMult, T NeuP, T NeuM>
class Matrice
{
	Vector<T> _tab;
	int _m; // hauteur matrice (indice j)
	int _n; // largeur matrice (indice i)

public :

	/**
	 * Constructeur
	 */
	Matrice(int n, int m)
	{
		this->_m = m;
		this->_n = n;
		this->_tab.resize(this->_n*this->_m);
	};

	/**
	 * Fonction d'acces
	 */
	inline int getHauteur()
	{
		return this->_m;
	}

	/**
	 * Fonction d'acces
	 */
	inline int getLargeur()
	{
		return this->_n;
	}

	/**
	 * Test si les indices i et j sont correct pour acceder
	 * a la matrice.
	 * 
	 * @param i  
	 * @param j  
	 * @return   
	 */
	bool isCorrectInd(int i, int j)
	{
		if ((i >= 0 && i<this->_n) &&
		    (j >= 0 && j<this->_m))
		{
			return true;
		}
		else
		{
			return false;
		}
	};

	/**
	 *
	 */
	/*
	T& operator[][] (int i, int j)
	{
		if (this->isCorrectInd(i,j))
		{
			return this->_tab[i*j];
		}
		else
		{
			// erreur
		}
	};
	*/

	/**
	 * Retourne la valeur de l'element de la matrice
	 * situe aux indices (i,j)
	 * 
	 * @param i  
	 * @param j  
	 * @return   
	 */
	T& get (int i, int j)
	{
		if (this->isCorrectInd(i,j))
		{
			return this->_tab[i*j];
		}
		else
		{
			// erreur
		}
	};

	/**
	 * Affecte la valeur "val" a l'element de la matrice
	 * situe aux indices (i,j)
	 * 
	 * @param i    
	 * @param j    
	 * @param val  
	 * @return     
	 */
	void set(int i, int j, T val)
	{
		if (this->isCorrectInd(i,j))
		{
			this->_tab[i*j] = val;
		}
		else
		{
			// erreur
		}
	};
	
	/**
	 * L'operateur "+" rond
	 */
	Vector<T> plusRond(Vector<>& v)
	{
		//
	}

	/**
	 * L'operateur "*" rond
	 */
	Vector<T> foisRond(Vector<>& v)
	{
		//
	}

};

#endif
