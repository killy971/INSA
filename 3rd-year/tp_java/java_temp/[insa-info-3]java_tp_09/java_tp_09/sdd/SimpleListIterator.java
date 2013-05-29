/*
* @(#) SimpleListIterator.java 1.0 26/07/2001
*
* (c) INSA de Rennes
*
*/
package sdd;

/**
 * It�rateur pour les listes "� l'ancienne" qui
 * peut-�tre horsliste et retarde les exceptions jusqu'a l'acc�s hors liste ;
 * il permet des modifications de la liste pendant le parcours. 
 *
 * @author  Jean-louis Pazat
 * @version 1.0 26/07/2001
 * @see SimpleList
 */
public interface SimpleListIterator  {                     
 // Requ�tes

/** Rend l'�l�ment courant de la collection.
    si on est soprti de la liste, l�vera
  * <tt>HorsListeException</tt>. 
  * <DL><DT><B>Pr�condition : </B><DD><TT>vrai</TT></DL>
  * <DL><DT><B>Postcondition : </B><DD><TT>r�sultat</TT></DL>
  * @return l'�l�ment courant s'il existe
  */
    public Object ec() throws SimpleListException;


 /**
  * Rend <tt>true</tt> si on est sorti de la liste
  * autrement dit si toute autre op�ration d'acc�s l�vera l'exception
  * <tt>HorsListeException</tt>. 
  * <DL><DT><B>Pr�condition : </B><DD><TT>vrai</TT></DL>
  * <DL><DT><B>Postcondition : </B><DD><TT>r�sultat</TT></DL>
  * @return <tt>true</tt> si l'it�rateur est en dehors de la liste
  */
  public boolean sorti();

 //D�placements
 /**
   * Methode pour se mettre en t�te de la liste. 
   * <DL><DT><B>Pr�condition :  </B><DD><TT>vrai</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT></TT></DL>
   */
    
 public void entete();
   
 /**
   * Methode pour se mettre en queue de la liste. 
   * <DL><DT><B>Pr�condition :  </B><DD><TT>vrai</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT></TT></DL>
   */
  public void enqueue();
   
/**
  * Methode pour se mettre sur l'�l�ment i de la liste. 
  */
    public void allera(int i)
	throws SimpleListException;

/**
   * Methode pour se mettre sur l'�l�ment suivant de la liste. 
   * <DL><DT><B>Pr�condition :  </B><DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT></TT></DL>
   */
   public void suc()
     throws SimpleListException;
   
/**
   * Methode pour se mettre sur l'�l�ment pr�c�dent de la liste. 
   * <DL><DT><B>Pr�condition :  </B><DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT></TT></DL>
   */
  public void pred()
    throws SimpleListException;
   
 // Modifications
 /**
   * Methode pour ajouter � gauche de l'ec un element
   * l'�l�ment ajout� devient l'ec. 
   * <DL><DT><B>Pr�condition :  </B><DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT>(l.contains(obj) = vrai) et (valEc==obj)</TT></DL>
   * @param obj r�f�rence du nouvel element
   */
   void inserer(Object obj)
     throws SimpleListException;
   
  /**
   * Methode pour ajouter a droite de ec un element
   * l'�l�ment ajout� devient ec. 
   * <DL><DT><B>Pr�condition : </B><DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT>(l.contains(obj) = vrai) et (valEc==obj)</TT></DL>
   * @param obj valeur du nouvel element
   */
  void ajouter(Object obj)
    throws SimpleListException;
   
  /**
   * Methode pour supprimer ec
   * ec devient le suivant de l'ancien ec. 
   * <DL><DT><B>Pr�condition : </B> <DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B> <DD><TT>l.contains(obj) = faux</TT></DL>
   */
  void oter()    
    throws SimpleListException;
   
  /**
   * Methode pour modifier la valeur de ec. 
   * <DL><DT><B>Pr�condition : </B><DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT>(l.contains(obj) = vrai) et (valEc==obj)</TT></DL>
   * @param obj valeur du nouvel element
   */
  void modifier(Object obj)
     throws SimpleListException;
   

} // SimpleListIterator
