import java.io.*;
    /** module qui délivre quelques primitives utiles en généalogie
     *  @author Etudiant
     *  @see Graphe
     *  @see Dijkstra
     *  @see Analyse
     *  @see Ensemble
     */
public class Genealogie{
    
    private Graphe leGraphe; 
    private Dico leDico;
     
    /** cree une instance de l'arbre généalogique 
     * <BR>appelle le module d'analyse qui délivre le graphe
     * et le dictionnaire
     * @author Etudiant
     */
	
    public Genealogie(){
	Analyse analyseur=new Analyse("exempleC");
	analyseur.arbre();
	leGraphe=analyseur.getGraphe();
	leDico=analyseur.getDico();
      // on a surdimensionné le graphe à 20 sommets au début de l'analyse
      // on ajuste la taille du graphe en fonction du nombre de noms :
      // les méthodes ajuste et getNbNoms ont été rajoutées
       leGraphe.ajuste(leDico.getNbNoms());

    }//Genealogie

    /** délivre les enfants d'une personne 
     *  <BR>utilisation de l'accès succ de la classe Graphe
     * @author Etudiant
     * @param le numero de la personne
     * @return l'Ensemble des enfants 
     */
    public Ensemble  getEnfants(int parent){
    	return leGraphe.succ(parent);

    }//getEnfants

    /** délivre les descendants de rang r d'une personne  
     *  <BR>utilisation de l'algo de Dijkstra : accès getDistances
     *  <BR>si la distance de la personne au sommet n'est pas INFINI,
     *  i est un descendant de la personne
     * @author Etudiant
     * @param num le numero de la personne
     * @param  r le rang des descendants
     * @return  l'Ensemble des descendants de rang i 
     */
    public Ensemble getDescendants(int num, int r){
    	Dijkstra dijk = new Dijkstra(num, leGraphe);
    	dijk.calculePlusCourtChemin();
    	
    	int[] tabDist = dijk.getDistances();
    	affiche("m",tabDist);
    	
    	Ensemble ensDesc = new Ensemble(tabDist.length);
    	
    	for (int i=0;i<tabDist.length;i++) {
    		if (tabDist[i]==r) {
    			ensDesc.ajout(i);
    		}
    	}
    	
    	return ensDesc;
    			
    }//getDescendants


     /** délivre les parents d'une personne 
     * @author Etudiant
     * @param nom le nom de la personne
     * @return les parents 
     */


     
    public Ensemble getParents(int  num){
    	return leGraphe.pred(num);
	
	
    }//getParents

     /** délivre les grands-parents d'une personne 
     * @author Etudiant
     * @param nom le numéro de la personne
     * @return l’ensemble des numéros des grands-parents
     */
    public Ensemble  getGrandsParents(int num){
        Ensemble ensParents = getParents(num);
        
    	return getParents(ensParents.element(0)).union(getParents(ensParents.element(1)));
	
    }//getGrandsParents
    
     /** délivre vrai si les 2 personnes sont cousin(e)s
     * @author Etudiant
     * @param nom1 le nom d'une personne
     * @param nom2 le nom d'une autre personne
     * @return vrai s'ils sont cousins
     */
    public boolean  estCousin(int nom1, int nom2){
	
	Ensemble ensGP1 = getGrandsParents(nom1);
	Ensemble ensGP2 = getGrandsParents(nom2);
	
	Ensemble ensInter = ensGP1.inter(ensGP2);
	
	return !(ensInter.vide());
    }//estCousin

   /** délivre les noms des personnes d'un ensemble de numéros 
     * @author Etudiant
     * @param e l'Ensemble des numéros
     * @return  les noms sous la forme { nom1 ....} 
     */


   public String lesNoms(Ensemble e){
     String resul="{";
     for (int i=0; i<e.card();i++){
        resul+= leDico.getNom(e.element(i))+" ";
     }
     return resul+"}";
   }//lesNoms
   
 // fonction affiche
 static void affiche(String nom,  int[] tCar){
     System.out.print(nom +" : ");
     for (int i =0; i<tCar.length ; i++){
	 System.out.print(tCar[i]+" ");
     }//for i
     System.out.println();
 }//affiche
 

public static void main(String[] args){
   
    Genealogie  genea = new Genealogie();
   
    	System.out.println(""+genea.leDico+genea.leGraphe);

	System.out.println("\nenfants de Blanche:"
             +genea.lesNoms(genea.getEnfants(genea.leDico.numNom("Blanche"))));
	System.out.println("\ndesc de LouisVI de rang 2:"
            +genea.lesNoms(genea.getDescendants(genea.leDico.numNom("LouisVI"),2)));
            
        System.out.println("\nparents de LouisIX:"
            +genea.lesNoms(genea.getParents(genea.leDico.numNom("LouisIX"))));
            
        System.out.println("\ngrands parents de LouisIX:"
            +genea.lesNoms(genea.getGrandsParents(genea.leDico.numNom("LouisIX"))));
        
        System.out.println("\nPhilippeII et PierreII sont-ils cousins ?"
            +genea.estCousin(genea.leDico.numNom("PhilippeII"),
                             genea.leDico.numNom("PierreII")
                             ));
                             
        System.out.println("\nPhilippeII et LouisVIII sont-ils cousins ?"
            +genea.estCousin(genea.leDico.numNom("PhilippeII"),
                             genea.leDico.numNom("LouisVIII")
                             ));
          

   // à complèter pour les méthodes supplémentaires
  }//main
    
}//Genealogie
/*
LouisVI : Adelaide(LouisVII,RobertIerDreux,Constance,Pierre);
LouisVII : Adele(Agnes, PhilippeII);
Pierre:Elisabeth(PierreII);
PhilippeII : Isabelle(LouisVIII);
LouisVIII : Blanche(LouisIX,RobertIerArtois,CharlesIer);
LouisIX :Marguerite(Robert,PhilippeIII).#0 : LouisVI
1 : Adelaide
2 : LouisVII
3 : RobertIerDreux
4 : Constance
5 : Pierre
6 : Adele
7 : Agnes
8 : PhilippeII
9 : Elisabeth
10 : PierreII
11 : Isabelle
12 : LouisVIII
13 : Blanche
14 : LouisIX
15 : RobertIerArtois
16 : CharlesIer
17 : Marguerite
18 : Robert
19 : PhilippeIII
(0):[ [ 2 3 4 5 ]]
(1):[ [ 2 3 4 5 ]]
(2):[ [ 7 8 ]]
(3):[ [ ]]
(4):[ [ ]]
(5):[ [ 10 ]]
(6):[ [ 7 8 ]]
(7):[ [ ]]
(8):[ [ 12 ]]
(9):[ [ 10 ]]
(10):[ [ ]]
(11):[ [ 12 ]]
(12):[ [ 14 15 16 ]]
(13):[ [ 14 15 16 ]]
(14):[ [ 18 19 ]]
(15):[ [ ]]
(16):[ [ ]]
(17):[ [ 18 19 ]]
(18):[ [ ]]
(19):[ [ ]]


enfants de Blanche:{LouisIX RobertIerArtois CharlesIer }
m : 10000 10000 1 1 1 1 10000 2 2 10000 2 10000 3 10000 4 4 4 10000 5 5 

desc de LouisVI de rang 2:{Agnes PhilippeII PierreII }

parents de LouisIX:{LouisVIII Blanche }

grands parents de LouisIX:{PhilippeII Isabelle }

PhilippeII et PierreII sont-ils cousins ?true

PhilippeII et LouisVIII sont-ils cousins ?false

*/
