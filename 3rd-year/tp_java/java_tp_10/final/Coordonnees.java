/**
 * La classe Coordonnee représente un couple de coordonnees qui sont ses
 * attributs longitude et latitude
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 10 23/11/2004
 */

public class Coordonnees {
	
	private float longitude;
	private float latitude;

	public Coordonnees(float lo, float la) {
		this.longitude = lo;
		this.latitude = la;
	}// Coordonnees(float lo, float la)

	/**
	 * Méthode d'accès à la longitude des coordonnées
	 *@param 
	 *@return 
	 */
	public float longitude() {
		return this.longitude;
	}// longitude()
	
	/**
	 * Méthode d'accès à la latitude des coordonnées
	 *@param 
	 *@return 
	 */
	public float latitude() {
		return this.latitude;
	}// latitude()

	/**
	 * Méthode rendant une string contenant les coordonnées
	 *@param void
	 *@return String : coordonnées
	 */
	public String toString() {
		return "Coordonnées: (longitude : "+this.longitude+",latitude : "+
		this.latitude+")";
	}

	/**
	 * Méthode qui vérifie l'égalité de 2 couples de coordonnées
	 *@param void
	 *@return boolean : vrai si les coordonnées o sont égaux aux coordonnées
	 * courants
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Coordonnees)) {
			return false;
		}
		return    (this.longitude == ((Coordonnees)o).longitude)
				&&(this.latitude == ((Coordonnees)o).latitude);
	}
		
}// Coordonnees