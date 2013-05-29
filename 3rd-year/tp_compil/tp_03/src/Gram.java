/**
 * Classe Gram : analyse grammaticalement le fichier d'entrée xml et lance
 * la génération du code html de sortie
 * @author Elodie Lebouvier
 * @author Guillaume Nargeot
 */

import java.io.*;

public abstract class Gram extends Analyse implements Constants {
	
	public Gram (InputStream flot)
	{
		super(flot);
	}

	/**
	 * ce qui suit sont les différentes méthodes découlants de
	 * l'automate à pile créé à partir de la grammaire de départ
	 */

	protected void document() throws AnalyseException
	{
		reconnaitre(DOC);
		titre();
		date();
		paragraphe();
		suitePara();
		reconnaitre(FINDOC);
	}

	private void titre() throws AnalyseException
	{
		reconnaitre(TITRE);
		reconnaitre(IDENT);
		suiteIdent();
		reconnaitre(FINTITRE);
	}

	private void date() throws AnalyseException
	{
		reconnaitre(DATE);
		reconnaitre(ENTIER);
		reconnaitre(FINDATE);
	}

	private void paragraphe() throws AnalyseException
	{
		reconnaitre(PARA);
		reconnaitre(IDENT);
		ponct();
		suiteIdentit();
		suitePara();
		reconnaitre(FINPARA);
	}

	private void suitePara() throws AnalyseException
	{
		switch(token.unite)
		{
			case PARA:
				paragraphe();
				suitePara();
			break;
			case FINPARA:
			case FINDOC:
				// rien
			break;
			default:
				// rien
		}
	}

	private void suiteIdent() throws AnalyseException
	{
		switch(token.unite)
		{
			case IDENT:
				reconnaitre(IDENT);
				suiteIdent();
			break;
			case FINTITRE:
				// rien
			break;
			default:
				// rien
		}
	}

	private void suiteIdentit() throws AnalyseException
	{
		switch(token.unite)
		{
			case IDENT:
				reconnaitre(IDENT);
				ponct();
				suiteIdentit();
			break;
			case PARA:
			case FINPARA:
				// rien
			break;
			default:
				// rien
		}
	}

	private void ponct() throws AnalyseException
	{
		switch(token.unite)
		{
			case VIRGULE:
				reconnaitre(VIRGULE);
			break;
			case POINT:
				reconnaitre(POINT);
			break;
			default:
				// rien
		}
	}

	// méthode abstraite de génération html, définie dans la classe Generation
	abstract protected void generationHTML();

}
