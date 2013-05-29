class Mot {

  private String _mot;
    
  /* constructeur Mot vide */
  public Mot() {
    _mot = new String;
  }
  
  /* constructeur Mot a partir d'une chaine  */
  public Mot(String s){
    _mot = new String(s);
  }
	
  /* Methode qui rend la/les initiale(s) */
  // On commencera par la mise en oeuvre na�ve qui ne rend que la 1re
  // lettre suivie d'un point.
  // (Antoine donnera "A." et Doisnel "D.")
  //
  // A am�liorer SEULEMENT A LA FIN DU TP
  // pour tenir compte des cas particuliers :
  // * Charles-Henri aura pour Initiales "C.-H."
  // * Du Bouleau aura "Du B."
  // * mais Ducon aura "D."
  // On ne g�rera pas les subtilit� genre De la Motte Piquet... 
  // Apr�s vous saurez tout sur les cha�nes !
  public String initiale(){
    return ...;
  }

  public String toString () {
    return _mot;
  }

  /* equals d�fini l'�galit� de deux mots 
     ind�pendamment des majuscules �ventuellement oubli�es */
  public boolean equals(Mot m) {
    return (this._mot).equalsIgnoreCase(m._mot);
  }

  public boolean aParticule(){ // De<blanc>chose ou Du<Blanc>Bidule
    return ...;
  }
  public boolean estCompos�(){ // ex Jean-Louis
    return ...;
  }

  public static void main (String[] args)
  {
    //main pour tester la classe en question
  }
}//class

