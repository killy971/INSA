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
	 * M�thode d'acc�s � la longitude des coordonn�es
	 *@param 
	 *@return 
	 */
	public float longitude() {
		return this.longitude;
	}// longitude()
	
	/**
	 * M�thode d'acc�s � la latitude des coordonn�es
	 *@param 
	 *@return 
	 */
	public float latitude() {
		return this.latitude;
	}// latitude()

	/**
	 * M�thode rendant une string contenant les coordonn�es
	 *@param void
	 *@return String : coordonn�es
	 */
	public String toString() {
		return "Coordonn�es: (longitude:"+this.longitude+",latitude: "+
		this.latitude+")";
	}

	/**
	 * M�thode qui v�rifie l'�galit� de 2 couples de coordonn�es
	 *@param void
	 *@return boolean : vrai si les coordonn�es c sont �gaux aux coordonn�es
	 * courants
	 */
	public boolean equals(Coordonnees c) {
		return    (this.longitude == c.longitude)
				&&(this.latitude == c.latitude);
	}
		
}// Coordonnees