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
     * Teste si l'�lement courant poss�de un successeur
     *@param void
     *@return boolean
     */
	public boolean aSuivant();

    /**
     * Test si l'�l�ment courant poss�de un pr�d�cesseur
     *@param void
     *@return boolean
     */
	public boolean aPrecedent();

    /**
     * Rend la valeur de l'�l�ment courant
     *@param void
     *@return Object
     */
	public Object element() throws ExceptionListeVide;

    /**
     * Ajoute un �l�ment � la liste
     *@param Object
     *@return void
     */
	public void ajouter(Object o);

    /**
     * Enl�ve l'�l�ment courant de la liste
     *@param void
     *@return void
     */
	public void enlever() throws ExceptionListeVide;

    /**
     * D�place le pointeur de l'�l�ment courant vers l'�l�ment successeur
     * de l'�l�ment courant
     *@param void
     *@return void
     */
	public void suivant() throws ExceptionHorsListe;

    /**
     * D�place le pointeur de l'�l�ment courant vers l'�l�ment pr�d�cesseur
     * de l'�l�ment courant
     *@param void
     *@return void
     */
	public void precedent() throws ExceptionHorsListe;

}// Liste