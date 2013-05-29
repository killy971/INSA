/**
 *La classe est une version améliorée de la classe SimplePile
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 5 05/10/2004
 */

package Piles;

public class MaPile implements Pile {

	private final int PILESIZE;	// taille de la pile
	private int pileSommet;	// indice du sommet de la pile
	private Object[] tab;	// tableau représentant la pile
	
	/**
	 * Constructeur de pile
	 *@param void
	 *@return void
	 */
	public MaPile() {
		PILESIZE = 10;
		pileSommet = 0;
		tab = new Object[PILESIZE];
	}// maPile()
	
	/**
	 * Vide la pile
	 *@param void
	 *@return void
	 */
	public void viderPile() {
		pileSommet = 0;
	}// viderPile()

	/**
	 * Ajoute un objet à la pile
	 *@param obj
	 *@return void
	 */
	public void empiler(Object obj) throws PilePleineException {
		if (obj == null) {
			// rien
		} else
		if (pileSommet==(PILESIZE-1)) {
			throw new PilePleineException("Empilement impossible: la pile est pleine");
		} else {
			tab[pileSommet] = obj;
			pileSommet++;
		}
	}// empiler()

	/**
	 * Enlève l'objet au sommet de la pile
	 *@param void
	 *@return Object
	 */
	public Object depiler() throws PileVideException {
		if (pileVide()) {
			throw new PileVideException("Depilement impossible: la pile est vide");
			// return null;
		} else {
			pileSommet--;
			return tab[pileSommet];
		}
	}// depiler()

	/**
	 * Rend l'objet au sommet de la pile
	 *@param void
	 *@return Object
	 */
	public Object sommetPile() {
		return tab[pileSommet - 1];
	}// sommetPile()

	/**
	 * Teste si la pile est vide
	 *@param void
	 *@return boolean
	 */
	public boolean pileVide() {
		return pileSommet == 0;
	}// pileVide()
	
	/**
	 * Renvoie à l'écran le statut de la pile
	 *@param void
	 *@return String
	 */
	public String toString() {
		String s = "-----\n";
		if (pileVide()) {
			s += "pile Vide\n";
		} else {
			s += "\n" + tab[pileSommet - 1];
			s += "   <- sommet";
			for (int i = pileSommet - 2; i >= 0; i--) {
				s += "\n" + tab[i];
			}// for
			s += "\n-----\n";
		}
		return s;
	}// toString()
	
}// MaPile
