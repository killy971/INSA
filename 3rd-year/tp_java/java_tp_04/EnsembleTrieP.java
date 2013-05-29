/**
 * La classe EnsembleTrieP représente des ensembles de Personne, triées par
 * ordre alphabétique
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 4 02/10/2004
 */

public class EnsembleTrieP extends EnsembleP {
	
	/**
	 * Ajoute la personne p dans l'ensemble si elle n'est pas présente,
	 * sinon renvoie un message d'erreur. Si le tableau est plein affiche un
	 * autre message d'erreur
	 * L'ajout se fait d'une façon intelligente aboutissant à un ensemble
	 * de peronnes classées par ordre alphabétique
	 */
	public void ajoute(Personne p) {
		if (appartient(p)) {
			System.out.println("Cette personne est déjà dans la liste");
		} else if (estPlein()) {
			System.out.println("L'ensemble est plein");
		} else {
			Personne[] new_tab = new Personne[cardinal+1];
			int i;
			for (i=0 ; (i<cardinal) && (!tab[i].inferieurA(p)) ; i++) {
				new_tab[i] = tab[i];
			}// for
			new_tab[i] = p;
			for ( ; i<cardinal ; i++) {
				new_tab[i+1] = tab[i];
			}// for
			cardinal++;
			tab = new_tab; // tab pointera vers l'objet créé en début de méthode
		}
		return;
	} // ajoute()

/*

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

*/

	/**
	 * Crée un nouvel ensemble dont les éléments sont ceux de E
	 * et de l'ensemble courant
	 * Ne fonctionne que si l'union des 2 ensemble ne necessite pas
	 * un tableau d'une taille supérieur à CAPACITE
	 *@return un ensemble étant l'union de this et E
	 */
	public EnsembleTrieP union(EnsembleTrieP E) {
		EnsembleTrieP union_E = new EnsembleTrieP();
		for (int i=0 ; i<this.cardinal ; i++) {
			union_E.ajoute(this.tab[i]);
		} // for i
		for (int i=0 ; i<E.cardinal ; i++) {
			union_E.ajoute(E.tab[i]);
		} // for i
		return union_E;
	} // union()

	public static void main(String args[]) {
		
	} // main()
	
	
} // EnsembleTrieP