/**
 *La classe Personne représente des personnes: nom prénom et téléphone
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 3 29/09/2004
 */

class Personne {

  private Mot _nom;    /* nom */
  private Mot _prenom; /* prénom */
  private int _tel;    /* numéro de téléphone */

  /* constructeur qui initialise les champs prenom et nom à partir  
     des chaînes passées en paramètre */
  public Personne(String prenom, String nom){
    _prenom = new Mot(prenom);
    _nom =    new Mot(nom);
  }
 
  /** affecte un numéro de téléphone à la personne
   * @param : le numéro de téléphone à attribuer
   */
  public void affecteTel(int tel) {
    _tel = tel;
  }

  /* rend le numero de la personne */
  public int tel(){
    return _tel;
  }


  /* rend une chaîne ultra-compacte décrivant la personne */
  /* par les initiales de son prénom et de son nom */
  /* Pour Antoine Doisnel on obtiendra la chaîne "A. D."  */
  public String persInitiales(){
    return new String(_prenom.initiale()+" "+_nom.initiale());
  }


  /* rend une chaîne compacte décrivant la personne */
  /* par les initiales de son prénom et son nom complet */
  /* Pour Antoine Doisnel on obtiendra la chaîne "A. Doisnel"  */
  public String persCourt(){
    return new String(_prenom.initiale()+" "+_nom);
  }

  /* rend une chaîne décrivant la personne */
  /* par son prénom et son nom complet */
  /* Pour Antoine Doisnel on obtiendra la chaîne "Antoine Doisnel"  */
  public String pers(){
    return new String(_prenom+" "+_nom);
  }

  /* rend une chaîne décrivant complètement la personne */
  /* Pour Antoine Doisnel on obtiendra la chaîne "Antoine Doisnel : 108" */
  /* si 108 est son numéro de téléphone */
  public String toString(){
    return new String(_prenom+" "+_nom+" : "+_tel);
  }

  /** equals défini l'égalité de deux personnes
   * au sens habituel du terme : leur nom et prénom sont identiques 
   * @param : personne à comparer
   */
  public boolean equals(Personne p) {
    return _nom.equals(p._nom)&&_prenom.equals(p._prenom);
  }

  public static void main (String[] args) {
	Personne toto = new Personne("Antoine","Doisnel");
	toto.affecteTel(9999);
	Personne tutu = new Personne("Antoine","Doisnel");
	tutu.affecteTel(9999);
	System.out.println(toto);
	System.out.println(toto.persInitiales());
	System.out.println(toto.persCourt());
	System.out.println(toto.pers());
	System.out.println(toto.equals(tutu));
  }
}

/* traces d'execution

Antoine Doisnel : 9999
A. D.
A. Doisnel
Antoine Doisnel
true

*/
