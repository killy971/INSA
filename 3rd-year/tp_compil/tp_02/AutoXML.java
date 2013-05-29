import java.io.*;
/**
 * la classe AutoXML concr�tise la classe abstraite Automate
 * pour l'analyse d'un document reconnaissable par un automate
 * fini
 * @author : Marie-Jo P�drono
 * date de cr�ation : 18 mars 2003
 */
public abstract class AutoXML extends Automate {
  
    int e = etatErreur;// � des fins de pr�sentation de la matrice

    //la matrice de transition n� ligne : �tat, n� colonne : unite

  private  final int[][] transition = {
  //autre,doc,fdoc,tit,ftit,date,fdate,para,fpara,ident,entier,pt,virg  //etat
    {e,    1,  e,   e,   e,   e,   e,    e,   e,    e,   e,     e,  e  },//0
    {e,    e,  e,   e,   e,   2,   e,    e,   e,    e,   e,     e,  e  },//1
    {e,    e,  e,   e,   e,   e,   e,    e,   e,    e,   3,     e,  e  },//2
    {e,    e,  e,   e,   e,   e,   4,    e,   e,    e,   e,     e,  e  },//3
    {e,    e,  e,   e,   e,   e,   e,    5,   e,    e,   e,     e,  e  },//4
    {e,    e,  e,   e,   e,   e,   e,    e,   e,    6,   e,     e,  e  },//5
    {e,    e,  e,   e,   e,   e,   e,    e,   8,    6,   e,     7,  7  },//6
    {e,    e,  e,   e,   e,   e,   e,    e,   8,    6,   e,     e,  e  },//7
    {e,    e,  9,   e,   e,   e,   e,    5,   e,    e,   e,     e,  e  } //8

  };
	
  abstract public void faireAction (int etat,int unite);


/**
 * Constructeur AutoXML pour initialisation
 * @param le flot d'entr�e
 * 
 */
    public AutoXML(InputStream flot){ 
	super(flot);
        etatInitial = 0;
	etatFinal = 9;
    }

  /**
   *  Methode qui calcule l'�tat suivant
   *  @param etat : l'�tat courant
   *  @param unite : l'unit� du token courant
   *  @return l'�tat suivant
   */
  int getTransition(int etat, int unite){
    return transition[etat][unite];
  }//getTransition
}//AutoXML
