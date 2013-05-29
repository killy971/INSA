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
  // On commencera par la mise en oeuvre naïve qui ne rend que la 1re
  // lettre suivie d'un point.
  // (Antoine donnera "A." et Doisnel "D.")
  //
  // A améliorer SEULEMENT A LA FIN DU TP
  // pour tenir compte des cas particuliers :
  // * Charles-Henri aura pour Initiales "C.-H."
  // * Du Bouleau aura "Du B."
  // * mais Ducon aura "D."
  // On ne gèrera pas les subtilité genre De la Motte Piquet... 
  // Après vous saurez tout sur les chaînes !
  public String initiale(){
    return ...;
  }

  public String toString () {
    return _mot;
  }

  /* equals défini l'égalité de deux mots 
     indépendamment des majuscules éventuellement oubliées */
  public boolean equals(Mot m) {
    return (this._mot).equalsIgnoreCase(m._mot);
  }

  public boolean aParticule(){ // De<blanc>chose ou Du<Blanc>Bidule
    return ...;
  }
  public boolean estComposé(){ // ex Jean-Louis
    return ...;
  }

  public static void main (String[] args)
  {
    //main pour tester la classe en question
  }
}//class

