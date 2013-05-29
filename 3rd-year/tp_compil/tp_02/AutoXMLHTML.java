/*
 * Created on 11 mars 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author elebouvi
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.io.*;

public class AutoXMLHTML extends AutoXML {
	
	private OutputStream fichierSortie;
	
	private  final int[][] action = {
	//autre,doc,fdoc,tit,ftit,date,fdate,para,fpara,ident,entier,pt,virg  //etat
	  {e,    1,  e,   e,   e,   e,   e,    e,   e,    e,   e,     e,  e  },//0
	  {e,    e,  e,   e,   e,   2,   e,    e,   e,    e,   e,     e,  e  },//1
	  {e,    e,  e,   e,   e,   e,   e,    e,   e,    e,   3,     e,  e  },//2
	  {e,    e,  e,   e,   e,   e,   4,    e,   e,    e,   e,     e,  e  },//3
	  {e,    e,  e,   e,   e,   e,   e,    5,   e,    e,   e,     e,  e  },//4
	  {e,    e,  e,   e,   e,   e,   e,    e,   e,    8,   e,     e,  e  },//5
	  {e,    e,  e,   e,   e,   e,   e,    e,   6,    8,   e,     9,  10  },//6
	  {e,    e,  e,   e,   e,   e,   e,    e,   6,    8,   e,     e,  e  },//7
	  {e,    e,  7,   e,   e,   e,   e,    5,   e,    e,   e,     e,  e  } //8
	};
	
	public AutoXMLHTML (InputStream flot,String f)
	{
		super(flot);
		fichierSortie = Ecriture.ouvrir(f);
	}
	
	public void faireAction (int etat,int unite)
	{
		executerAction(action[etat][unite]);
	}
	
	private void executerAction(int a)
	{
		switch(a)
		{
			case 1:
				Ecriture.ecrireString(fichierSortie,"<html>\n<body>\n");
			break;
			case 2:
				Ecriture.ecrireString(fichierSortie,"<center>\n<h1>\n");
			break;
			case 3:
				// on met la date en rouge si elle n'est pas correcte
				int i = lex.entierLu;
				if (i<=2002 && i>=1900)
				{
					Ecriture.ecrireString(fichierSortie,(new Integer(i)).toString());
				}
				else
				{
					Ecriture.ecrireString(fichierSortie,("<font color=red>"+
					new Integer(i)).toString()
					+"</font>");
				}
			break;
			case 4:
				Ecriture.ecrireString(fichierSortie,"</h1>\n</center>\n");
			break;
			case 5:
				Ecriture.ecrireString(fichierSortie,"<h1>\n");
			break;
			case 6:
				Ecriture.ecrireString(fichierSortie,"</h1>\n");
			break;
			case 7:
				Ecriture.ecrireString(fichierSortie,"</body>\n</html>\n");
			break;
			case 8:
				Ecriture.ecrireString(fichierSortie,lex.identLu+" ");
			break;
			case 9:
				Ecriture.ecrireString(fichierSortie,". ");
			break;
			case 10:
				Ecriture.ecrireString(fichierSortie,", ");
			break;
			default:
				Ecriture.ecrireString(fichierSortie,"");
			break;
		}
	}
	
}