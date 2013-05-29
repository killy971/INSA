/*
 * Created on 4 mars 2005
*/

/**
*La classe TokenManager permet de générer des "token"
*@author Guillaume Nargeot
*@author Elodie Lebouvier
*@version TP1, 04/03/05
*/
import java.io.*;

public class TokenManager implements Constants
{

	/**
	*Type d'unité lexicale
	*/
	public String identLu;

	/**
	*Type d'unité lexicale
	*/
	public int entierLu;

	/**
	*Type d'unité lexicale
	*/
	private String lexeme;

	/**
	*Type d'unité lexicale
	*/
	private int ligne;

	/**
	*Type d'unité lexicale
	*/
	private InputStream flot;

	/**
	*Type d'unité lexicale
	*/
	private char carLu;
	

	/**
	*Constructeur de la classe qui initialise le flot et le numero de ligne et lit le premier caractère
	*/
	public TokenManager(InputStream f) {
		flot=f;
		ligne=1;
		carLu=Lecture.lireChar(flot);
	}
	
	/**
	*Méthode qui choisit quelle méthode appeler en fonction du caractère carLu (ce caractère va definir le type de lexeme en cours de lecture)
	*@return int l'unité lexicale du lexeme en cours de lecture
	*/
	private int lireUnite()
	{
		int temp_unite;
		if (Character.isWhitespace(carLu))
		{
			lireCarLu();
			temp_unite = lireUnite();
		}
		else if (carLu=='<')
		{
			temp_unite = lireBalise();
			lireCarLu();
		}
		else if (Character.isLetter(carLu))
		{
			lireIdent();
			temp_unite=IDENT;
			identLu = lexeme;
		}
		else if (Character.isDigit(carLu))
		{
			lireEntier();
			temp_unite=ENTIER;
		}
		else if (carLu=='.')
		{
			lexeme="";
			lexeme+=carLu;
			temp_unite=POINT;
			lireCarLu();
		}
		else if (carLu==',')
		{
			lexeme="";
			lexeme+=carLu;
			temp_unite=VIRGULE;
			lireCarLu();
		}
		else if (carLu=='\0')
		{
			lexeme="<EOF>";
			temp_unite=EOF;
		}
		else
		{
			temp_unite=AUTRE;
			lireCarLu();
		}
		return temp_unite;
	}

	/**
	*Méthode qui lit le caractère suivant dans le fichier. Si c'est un retour à la ligne, on incrémente le compteur ligne.
	*/
	private void lireCarLu()
	{    
		if(Lecture.finFichier(flot))
		{
			carLu='\0';
		}
		else
		{
			carLu=Lecture.lireChar(flot);
			if (carLu=='\n')
			{
				ligne++;
			}
		}
	}

	/**
	*Méthode qui lit un identificateur
	*/
	private void lireIdent()
	{
		lexeme="";
		while(Character.isLetter(carLu))
		{
			lexeme+=carLu;
			lireCarLu();
		}
	}
	
	/**
	*Méthode qui lit un entier et initialise entierLu
	*/
	private void lireEntier()
	{
		lexeme="";
		while(Character.isDigit(carLu))
		{
			lexeme+=carLu;
			lireCarLu();
		}
		entierLu = Integer.parseInt(lexeme);
	}
	
	/**
	*Méthode qui lit une balise et definit l'unité lexicale de la balise
	*/
	private int lireBalise()
	{
		int temp_unite;
		boolean fin=false;
		lireCarLu();
		if (carLu=='/')
		{
			fin=true;
			lireCarLu();
		}
		String temp_balise="";
		while(carLu!='>') {
			temp_balise+=carLu;
			lireCarLu();
		}
		if(temp_balise.equals("doc"))
		{
			temp_unite=DOC;
		}
		else if(temp_balise.equals("titre"))
		{
			temp_unite=TITRE;
		}
		else if(temp_balise.equals("date"))
		{
			temp_unite=DATE;
		}
		else if(temp_balise.equals("para"))
		{
			temp_unite=PARA;
		}
		else
		{
			temp_unite=AUTRE;
			
		}
		if (temp_unite==0) {
			
		}
		if (fin)
		{
			temp_unite++;
			lexeme="</"+temp_balise;
			if (temp_unite!=0) lexeme+=">";
		}
		else {
			lexeme="<"+temp_balise;
			if (temp_unite!=0) lexeme+=">";
		}
		return temp_unite;
	}
	
	/**
	*Méthode qui crée un token que l'on lit dans le fichier passé en argument du main
	*@return Token le token lu
	*/
	public Token getToken()
	{
		Token t = new Token();
		t.unite = lireUnite();
		t.lexeme = lexeme;
		t.ligne = ligne;
		return t;
	}
	

	/**
	*Méthode principale qui ouvre le fichier passé en paramètre de la méthode, crée un TokenManager sur ce fichier.
	*/
	public static void main(String args[])
	{
		String nomFichier = args[0];
		InputStream flot = Lecture.ouvrir(nomFichier);
		TokenManager l = new TokenManager(flot);
		Token t;
		
		t = l.getToken();
		
		while (t.unite!=EOF)
		{
			System.out.println(t.toString());
			t = l.getToken();
		}
	}
}
