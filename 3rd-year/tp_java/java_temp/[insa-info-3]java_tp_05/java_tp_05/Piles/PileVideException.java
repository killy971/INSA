/**
 *La classe PileVideException permet de repérer un type d'exception particulier
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 5 05/10/2004
 */

package Piles;

public class PileVideException extends Exception {
	public PileVideException (String s) {
		super(s);
	}

	public PileVideException () {
		super();
	}
}
