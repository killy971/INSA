/*
* @(#) SimpleList.java 1.0 26/07/2001
*
* (c) INSA de Rennes
*
*/
package sdd;

/**
* Interface Liste "à l'ancienne"
* dont les parcours sont gérés par des itérateurs simples. 
*
 * @author  Jean-louis Pazat
 * @version 1.0 26/07/2001
 * @see SimpleListIterator
 */
public interface SimpleList {                     
 // Requêtes
 
  /**
   * Méthode pour tester si une liste est vide.
   * <DL><DT><B>Précondition : </B><DD><TT>vrai</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT>résultat</TT></DL>
   * @return <TT>true</TT> si la liste est vide
   */
  boolean estVide();      


/**
  * enlève tous les éléments de la liste. 
  * <DL><DT><B>Précondition : </B><DD><TT>vrai</TT></DL>
  * <DL><DT><B>Postcondition : </B><DD><TT>listeVide=vrai</TT></DL>
  */
   void vider();



/**
  * Rend un itérateur sur la liste. 
  * <DL><DT><B>Précondition : </B><DD><TT>vrai</TT></DL>
  * <DL><DT><B>Postcondition : </B><DD></DL>
  * @return <tt>SimpleListIterator</tt> qui permet de parcourir et d'accéder la liste
  */
   SimpleListIterator iterateur();

  /**
   * Methode pour rendre une représentation de la liste sous forme 
   * d'une chaîne. 
   * @precondition True
   * @postcondition False
   * @return une chaine representant la liste
   */

  public String toString();        

} // interface Liste
