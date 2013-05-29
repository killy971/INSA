/**
 * 
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 10 23/11/2004
 */

import sdd.*;

public class BdGeographiqueImpl {

	private SimpleListImpl baseDeDonnee;
	
	public BdGeographiqueImpl() {
		baseDeDonnee = new SimpleListImpl();
	}
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public boolean present(Enregistrement e) {
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		try {
			while (true) {
				if (((Enregistrement)it.ec()).equals(e)) {
					return true;
				}
			}// while
		}
		catch (SimpleListException simpleListException) {
			return false;
		}
	}// present(Enregistrement e)
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public void vider() {
		this.baseDeDonnee.vider();
	}// vide()
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public void ajout(Enregistrement e) throws PresentException {
		if (present(e)) throw new PresentException();
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		try {
			it.ajouter((Enregistrement)e);
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
		}
		return;
	}// ajout(Enregistrement e)
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public void retrait(Enregistrement e) throws AbsentException {
		if (!present(e)) throw new AbsentException();
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		try {
			while (true) {
				if (((Enregistrement)it.ec()).equals(e)) {
					it.oter();
					return;
				}
			}
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
		}
	}// retrait(Enregistrement e)
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public String toString() {
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		String s = "";
		try {
			while (true) {
				s += ((Enregistrement)it.ec()).toString()+"\n";
				it.suc();
			}
		}
		catch (SimpleListException e) {
			return s;
		}
	}// toString()
	
}