#include "stdafx.h"
#include "list.h"

template <class T> class Ensemble : public List<T>{
public :
	Ensemble<T>(): List<T>() {}
	Ensemble<T>(const Ensemble<T> &e): List<T>(e) {}

	Ensemble<T> operator-(Ensemble<T>& e2){
		Ensemble<T> e1;
		
		for(ListIterator<T> i(*this);!i.horsListe();i.succ()){
			if(!e2.exist(i.courant()))
				e1.add(i.courant());
		}
		return e1;
	}

	Ensemble<T> operator+( Ensemble<T>& e2){
		Ensemble<T> e1(*this);
		for(ListIterator<T> i(e2);!i.horsListe();i.succ()){
			if(!exist(i.courant()))
				e1.add(i.courant());
		}
		return e1;
	}


	Ensemble<T> operator*(Ensemble<T>& e2) {
		Ensemble<T> e1;
		for(ListIterator<T> i(*this);!i.horsListe();i.succ()){
			if(e2.exist(i.courant()))
				e1.add(i.courant());
		}
		return e1;
	}
	
	
	Ensemble<T> operator/( Ensemble<T>& e2) {
		return ((*this)+e2)-((*this)*e2);
	}

};


template <class T> std::ostream& operator<< (std::ostream& out, Ensemble<T>& e){
	out << "Ensemble a " <<e.size()<< " element(s):"<<std::endl;
	for(ListIterator<T>i(e);!i.horsListe();i.succ())
		out << " " <<i.courant();
	return out;
}
template <class T> std::istream& operator>> (std::istream& in, Ensemble<T>& e){
	int nbr;
	T i;
	in >>nbr ;
	for(int j=0; j<nbr;j++){
		in >> i;
		e.add(i);
	}
	return in;
}