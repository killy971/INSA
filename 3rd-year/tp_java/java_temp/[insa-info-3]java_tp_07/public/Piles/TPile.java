/**
* Definition de la mise en oeuvre de la Pile d'objets
* par tableau
* auteur Jean-Louis Pazat
* version 1
* date de creation : 28 30/9/99
* derniere modification : 01/10/01 (suppression des tests)
*/
package Piles;
public class TPile implements Pile {    // Array based stack class
    
    private static final int tailleMax = 10;
    
    private int taille;        // Maximum size of this stack
    private int sommet;        // Index for top Object
    private Object [] tab;     // Array holding stack Objects
    
//    static private void erreur(String s){ 
    // System.out.println("****************************");
    //System.out.println("* "+s);
    //System.out.println("****************************");
    //}
    
    public TPile() { 
	taille = tailleMax;  
	sommet = 0; 
	tab = new Object[taille]; 
    }
    
    public void viderPile()              // Remove all Objects from stack
    { sommet = 0; }
    
    public void empiler(Object it) {    // Push Object onto stack
	// if (sommet==taille) {
	//    erreur("Stack overflow dans empiler");
	//    return;
	//}
	tab[sommet] = it;
	sommet++;
    }
    
    public Object depiler() {            // Pop Object from top of stack
	//if (pileVide()){
	//    erreur("Empty stack dans depiler");
	//    return null;
	//}
	sommet--;
	return tab[sommet];
    }
    
    public Object sommetPile() {       // Return value of top Object
        //if (pileVide()){
        //    erreur("Empty stack dans sommet");
        //    return null;
	//}
	return tab[sommet-1];
    }
    
    public boolean pileVide()         // Return true if stack is empty
    { return sommet == 0; }

    public String toString(){    // représente la valeur de la pile
	String s = "-----\n";
	if (pileVide()) 
	    s+="pile Vide";
	else
	{
	    s+="\n"+tab[sommet-1];
	    s+="   <- sommet";
	    for (int i = sommet-2;i>=0;i--)
		s+="\n"+tab[i];
	    s+="\n-----\n";
	}
	return s;
    }
public static void main (String[] args) {
    TPile wonder = new TPile();
    wonder.empiler(new Integer(1));
    System.out.println(wonder);
    wonder.empiler(new Integer(2));
    System.out.println(wonder);
    wonder.empiler(new Integer(3));
    System.out.println(wonder);
}
} // class TPile
