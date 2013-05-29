/**
 * La classe HoraireDeCours sert à representer une horaire sous la forme d'un
 * couple d'attributs correspondant au jour et à l'heure de début
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 12 23/11/2004
 */

public class HoraireDeCours {
	private String jour;
	private int heure; // heure de début
	
	public HoraireDeCours(String j, int h) {
		this.jour = j;
		this.heure = h;
	}// HoraireDeCours(String j, int h)
	
	/**
	 * Méthode d'accès à l'attribut jour
	 *@param void
	 *@return String : jour
	 */
	public String getJour() {
		return this.jour;
	}// getJour()
	
	/**
	 * Méthode d'accès à l'attribut heure
	 *@param void
	 *@return int : heure de début
	 */
	public int getHeure() {
		return this.heure;
	}// getHeure()
	
	/**
	 * Méthode permettant de vérifier si la plage horaire donnée en paramètre
	 * ne va pas recouvrir la plage horaire de l'horaire courante.
	 * On suppose que les enseignements ne travaillent pas la nuit.
	 * Par convention on prendra comme horaires possibles:
	 * de 8h le matin à 18h le soir
	 *@param e : HoraireDeCours
	 *@return boolean : rend true si les horaires sont compatibles, false sinon
	 */
	public boolean compatible(HoraireDeCours e) {
		if (this.jour.equals(e.getJour())) {
			if (Math.abs(this.heure-e.getHeure())<2) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}// compatible(HoraireDeCours e)
	
	/**
	 * Méthode pour vérifier si l'horaire passée en paramètre est égale à
	 * l'horaire courante.
	 * Surcharge de la méthode equals()
	 *@param h : horaire à comprarer avec l'horaire courante
	 *@return boolean : rend true si les deux horaires sont éuqivalentes
	 */
	public boolean equals(HoraireDeCours h) {
		return (this.jour.equals(h.getJour())&&(this.heure==h.getHeure()));
	}// equals(HoraireDeCourse h)
	
	/**
	 * Méthode pour vérifier si l'horaire passée en paramètre est égale à
	 * l'horaire courante.
	 * Redifition de la méthode equals(Object o) de la classe Object
	 *@param h : horaire à comprarer avec l'horaire courante
	 *@return boolean : rend true si les deux horaires sont éuqivalentes
	 */
	public boolean equals(Object o) {
		return (this.jour.equals(((HoraireDeCours)o).getJour())
		&&(this.heure==((HoraireDeCours)o).getHeure()));
	}// equals(Object o)
	
	/**
	 * Méthode pour afficher sous forme textuelle une horaire
	 *@param void
	 *@return String : horaire, sous la forme "jour - heure de début"
	 */
	public String toString() {
		String sHeure = "";
		if (heure<10) {
			sHeure = "0";
		}
		sHeure += this.heure;
		return jour+" - "+sHeure+"h";
	}// toString()
	
	public static void main(String args[]) {
		
		HoraireDeCours h1 = new HoraireDeCours("Lundi",8);
		HoraireDeCours h2 = new HoraireDeCours("Mardi",8);
		HoraireDeCours h3 = new HoraireDeCours("Lundi",9);
		HoraireDeCours h4 = new HoraireDeCours("Mardi",9);
		HoraireDeCours h5 = new HoraireDeCours("Lundi",8);
		
		System.out.println(h1.equals(h1));
		System.out.println(h1.equals(h2));
		System.out.println(h1.equals(h3));
		System.out.println(h1.equals(h4));
		System.out.println(h1.equals(h5));
		System.out.println(h5.equals(h1));
		
	}
}// HoraireDeCours
