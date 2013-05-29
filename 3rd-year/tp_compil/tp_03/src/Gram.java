/**
 * Classe Gram : analyse grammaticalement le fichier d'entr�e xml et lance
 * la g�n�ration du code html de sortie
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
	 * ce qui suit sont les diff�rentes m�thodes d�coulants de
	 * l'automate � pile cr�� � partir de la grammaire de d�part
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

	// m�thode abstraite de g�n�ration html, d�finie dans la classe Generation
	abstract protected void generationHTML();

}
