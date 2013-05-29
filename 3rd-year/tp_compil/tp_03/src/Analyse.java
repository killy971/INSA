/**
 * Classe Analyse : lance le traitement du fichier xml d'entrée
 * @author Elodie Lebouvier
 * @author Guillaume Nargeot
 */

import java.io.*;
import java.util.Date;

public abstract class Analyse implements Constants  {
 
	TokenManager lex;
	public Token token;

	public Analyse(InputStream flot) {
		lex = new TokenManager(flot);
		token =  lex.getToken();
	}

    // cette méthode valide le token courant, génère le code html,
    // et lit le token suivant
	public void reconnaitre(int unite) throws AnalyseException
	{
		if(token.unite==unite)
		{
			generationHTML(); // génération du code html en sortie
			avance();
		}
		else
		{
			throw new AnalyseException("erreur "+images[unite]+" attendu au lieu de "+token);
		}
	}
	
	// cette méthode fait avancer au token suivant
	public void avance()
	{
		token = lex.getToken();	
	}

	// méthode abstraite de génération html, définie dans la classe Generation
	abstract protected void generationHTML();

	public static void main(String args[]) {
   
		InputStream flot;
		if (args.length==2)
		{
			System.out.print(args[0] + ": ");
			try {
				flot  = new FileInputStream(args[0]);
			}
			catch (FileNotFoundException e) {
				System.out.println("Fichier introuvable.");
				return;
			}
		}
		else
		{
			System.out.println("Usage: java Analyse [fichier in] [fichier out]");
			return;
		}
		try
		{
			Generation analyseur = new Generation(flot,args[1]);
			Date startDate = new Date();
			analyseur.document();
			Date endDate = new Date();
			long execTime = endDate.getTime() - startDate.getTime();
			System.out.println("analyse reussie!\n");
			if (execTime<1000)
			{
				System.out.println("Temps de traitement : "+execTime+" ms");
			}
			else
			{
				System.out.println("Temps de traitement : "+execTime/1000+" s");
			}
			
		}
		catch (AnalyseException e)
		{
			String msg = e.getMessage();
			System.out.println("Erreur de syntaxe : \n"+msg);
			e.printStackTrace();
		}
	}
   
}//Analyse

class AnalyseException extends Exception{
	AnalyseException (String message)
	{
		super(message);
    }
}
