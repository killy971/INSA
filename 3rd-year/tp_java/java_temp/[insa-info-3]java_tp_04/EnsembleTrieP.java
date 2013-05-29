/**
 * La classe EnsembleTrieP repr�sente des ensembles de Personne, tri�es par
 * ordre alphab�tique
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 4 02/10/2004
 */

public class EnsembleTrieP extends EnsembleP {
	
	/**
	 * Ajoute la personne p dans l'ensemble si elle n'est pas pr�sente,
	 * sinon renvoie un message d'erreur. Si le tableau est plein affiche un
	 * autre message d'erreur
	 * L'ajout se fait d'une fa�on intelligente aboutissant � un ensemble
	 * de peronnes class�es par ordre alphab�tique
	 */
	public void ajoute(Personne p) {
		if (appartient(p)) {
			System.out.println("Cette personne est d�j� dans la liste");
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
			tab = new_tab; // tab pointera vers l'objet cr�� en d�but de m�thode
		}
		return;
	} // ajoute()

/*

	public void ajoute(int e) {
		if (estVide()) {
			tab[0] = e;
		} else if (estPlein()) {
			System.out.println("Impossible d'ajouter un �l�ment l'ensemble est plein.");
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
			// System.out.prinln("L'�l�ment '"+e+"' existe d�j� dans
			//  l'ensemble");
			return;
		}
		card++;
		return;
	} // ajoute()

*/

	/**
	 * Cr�e un nouvel ensemble dont les �l�ments sont ceux de E
	 * et de l'ensemble courant
	 * Ne fonctionne que si l'union des 2 ensemble ne necessite pas
	 * un tableau d'une taille sup�rieur � CAPACITE
	 *@return un ensemble �tant l'union de this et E
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