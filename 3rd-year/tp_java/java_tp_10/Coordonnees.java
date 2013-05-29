/**
 * 
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
		return "Coordonnées: (longitude:"+this.longitude+",latitude: "+
		this.latitude+")";
	}

	/**
	 * Méthode qui vérifie l'égalité de 2 couples de coordonnées
	 *@param void
	 *@return boolean : vrai si les coordonnées c sont égaux aux coordonnées
	 * courants
	 */
	public boolean equals(Coordonnees c) {
		return    (this.longitude == c.longitude)
				&&(this.latitude == c.latitude);
	}
		
}// Coordonnees