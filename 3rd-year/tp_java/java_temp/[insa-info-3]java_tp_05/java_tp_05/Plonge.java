/**
 *La classe Plonge repr�sente un type qui lave ses assiettes
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 5 05/10/2004
 */

import Piles.*; // importe toutes les d�finitions du package Piles

/**
 * Plonge.java
 *
 * Classe repr�sentant quelqu'un qui fait "la plonge",
 * utilisant une pile de vaisselle sale et une pile de vaisselle propre.
 *
 * @author D�partement informatique
 * @version 07/10/2003
 */

public class Plonge  {
	/* Remarque Importante : utilise un attribut de type g�n�ral Pile (!!!) */
	protected Pile sale;   // les assiettes sales
	protected Pile propre; // les assiettes propres

	/**
	 * Consctructeur � partir de deux piles
	 *@param a pile des assiettes sales
	 *@param b pile des assiettes propres
	 *@return void
	 */
	public Plonge(Pile a, Pile b) {
		sale=a; propre=b; // on se met � la plonge sur les piles fournies
	}// Plonge()
  
	/**
	 * Ajoute une assiette sale
	 *@param n entier repr�sentant l'assiette
	 *@return void
	 */
	public void ajouterUneSale(int n) throws PilePleineException  {
		sale.empiler( new Integer(n) ); // empile une assiette num�rot�e
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
	 *@return num�ro de l'assiette retir�e (type int)
	 */
	public int retirerUnePropre() throws PileVideException {
		Integer i = (Integer)propre.depiler(); //sort une assiette num�rot�e
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
	 * Retourne en sortie �cran le contenu des piles d'assiettes
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
