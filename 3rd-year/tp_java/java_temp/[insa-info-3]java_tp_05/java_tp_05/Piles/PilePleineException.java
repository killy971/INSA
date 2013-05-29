/**
 *La classe PilePleineException permet de repérer un type d'exception particulier
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 5 05/10/2004
 */

package Piles;

public class PilePleineException extends Exception {
	public PilePleineException (String s) {
		super(s);
	}

	public PilePleineException () {
		super();
	}
}
