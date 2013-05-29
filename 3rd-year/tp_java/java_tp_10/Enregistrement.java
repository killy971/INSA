/**
 * 
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 10 23/11/2004
 */

public class Enregistrement {
	
	private String nom;
	private Coordonnees coordonnees;
	private int population;
	
	public Enregistrement(String n, Coordonnees c, int p) {
		this.nom = n;
		this.coordonnees = c;
		this.population = p;
	}// Enregistrement()
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public String nom() {
		return this.nom;
	}// nom()
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public Coordonnees coordonnees() {
		return this.coordonnees;
	}// coordonnees()
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public int population() {
		return this.population;
	}// population()
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public String toString() {
		String s = "Nom: "+this.nom+"\n";
		s += this.coordonnees.toString()+"\n";
		s += "Population: "+this.population;
		return s;
	}// toString()
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public boolean equals(Enregistrement e) {
		return    (this.nom.equals(e.nom))
				&&(this.coordonnees.equals(e.coordonnees))
				&&(this.population == e.population);
	}
	
	public static void main(String args[]) {
		
		Enregistrement e1 = new Enregistrement("Basse-Terre",
		new Coordonnees(21,41),40000);
		System.out.println(e1);
		
	}
}