package Piles;

public class MaPile implements Pile {

	private final int PILESIZE;	// taille de la pile
	private int pileSommet;	// indice du sommet de la pile
	private Object[] tab;	// tableau représentant la pile
	
	public MaPile() {
		PILESIZE = 10;
		pileSommet = 0;
		tab = new Object[PILESIZE];
	}// maPile()
	
	public void viderPile() {
		pileSommet = 0;
	}// viderPile()

	public void empiler(Object obj) {
		if (obj == null) {
			// rien
		} else
		if (pileSommet==(PILESIZE-1)) {
			System.out.println("Empilement impossible: la pile est pleine");
		} else {
			tab[pileSommet] = obj;
			pileSommet++;
		}
	}// empiler()

	public Object depiler() {
		if (pileVide()) {
			System.out.println("Depilement impossible: la pile est vide");
			return null;
		} else {
			pileSommet--; // à vérifier
			return tab[pileSommet];
		}
	}// depiler()

	public Object sommetPile() {
		return tab[pileSommet - 1];
	}// sommetPile()

	public boolean pileVide() {
		return pileSommet == 0;
	}// pileVide()
	
	public String toString() {
		String s = "-----\n";
		if (pileVide()) {
			s += "pile Vide\n";
		} else {
			s += "\n" + tab[pileSommet - 1];
			s += "   <- sommet";
			for (int i = pileSommet - 2; i >= 0; i--) {
				s += "\n" + tab[i];
			}// for
			s += "\n-----\n";
		}
		return s;
	}// toString()
	
}// MaPile
