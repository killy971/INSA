/**
 *La classe Pile définie une interface de pile
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 5 05/10/2004
 */

package Piles;

public interface Pile {

	/**
	 * Enlève le dernier objet ajouté à la pile
	 *@param void
	 *@return Object
	 */
	public Object depiler() throws PileVideException;

	/**
	 * Ajoute un objet au sommet de la pile
	 *@param Object
	 *@return void
	 */
	public void empiler(Object item) throws PilePleineException;

	/**
	 * Teste si la pile est vide
	 *@param void
	 *@return boolean
	 */
	public boolean pileVide();

	/**
	 * Renvoie l'objet au sommet de la pile
	 *@param void
	 *@return Object
	 */
	public Object sommetPile();

	/**
	 * Vide totalement la pile
	 *@param void
	 *@return void
	 */
	public void viderPile();
}
