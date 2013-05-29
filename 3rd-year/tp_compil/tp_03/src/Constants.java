/*
 * Analyseur lexical
 * @author I. Leplumey, INSA dépt. informatique
 */


public interface  Constants {
//============= Unités lexicales =============
      int AUTRE   =0;
      int DOC     =1;
      int FINDOC  =2;
      int TITRE   =3;
      int FINTITRE=4;
      int DATE    =5;
      int FINDATE =6;
      int PARA    =7;
      int FINPARA =8;
      int IDENT   =9;
      int ENTIER  =10;
      int POINT   =11;
      int VIRGULE =12;
      int EOF     =13;

    // Liste des images 
      String[] images ={
       "<autre>", "<doc>","</doc>","<titre>","</titre>",
       "<date>","</date>","<para>","</para>","<ident>",
       "<entier>",".",",","<eof>"
    }; 
       int DEBBALISE = 1;//intervalle des balises 
       int FINBALISE = 8; 
}//interface
