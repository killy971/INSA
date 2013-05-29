/**
 * La classe BdGeographiqueImpl est une base de donn�e se reposant sur un
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
	 * M�thode pour v�rifier si un enregistrement est pr�sent dans la bdd
	 *@param e : enregistrement de la ville � v�rifier
	 *@return boolean : rend true si e est pr�sent dans la bdd
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
	 * M�thode pour vider la base de donn�e
	 *@param void
	 *@return void
	 */
	public void vider() {
		this.baseDeDonnee.vider();
	}// vide()
	
	/**
	 * M�thode pour ajouter un enregistrement dans la bdd
	 *@param e : enregistrement � ajouter
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
	 * M�thode pour retirer un enregistrement de la bdd
	 *@param e : enregistrement � retirer
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
	 * M�thode pour chercher un enregistrement dont le nom de la ville est celui
	 * pass� en param�tre.
	 * L�ve l'exception AbsentException si aucun enregistrement ne correspond �
	 * cette recherche.
	 *@param v : nom de la ville � rechercher
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
	 * M�thode pour chercher un enregistrement dont les coordonnees de la ville
	 * sont ceux passs� en param�tre.
	 * L�ve l'exception AbsentException si aucun enregistrement ne correspond �
	 * cette recherche.
	 *@param c : coordonnees de la ville � rechercher
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
	 * M�thode pour retirer de la bdd un enregistrement dont le nom de la ville
	 * est celui pass� en param�tre.
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
	 * M�thode pour retirer de la bdd un enregistrement dont les coordonnees de 
	 * la ville sont veux pass�s en param�tre.
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
	 * M�thode pour calculer la population totale de tous les enregistrements
	 * de la base de donn�e
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
	 * M�thode pour calculer la distance maximum qui s�pare 2 enregistrements
	 * quelconques de la base de donn�e
	 *@param void
	 *@return float : distance max s�parant 2 villes qconques de la bdd
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
	 * M�thode pour calculer la distance minimum qui s�pare 2 enregistrements
	 * quelconques de la base de donn�e
	 *@param void
	 *@return float : distance min s�parant 2 villes qconques de la bdd
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
	 * M�thode pour calculer sommairement la surface minimale contenant toutes
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
	 * M�thode pour calculer la densit� de population de la bdd
	 *@param void
	 *@return float : densit� de la population de la bdd
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