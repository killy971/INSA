/**
 * La classe ListeSimpleImpl repr�sente une structure de donn�e de liste
 * l'it�rateur est cod� s�par�ment, dans une classe interne
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
	 * M�thode pour tester si une liste est vide.
	 *@param void
	 *@return boolean : rend vrai si la liste est vide
	 */
	public boolean estVide() {
		return (cardinal == 0);
	}// estVide()

	/**
	 * M�thode pour tester si une liste est pleine.
	 *@param void
	 *@return boolean : rend vrai si la liste est pleine
	 */
	public boolean estPleine() {
		return (cardinal == capacite-1);
	}// estPleine()
	
	/**
	 * M�thode pour vider la liste: enl�ve tous les �l�ments
	 *@param void
	 *@return void
	 */
	public void vider() {
		cardinal = 0;
	}// vider()
	
	/**
	 * M�thode pour retourner un it�rateur sur la liste
	 *@param void
	 *@return SimpleListIterateur : it�rateur
	 */
	public SimpleListIterator iterateur() {
		return new SimpleListIteratorImpl(this);
	}// iterateur()
	
	/**
	 * M�thode pour rendre une String donnant le contenu de la liste
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
	        private int indice; // indice de l'�l�ment courant
		
		// constructeur
		SimpleListIteratorImpl(SimpleListImpl s) {
			liste = s;
			entete();
		}// SimpleListIteratorImpl(SimpleListImpl s)
		
		/**
		 * M�thode pour renvoyer la valeur de l'�l�ment courant
		 *@param void
		 *@return Object : �l�ment courant
		 */
		public Object ec() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			return liste.elements[indice];
		}// ec()
	
		/**
		 * M�thode qui v�rifie si on est hors liste
		 *@param void
		 *@return boolean rend vrai si on est hors liste
		 */
		public boolean sorti() {
			return ((indice<0) || (indice>=cardinal));
		}// sorti()
	
		/**
		 * M�thode pour se placer en t�te
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
		 * M�thode pour se placer en queue
		 *@param void
		 *@return void
		 */
		public void enqueue() {
			indice = cardinal-1;
		}// enqueue()

		/**
		 * M�thode pour placer le pointeur sur l'�l�ment en param�tre
		 *@param i : indice de l'�l�ment voulu (premier element correspond � 0)
		 *@return void
		 */
		public void allera(int i) throws SimpleListException {
			indice=i;
		}// allera()
	
		/**
		 * M�thode pour se placer sur l'�l�ment suivant
		 *@param void
		 *@return void
		 */
		public void suc() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			indice++;
		}// suc()
	
		/**
		 * M�thode pour se placer sur l'�l�ment pr�c�dent
		 *@param void
		 *@return void
		 */
		public void pred() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			indice--;
		}// pred()
	
		/**
		 * M�thode pour ins�rer un objet dans la liste avant l'objet courant
		 *@param obj : objet � ins�rer
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
		 * M�thode pour ajouter un objet dans la liste apr�s l'objet courant
		 *@param obj : objet � ajouter
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
		 * M�thode pour oter l'objet courant de la liste
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
		 * M�thode pour modifier la valeur de l'objet courant
		 *@param obj : nouvelle valeur de l'�l�ment courant
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

