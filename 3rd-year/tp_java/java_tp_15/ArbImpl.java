/*
 * Created on 4 janv. 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author gnargeot
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.util.ArrayList;


public class ArbImpl implements Arb {
	
	ArrayList noeuds;
	int ec;
	int racine;
	
	public ArbImpl (Object v) {
		noeuds = new ArrayList();
		creerRac(v);
		positRac();
	}
	
	/* (non-Javadoc)
	 * @see Arb#arbreVide()
	 */
	public boolean arbreVide() {
		return noeuds.isEmpty();
	}

	/* (non-Javadoc)
	 * @see Arb#horsArbre()
	 */
	public boolean horsArbre() {
		return (ec < 0 || ec >= noeuds.size());
	}

	/* (non-Javadoc)
	 * @see Arb#positRac()
	 */
	public void positRac() {
		ec = racine;
	}

	/* (non-Javadoc)
	 * @see Arb#positPere()
	 */
	public void positPere() {
		ec = ((Noeud)noeuds.get(ec)).getPere();

	}

	/* (non-Javadoc)
	 * @see Arb#positFils(int)
	 */
	public void positFils(int i) {
		ec = ((Noeud)noeuds.get(ec)).getFils(i);
	}

	/* (non-Javadoc)
	 * @see Arb#racine()
	 */
	public boolean racine() {
		return (ec == racine);
	}

	/* (non-Javadoc)
	 * @see Arb#feuille()
	 */
	public boolean feuille() {
		return ((Noeud)noeuds.get(ec)).aFils();
	}

	/* (non-Javadoc)
	 * @see Arb#fils(int)
	 */
	public boolean fils(int i) {
		return ((Noeud)noeuds.get(ec)).aFils(i);
	}

	/* (non-Javadoc)
	 * @see Arb#valNoeud()
	 */
	public Object valNoeud() {
		return ((Noeud)noeuds.get(ec)).getVal();
	}

	/* (non-Javadoc)
	 * @see Arb#modifNoeud(java.lang.Object)
	 */
	public void modifNoeud(Object v) {
		((Noeud)noeuds.get(ec)).setVal(v);
	}

	/* (non-Javadoc)
	 * @see Arb#ajoutFils(int, java.lang.Object)
	 */
	public void ajoutFils(int i, Object v) {
		Noeud n = ((Noeud)noeuds.get(ec));
		if(!(i>n.nbFils()+1)) {
			Noeud nouveau = new Noeud(v,ec);
			noeuds.add(nouveau);
			n.ajoutFils(i,noeuds.size()-1);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see Arb#creerRac(java.lang.Object)
	 */
	public void creerRac(Object v) {
		Noeud rac = new Noeud(v,0);
		racine = 0;
		noeuds.add((Object)rac);
	}

	public String toString() {
		String s = "";
		for(int i=0 ; i<noeuds.size() ; i++) {
			Noeud n = ((Noeud)noeuds.get(i));
			if(n != null) {
				s += "Noeud: "+i+", "+n.toString()+"\n";
			}
		}
	return s;
	}

	/* (non-Javadoc)
	 * @see Arb#oterArbre()
	 */
	public void oterArbre() {
/*
		int[] l = ((Noeud)noeuds.get(ec)).getFils();
		for (int i=0 ; i<l.length ; i++) {
			l[i]oterArbre();
		}
*/
	}
	
	/* (non-Javadoc)
	 * @see Arb#oterNoeud()
	 */
	public void oterNoeud() {
		Noeud n = ((Noeud)noeuds.get(ec));
		if (n.nbFils()==0) {
			int f = ec;
			ec = n.getPere();
			Noeud p = ((Noeud)noeuds.get(ec));
			p.supprimeFils(f);
			noeuds.set(f,null);
		} else if (n.nbFils()==1) {
			int c = ec;
			int pere = n.getPere();
			int fils = n.getFils(1);
			Noeud p = ((Noeud)noeuds.get(fils));
			p.setPere(pere);
			ec = pere;
			p = ((Noeud)noeuds.get(pere));
			p.modifFils(c,fils);
			noeuds.set(c,null);
		} else {
			System.out.println("erreur");
		}
	}
	
	public static void main(String args[]) {
		ArbImpl a = new ArbImpl("R");
		a.ajoutFils(1,"A");
		a.ajoutFils(2,"B");
		a.positFils(1);
		a.ajoutFils(1,"C");
		a.ajoutFils(2,"D");
		a.ajoutFils(3,"E");
		a.positPere();
		a.positFils(2);
		a.ajoutFils(1,"F");
		System.out.println(a.toString());
		a.oterNoeud();
		System.out.println(a.toString());
	}
	
}