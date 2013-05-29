public class SimpleListImpl implements SimpleList {
	
	private int cardinal;
	private int capacite;
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
		System.out.println("{");
		for(int i=0 ; i<cardinal-1 ; i++) {
			System.out.println(elements[i].toString()+",");
		}// for
		System.out.println(elements[i].toString()"}")
	}// toString()
	
	class SimpleListIteratorImpl implements SimpleListIterator {
		
		private SimpleListImpl liste;
		int indice; // indice de l'�l�ment courant
		
		// constructeur
		SimpleListIteratorImpl(SimpleListImpl s) {
			indice = -1;
			liste = s;
			entete();
		}// SimpleListIteratorImpl(SimpleListImpl s)
		
		/**
		 * 
		 *@param 
		 *@return 
		 */
		public Object ec() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			return liste.elements[indice];
		}// ec()
	
		/**
		 * 
		 *@param 
		 *@return 
		 */
		public boolean sorti() {
			return ((indice<0) || (indice>=cardinal));
		}// sorti()
	
		/**
		 * 
		 *@param 
		 *@return 
		 */
		public void entete() {
			if(liste.estVide()) {
				indice = -1;
			} else {
				indice = 0;
			}
		}// entete()
	
		/**
		 * 
		 *@param 
		 *@return 
		 */
		public void enqueue() {
			indice = cardinal-1;
		}// enqueue()

		/**
		 * 
		 *@param 
		 *@return 
		 */
		public void allera(int i) throws SimpleListException {
			indice=i;
		}// allera()
	
		/**
		 * 
		 *@param 
		 *@return 
		 */
		public void suc() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			indice++;
		}// suc()
	
		/**
		 * 
		 *@param 
		 *@return 
		 */
		public void pred() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			indice--;
		}// pred()
	
		/**
		 * 
		 *@param 
		 *@return 
		 */
		public void inserer(Object obj) throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			
		}// inserer()
	
		/**
		 * 
		 *@param 
		 *@return 
		 */
		public void ajouter(Object obj) throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			
		}// ajouter()
	
		/**
		 * 
		 *@param 
		 *@return 
		 */
		public void oter() throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			for(int i=indice ; i<cardinal-1 ; i++) {
				liste.elements[indice] = liste.elements[indice+1]
			}
			pref();
			cardinal--;
		}// oter()
	
		/**
		 * 
		 *@param 
		 *@return 
		 */
		public void modifier(Object obj) throws SimpleListException {
			if (sorti()) throw new SimpleListException("Erreur: Hors Liste");
			liste.elements[indice] = obj;
		}// modifier()
		
	}// SimpleListIteratorImpl
	
	
}

