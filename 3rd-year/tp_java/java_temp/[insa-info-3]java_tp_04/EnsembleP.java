/**
 *La classe EnsembleP représente des ensembles de Personne
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 4 02/10/2004
 */

public class EnsembleP {
	
	// attributs
	
	protected static final int CAPACITE = 50;
	protected Personne[] tab;
	protected int cardinal;
	
	// constructeur
	
	public EnsembleP() {
		tab = new Personne[CAPACITE];
		cardinal = 0;
	} // Ensemble()
	
	
	// méthodes
	
	/**
	 * Donne le nombre d'éléments de l'ensemble
	 *@return cardinal de l'ensemble
	 */
	public int cardinal() {
		return cardinal;
	} // cardinal()
	
	/**
	 * Rend vrai si l'ensemble est vide
	 *@return un boolean selon que l'ensemble soit vide ou pas
	 */
	public boolean estVide() {
		if (cardinal == 0) {
			return true;
		} else {
			return false;
		}
	} // estVide()
	
	/**
	 * Rend vrai si le tableau représentant l'ensemble est plein
	 *@return un boolean selon que l'ensemble soit plein ou pas
	 */
	public boolean estPlein() {
		if (cardinal == CAPACITE) {
			return true;
		} else {
			return false;
		}
	} // estPlein()
	
	/**
	 * Rend vrai si e appartient à l'ensemble
	 */
	public boolean appartient(Personne p) {
		for (int i=0 ; i<cardinal ; i++) {
			if (tab[i].equals(p)) {
				return true;
			}
		}
		return false;
	} // appartient()
	
	/**
	 * Ajoute l'élément e dans l'ensemble s'il n'est pas présent,
	 * sinon ne fait rien. Si le tableau est plein affiche un
	 * message d'erreur
	 */
	public void ajoute(Personne p) {
		if (appartient(p)) {
			System.out.println("Cette personne est déjà dans la liste");
		} else if (estPlein()) {
			System.out.println("L'ensemble est plein");
		} else {
			tab[cardinal] = p;
			cardinal++;
		}
		return;
	} // ajoute()
	
	/**
	 * Enlève l'élément e s'il est présent, sinon ne fait rien
	 */
	public void enleve(Personne p) {
		if (!appartient(p)) {
			System.out.println("Cette personne n est pas dans l'enmeble");
			return;
		}
		int i;
		for (i=0 ; i<cardinal && !tab[i].equals(p) ; i++) {
		}
		cardinal--;
		for (; i<cardinal ; i++) {
			tab[i]=tab[i+1];
		}
		return;
	} // enleve()

	/**
	 * Rend vrai si P est inclus dans l'ensemble courant
	 */
	public boolean inclus(EnsembleP E) {
		for (int i=0 ; i<E.cardinal ; i++) {
			if (!this.appartient(E.tab[i])) {
				return false;
			} // if
		} // for i
		return true;
	} // inclus()
	
	/**
	 * Rend vrai si P et l'ensemble courant n'ont
	 * aucun élément en commun
	 */
	public boolean disjoint(EnsembleP E) {
		for (int i=0 ; i<E.cardinal ; i++) {
			if (this.appartient(E.tab[i])) {
				return false;
			} // if
		} // for i
		return true;
	} // disjoint()
	
	/**
	 * Rend vrai si l'ensemble P a les mêmes éléments
	 * que l'ensemble courant
	 */
	public boolean egal(EnsembleP E) {
		if (this.inclus(E) && E.inclus(this)) {
			return true;
		} else {
			return false;
		}
	} // egal()
	
	/**
	 * Crée un nouvel ensemble dont les éléments sont ceux de
	 * l'intersection de P et de l'ensemble courant
	 *@return un ensemble étant l'intersection de this et P
	 */
	public EnsembleP intersection(EnsembleP E) {
		EnsembleP intersection_E = new EnsembleP();
		for (int i=0 ; i<this.cardinal ; i++) {
			if (E.appartient(this.tab[i])) {
				intersection_E.ajoute(this.tab[i]);
			} // if
		} // for i
		return intersection_E;
	} // intersection()
	
	/**
	 * Crée un nouvel ensemble dont les éléments sont ceux de E
	 * et de l'ensemble courant
	 * Ne fonctionne que si l'union des 2 ensemble ne necessite pas
	 * un tableau d'une taille supérieur à CAPACITE
	 *@return un ensemble étant l'union de this et E
	 */
	public EnsembleP union(EnsembleP E) {
		EnsembleP union_E = new EnsembleP();
		for (int i=0 ; i<this.cardinal ; i++) {
			union_E.ajoute(this.tab[i]);
		} // for i
		for (int i=0 ; i<E.cardinal ; i++) {
			union_E.ajoute(E.tab[i]);
		} // for i
		return union_E;
	} // union()


	/**
	 * Rend une String avec le contenu de l'ensemble
	 */

	public String toString() {
		String reponse = new String();
		reponse += "\n";
		for (int i=0 ; i<cardinal ; i++) {
			reponse += tab[i].toString()+"\n";
		}
		return reponse;
	}// toString()

	public static void main(String args[]) {
		EnsembleP e1 = new EnsembleP();
		EnsembleP e2 = new EnsembleP();
		EnsembleP e3 = new EnsembleP();
		EnsembleP e4 = new EnsembleP();
		
		Personne p1 = new Personne("Antoine","Doisnel");
		Personne p2 = new Personne("Roland","Louis");
		Personne p3 = new Personne("Bob","Marley");
		Personne p4 = new Personne("George","Bush");
		Personne p5 = new Personne("François-Julien","Bourget Marbaud");
		Personne p6 = new Personne("Pierre","Dubois");

		e1.ajoute(p1);
		e1.ajoute(p2);
		e1.ajoute(p3);
		e1.ajoute(p4);
		e1.ajoute(p5);
		e1.ajoute(p6);
		
		e2.ajoute(p2);
		e2.ajoute(p4);
		e2.ajoute(p6);
		
		e3.ajoute(p1);
		e3.ajoute(p3);
		e3.ajoute(p5);
		
		e4.ajoute(p1);
		e4.ajoute(p5);

		System.out.print("e1:"+e1);

		e1.enleve(p2);
		e1.enleve(p4);
		e1.enleve(p6);

		System.out.print("e1:"+e1);
		
		System.out.print("e1:"+e1);
		System.out.print("e2:"+e2);
		System.out.print("e3:"+e3);
		System.out.print("e4:"+e4);

		System.out.println("e4 inclus dans e1 ? "+e1.inclus(e4));
		System.out.println("e1 = e3 ? "+e1.egal(e3));
		System.out.println("e1 et e2 disjoints ? "+e1.disjoint(e2));
		System.out.print("intersection de e1 et e2: "+e1.intersection(e2));
		System.out.print("union de e1 et e2: "+e1.union(e2));

	} // main()
	
} // EnsembleP

/**
 * traces d'execution:



 */