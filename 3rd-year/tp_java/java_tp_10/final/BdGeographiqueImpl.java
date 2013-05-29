/**
 * La classe BdGeographiqueImpl est une base de donnée se reposant sur un
 * attribut SimpleListImpl, qui contient des Enregistrement.
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 10 23/11/2004
 */

import sdd.*;

public class BdGeographiqueImpl {

	private SimpleListImpl baseDeDonnee;
	
	public BdGeographiqueImpl() {
		baseDeDonnee = new SimpleListImpl();
	}
	
	/**
	 * Méthode pour vérifier si un enregistrement est présent dans la bdd
	 *@param e : enregistrement de la ville à vérifier
	 *@return boolean : rend true si e est présent dans la bdd
	 */
	public boolean present(Enregistrement e) {
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		try {
			while (!it.sorti()) {
				if (((Enregistrement)it.ec()).equals(e)) {
					return true;
				}
				it.suc();
			}// while
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
		}
		return false;
	}// present(Enregistrement e)
	
	/**
	 * Méthode pour vider la base de donnée
	 *@param void
	 *@return void
	 */
	public void vider() {
		this.baseDeDonnee.vider();
	}// vide()
	
	/**
	 * Méthode pour ajouter un enregistrement dans la bdd
	 *@param e : enregistrement à ajouter
	 *@return void
	 */
	public void ajout(Enregistrement e) throws PresentException {
		if (present(e)) throw new PresentException();
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		try {
			it.ajouter((Enregistrement)e);
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
		}
		return;
	}// ajout(Enregistrement e)
	
	/**
	 * Méthode pour retirer un enregistrement de la bdd
	 *@param e : enregistrement à retirer
	 *@return void
	 */
	public void retrait(Enregistrement e) throws AbsentException {
		if (!present(e)) throw new AbsentException();
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		try {
			while (!it.sorti()) {
				if (((Enregistrement)it.ec()).equals(e)) {
					System.out.println("bouh");
					it.oter();
					return;
				}
				it.suc();
			}
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
		}
	}// retrait(Enregistrement e)
	
	/**
	 * Méthode pour chercher un enregistrement dont le nom de la ville est celui
	 * passé en paramètre.
	 * Lève l'exception AbsentException si aucun enregistrement ne correspond à
	 * cette recherche.
	 *@param v : nom de la ville à rechercher
	 *@return Enregistrement : Enregistrement dont le nom de la ville est v
	 */
	public Enregistrement ville(String v) throws AbsentException {
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		try {
			while (!it.sorti()) {
				if (((Enregistrement)it.ec()).nom().equals(v)) {
					return (Enregistrement)it.ec();
				}
				it.suc();
			}// while
			throw new AbsentException();
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
			System.exit(0);
			return null;
		}
	}// ville(String v)

	/**
	 * Méthode pour chercher un enregistrement dont les coordonnees de la ville
	 * sont ceux passsé en paramètre.
	 * Lève l'exception AbsentException si aucun enregistrement ne correspond à
	 * cette recherche.
	 *@param c : coordonnees de la ville à rechercher
	 *@return Enregistrement : Enregistrement dont les coordonnees sont c
	 */
	public Enregistrement coord(Coordonnees c) throws AbsentException {
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		try {
			while (!it.sorti()) {
				if (((Enregistrement)it.ec()).coordonnees().equals(c)) {
					return (Enregistrement)it.ec();
				}
				it.suc();
			}// while
			throw new AbsentException();
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
			System.exit(0);
			return null;
		}
	}// Enregistrement coord(Coordonnees c)

	/**
	 * Méthode pour retirer de la bdd un enregistrement dont le nom de la ville
	 * est celui passé en paramètre.
	 *@param ville : nom de la ville dont on veut retirer l'enregistrement
	 *@return void
	 */
	public void retrait(String ville) throws AbsentException {
		try {
			retrait(ville(ville));
		}
		catch (AbsentException e) {
			System.out.println(e.getMessage());
		}
	}// retrait(String ville)

	/**
	 * Méthode pour retirer de la bdd un enregistrement dont les coordonnees de 
	 * la ville sont veux passés en paramètre.
	 *@param coord : coordonnees de la ville dont on veut retirer
	 * l'enregistrement
	 *@return void
	 */
	public void retrait(Coordonnees coord) throws AbsentException {
		try {
			retrait(coord(coord));
		}
		catch (AbsentException e) {
			System.out.println(e.getMessage());
		}
	}// retrait(Coordonnees coord)

