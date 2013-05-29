/**
 * Interface Pile.
 */

package Piles;

public interface Pile {                 
	public Object depiler() throws Exception;
	public void empiler(Object item) throws Exception;
	public boolean pileVide();
	public Object sommetPile();
	public void viderPile();
}
