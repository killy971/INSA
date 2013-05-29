class Personne {

  private Mot _nom;    /* nom */
  private Mot _prenom; /* pr�nom */
  private int _tel;    /* num�ro de t�l�phone */

  /* constructeur qui initialise les champs prenom et nom � partir  
     des cha�nes pass�es en param�tre */
  public Personne(String prenom, String nom){
    _prenom = ...;
    _nom =    ...;
  }
 
  /* affecte un num�ro de t�l�phone � la personne */
  public void affecteTel(int tel) {
    _tel = ...;
  }

  /* rend le numero de la personne */
  public int tel(){
    return ...;
  }


  /* rend une cha�ne ultra-compacte d�crivant la personne */
  /* par les initiales de son pr�nom et de son nom */
  /* Pour Antoine Doisnel on obtiendra la cha�ne "A. D."  */
  public String persInitiales(){
    return new String(...);
  }


  /* rend une cha�ne compacte d�crivant la personne */
  /* par les initiales de son pr�nom et son nom complet */
  /* Pour Antoine Doisnel on obtiendra la cha�ne "A. Doisnel"  */
  public String persCourt(){
    return new String(...);
  }

  /* rend une cha�ne d�crivant la personne */
  /* par son pr�nom et son nom complet */
  /* Pour Antoine Doisnel on obtiendra la cha�ne "Antoine Doisnel"  */
  public String pers(){
    return new String(...);
  }

  /* rend une cha�ne d�crivant compl�tement la personne */
  /* Pour Antoine Doisnel on obtiendra la cha�ne "Antoine Doisnel : 108" */
  /* si 108 est son num�ro de t�l�phone */
  public String toString(){
    return new String(...);
  }

  /* equals d�fini l'�galit� de deux personnes */
  /* au sens habituel du terme : leur nom et pr�nom sont identiques */
  public boolean equals(Personne p) {
    return ...;
  }

  public static void main (String[] args) {
    Personne toto = new Personne("Antoine","Doisnel");
    toto.affecteTel(9999);
    System.out.println(toto);

    // ... tester ici les autres m�thodes
  }
}
