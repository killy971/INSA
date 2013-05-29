/**
 *La classe Personne représente des personnes: nom prénom et téléphone
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 4 02/10/2004
 */

class Personne {

	private Mot _nom;		// nom
	private Mot _prenom;	// prénom
	private int _tel;		// numéro de téléphone

	/**
	 * constructeur qui initialise les champs prenom et nom à partir
	 * des chaînes passées en paramètre
	 */
	public Personne(String prenom, String nom){
		_prenom = new Mot(prenom);
		_nom =    new Mot(nom);
	}// Personne()
 
	/** affecte un numéro de téléphone à la personne
	 * @param : le numéro de téléphone à attribuer
	 */
	public void affecteTel(int tel) {
		_tel = tel;
	}// affecteTel()

	/* rend le numero de la personne */
		public int tel() {
		return _tel;
	}

	/* rend une chaîne ultra-compacte décrivant la personne */
	/* par les initiales de son prénom et de son nom */
	/* Pour Antoine Doisnel on obtiendra la chaîne "A. D."  */
	public String persInitiales() {
		return new String(_prenom.initiale()+" "+_nom.initiale());
	}// persInitiales()


	/* rend une chaîne compacte décrivant la personne */
	/* par les initiales de son prénom et son nom complet */
	/* Pour Antoine Doisnel on obtiendra la chaîne "A. Doisnel"  */
	public String persCourt() {
		return new String(_prenom.initiale()+" "+_nom);
	}// persCourt()

	/* rend une chaîne décrivant la personne */
	/* par son prénom et son nom complet */
	/* Pour Antoine Doisnel on obtiendra la chaîne "Antoine Doisnel"  */
	public String pers() {
		return new String(_prenom+" "+_nom);
	}// pers()
	
	public String persNomPrenom() {
		return new String(_nom+" "+_prenom);
	}// persNomPrenom()

	/* rend une chaîne décrivant complètement la personne */
	/* Pour Antoine Doisnel on obtiendra la chaîne "Antoine Doisnel : 108" */
	/* si 108 est son numéro de téléphone */
	public String toString(){
		return new String(_prenom+" "+_nom+" : "+_tel);
	}// toString()

	/** equals défini l'égalité de deux personnes
	 * au sens habituel du terme : leur nom et prénom sont identiques 
	 * @param : personne à comparer
	 */
	public boolean equals(Personne p) {
		return _nom.equals(p._nom)&&_prenom.equals(p._prenom);
	}

	/**
	 * Compare p à la personne courante.
	 * Retourne false s'ils sont égaux alphabetiquement
	 * Retourne false si p succède à la personne courante dans l'alphabet
	 * Retourne true si p précède la personne courante dans l'alphabet
	 */
	public boolean inferieurA(Personne p) {
		int ordre = this.persNomPrenom().compareToIgnoreCase(p.persNomPrenom());
		if (ordre<=0) {
			return false;
		} else {
			return true;
		}
	}// inferieurA()

	public static void main (String[] args) {

		Personne p1 = new Personne("Antoine","Doisnel");
		Personne p2 = new Personne("Roland","Louis");
		Personne p3 = new Personne("Bob","Marley");
		Personne p4 = new Personne("George","Bush");
		Personne p5 = new Personne("François-Julien","Bourget Marbaud");
		Personne p6 = new Personne("Pierre","Dubois");
		Personne p7 = new Personne("Roland","Louis");
		Personne p8 = new Personne("Charles","Louis");
		Personne p9 = new Personne("Steeve","Louis");

		p2.affecteTel(590812915);
		System.out.println(p2);
		System.out.println(p2.persInitiales());
		System.out.println(p2.persCourt());
		System.out.println(p2.pers());
		
		System.out.println("");
		System.out.println("p2=p7 ?"+p2.equals(p7));
		System.out.println(p2.inferieurA(p7));
		System.out.println(p2.inferieurA(p8));
		System.out.println(p2.inferieurA(p9));
		
	}// main()
}// Personne

/* traces d'execution



*/
