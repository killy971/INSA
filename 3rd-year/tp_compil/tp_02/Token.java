/*
 * Created on 4 mars 2005
*/

/**
*La classe Token permet de modéliser un "token"
*@author Guillaume Nargeot
*@author Elodie Lebouvier
*@version TP1, 04/03/05
*/
public class Token implements Constants {

	/**
	*Type d'unité lexicale
	*@see #unite
	*/
	public String lexeme;

	/**
	*Unité lexicale du lexeme
	*@see #lexeme
	*/
	public int unite;

	/**
	*Numero de ligne du fichier source d'où est extrait le lexeme
	*/
	public int ligne;
	
	/**
	*Renvoie le token sous forme de chaine de caractères
	*return String la chaine de caractères représentée par le token
	*/
	public String toString() {
		return "lexeme = "+lexeme+" - unite="+unite+" - ligne n°"+ligne;
	}
}
