/**
 *La classe Plonge représente un type qui lave ses assiettes
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 5 05/10/2004
 */

import Piles.*; // importe toutes les définitions du package Piles

/**
 * Plonge.java
 *
 * Classe représentant quelqu'un qui fait "la plonge",
 * utilisant une pile de vaisselle sale et une pile de vaisselle propre.
 *
 * @author Département informatique
 * @version 07/10/2003
 */

public class Plonge  {
	/* Remarque Importante : utilise un attribut de type général Pile (!!!) */
	protected Pile sale;   // les assiettes sales
	protected Pile propre; // les assiettes propres

	/**
	 * Consctructeur à partir de deux piles
	 *@param a pile des assiettes sales
	 *@param b pile des assiettes propres
	 *@return void
	 */
	public Plonge(Pile a, Pile b) {
		sale=a; propre=b; // on se met à la plonge sur les piles fournies
	}// Plonge()
  
	/**
	 * Ajoute une assiette sale
	 *@param n entier représentant l'assiette
	 *@return void
	 */
	public void ajouterUneSale(int n) throws PilePleineException  {
		sale.empiler( new Integer(n) ); // empile une assiette numérotée
	}// ajouterUneSale()

	/**
	 * Lave une assiette
	 *@param void
	 *@return void
	 */
	public void laverUneAssiette() throws PileVideException,PilePleineException {
		Object o = sale.depiler();
		System.out.println("Lave assiette "+o);
		propre.empiler(o);
	}// laverUneAssiette()

	/**
	 * Retire une assiette de la pile des assiettes propres
	 *@param void
	 *@return numéro de l'assiette retirée (type int)
	 */
	public int retirerUnePropre() throws PileVideException {
		Integer i = (Integer)propre.depiler(); //sort une assiette numérotée
		return i.intValue();
	}// retirerUnePropre()

	/**
	 * Vide completement les piles d'assiettes
	 *@param void
	 *@return void
	 */
	public void toutVider() {
		sale.viderPile();
		propre.viderPile();
	}// toutVider()

	/**
	 * Retourne en sortie écran le contenu des piles d'assiettes
	 *@param void
	 *@return String
	 */
	public String toString() {
		return "Situation de la plonge :\n"+sale+propre;
	}// toString()

	public static void main(String[] args) {
		Plonge p = new Plonge( new MaPile(), new MaPile() );
		try {
			p.ajouterUneSale(1);
			p.ajouterUneSale(2);
			p.ajouterUneSale(3);
			p.laverUneAssiette();
			p.laverUneAssiette();
			p.ajouterUneSale(4);
			int n = p.retirerUnePropre();
			System.out.println(p + "assiette retiree propre : "+n);
			p.laverUneAssiette();
			p.laverUneAssiette();
			p.laverUneAssiette();
			System.out.println(p);
		}// try
		catch(PileVideException e) {
			System.out.println(e.getMessage());
		}
		catch(PilePleineException e) {
			System.out.println(e.getMessage());
		}
	}// main()
}// Plonge

/*

Lave assiette 3
Lave assiette 2
Situation de la plonge :
-----
 
4   <- sommet
1
-----
-----
 
3   <- sommet
-----
assiette retiree propre : 2
Lave assiette 4
Lave assiette 1
Depilement impossible: la pile est vide

*/
