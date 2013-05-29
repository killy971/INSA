#ifndef CARTE_H
#define CARTE_H

enum Couleur {pique=1,coeur,carreau,trefle};
enum Valeur {As=1,Deux,Trois,Quatre,Cinq,Six,Sept,Huit,Neuf,Dix,Valet,Dame,Roi};

class Carte
{
	Couleur _c;
	Valeur _v;
	char _p;
	static Carte* Nt;
	static Carte* St;
	static Carte* Nq;
	static Carte* Sq;
	Carte* _suivant;
	
public:
	Couleur getCouleur();
	Valeur getValeur();
	char getProp();
	Carte(Couleur c,Valeur v, char prop);
	Carte();
	~Carte();

	Carte* suc();
	static void afficherN();
	static void afficherS();
	void afficher();
	void changerProp();
	void passerDerriere();
	static Carte* getNTete();
	static Carte* getSTete();
	bool supAbs(const Carte &c) const;
	bool egale(const Carte &c) const;
};



#endif 