	/**
	 * Méthode pour calculer la population totale de tous les enregistrements
	 * de la base de donnée
	 *@param void
	 *@return int : nombre d'habitants au total
	 */
	public int population() {
		int populationTotale = 0;
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		try {
			while (!it.sorti()) {
				populationTotale += ((Enregistrement)it.ec()).population();
				it.suc();
			}// while
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
		}
		return populationTotale;
	}// population()

	/**
	 * Méthode pour calculer la distance maximum qui sépare 2 enregistrements
	 * quelconques de la base de donnée
	 *@param void
	 *@return float : distance max séparant 2 villes qconques de la bdd
	 */
	public float distanceMax() {
		float distMax = 0;
		int it2i = 1;
		SimpleListIterator it1 = this.baseDeDonnee.iterateur();
		try {
			while (!it1.sorti()) {
				SimpleListIterator it2 = this.baseDeDonnee.iterateur();
				it2.allera(it2i);
				Enregistrement e = (Enregistrement)it1.ec();
				while (!it2.sorti()) {
					float valeur = e.distance((Enregistrement)it2.ec());
					if (valeur > distMax) distMax=valeur;
					it2.suc();
				}//while
				it1.suc();
				it2i++;
			}// while
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
		}
		return distMax;
	}// distanceMax()	

	/**
	 * Méthode pour calculer la distance minimum qui sépare 2 enregistrements
	 * quelconques de la base de donnée
	 *@param void
	 *@return float : distance min séparant 2 villes qconques de la bdd
	 */
	public float distanceMin() {
		float distMin = 0;
		int it2i = 1;
		SimpleListIterator it1 = this.baseDeDonnee.iterateur();
		try {
			while (!it1.sorti()) {
				SimpleListIterator it2 = this.baseDeDonnee.iterateur();
				it2.allera(it2i);
				Enregistrement e = (Enregistrement)it1.ec();
				while (!it2.sorti()) {
					float valeur = e.distance((Enregistrement)it2.ec());
					if ((distMin==0) || (valeur<distMin)) distMin=valeur;
					it2.suc();
				}//while
				it1.suc();
				it2i++;
			}// while
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
		}
		return distMin;
	}// distanceMin()

	/**
	 * Méthode pour calculer sommairement la surface minimale contenant toutes
	 * les villes de la base (pour simplifier on prendra calculera la surface
	 * du plus petit "rectangle" contenant toutes les villes)
	 *@param void
	 *@return float : surface min contenant toutes les villes
	 */
	public float superficie() {
		float loMax=0,loMin=0,laMax=0,laMin=0;
		try {	
			SimpleListIterator it = this.baseDeDonnee.iterateur();
			loMax = ((Enregistrement)it.ec()).coordonnees().longitude();
			loMin = loMax;
			laMax = ((Enregistrement)it.ec()).coordonnees().latitude();
			laMin = laMax;
			it.suc();
			while (!it.sorti()) {
				float log = ((Enregistrement)it.ec()).coordonnees().longitude();
				float lat = ((Enregistrement)it.ec()).coordonnees().latitude();
				if (log > loMax) loMax = log;
				if (log < loMin) loMin = log;
				if (lat > laMax) laMax = lat;
				if (lat < laMin) laMin = lat; 
				it.suc();
			}// while
		}
		catch (SimpleListException simpleListException) {
			System.out.println(simpleListException.getMessage());
		}
		return (loMax-loMin)*(laMax-laMin);
	}// superficie()

	/**
	 * Méthode pour calculer la densité de population de la bdd
	 *@param void
	 *@return float : densité de la population de la bdd
	 */
	public float densite() {
		return this.population()/this.superficie();
	}// densite()

	public String toString() {
		SimpleListIterator it = this.baseDeDonnee.iterateur();
		String s = "";
		try {
			while (!it.sorti()) {
				s += ((Enregistrement)it.ec()).toString()+"\n";
				it.suc();
			}
		}
		catch (SimpleListException e) {
			System.out.println(e.getMessage());
		}
		return s;
	}// toString()
	
}