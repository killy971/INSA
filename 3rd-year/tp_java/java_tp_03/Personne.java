/**
 *La classe Personne repr�sente des personnes: nom pr�nom et t�l�phone
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 3 29/09/2004
 */

class Personne {

  private Mot _nom;    /* nom */
  private Mot _prenom; /* pr�nom */
  private int _tel;    /* num�ro de t�l�phone */

  /* constructeur qui initialise les champs prenom et nom � partir  
     des cha�nes pass�es en param�tre */
  public Personne(String prenom, String nom){
    _prenom = new Mot(prenom);
    _nom =    new Mot(nom);
  }
 
  /** affecte un num�ro de t�l�phone � la personne
   * @param : le num�ro de t�l�phone � attribuer
   */
  public void affecteTel(int tel) {
    _tel = tel;
  }

  /* rend le numero de la personne */
  public int tel(){
    return _tel;
  }


  /* rend une cha�ne ultra-compacte d�crivant la personne */
  /* par les initiales de son pr�nom et de son nom */
  /* Pour Antoine Doisnel on obtiendra la cha�ne "A. D."  */
  public String persInitiales(){
    return new String(_prenom.initiale()+" "+_nom.initiale());
  }


  /* rend une cha�ne compacte d�crivant la personne */
  /* par les initiales de son pr�nom et son nom complet */
  /* Pour Antoine Doisnel on obtiendra la cha�ne "A. Doisnel"  */
  public String persCourt(){
    return new String(_prenom.initiale()+" "+_nom);
  }

  /* rend une cha�ne d�crivant la personne */
  /* par son pr�nom et son nom complet */
  /* Pour Antoine Doisnel on obtiendra la cha�ne "Antoine Doisnel"  */
  public String pers(){
    return new String(_prenom+" "+_nom);
  }

  /* rend une cha�ne d�crivant compl�tement la personne */
  /* Pour Antoine Doisnel on obtiendra la cha�ne "Antoine Doisnel : 108" */
  /* si 108 est son num�ro de t�l�phone */
  public String toString(){
    return new String(_prenom+" "+_nom+" : "+_tel);
  }

  /** equals d�fini l'�galit� de deux personnes
   * au sens habituel du terme : leur nom et pr�nom sont identiques 
   * @param : personne � comparer
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
