/**
 * La classe Maillon représente des maillons, qui serviront pour la structure de
 * donnée ListeDbleImpl
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 8 10/11/2004
 */

public class Maillon {
	
	// maillons successeur et précdécesseur directs du maillon
	private Maillon suivant, precedent;
	// valeur du maillon
	private Object valeur;
    
    /**
     * Retourne le maillon suivant du maillon courant
     *@param void
     *@return Maillon
     */
	public Maillon getSuivant() { return this.suivant; }
	
    /**
     * Fixe le maillon suivant du maillon courant
     *@param 
     *@return 
     */
	public void setSuivant(Maillon m) { this.suivant = m; }
	
    /**
     * Retourne le maillon précédent du maillon courant 
     *@param void
     *@return Maillon
     */
	public Maillon getPrecedent() { return this.precedent; }
	
    /**
     * Fixe le maillon précédent du maillon courant
     *@param void
     *@return Maillon
     */
	public void setPrecedent(Maillon m) { this.precedent = m; }
	
    /**
     * Retourne la valeur du maillon courant
     *@param void
     *@return Object
     */
	public Object getValeur() { return this.valeur; }
	
    /**
     * Fixe la valeur du maillon courant
     *@param Object
     *@return void
     */
	public void setValeur(Object e) { this.valeur = e; }
	
    /**
     * Méthode pour l'affichage d'un maillon
     *@param void
     *@return String
     */
	public String toString() {
		return "Valeur (precendent,courant,suivant): "+"("+
		(Integer)this.getPrecedent().getValeur()+","+(Integer)this.getValeur()+
		","+this.getSuivant().getValeur()+")";
	}
	
	public static void main(String args[]) {
		Maillon m1 = new Maillon();
		Maillon m2 = new Maillon();
		Maillon m3 = new Maillon();
		m1.setValeur(new Integer(1));
		m2.setValeur(new Integer(2));
		m3.setValeur(new Integer(3));
		m2.setPrecedent(m1);
		m2.setSuivant(m3);
		System.out.println(m2);
	}
}// Maillon

/*

Valeur (precendent,courant,suivant): (1,2,3)

*/