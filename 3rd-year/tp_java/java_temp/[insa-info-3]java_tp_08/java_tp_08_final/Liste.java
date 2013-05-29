/**
 * Interface Liste
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 8 10/11/2004
 */

public interface Liste {

    /**
     * Teste si la liste est vide
     *@param void
     *@return boolean
     */
	public boolean listeVide();

    /**
     * Teste si l'élement courant possède un successeur
     *@param void
     *@return boolean
     */
	public boolean aSuivant();

    /**
     * Test si l'élément courant possède un prédécesseur
     *@param void
     *@return boolean
     */
	public boolean aPrecedent();

    /**
     * Rend la valeur de l'élément courant
     *@param void
     *@return Object
     */
	public Object element() throws ExceptionListeVide;

    /**
     * Ajoute un élément à la liste
     *@param Object
     *@return void
     */
	public void ajouter(Object o);

    /**
     * Enlève l'élément courant de la liste
     *@param void
     *@return void
     */
	public void enlever() throws ExceptionListeVide;

    /**
     * Déplace le pointeur de l'élément courant vers l'élément successeur
     * de l'élément courant
     *@param void
     *@return void
     */
	public void suivant() throws ExceptionHorsListe;

    /**
     * Déplace le pointeur de l'élément courant vers l'élément prédécesseur
     * de l'élément courant
     *@param void
     *@return void
     */
	public void precedent() throws ExceptionHorsListe;

}// Liste