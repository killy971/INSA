/**
 * Interface Pile.
 */

package Piles;

public interface Pile {                 
	public Object depiler() ;
	public void empiler(Object item);
	public boolean pileVide();
	public Object sommetPile();
	public void viderPile();
}