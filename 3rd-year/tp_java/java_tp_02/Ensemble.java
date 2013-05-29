/**
 *La classe Ensemble représente des ensembles de nombres entiers
 *@author Nargeot Guillaume
 *@author Leroy
 *@version TP_2 26/09/2004
 */

public class Ensemble {
	
	// attributs
	
	private static final int CAPACITE = 50;
	private int[] tab;
	private int card;
	
	// constructeur
	
	public Ensemble() {
		tab = new int[CAPACITE];
		card = 0;
	} // Ensemble()
	
	
	// méthodes
	
	/**
	 * Donne le nombre d'éléments de l'ensemble
	 *@return cardinal de l'ensemble
	 */
	public int cardinal() {
		return card;
	} // cardinal()
	
	/**
	 * Rend vrai si l'ensemble est vide
	 *@return un boolean selon que l'ensemble soit vide ou pas
	 */
	public boolean estVide() {
		if (card == 0) {
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
		if (card == CAPACITE) {
			return true;
		} else {
			return false;
		}
	} // estPlein()
	
	/**
	 * Rend vrai si e appartient à l'ensemble
	 */
	public boolean appartient(int e) {
		for (int i=0 ; i<card ; i++) {
			if (tab[i] == e) {
				return true;
			}
		}
		return false;
	} // appartient()
	
	/**
	 * Affiche le contenu de l'ensemble
	 */
	public void affiche() {
		if (!this.estVide()) {
			System.out.print("{");
			int i;
			for (i=0 ; i<(card-1) ; i++) {
				System.out.print(tab[i]+",");
			}
			System.out.println(tab[i]+"}");
			return;
		} else {
			System.out.println("{}");
		}
	} // affiche()
	
	/**
	 * Ajoute l'élément e dans l'ensemble s'il n'est pas présent,
	 * sinon ne fait rien. Si le tableau est plein affiche un
	 * message d'erreur
	 */
	public void ajoute(int e) {
		if (estVide()) {
			tab[0] = e;
		} else if (estPlein()) {
			System.out.println("Impossible d'ajouter un élément l'ensemble est plein.");
			return;
		} else if (!appartient(e)) {
			int i=card-1;
			if (e<tab[card-1]) {
				for (i=card-1 ; i>=0 && e<tab[i] ; i--) {
					tab[i+1] = tab[i];
				}
				tab[i+1] = e;
			} else {
				tab[i+1] = e;
			}
		} else {
			// System.out.prinln("L'élément '"+e+"' existe déjà dans
			//  l'ensemble");
			return;
		}
		card++;
		return;
	} // ajoute()	
	
	/**
	 * Enlève l'élément e s'il est présent, sinon ne fait rien
	 */
	public void enleve(int e) {
		if (appartient(e)) {
			boolean deplace = false;
			for (int i=0 ; i<(card-1) ; i++) {
				if (tab[0] == e) {
					deplace = true;
				}
				if (!deplace) {
					if (tab[i+1] == e) {
						deplace = true;
					}
				} else {
					tab[i] = tab[i+1];
				}
			}
			card--;
			return;
		} else {
			return;
		}
	} // enleve()
	
	/**
	 * Lit un élément au clavier qui sera ajouté à l'ensemble
	 */
	public void lireUn() {
		int f = Lecture.lireInt();
		ajoute(f);
	} // lireUn()
	
	/**
	 *
	 */
	/*
	public void lire() {
		StringTokenizer st = new StringTokenizer(Lecture.lireString());
		
	}
	*/
	
	/**
	 * Rend vrai si E est inclus dans l'ensemble courant
	 */
	public boolean inclus(Ensemble E) {
		for (int i=0 ; i<E.card ; i++) {
			if (!this.appartient(E.tab[i])) {
				return false;
			} // if
		} // for i
		return true;
	} // inclus()
	
	/**
	 * Rend vrai si E et l'ensemble courant n'ont
	 * aucun élément en commun
	 */
	public boolean disjoint(Ensemble E) {
		for (int i=0 ; i<E.card ; i++) {
			if (this.appartient(E.tab[i])) {
				return false;
			} // if
		} // for i
		return true;
	} // disjoint()
	
	/**
	 * Rend vrai si l'ensemble E a les mêmes éléments
	 * que l'ensemble courant
	 */
	public boolean egal(Ensemble E) {
		if (this.inclus(E) && E.inclus(this)) {
			return true;
		} else {
			return false;
		}
	} // egal()
	
	/**
	 * Crée un nouvel ensemble dont les éléments sont ceux de
	 * l'intersection de E et de l'ensemble courant
	 *@return un ensemble étant l'intersection de this et E
	 */
	public Ensemble intersection(Ensemble E) {
		Ensemble intersection_E = new Ensemble();
		for (int i=0 ; i<this.card ; i++) {
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
	public Ensemble union(Ensemble E) {
		Ensemble union_E = new Ensemble();
		
		/** autre méthode revenant au même **
		for (int i=0 ; i<this.card ; i++) {
			if (!E.appartient(this.tab[i])) {
				union_E.ajoute(tab[i]);
			} // if
		} // for i
		for (int i=0 ; i<E.card ; i++) {
			union_E.ajoute(E.tab[i]);
		} // for i
		************************************/
		
		for (int i=0 ; i<this.card ; i++) {
			union_E.ajoute(this.tab[i]);
		} // for i
		for (int i=0 ; i<E.card ; i++) {
			union_E.ajoute(E.tab[i]);
		} // for i
		return union_E;
	} // union()
	
	public static void main(String args[]) {
		Ensemble e1 = new Ensemble();
		Ensemble e2 = new Ensemble();
		Ensemble e3 = new Ensemble();
		Ensemble e4 = new Ensemble();
		Ensemble e5 = new Ensemble();
		
		e1.ajoute(2);
		e1.ajoute(4);
		e1.ajoute(6);
		e1.ajoute(7);
		e1.ajoute(8);
		
		e2.ajoute(4);
		e2.ajoute(7);
		e2.ajoute(8);
		
		e3.ajoute(4);
		e3.ajoute(7);
		e3.ajoute(8);
		
		e4.ajoute(1);
		e4.ajoute(3);
		e4.ajoute(5);

		e5.ajoute(0);
		e5.ajoute(2);
		e5.ajoute(6);
		e5.ajoute(9);

		System.out.print("e1: ");
		e1.affiche();
		System.out.print("e2: ");
		e2.affiche();
		System.out.print("e3: ");
		e3.affiche();
		System.out.print("e4: ");
		e4.affiche();
		System.out.print("e5: ");
		e5.affiche();

		System.out.println("e2 inclus dans e1 ? "+e1.inclus(e2));
		System.out.println("e3 = e2 ? "+e2.egal(e3));
		System.out.println("e1 et e4 disjoints ? "+e1.disjoint(e4));
		System.out.print("intersection de e1 et e5: ");
		e1.intersection(e5).affiche();
		System.out.print("union de e1 et e5: ");
		e1.union(e5).affiche();
	} // main()
	
} // Ensemble

/**
 * traces d'execution:

e1: {2,4,6,7,8}
e2: {4,7,8}
e3: {4,7,8}
e4: {1,3,5}
e5: {0,2,6,9}
e2 inclus dans e1 ? true
e3 = e2 ? true
e1 et e4 disjoints ? true
intersection de e1 et e5: {2,6}
union de e1 et e5: {0,2,4,6,7,8,9}

 */