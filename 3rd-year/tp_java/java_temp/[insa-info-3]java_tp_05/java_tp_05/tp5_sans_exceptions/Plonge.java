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

	public Plonge(Pile a, Pile b) {
		sale=a; propre=b; // on se met à la plonge sur les piles fournies
	}// Plonge()
  
	public void ajouterUneSale(int n) {
		sale.empiler( new Integer(n) ); // empile une assiette numérotée
	}// ajouterUneSale()

	public void laverUneAssiette() {
			Object o = sale.depiler();
			System.out.println("Lave assiette "+o);
			propre.empiler(o);
	}// laverUneAssiette()

	public int retirerUnePropre() {
			Integer i = (Integer)propre.depiler(); //sort une assiette numérotée
			return i.intValue();
	}// retirerUnePropre()

	public void toutVider() {
		sale.viderPile();
		propre.viderPile();
	}// toutVider()

	public String toString() {
		return "Situation de la plonge :\n"+sale+propre;
	}// toString()

	public static void main(String[] args) {
		Plonge p = new Plonge( new MaPile(), new MaPile() );

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
	}// main()
}// Plonge
