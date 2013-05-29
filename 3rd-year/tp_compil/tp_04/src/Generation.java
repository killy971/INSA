public class Generation implements GramConstants
{
	public Generation () {}
	
	// fichier html de sortie
	static private java.io.OutputStream fichierSortie = Ecriture.ouvrir("./files/output.html");
	// ArrayList pour num�rotation des para
	static private java.util.ArrayList numerotation = new java.util.ArrayList();
	static private int tempLineSize=0; // taille de la ligne courante (en caract�res)
	static private int precBalisePara=FINPARA; // pr�c�dente balise de type para rencontr�e


	/**
	 * M�thode se chargeant de g�n�rer la balise <hx> de d�but de para
	 * ainsi que le num�ro de paragraphe, en analysant l'ArrayList numerotation.
	 * La m�thode remplace 'x' dans la balise <hx> par le chiffre ad�quate
	 * selon l'imbrication de ce paragraphe.
	 * Note : On utilise ici un ArrayList au lieu d'un Stack car dans Stack
	 * on ne peut pas facilement acc�der aux �l�ments autres que celui en sommet
	 * Mais la m�thode consiste � se servir d'une logique proche de celle de
	 * la pile. 
	 */
	static private void generationNumerotation()
	{
		int taille = numerotation.size();
		int indice = taille-1;
		String balise = "\n<h"+generationHNum(taille)+">\n";
		String num = ((Integer)numerotation.get(indice--)).toString();
		String esp =""; // pour la gestion de l'indentation en source html
		while (indice>=0)
		{
			num = ((Integer)numerotation.get(indice--)).toString()+"."+num;
			esp += "&nbsp;&nbsp;&nbsp;";
		}
		Ecriture.ecrireString(fichierSortie,balise+esp+num);
		// on met � jour la taille de la ligne courante
		tempLineSize += (balise+esp+num).length();
	}

	/**
	 * M�thode calculant le 'x' de <hx> ou </hx> lors du traitement
	 * d'un paragraphe en fonction de sa profondeur.
	 */
	static private int generationHNum(int t)
	{
		if (t<6)
		{
			return t+1;
		}
		else
		{
			return 6;
		}
	}

	/**
	 * M�thode pour g�n�rer le fichier de sortie en fonction du token en cours
	 * Note: la validit� du token dans la grammaire a d�j� �t� v�rifi�e
	 * dans la fonction reconnaitre(int unite) qui fait appel � generation
	 */
	static protected void generationHTML()
	{
		int taille = numerotation.size();
		if (taille==0)
		{
			numerotation.add(new Integer(0));
		}
		int indiceFin = taille-1;
		// System.out.println(numerotation);
		switch(Gram.token.kind)
		{
			case DOC:
				Ecriture.ecrireString(fichierSortie,"<html>\n  <body>\n");
			break;
			case FINDOC:
				Ecriture.ecrireString(fichierSortie,"  </body>\n</html>\n");
			break;
			case TITRE:
				Ecriture.ecrireString(fichierSortie,"    <center><h1>");
			break;
			case FINTITRE:
				Ecriture.ecrireString(fichierSortie,"</h1></center>\n");
			break;
			case DATE:
				Ecriture.ecrireString(fichierSortie,"    <center><h1>");
			break;
			case FINDATE:
				Ecriture.ecrireString(fichierSortie,"</h1></center>\n");
			break;
			case PARA:
				// on remet � 0 le compteur de la taille de la ligne courante
				tempLineSize = 0;
				// gestion de la profondeur des paragraphes
				// int taille = numerotation.size();
				if (precBalisePara == FINPARA)
				{
					// on continue � la m�me profondeur: on incr�ment le num�ro
					int i = ((Integer)numerotation.get(indiceFin)).intValue();
					numerotation.remove(indiceFin);
					i++;
					numerotation.add(new Integer(i));
				}
				else
				if (precBalisePara == PARA)
				{
					// on s'enfonce d'un �tage en profondeur de paragraphe
					numerotation.add(new Integer(1));
				}
				precBalisePara = PARA;
				generationNumerotation();
			break;
			case FINPARA:
				// gestion de la profondeur des paragraphes
				// int taille = numerotation.size();
				if (precBalisePara == PARA)
				{
					// on continue � la m�me profondeur de paragraphe
				}
				else
				if (precBalisePara == FINPARA)
				{
					// on remonte d'un �tage en profondeur de paragraphe
					numerotation.remove(indiceFin);
				}
				precBalisePara = FINPARA;
				taille = numerotation.size(); // mise � jour necessaire
				String balise = "\n</h";
				balise += generationHNum(taille);
				balise += ">";
				Ecriture.ecrireString(fichierSortie,balise);
			break;
			case IDENT:
				// on rajoute un caract�re de retour � la ligne si la taille
				// de la ligne de sortie html courante d�passe 60 caract�res
				if ((tempLineSize+=GramTokenManager.identLu.length()+1)>60)
				{
					tempLineSize = 0;
					Ecriture.ecrireString(fichierSortie,"\n");
				}
				else
				{
					Ecriture.ecrireString(fichierSortie," ");
				}
				Ecriture.ecrireString(fichierSortie,GramTokenManager.identLu);
			break;
			case ENTIER:
				// on v�rifie la date avant de l'�crire
				int date = GramTokenManager.entierLu;
				String dateText = "";
				if(date<1900 || date>2005)
				{
					// si la date n'est pas conforme, elle est affich�e
					// en rouge avec en plus un warning s'affichant
					// lors du survol de la souris sur la date
					dateText += "<font color=red>"+
								"<a title=\"Attention : date non conforme !\">"
								+date+
								"</a></font>";
				}
				else
				{
					dateText += date;
				}
				Ecriture.ecrireString(fichierSortie,dateText);
			break;
			case POINT:
				Ecriture.ecrireString(fichierSortie,".");
				// on met � jour la taille de la ligne courante
				tempLineSize++;
			break;
			case VIRGULE:
				Ecriture.ecrireString(fichierSortie,",");
				// on met � jour la taille de la ligne courante
				tempLineSize++;
			break;
			default:
				// rien

		}
		}	
}