/**
 * La classe ListeSimpleImpl représente une structure de donnée de liste
 * l'itérateur est codé séparément, dans une classe interne
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 8 16/11/2004
 */

package sdd;

public class SimpleListImpl implements SimpleList {
	
    private int cardinal,capacite;
	private Object[] elements;
	
	/**
	 * Constructeur
	 */
	public SimpleListImpl() {
		capacite = 10;
		cardinal = 0;
		elements = new Object[capacite];
	}
	
	/**
	 * Méthode pour tester si une liste est vide.
	 *@param void
	 *@return boolean : rend vrai si la liste est vide
	 */
	public boolean estVide() {
		return (cardinal == 0);
	}// estVide()

	/**
	 * Méthode pour tester si une liste est pleine.
	 *@param void
	 *@return boolean : rend vrai si la liste est pleine
	 */
	public boolean estPleine() {
		return (cardinal == capacite-1);
	}// estPleine()
	
	/**
	 * Méthode pour vider la liste: enlève tous les éléments
	 *@param void
	 *@return void
	 */
	public void vider() {
		cardinal = 0;
	}// vider()
	
	/**
	 * Méthode pour retourner un itérateur sur la liste
	 *@param void
	 *@return SimpleListIterateur : itérateur
	 */
	public SimpleListIterator iterateur() {
		return new SimpleListIteratorImpl(this);
	}// iterateur()
	
	/**
	 * Méthode pour rendre une String donnant le contenu de la liste
	 *@param void
	 *@return String : contenu de la liste
	 */
	public String toString() {
	    String s = new String("{");
	    int i=0;
	    for(; i<cardinal-1 ; i++) {
		s += elements[i].toString()+",";
	    }// for
	    s += elements[i].toString()+"}";
	    return s;
	}// toString()
	
	private class SimpleListIteratorImpl implements SimpleListIterator {
		
		private SimpleListImpl liste;
	        private int indice; // indice de l'élément courant
		
		// constructeur
		SimpleListIteratorImpl(SimpleListImpl s) {
			liste = s;
			entete();
		}// SimpleListIteratorImpl(SimpleListImpl s)
		
		/**
		 * Méthode pour renvoyer la valeur de l'élément courant
		 *@param void
		 *@return Object : élément courant
		 */
		public Object ec() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			return liste.elements[indice];
		}// ec()
	
		/**
		 * Méthode qui vérifie si on est hors liste
		 *@param void
		 *@return boolean rend vrai si on est hors liste
		 */
		public boolean sorti() {
			return ((indice<0) || (indice>=cardinal));
		}// sorti()
	
		/**
		 * Méthode pour se placer en tête
		 *@param void
		 *@return void
		 */
		public void entete() {
			if(liste.estVide()) {
				indice = -1;
			} else {
				indice = 0;
			}
		}// entete()
	
		/**
		 * Méthode pour se placer en queue
		 *@param void
		 *@return void
		 */
		public void enqueue() {
			indice = cardinal-1;
		}// enqueue()

		/**
		 * Méthode pour placer le pointeur sur l'élément en paramètre
		 *@param i : indice de l'élément voulu (premier element correspond à 0)
		 *@return void
		 */
		public void allera(int i) throws SimpleListException {
			indice=i;
		}// allera()
	
		/**
		 * Méthode pour se placer sur l'élément suivant
		 *@param void
		 *@return void
		 */
		public void suc() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			indice++;
		}// suc()
	
		/**
		 * Méthode pour se placer sur l'élément précédent
		 *@param void
		 *@return void
		 */
		public void pred() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			indice--;
		}// pred()
	
		/**
		 * Méthode pour insérer un objet dans la liste avant l'objet courant
		 *@param obj : objet à insérer
		 *@return void
		 */
		public void inserer(Object obj) throws SimpleListException {
		        if (liste.estPleine()) throw new SimpleListException("Erreur: Liste Pleine");
			if (liste.estVide()) {
				liste.cardinal=1;
				liste.elements[0] = obj;
				indice=0;
				return;
			}
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			for (int i = cardinal-1;i>=indice;i--){
			    liste.elements[i+1]=liste.elements[i];
			}
			liste.elements[indice]=obj;
			cardinal++;
		}// inserer()
	
		/**
		 * Méthode pour ajouter un objet dans la liste après l'objet courant
		 *@param obj : objet à ajouter
		 *@return void
		 */
		public void ajouter(Object obj) throws SimpleListException {
		        if (liste.estPleine()) throw new SimpleListException("Erreur: Liste Pleine");
			if (liste.estVide()) {
				liste.cardinal=1;
				liste.elements[0] = obj;
				indice=0;
				return;
			}
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			for (int i = cardinal -1;i>indice;i--){
			    liste.elements[i+1]=liste.elements[i];
			}
			suc();
			liste.elements[indice]=obj;
			cardinal++;
		}// ajouter()
	
		/**
		 * Méthode pour oter l'objet courant de la liste
		 *@param void
		 *@return void
		 */
		public void oter() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			for(int i=indice ; i<cardinal-1 ; i++) {
			    liste.elements[indice] = liste.elements[indice+1];
			}
			pred();
			cardinal--;
		}// oter()
	
		/**
		 * Méthode pour modifier la valeur de l'objet courant
		 *@param obj : nouvelle valeur de l'élément courant
		 *@return void
		 */
		public void modifier(Object obj) throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			liste.elements[indice] = obj;
		}// modifier()
		
	}// SimpleListIteratorImpl
	
    public static void main(String args[]) {
	SimpleListImpl l = new SimpleListImpl();
	SimpleListIterator i = l.iterateur();
	try {
	    i.ajouter(new Integer(1));
	    i.ajouter(new Integer(2));
	    System.out.println(l);
	    i.pred();
	    System.out.println(l);
	    i.ajouter(new Integer(3));
	    i.inserer(new Integer(4));
	    System.out.println(l);
	    System.out.println(i.ec());
	    i.allera(2);
	    System.out.println(i.ec());
	    i.entete();
	    System.out.println(i.ec());
	    i.enqueue();
	    System.out.println(i.ec());
	}
	catch(Exception e) {
	    System.out.println(e);
	}
	try {
	    SimpleListImpl l2 = new SimpleListImpl();
	    SimpleListIterator i2 = l2.iterateur();
	    i2.pred();
	}
	catch(Exception e) {
	    System.out.println(e);
	}
	try {
	    SimpleListImpl l3 = new SimpleListImpl();
	    SimpleListIterator i3 = l3.iterateur();
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));
	    i3.ajouter(new Integer(1));

	}
	catch(Exception e) {
	    System.out.println(e);
	}
	try {
	    i.suc();
	    i.suc();
	    i.suc();
	}
	catch(Exception e) {
	    System.out.println(e);
	}
    }
	
}

