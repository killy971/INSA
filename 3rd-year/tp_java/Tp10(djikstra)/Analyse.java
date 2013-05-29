import java.io.*;
    /** analyseur qui reconnait un arbre genealogique
     *  decrit dans un fichier
     *  @author Etudiant
     *  @see Graphe
     *  @see Dico
     */
public class Analyse{
  
   
    //les attributs pour l'analyse
    private  char  carLu;     // la tete de lecture
    private InputStream flot; // le flot de lecture
    private String nomLu;     // l'attribut associe au nom lu

    //les attributs pour la génération du graphe
    private Graphe leGraphe;
    private Dico leDico;
    private int mere; //pour mémoriser en cours d'analyse
    private int pere;
    private int enfant;
     
    /** cree une instance de l'analyse 
     * <BR>cree le flot de lecture associe au fichier
     * <BR>positionne la tete de lecture sur le premier caractere
     * @author Etudiant
     * @param le nom du fichier à analyser
     */
	
    public Analyse(String nomFich){
      flot=Lecture.ouvrir(nomFich);
      getChar();

      //pour la génération
      leGraphe=new Graphe(20);//20 sommets maxi
      leDico=new Dico(20);
    }//Analyse

   /** affiche le message d'erreur et quitte définitivement 
     * le programme 
     * @author Etudiant
     * @param message la chaine a afficher
     */
    private  void erreur(String message){
	System.out.println(message);
	System.exit(2);
    }

    /** avance la tete de lecture en ignorant les blancs et les
     * retour-chariot 
     * <BR> affiche le contenu du fichier lu
     * @author Etudiant
     */
    private void getChar(){
	carLu=Lecture.lireChar(flot);
	System.out.print(""+carLu);
	while (carLu==' ' || carLu=='\n'){	   
	    carLu=Lecture.lireChar(flot);
	    System.out.print(""+carLu);
	}
    }//getChar 

    /** verifie que le caractere c est une lettre minuscule ou majuscule 
     * @author Etudiant
     * @param  c caractere analyse
     * @return le resultat de la comparaison
     */
    private boolean lettre(char c){
	return (c>='A'&&c<='Z') || (c>='a' && c<='z');
    }//lettre

    /** verifie que la tete de lecture carLu contient le caractere 
     * attendu <BR>si oui avance la tete de lecture,<BR> sinon le 
     * programme se termine en affichant l'erreur
     * @author Etudiant
     * @param  c caractere attendu
     */
    private void reconnaitre(char c){
	if (carLu!=c){erreur ("caractere attendu : "+c);}
	getChar();
    }//lettre

    // la grammaire
    // Vt = {'.',';',"(",")",":", nom}
    // Vn = {arbre, autresfiches, fiche, enfants, autresEnfants}
    // P = 
    // arbre 		-> fiche  autresFiches 
    // autresFiches	-> ; fiche  autresFiches  |  .
    // fiche 		-> nom : nom  enfants 
    // enfants 		-> ( nom autresEnfants 
    // autresEnfants 	-> , nom autresEnfants | )

    /** axiome de la grammaire : point d'entrée de l'analyseur 
     * @author Etudiant
     */
    public  void arbre(){
     //.................
       fiche();
       autresFiches();
     }
     
    private void fiche(){
    	nom();
    	
    	//sauvegarde l'indice du pere
    	pere = leDico.numNom(nomLu);
    	
    	reconnaitre(':');
    	nom();
    	
    	//et celui de la mere
    	mere = leDico.numNom(nomLu);
    	
    	enfants();
    }
    
    private void autresFiches(){
    	if (carLu=='.') {
    		reconnaitre('.');
    	} else {
    		reconnaitre(';');
    		fiche();
    		autresFiches();
    	}
    }

    private void enfants() {
    	reconnaitre('(');
    	nom();
    	autresEnfants();
    }
    
    private void autresEnfants() {
    	// ajoute l'enfant au graphe
    	enfant = leDico.numNom(nomLu);
    	leGraphe.ajoutArc(pere,enfant,1);
    	leGraphe.ajoutArc(mere,enfant,1);
    	
    	        
    	if (carLu==')') {
    		reconnaitre(')');
    	} else {
    		reconnaitre(',');
    		nom();
    		autresEnfants();
    	}	
    }
    		
    	

    /** reconnait un nom : suite de lettres
     *  <BR>construit l'attribut nomLu en concatenant les lettres
     * @author Etudiant
     */
    private void nom(){
	if (!lettre(carLu)){erreur("mot attendu");}
	nomLu=""+carLu;
	for(getChar(); lettre(carLu) ; getChar()){
	    nomLu+=carLu;
	}//for
	
	//ajoute le nom reconnu au dico:
	leDico.numNom(nomLu);

    }//mot

    /** délivre l'attribut dictionnaire construit par l'analyseur
     * @author Etudiant
     * @return le dictionnaire
     */
    public Dico getDico(){
	return leDico;
    }//getDico

    
    /** délivre l'attribut graphe construit par l'analyseur
     * @author Etudiant
     * @return le graphe
     */
    public Graphe getGraphe(){
	return leGraphe;
    }

public static void main(String[] args){
    System.out.println("Donnez le nom du fichier à analyser : ");
    String nomFich=Lecture.lireString();
    Analyse analyseur = new Analyse(nomFich); 
    //appel de l'axiome de la grammaire
    analyseur.arbre();
    // on a surdimensionné le graphe à 20 sommets au début de l'analyse
    // on ajuste la taille du graphe en fonction du nombre de noms :
    // les méthodes ajuste et getNbNoms ont été rajoutées
    analyseur.leGraphe.ajuste(analyseur.leDico.getNbNoms());
    // on n'a pas quitté le programme sur erreur d'analyse et donc
    System.out.println("chaine reconnue !!!");
    System.out.println(""+analyseur.leDico+analyseur.leGraphe);
 
  }//main
    
}//Analyse

/* Traces d'execution
Donnez le nom du fichier à analyser : 
Pierre:Marie(Luc,Anne);Luc : Eva(Jean);Michel : Anne(Lea) .#chaine reconnue !!!
0 : Pierre
1 : Marie
2 : Luc
3 : Anne
4 : Eva
5 : Jean
6 : Michel
7 : Lea
(0):[ [ 2 3 ]]
(1):[ [ 2 3 ]]
(2):[ [ 5 ]]
(3):[ [ 7 ]]
(4):[ [ 5 ]]
(5):[ [ ]]
(6):[ [ 7 ]]
(7):[ [ ]]

*/