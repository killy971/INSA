#pragma once
#include "stdafx.h"
#include <iostream>

/*----------*/

template<class T> class List;
template<class T> class ListIterator;

/**
 * Classe ListElement
 */
template <class T>
class ListElement
{
protected:
	
	T _val;				
	ListElement<T> *_pred;	
	ListElement<T> *_succ;	
	
	ListElement(const T& v,
	            ListElement<T> *p,
	            ListElement<T> *s) : _val(v),
	                                 _pred(p),
	                                 _succ(s)
	{
		if (p!=NULL)
		{
			p->_succ = this;
		}
		if (s!=NULL)
		{
			s->_pred = this;
		}
	}

	~ListElement()
	{
		if (_pred!=NULL)
		{
			_pred->_succ = _succ;
		}
		if (_succ!=NULL)
		{
			_succ->_pred = _pred;
		}
	}

	friend class List<T>;
	friend class ListIterator<T>;
};

/*---------------------------------------------*/

/**
 * Classe List
 */
template <class T>
class List
{
	friend class ListElement<T>;
	friend class ListIterator<T>;
protected:
	int _nbr;				
	ListElement<T> *_tete;	
	ListElement<T> *_queue;	
public:
	/**
	 * Constructeur
	 */
	List()
	{
		this->_nbr = 0;
	// ces deux éléments permettent une réaliation
	// plus simple(moins de ca sparticulier)
		this-> _tete  = new ListElement<T>(T(),NULL,NULL);
		this-> _queue = new ListElement<T>(T(),_tete,NULL);
	};
	
	/**
	 * Destructeur
	 */
	~List()
	{
		while (_tete!=NULL){
			ListElement<T> *tmp=_tete;
			_tete=_tete->_succ;
			delete tmp;
		}
	}
	
	int size()
	{
		return this->_nbr;
	}

	/**
	 * Constructeur par recopie
	 */
	List(const List<T> &l)
	{
		this->_tete  = new ListElement<T>(T(),NULL,NULL);
		this->_queue = new ListElement<T>(T(),this->_tete,NULL);
		
		ListElement<T> *tmp = l._tete->_succ;

		while (tmp != l._queue){
			// Création d'un nouvel élément
			new ListElement<T>(tmp->_val, _queue->_pred, _queue);			
			tmp = tmp->_succ;
		}
		this->_nbr = l._nbr;
	}

	/**
	 * Operateur d'affectation
	 */
	List<T> &operator=(const List<T> &l){
		if (this!=&l){

			while (_tete!=NULL){
				ListElement<T> *tmp=_tete;
				_tete=_tete->_succ;
				delete tmp;
			}


			this->_tete=new ListElement<T>(T(),NULL,NULL);
			this->_queue=new ListElement<T>(T(),this->_tete,NULL);
		
			ListElement<T> *tmp=l._tete->_succ;

			while (tmp != l._queue){
				// Création d'un nouvel élément
				new ListElement<T>(tmp->_val, _queue->_pred, _queue);			
				tmp=tmp->_succ;
			}
			_nbr = l._nbr;
		
		}
		return(*this);
	}

	/**
	 * Ajoute "v" a la fin de la liste
	 */
	List<T> add(const T& v){
		new ListElement<T>(v, _queue->_pred, _queue);
		_nbr++;
		return(*this);
	}
	
	/**
	 * Test l'existence de la valeur "val" dans la liste
	 */
	bool exist(T val){
		ListElement<T> *tmp=_tete->_succ;
		while (tmp != _queue){
			if(tmp->_val == val)
				return true;
			tmp=tmp->_succ;
		}
		return false;
	}

	//friend ostream& operator<<(ostream& o, const List<T>& l);

	friend class ListIterator<T>;
};

/*---------------------------------------------*/

/**
 * Classe ListIterator
 */
template<class T>
class ListIterator
{
protected:
	List<T>& _liste;				
	ListElement<T>* _courant;	

public:
	ListIterator(List<T>& l) : _liste(l), _courant(l._tete->_succ){}
	
	/**
	 * 
	 */
	bool horsListe()
	{
		return(this->_courant == this->_liste._tete ||
		       this->_courant == this->_liste._queue);
	}

	/**
	 * 
	 */
	void succ()
	{
		if (!this->horsListe())
		{
			this->_courant = this->_courant->_succ;
		}
	}

	/**
	 * 
	 */
	void pred()
	{
		if (!this->horsListe())
		{
			this->_courant = this->_courant->_pred;
		}
	}

	/**
	 * 
	 */
	T& courant()
	{
		return(this->_courant->_val);
	}
};
