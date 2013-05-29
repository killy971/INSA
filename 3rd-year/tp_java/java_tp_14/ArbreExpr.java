/**
  Exemple de quelques fonctions utilisant la classe Arbre
  => à transformer en méthodes pour la classe ArbreExpr à constituer...

  @author Insa de Rennes, Département Informatique
  @version 08/01/2004, Étudiant
*/

import java.util.Map;
import java.util.HashMap;
import java.lang.Math;

public class ArbreExpr extends Arbre
{

	static private Map val = new HashMap();

	public ArbreExpr() {
		super();
	}
	
	public ArbreExpr(Object obj, Arbre arbre, Arbre arbre1) {
		super(obj,arbre,arbre1);
	}
	
	public ArbreExpr(Object obj) {
		super(obj);
	}
	
	public int hauteur() {
		if(this.vide()) {
			return 0;
		} else {
			return 1 + Math.max(((ArbreExpr)filsg).hauteur(),((ArbreExpr)filsd).hauteur());
		}
	}
	
	public int denombrerNoeud(String n) {
		if(this.vide()) {
			return 0;
		} else {
			if (((String)this.racine).equals(n)) {
				return 1 + ((ArbreExpr)filsg).denombrerNoeud(n)+((ArbreExpr)filsd).denombrerNoeud(n);
			} else {
				return ((ArbreExpr)filsg).denombrerNoeud(n)+((ArbreExpr)filsd).denombrerNoeud(n);
			}
		}
	}
	
	public void remplacerNoeud(String n1, String n2) {
		if(this.vide()) {
			return;
		} else {
			if (((String)this.racine).equals(n1)) {
				this.modifracine(n2);
			}
			((ArbreExpr)this.filsg).remplacerNoeud(n1,n2);
			((ArbreExpr)this.filsd).remplacerNoeud(n1,n2);
		}
	}
	
	public void afficherExpr() {
		if (!this.vide()) {
			System.out.print("(");
			((ArbreExpr)filsg).afficherExpr();
			System.out.print(this.racine());
			((ArbreExpr)filsd).afficherExpr();
			System.out.print(")");
		}
	}
	
	public void associerValeur(String symbole, double valeur) {
		val.put(symbole,new Double(valeur));
	}

	public Double valeur(String v) {
		if (val.containsKey(v)) {
			return (Double)val.get(v);
		} else {
			return new Double(v);
		}
	}

//	public ArbreExpr simplifier() {
//		ArbreExpr res = new ArbreExpr();
//		if()
//	}

	public double evaluer() {
		if(this.filsg.vide() && this.filsd.vide()) {
			return (valeur((String)this.racine())).doubleValue();
		} else {
			if(((String)this.racine).equals("+")) {
				return ((ArbreExpr)filsg).evaluer() + ((ArbreExpr)filsd).evaluer();
			} else if(((String)this.racine).equals("-")) {
				return ((ArbreExpr)filsg).evaluer() - ((ArbreExpr)filsd).evaluer();
			} else if(((String)this.racine).equals("*")) {
				return ((ArbreExpr)filsg).evaluer() * ((ArbreExpr)filsd).evaluer();
			} else if(((String)this.racine).equals("/")) {
				return ((ArbreExpr)filsg).evaluer() / ((ArbreExpr)filsd).evaluer();
			} else {
				return 0; // bouh
			}
		}
	}

	

  /** Quelques tests
   */
  public static void main(String[] args) {
    Arbre a1 = new Arbre("+",
			 new Arbre("*", new Arbre("a"), new Arbre("b") ),
			 new Arbre("c")
			 );
    //afficher_arbre(a1,0);

    //Arbre a2 = saisieArbreExpr("racine de a2");
    //afficher_arbre(a2,0);
	
	ArbreExpr a3 = new ArbreExpr("+",
			 new ArbreExpr("*", new ArbreExpr("a"), new ArbreExpr("b") ),
			 new ArbreExpr("c")
			 );
	
	a3.associerValeur(new String("a"),(double)2);
	a3.associerValeur(new String("b"),(double)3);
	a3.associerValeur(new String("c"),(double)5);
	
	afficher_arbre(a3,0);

	System.out.println(a3.evaluer());
    
  }


  /* Vue (en rotation vers la gauche) de la structure arborescente
   */
  static void afficher_arbre(Arbre A, int decale) {
    if ( !A.vide() ) { // d'abord filsd car vue "en rotation" d'1/4 de tour
      afficher_arbre( A.filsd() , decale+1 );
      afficher_espaces(decale); System.out.println( A.racine() );
      afficher_arbre( A.filsg() , decale+1);
    }
  }


  static void afficher_espaces(int n) {
    for(int i=1; i<=n; i++)
      System.out.print("  ");	// chaque niveau décalé 2 espaces plus loin
  }


  /* Saisie d'un arbre complet, sous-arbre par sous-arbre...
     Si on veut manuellement placer un sous-arbre vide, on peut saisir "nil".
  */
  static Arbre saisieArbreExpr(String msg) {
    Arbre A, filsg, filsd;
    String valnoeud;

    System.out.print("entrer la valeur du noeud "+msg+" : ");
    valnoeud = Lecture.lireString();

    if ( ! valnoeud.equals("nil") ) {
      if ( operateur(valnoeud) ) {
	filsg = saisieArbreExpr("filsg de "+valnoeud);
	filsd = saisieArbreExpr("filsd de "+valnoeud);
	A = new Arbre(valnoeud, filsg, filsd);
      }
      else
	A = new Arbre(valnoeud);
    }
    else {
      A = new Arbre();
    }
    return A;
  }

  static boolean operateur(String v) {
    return ( v.equals("+") || v.equals("-")
	     || v.equals("*") || v.equals("/")
	     || v.equals("^")
	     );
  }
}
