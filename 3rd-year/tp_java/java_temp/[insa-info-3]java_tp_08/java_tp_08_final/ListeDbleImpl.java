/**
 * La classe ListeDbleImpl repr�sente une structure de donn�e de liste
 * doublement chain�e, utilisant des instances de la classe Maillon.
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 8 10/11/2004
 */

public class ListeDbleImpl implements Liste {
	
	// les 3 maillons n�cessaires: le maillon de t�te, celui de queue, et le
	// maillon courant (respectivement)
    private Maillon tete,queue,ec;
    
	public ListeDbleImpl( ){
		this.tete = new Maillon();
		this.queue = new Maillon();
		this.tete.setSuivant(queue);
		this.queue.setPrecedent(tete);
		this.ec = tete;
	}// ListeDbleImpl()
    
    /**
     * Teste si la liste est vide
     *@param void
     *@return boolean
     */
	public boolean listeVide() {
		return (tete.getSuivant()==queue) && (queue.getPrecedent()==tete);
	}// listeVide()
    
    /**
     * Teste si l'�lement courant poss�de un successeur
     *@param void
     *@return boolean
     */
	public boolean aSuivant() {
		return ec.getSuivant()!=queue;
	}// aSuivant()
    	
    /**
     * Test si l'�l�ment courant poss�de un pr�d�cesseur
     *@param void
     *@return boolean
     */
	public boolean aPrecedent() {
		return ec.getPrecedent()!=tete;
	}// aPrecedent()
    
    /**
     * Rend la valeur de l'�l�ment courant
     *@param void
     *@return Object
     */
	public Object element() throws ExceptionListeVide {
		if (this.listeVide()) throw new  ExceptionListeVide();
		return ec.getValeur();
	}// element()
    
    /**
     * Ajoute un �l�ment � la liste
     *@param Object
     *@return void
     */
	public void ajouter(Object o) {
		Maillon m = new Maillon();
		m.setValeur(o);
		m.setPrecedent(ec);
		m.setSuivant(ec.getSuivant());
		this.ec.getSuivant().setPrecedent(m);
		this.ec.setSuivant(m);
		this.ec = m;
	}// ajouter()
    
    /**
     * Enl�ve l'�l�ment courant de la liste
     *@param void
     *@return void
     */
	public void enlever() throws ExceptionListeVide {
		if (this.listeVide()) throw new ExceptionListeVide();
		ec.getPrecedent().setSuivant(ec.getSuivant());
		ec.getSuivant().setPrecedent(ec.getPrecedent());
		this.ec = ec.getPrecedent();
	}// enlever()
    
    /**
     * D�place le pointeur de l'�l�ment courant vers l'�l�ment successeur
     * de l'�l�ment courant
     *@param void
     *@return void
     */
	public void suivant() throws ExceptionHorsListe {
		if (!this.aSuivant()) throw new ExceptionHorsListe();
		this.ec = ec.getSuivant();
	}// suivant()
    
    /**
     * D�place le pointeur de l'�l�ment courant vers l'�l�ment pr�d�cesseur
     * de l'�l�ment courant
     *@param void
     *@return void
     */
	public void precedent() throws ExceptionHorsListe {
		if (!this.aPrecedent())throw new ExceptionHorsListe();
		this.ec = ec.getPrecedent();
	}// precedent()

	public static void main(String args[]) {
		ListeDbleImpl l = new ListeDbleImpl();
		ListeDbleImpl l2 = new ListeDbleImpl();
		System.out.println("* test de listeVide(new())");
		System.out.println(l.listeVide());
		l.ajouter(new Integer(1));
		System.out.println("* test de non listeVide(ajouter(L,e))");
		System.out.println(l.listeVide());
		l.ajouter(new Integer(2));
		l.ajouter(new Integer(3));
		try {
		    
		    l.precedent();
		    l.precedent();
		    System.out.println("* test de aSuivant(precedent(L))");
		    System.out.println(l.aSuivant());
		    l.suivant();
		    l.suivant();
		    System.out.println("* test de aPrecedent(suivant(L))");
		    System.out.println(l.aPrecedent());
		    l2.ajouter(new Integer(4));
		}
		catch(Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			System.out.println("* test de la pr�condition de precedent()");
		    l2.precedent();
		}
		catch(Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			System.out.println("* test de la pr�condition de suivant()");
		    l2.suivant();
		}
		catch(Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
		    l2.enlever();
		    System.out.println("* test de la pr�condition de enlever()");
		    l2.enlever();
		}
		catch(Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			System.out.println("* test de la pr�condition de element()");
		    l2.element();
		}
		catch(Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			Integer integer1 = new Integer(7);
		    l2.ajouter(integer1);
		    System.out.println("* test de element(ajouter(L,e))=e");
		    System.out.println("Valeur de l'�l�ment courant: "+l2.element());

		    l2.ajouter(new Integer(5));
		    l2.precedent();
		    System.out.println("element(precedent(ajouter(L,e)))=element(L)");
		    System.out.println(l2.element()==integer1);
			
			System.out.println("Valeur de l'�l�ment courant: "+l2.element());
			l2.ajouter(new Integer(20));
			l2.enlever();
			System.out.println("enlever(ajouter(L,e))=L");
			System.out.println("Valeur de l'�l�ment courant: "+l2.element());
			
			l2.ajouter(new Integer(32));
			
			System.out.println("* test de precedent(suivant(L))=L");
			System.out.println("Valeur de l'�l�ment courant: "+l2.element());
			l2.precedent();
			l2.suivant();
			System.out.println("Valeur de l'�l�ment courant: "+l2.element());
			
			System.out.println("* test de suivant(precedent(L))=L");
			System.out.println("Valeur de l'�l�ment courant: "+l2.element());
			l2.suivant();
			l2.precedent();
			System.out.println("Valeur de l'�l�ment courant: "+l2.element());
		}
		catch(Exception e) {
		    System.out.println(e.getMessage());
		}
	}
}

/* traces d'execution;


* test de listeVide(new())
true
* test de non listeVide(ajouter(L,e))
false
* test de aSuivant(precedent(L))
true
* test de aPrecedent(suivant(L))
true
* test de la pr�condition de precedent()
erreur vous �tes hors liste
* test de la pr�condition de suivant()
erreur vous �tes hors liste
* test de la pr�condition de enlever()
erreur la liste est vide
* test de la pr�condition de element()
erreur la liste est vide
* test de element(ajouter(L,e))=e
Valeur de l'�l�ment courant: 7
element(precedent(ajouter(L,e)))=element(L)
true
Valeur de l'�l�ment courant: 7
enlever(ajouter(L,e))=L
Valeur de l'�l�ment courant: 7
* test de precedent(suivant(L))=L
Valeur de l'�l�ment courant: 32
Valeur de l'�l�ment courant: 32
* test de suivant(precedent(L))=L
Valeur de l'�l�ment courant: 32
Valeur de l'�l�ment courant: 32

*/

