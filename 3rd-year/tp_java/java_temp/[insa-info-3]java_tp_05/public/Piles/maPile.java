package Piles;

public class maPile implements Pile {

	private int pileSize;	// taille de la pile
	private int pileSommet;	// indice du sommet de la pile
	private Object[] tab;	// tableau représentant la pile
	
	public maPile() {
		pileSize = 10;
		pileSommet = 0;
		tab = new Object[pileSize];
	}// maPile()
	
	public void viderPile() {
		pileSommet = 0;
	}// viderPile()

	public void empiler(Object obj) throws Exception {
		if (pileSommet==pileSize) {
			throw new Exception("Empilement impossible: la pile est pleine");
		} else {
			tab[pileSommet] = obj;
			pileSommet++;
		}
	}// empiler()

	public Object depiler() throws Exception {
		if (pileVide()) {
			throw new Exception("Depilement impossible: la pile est vide"); 
		} else {
			pileSommet--;
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
			s += "pile Vide";
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
	
}// maPile