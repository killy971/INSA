/*
 * Created on 4 mars 2005
*/

/**
*La classe Token permet de mod�liser un "token"
*@author Guillaume Nargeot
*@author Elodie Lebouvier
*@version TP1, 04/03/05
*/
public class Token implements Constants {

	/**
	*Type d'unit� lexicale
	*@see #unite
	*/
	public String lexeme;

	/**
	*Unit� lexicale du lexeme
	*@see #lexeme
	*/
	public int unite;

	/**
	*Numero de ligne du fichier source d'o� est extrait le lexeme
	*/
	public int ligne;
	
	/**
	*Renvoie le token sous forme de chaine de caract�res
	*return String la chaine de caract�res repr�sent�e par le token
	*/
	public String toString() {
		return "lexeme = "+lexeme+" - unite="+unite+" - ligne n�"+ligne;
	}
}
