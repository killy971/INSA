/*
* @(#) SimpleListIterator.java 1.0 26/07/2001
*
* (c) INSA de Rennes
*
*/
package sdd;

/**
 * Itérateur pour les listes "à l'ancienne" qui
 * peut-être horsliste et retarde les exceptions jusqu'a l'accès hors liste ;
 * il permet des modifications de la liste pendant le parcours. 
 *
 * @author  Jean-louis Pazat
 * @version 1.0 26/07/2001
 * @see SimpleList
 */
public interface SimpleListIterator  {                     
 // Requêtes

/** Rend l'élément courant de la collection.
    si on est soprti de la liste, lèvera
  * <tt>HorsListeException</tt>. 
  * <DL><DT><B>Précondition : </B><DD><TT>vrai</TT></DL>
  * <DL><DT><B>Postcondition : </B><DD><TT>résultat</TT></DL>
  * @return l'élément courant s'il existe
  */
    public Object ec() throws SimpleListException;


 /**
  * Rend <tt>true</tt> si on est sorti de la liste
  * autrement dit si toute autre opération d'accès lèvera l'exception
  * <tt>HorsListeException</tt>. 
  * <DL><DT><B>Précondition : </B><DD><TT>vrai</TT></DL>
  * <DL><DT><B>Postcondition : </B><DD><TT>résultat</TT></DL>
  * @return <tt>true</tt> si l'itérateur est en dehors de la liste
  */
  public boolean sorti();

 //Déplacements
 /**
   * Methode pour se mettre en tête de la liste. 
   * <DL><DT><B>Précondition :  </B><DD><TT>vrai</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT></TT></DL>
   */
    
 public void entete();
   
 /**
   * Methode pour se mettre en queue de la liste. 
   * <DL><DT><B>Précondition :  </B><DD><TT>vrai</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT></TT></DL>
   */
  public void enqueue();
   
/**
  * Methode pour se mettre sur l'élément i de la liste. 
  */
    public void allera(int i)
	throws SimpleListException;

/**
   * Methode pour se mettre sur l'élément suivant de la liste. 
   * <DL><DT><B>Précondition :  </B><DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT></TT></DL>
   */
   public void suc()
     throws SimpleListException;
   
/**
   * Methode pour se mettre sur l'élément précédent de la liste. 
   * <DL><DT><B>Précondition :  </B><DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT></TT></DL>
   */
  public void pred()
    throws SimpleListException;
   
 // Modifications
 /**
   * Methode pour ajouter à gauche de l'ec un element
   * l'élément ajouté devient l'ec. 
   * <DL><DT><B>Précondition :  </B><DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT>(l.contains(obj) = vrai) et (valEc==obj)</TT></DL>
   * @param obj référence du nouvel element
   */
   void inserer(Object obj)
     throws SimpleListException;
   
  /**
   * Methode pour ajouter a droite de ec un element
   * l'élément ajouté devient ec. 
   * <DL><DT><B>Précondition : </B><DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT>(l.contains(obj) = vrai) et (valEc==obj)</TT></DL>
   * @param obj valeur du nouvel element
   */
  void ajouter(Object obj)
    throws SimpleListException;
   
  /**
   * Methode pour supprimer ec
   * ec devient le suivant de l'ancien ec. 
   * <DL><DT><B>Précondition : </B> <DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B> <DD><TT>l.contains(obj) = faux</TT></DL>
   */
  void oter()    
    throws SimpleListException;
   
  /**
   * Methode pour modifier la valeur de ec. 
   * <DL><DT><B>Précondition : </B><DD><TT>non horsliste</TT></DL>
   * <DL><DT><B>Postcondition : </B><DD><TT>(l.contains(obj) = vrai) et (valEc==obj)</TT></DL>
   * @param obj valeur du nouvel element
   */
  void modifier(Object obj)
     throws SimpleListException;
   

} // SimpleListIterator
