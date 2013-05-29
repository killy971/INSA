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

import java.util.LinkedList;

public class Noeud {
	Object val;
	int pere;
	LinkedList fils;
	
	public Noeud(Object v, int p) {
		val = v;
		pere = p;
		fils = new LinkedList();
	}
	
	public void setVal(Object v) {
		val = v;
	}
	
	public Object getVal() {
		return val;
	}

	public int nbFils() {
		return fils.size();
	}


	public boolean aFils() {
		return nbFils()>0;
	}
	
	public boolean aFils(int i) {
		return nbFils()>=i;
	}

	public int getPere() {
		return pere;
	}

	public void setPere(int i) {
		pere = i;
	}
	
	public int[] getFils() {
		int[] rep = new int[nbFils()];
		for (int i=0 ; i< nbFils() ; i++) {
			rep[i] = Integer.parseInt(((Integer)fils.get(i)).toString());
		}
		return rep;
	}
	
	public int getFils(int i) {
		return Integer.parseInt(((Integer)fils.get(i-1)).toString());
	}
	
	public void ajoutFils(int numFils,int indexFils) {
		fils.add(numFils-1, new Integer(indexFils));
	}

// remplace le fils d index c dans la liste des noeuds par le noeud d index f
	public void modifFils(int c, int f) {
		int i = fils.indexOf(new Integer(c));
		fils.set(i,new Integer(f));		
	}

	public void supprimeFils(int i) {
		fils.remove(new Integer(i));
	}
	
	public String toString() {
		return "Valeur :"+val+", pere: "+pere+", fils: "+fils.toString()+", nbFils: "+nbFils();
	}
	
}
