/*
* @(#) SimpleList.java 1.0 26/07/2001
*
* (c) INSA de Rennes
*
*/
package sdd;

/**
* Interface Liste "� l'ancienne"
* dont les parcours sont g�r�s par des it�rateurs simples. 
*
 * @author  Jean-louis Pazat
 * @version 1.0 26/07/2001
 * @see SimpleListIterator
 */
public interface SimpleList {                     
 // Requ�tes
 
  /**
   * M�thode pour tester si une liste est vide.
   * <DL><DT><B>Pr�condition : </B><DD><TT>vrai</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT>r�sultat</TT></DL>
   * @return <TT>true</TT> si la liste est vide
   */
  boolean estVide();      


/**
  * enl�ve tous les �l�ments de la liste. 
  * <DL><DT><B>Pr�condition : </B><DD><TT>vrai</TT></DL>
  * <DL><DT><B>Postcondition : </B><DD><TT>listeVide=vrai</TT></DL>
  */
   void vider();



/**
  * Rend un it�rateur sur la liste. 
  * <DL><DT><B>Pr�condition : </B><DD><TT>vrai</TT></DL>
  * <DL><DT><B>Postcondition : </B><DD></DL>
  * @return <tt>SimpleListIterator</tt> qui permet de parcourir et d'acc�der la liste
  */
   SimpleListIterator iterateur();

  /**
   * Methode pour rendre une repr�sentation de la liste sous forme 
   * d'une cha�ne. 
   * @precondition True
   * @postcondition False
   * @return une chaine representant la liste
   */

  public String toString();        

} // interface Liste
