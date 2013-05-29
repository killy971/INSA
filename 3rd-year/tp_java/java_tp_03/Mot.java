/**
 *La classe Mot représente des mots
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 3 29/09/2004
 */


class Mot {

  private String _mot;
    
  /* constructeur Mot vide */
  public Mot() {
	_mot = new String(); 
  }//Mot()
  /* constructeur Mot a partir d'une chaine  */
  public Mot(String s){
	_mot = new String(s);
  }//Mot(String s)
	
  /** Methode qui rend la/les initiale(s)
   * @return rend les initiales suivies d un point, s adapte en cas de nom composé ou à particule
   */
  public String initiale(){
	if (aParticule()) {
		return _mot.substring(0,4)+".";
	} else if (estCompose()) {
		for (int i=0 ; ; i++) {
			if(_mot.substring(i,i+1).equals("-")) {
				return _mot.substring(0,1)+"."+_mot.substring(i,i+2)+".";
			}
		}
	} else {
		return _mot.substring(0,1)+".";
	}
  }

  public String toString () {
    return _mot;
  }

  /** equals défini l'égalité de deux mots indépendamment des majuscules éventuellement oubliées
   * @param mot à comparer
   */
  public boolean equals(Mot m) {
    return (this._mot).equalsIgnoreCase(m._mot);
  }
  /* aParticule rend vrai si le mot a une particule */
  public boolean aParticule(){ // De<blanc>chose ou Du<Blanc>Bidule
	if (_mot.length() < 3) {
		return false;
	}
	return _mot.substring(0,3).equals("Du ") || _mot.substring(0,3).equals("De ");
  }
  /* aParticule rend vrai si le mot est composé */
  public boolean estCompose(){ // ex Jean-Louis
	for (int i=0 ; i<_mot.length() ; i++) {
		if (_mot.substring(i,i+1).equals("-")) {
			return true;
		}
	}
	return false;
  }

  public static void main (String[] args)
  {
	Mot mot1 = new Mot("Insa");
	System.out.println(mot1.initiale());
	Mot mot2 = new Mot("Insa");
	System.out.println(mot1.equals(mot2));
	Mot mot3 = new Mot("Charles-Henri");
	Mot mot4 = new Mot("Du Montel");
	System.out.println(mot3.estCompose());
	System.out.println(mot4.aParticule());
	System.out.println(mot3.initiale());
	System.out.println(mot4.initiale());
  }
}//class

/* traces d'exec

I.
true
true
true
C.-H.
Du M.
contrex[127]

*/
