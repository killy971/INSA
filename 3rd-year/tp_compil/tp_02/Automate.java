import java.io.*;
/**
 * la classe Automate modélise un automate abstrait
 * @author : Marie-Jo Pédrono
 * date de création : 18 mars 2003
 */
 abstract class Automate {

    TokenManager lex;// l'analyseur lexical
    public  Token token;// le token courant

   int etatInitial ; //états spécifiques de l'automate
   int etatFinal ;
   int etatErreur = -1;

/**
 * Constructeur Automate pour initialisation
 * appel le constructeur d'analyse lexicale
 * @param le flot d'entrée
 * 
 */
   Automate (InputStream flot){
       lex=new TokenManager(flot);
   }

 
 	
   abstract public void faireAction (int etat,int unite);
  /**
   *  Methode qui fait tourner l'automate
   *  
   */
  void analyse() throws AnalyseException{
      int etat=etatInitial;
      while ( etat!=etatFinal && etat!=etatErreur ){
          token =  lex.getToken();
		faireAction(etat,token.unite);
          etat= getTransition(etat, token.unite);
      }
      if (etat==etatErreur){
	  throw new AnalyseException( "erreur sur le token " + token);
      }
  }
              
 /**
   *  Methode astraite qui calcule l'état suivant
   *  @param etat : l'état courant
   *  @param unite : l'unité du token courant
   *  @return l'état suivant
   */
 
   abstract int getTransition(int etat, int unite);
 }//Automate
