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

	public Plonge(Pile a, Pile b) {
		sale=a; propre=b; // on se met � la plonge sur les piles fournies
	}// Plonge()
  
	public void ajouterUneSale(int n) {
		try {
			sale.empiler( new Integer(n) ); // empile une assiette num�rot�e
		}
		catch (Exception e) {
			// rien
		}
	}// ajouterUneSale()

	public void laverUneAssiette() {
		try {
			Object o = sale.depiler();
			System.out.println("Lave assiette "+o);
			propre.empiler(o);
		}
		catch (Exception e) {
			System.out.println("boulay");
		}
	}// laverUneAssiette()

	public int retirerUnePropre() {
		try {
			Integer i = (Integer)propre.depiler(); //sort une assiette num�rot�e
			return i.intValue();
		}
		catch (Exception e) {
			return 0;
		}
	}// retirerUnePropre()

	public void toutVider() {
		sale.viderPile();
		propre.viderPile();
	}// toutVider()

	public String toString() {
		return "Situation de la plonge :\n"+sale+propre;
	}// toString()

	public static void main(String[] args) {
		Plonge p = new Plonge( new maPile(), new maPile() );

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
