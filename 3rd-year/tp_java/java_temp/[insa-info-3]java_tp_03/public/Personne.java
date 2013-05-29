class Personne {

  private Mot _nom;    /* nom */
  private Mot _prenom; /* prénom */
  private int _tel;    /* numéro de téléphone */

  /* constructeur qui initialise les champs prenom et nom à partir  
     des chaînes passées en paramètre */
  public Personne(String prenom, String nom){
    _prenom = ...;
    _nom =    ...;
  }
 
  /* affecte un numéro de téléphone à la personne */
  public void affecteTel(int tel) {
    _tel = ...;
  }

  /* rend le numero de la personne */
  public int tel(){
    return ...;
  }


  /* rend une chaîne ultra-compacte décrivant la personne */
  /* par les initiales de son prénom et de son nom */
  /* Pour Antoine Doisnel on obtiendra la chaîne "A. D."  */
  public String persInitiales(){
    return new String(...);
  }


  /* rend une chaîne compacte décrivant la personne */
  /* par les initiales de son prénom et son nom complet */
  /* Pour Antoine Doisnel on obtiendra la chaîne "A. Doisnel"  */
  public String persCourt(){
    return new String(...);
  }

  /* rend une chaîne décrivant la personne */
  /* par son prénom et son nom complet */
  /* Pour Antoine Doisnel on obtiendra la chaîne "Antoine Doisnel"  */
  public String pers(){
    return new String(...);
  }

  /* rend une chaîne décrivant complètement la personne */
  /* Pour Antoine Doisnel on obtiendra la chaîne "Antoine Doisnel : 108" */
  /* si 108 est son numéro de téléphone */
  public String toString(){
    return new String(...);
  }

  /* equals défini l'égalité de deux personnes */
  /* au sens habituel du terme : leur nom et prénom sont identiques */
  public boolean equals(Personne p) {
    return ...;
  }

  public static void main (String[] args) {
    Personne toto = new Personne("Antoine","Doisnel");
    toto.affecteTel(9999);
    System.out.println(toto);

    // ... tester ici les autres méthodes
  }
}
