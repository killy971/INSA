/**
 * La classe Enregistrement repr�sente l'enregistrement d'une ville, avec
 * 3 attributs priv�s: son nom (String), ses coordonn�es (Coordonnees) et sa
 * population (int).
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
	 * M�thode d'acc�s � l'attribut nom
	 *@param void
	 *@return String : nom de la ville de l'enregistrement
	 */
	public String nom() {
		return this.nom;
	}// nom()
	
	/**
	 * M�thode d'acc�s � l'attribut coordonnees
	 *@param void
	 *@return Coordonnees : coordonnees de l'enregistrement
	 */
	public Coordonnees coordonnees() {
		return this.coordonnees;
	}// coordonnees()
	
	/**
	 * M�thode d'acc�s � l'attribut population
	 *@param void
	 *@return int : population de l'enregistrement
	 */
	public int population() {
		return this.population;
	}// population()
	
	/**
	 * M�thode pour calculer la distance entre l'enregistrement courant et
	 * l'enregistrement pass� en param�tre
	 *@param e : Enregistrement
	 *@return float : distance entre e et this
	 */
	public float distance(Enregistrement e) {
		float xa,ya,xb,yb;
		xa = this.coordonnees.latitude();
		ya = this.coordonnees.longitude();
		xb = e.coordonnees.latitude();
		yb = e.coordonnees.longitude();
		return (float)java.lang.Math.sqrt((double)((xb-xa)*(xb-xa)+(yb-ya)*(yb-ya)));
	}// distance()
	
	/**
	 * M�thode pour afficher un enregistrement
	 *@param void
	 *@return String: sous la forme "Nom: **\n Coordonnees: **\n Population: **"
	 */
	public String toString() {
		String s = "Nom: "+this.nom+"\n";
		s += this.coordonnees.toString()+"\n";
		s += "Population: "+this.population;
		return s;
	}// toString()
	
	/**
	 * M�thode pour v�rifier l'�galit� entre 2 enregistrement
	 *@param o : enregistrement
	 *@return boolean : rend true si o et this sont �gaux
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Enregistrement)) {
			return false;
		}
		return    (this.nom.equals(((Enregistrement)o).nom))
				&&(this.coordonnees.equals(((Enregistrement)o).coordonnees))
				&&(this.population == ((Enregistrement)o).population);
	}
	
	public static void main(String args[]) {
		Enregistrement e1 = new Enregistrement("Basse-Terre",
		new Coordonnees(21,41),40000);
		System.out.println(e1);
	}
}