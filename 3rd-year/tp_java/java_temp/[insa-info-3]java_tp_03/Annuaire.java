/**
 *La classe Annuaire représente un annuaire de personnes
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 3 29/09/2004
 */

public class Annuaire{
  private final int CAPACITE = 50;
  
  private int _cardinal;
  private Personne[] _elements;

  /* constructeur */
  public Annuaire() {
	_elements = new Personne[CAPACITE];
	_cardinal = 0;
  }

  /* donne le nombre d'éléments de l'ensemble */
  public int cardinal() {
    return _cardinal;
  }

  /* rend vrai si l'ensemble est vide */ 
  public boolean estVide() {
    return _cardinal==0;
  }

  /* rend vrai si le tableau représentant l'ensemble est plein */ 
  public boolean estPlein() {
    return _cardinal==CAPACITE;
  }
  
  /* rend vrai si la personne p est dans l'annuaire 
     même avec un autre numéro de tel */
	public boolean appartient(Personne p) {
		for (int i=0 ; i<_cardinal ; i++) {
			if (_elements[i].equals(p)) {
				return true;
			}
		}
		return false;
	} 

  /* rend le contenu de l'ensemble dans une chaîne 
     avec des retours a la ligne entre chaque enrgistrement */
  public String toString() {
	String reponse = new String();
	for (int i=0 ; i<_cardinal ; i++) {
		reponse += _elements[i].toString()+"\n";
	}
	return reponse;
  }

  /* ajoute la personne p dans l'ensemble si elle n'est pas déja présente
     (avec le même numéro ou non), 
     sinon affiche un message d'erreur explicite.
     Si le tableau est plein affiche un message d'erreur 
  */
  public void ajoute(Personne p) {
	if (appartient(p)) {
		System.out.println("Cette personne est déjà dans la liste");
	} else if (estPlein()) {
		System.out.println("L'annuaire est plein");
	} else {
		_elements[_cardinal] = p;
		_cardinal++;
	}
	return;
  }

  /* enlève la personne p si elle est présente, même avec un autre numéro
     sinon ne fait rien */
  public void enleve(Personne p) {
	if (!appartient(p)) {
		System.out.println("Cette personne n est pas dans la liste");
		return;
	}
	int i;
	for (i=0 ; i<_cardinal && !_elements[i].equals(p) ; i++) {
	}
	_cardinal --;
	for (; i<_cardinal ; i++) {
		_elements[i]=_elements[i+1];
	}
	return;
  }

  /* donne le numéro de téléphone qui est dans l'annuaire pour la personne 
     passée en paramètre */
  public int tel(Personne p){
    return p.tel();
  }

  /* donne la personne de numéro de tel passé en paramètre */
  public Personne telInv(int num){
	for (int i=0 ; i<_cardinal ; i++) {
		if(_elements[i].tel() == num) {
			return _elements[i];
		}
	}
	System.out.println("Le numéro n'a pas été trouvé");
	return null;
  }

	public boolean inclus(Annuaire A) {
		for (int i=0 ; i<A._cardinal ; i++) {
			if (!this.appartient(A._elements[i])) {
				return false;
			} // if
		} // for i
		return true;
	} // inclus()

	public boolean disjoint(Annuaire A) {
		for (int i=0 ; i<A._cardinal ; i++) {
			if (this.appartient(A._elements[i])) {
				return false;
			} // if
		} // for i
		return true;
	} // disjoint()

	public boolean egal(Annuaire A) {
		if (this.inclus(A) && A.inclus(this)) { // propriété des inclusions
			return true;
		} else {
			return false;
		}
	} // egal()

	public Annuaire intersection(Annuaire A) {
		Annuaire intersection_A = new Annuaire();
		for (int i=0 ; i<this._cardinal ; i++) {
			if (A.appartient(this._elements[i])) {
				intersection_A.ajoute(this._elements[i]);
			} // if
		} // for i
		return intersection_A;
	} // intersection()

	public Annuaire union(Annuaire A) {
		Annuaire union_A = new Annuaire();
		for (int i=0 ; i<this._cardinal ; i++) {
			union_A.ajoute(this._elements[i]);
		} // for i
		for (int i=0 ; i<A._cardinal ; i++) {
			union_A.ajoute(A._elements[i]);
		} // for i
		return union_A;
	} // union()


  public static void main (String[] args) {
    Annuaire annuaire = new Annuaire();
    Annuaire annuaire2 = new Annuaire();

    System.out.println(annuaire);
    Personne p1 = new Personne("Antoine","Doisnel");
    Personne p2 = new Personne("Antoine2","Doisnel2");
    Personne p3 = new Personne("Bob","Marley");

    p1.affecteTel(998);
    annuaire.ajoute(p1);
    annuaire.ajoute(p2);
    annuaire2.ajoute(p1);
    annuaire2.ajoute(p3);

    System.out.println("Annuaire 1:");
    System.out.println(annuaire);
    System.out.println("Annuaire 2:");
    System.out.println(annuaire2);
    System.out.println(annuaire.telInv(998));
    System.out.println(annuaire.telInv(99));
    System.out.println("Annuaires 1 et 2 égaux ?");
    System.out.println(annuaire.egal(annuaire2));
    System.out.println("Intersection annuaires 1 et 2:");
    System.out.println(annuaire.intersection(annuaire2));
    System.out.println("Union annuaires 1 et 2:");
    System.out.println(annuaire.union(annuaire2));
  }
}

/* Traces d'execution

Annuaire 1:
Antoine Doisnel : 998
Antoine2 Doisnel2 : 0
 
Annuaire 2:
Antoine Doisnel : 998
Bob Marley : 0
 
Antoine Doisnel : 998
Le numéro n'a pas été trouvé
null
Annuaires 1 et 2 égaux ?
false
Intersection annuaires 1 et 2:
Antoine Doisnel : 998
 
Union annuaires 1 et 2:
Cette personne est déjà dans la liste
Antoine Doisnel : 998
Antoine2 Doisnel2 : 0
Bob Marley : 0

*/
