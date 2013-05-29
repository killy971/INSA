/**
 * 
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 12 23/11/2004
 */

import java.lang.Math;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

public class EmploiDuTemps {
	
	private int nbEns; // nombre d'enseignants
	private HashMap[] enseign;
	
	public EmploiDuTemps(int n) {
		nbEns = n;
		enseign = new HashMap[nbEns];
		for(int i=0 ; i<nbEns ; i++) {
		enseign[i] = new HashMap();
		
		}
	}
	
	/**
	 * M�thode v�rifiant si une Map pass�e en param�tre contient parmis ses Key
	 * l'horaire pass�e en deuxi�me param�tre.
	 * Permet de remplacer la m�thode containsKey() qui n'a l'air de v�rifier
	 * que l'�galit� des adresses (et pas l'�galit� de la structure des objets)
	 *@param m : Map
	 *@param h : horaire du cours dont on souhaite savoir si elle est pr�sente
	 * parmi les Key de la Map m
	 *@return boolean : rend true si l'horaire existe dans les Key de la Map m
	 */
	public boolean contientHoraire(Map m, HoraireDeCours h) {
		Iterator i = m.keySet().iterator();
		while(i.hasNext()) {
			if(((HoraireDeCours)i.next()).equals(h)) {
				return true;
			}
		}// while
		return false;
	}// contientHoraire(Map m, HoraireDeCours h)

	/**
	 * M�thode pour v�rifier si l'enseignent e est libre � l'horaire h
	 *@param e : num�ro de l'enseignant
	 *@param h : horaire � v�rifier
	 *@return boolean : rend true si l'enseignant e est libre � l'horaire h,
	 * rend false sinon
	 */
	public boolean ensLibre(int e, HoraireDeCours h) {
		Set s = this.enseign[e].keySet();
		Iterator i = s.iterator();
		while(i.hasNext()) {
			if(!(h.compatible((HoraireDeCours)i.next()))) {
				return false;
			}
		}// while
		return true;
	}// ensLibre(int e, HoraireDeCours h)
	
	/**
	 * M�thode pour ajouter dans l'emploi du temps un cours pr�sent� par
	 * l'enseignant e, � l'heure h, dans la mati�re m.
	 *@param e : num�ro de l'enseignant
	 *@param h : horaire du cours
	 *@param m : mati�re
	 *@return void
	 */
	public void ajouterCours(int e, HoraireDeCours h, String m)
	throws OccupeException,InexistantException {
		if (e>=nbEns) throw new InexistantException();
		if (!ensLibre(e,h)) throw new OccupeException();
		Object temp = this.enseign[e].put((HoraireDeCours)h,(String)m);
		return;
	}// ajouterCours(int ens, HoraireDeCours h, String m)
	
	/**
	 * M�thode pour calculer le nombre de cours commen�ants � l'horaire h
	 *@param h : horaire
	 *@return int : nombre de cours dispens�s � l'horaire h
	 */
	public int nbCours(HoraireDeCours h) {
		int nbCours = 0;
		for(int i=0 ; i<nbEns ; i++) {
			// if(enseign[i].containsKey((HoraireDeCours)h)) {
			// Pose probl�me car v�rifie simplement si une cl� pointe vers
			// le m�me objet que h
			// Ne marche donc pas avec 2 objets diff�rents codant pourtant
			// la m�me horaire.
			if(contientHoraire(enseign[i],h)) {
				nbCours++;
			}
		}// for i
		return nbCours;
	}// nbCours(HoraireDeCours h)

	/**
	 * M�thode pour calculer le nombre de cours durant le jour j
	 *@param j : jour
	 *@return int : nombre de cours dispens�s le jour j
	 */
	public int nbCoursv1(String j) {
		// version 1, co�teuse en complexit�
		int nbCours = 0;
		for(int i=8 ; i<=18 ; i++) {
			nbCours += nbCours(new HoraireDeCours(j,i));
		}// for i
		return nbCours;
	}// nbCoursv1(String j)

	/**
	 * 
	 *@param 
	 *@return 
	 */
	public int nbCoursv2(String j) {
		// version 2, moins co�teuse en complexit�
		int nbCours = 0;
		// ...
		return nbCours;
	}// nbCoursv2(String j)
	
	/**
	 * Renvoie le nombre de cours dispens�s le matin par un enseignant pass�
	 * en param�tre. Un cours est consid�r� comme effectu� le matin si son
	 * heure de d�but est inf�rieure � 12.
	 *@param enseignant
	 *@return int : nombre de cours dispens�s le matin par l'enseignant 
	 */
	public int nbCoursMatin(int enseignant) {
		Iterator i = this.enseign[enseignant].keySet().iterator();
		int nbH = 0;
		while (i.hasNext()) {
			if (((HoraireDeCours)i.next()).getHeure()<12) {
				nbH++;
			}
		}// while
		return nbH;
	}
	
	/**
	 * M�thode pour calculer quel est l'enseignant qui dispense le plus de
	 * cours le matin
	 *@param void
	 *@return int : num�ro de l'enseignant qui dispense le plus de cours
	 * le matin
	 */
	public int bestMorningTeacher() {
		int bestMorningTeacher = 0;
		int nbCoursBest = nbCoursMatin(bestMorningTeacher);
		for(int i=1 ; i<nbEns ; i++) {
			int nbCours = nbCoursMatin(i);
			if (nbCoursBest<nbCours) {
				nbCoursBest = nbCours;
				bestMorningTeacher = i;
			}
		}// for i
		return bestMorningTeacher;
	}// bestMorningTeacher()

	/**
	 * M�thode pour calculer le nombre de mati�re diff�rentes qu'enseigne
	 * l'enseignant pass� en param�tre
	 *@param enseignant
	 *@return int : nombre de mati�re qu'enseigne l'enseignant
	 */
	public int nbMat(int enseignant) {
		ArrayList matieres = new ArrayList();
		Iterator i = this.enseign[enseignant].values().iterator();
		while(i.hasNext()) {
			String matTemp = (String)i.next();
			if(!matieres.contains((String)matTemp)) {
				matieres.add(matTemp);
			}
		}// while
		return matieres.size();
	}// nbMat()

	/**
	 * M�thode pour calculer quel est l'enseignant qui enseigne le plus de
	 * mati�res diff�rentes
	 *@param void
	 *@return int : num�ro de l'enseignant enseignant le plus de mati�res diffs
	 */
	public int bestVariousTeacher() {
		int bestVariousTeacher = 0;
		int nbMatBest = nbMat(bestVariousTeacher);
		for(int i=1 ; i<nbEns ; i++) {
			int nbMat = nbMat(i);
			if (nbMatBest<nbMat) {
				nbMatBest = nbMat;
				bestVariousTeacher = i;
			}
		}
		return bestVariousTeacher;
	}// bestVariousTeacher()
	
	/**
	 * 
	 *@param 
	 *@return 
	 */
	public Map makeEmploiDuTempsv2() {
		HashMap emploiDuTempsv2 = new HashMap();
		// ...
		return emploiDuTempsv2;
	}// makeEmploiDuTempsv2()
	
	/**
	 * M�thode pour rendre une String faisant �tat de l'emploi du temps
	 *@param void
	 *@return String : contenu de l'emploi du temps
	 */
	public String toString() {
		String s = "";
		for(int i=0 ; i<nbEns ; i++) {
			s += "Enseignant"+i+": "+enseign[i].toString()+"\n";
		}
		return s;
	}// toString()
	
	public static void main(String args[]) {
		EmploiDuTemps emp = new EmploiDuTemps(6);
		try {
			HoraireDeCours lu08 = new HoraireDeCours("Lundi",8);
			HoraireDeCours lu08bis = new HoraireDeCours("Lundi",8);
			HoraireDeCours lu09 = new HoraireDeCours("Lundi",9);
			HoraireDeCours lu10 = new HoraireDeCours("Lundi",10);
			HoraireDeCours lu11 = new HoraireDeCours("Lundi",11);
			HoraireDeCours lu12 = new HoraireDeCours("Lundi",12);
			HoraireDeCours lu14 = new HoraireDeCours("Lundi",14);
			HoraireDeCours lu15 = new HoraireDeCours("Lundi",15);
			HoraireDeCours lu16 = new HoraireDeCours("Lundi",16);
			HoraireDeCours ma08 = new HoraireDeCours("Mardi",8);
			HoraireDeCours ma09 = new HoraireDeCours("Mardi",9);
			HoraireDeCours ma10 = new HoraireDeCours("Mardi",10);
			HoraireDeCours ma11 = new HoraireDeCours("Mardi",11);
			HoraireDeCours me08 = new HoraireDeCours("Mercredi",8);
			HoraireDeCours me10 = new HoraireDeCours("Mercredi",10);
			HoraireDeCours je08 = new HoraireDeCours("Jeudi",8);
			HoraireDeCours je10 = new HoraireDeCours("Jeudi",10);
			HoraireDeCours ve10 = new HoraireDeCours("Vendredi",10);
			
			
			emp.ajouterCours(0,lu08,"Java");
			emp.ajouterCours(0,lu10,"Java");
			emp.ajouterCours(0,lu12,"Archi");
			emp.ajouterCours(0,lu14,"Java");
			emp.ajouterCours(0,lu16,"Java");
			emp.ajouterCours(0,ma09,"Archi");
			emp.ajouterCours(0,ma11,"Java");
			emp.ajouterCours(1,lu08,"CUL&COM");
			emp.ajouterCours(2,lu09,"ASM");
			emp.ajouterCours(3,lu08bis,"Systeme");
			emp.ajouterCours(4,lu10,"Sport");
			emp.ajouterCours(5,lu15,"TP Java");
			
			System.out.println(emp);
			System.out.println("Nombre de cours Lundi a 8h:"+emp.nbCours(lu08));
			System.out.println("BestMorningTeacher: "+emp.bestMorningTeacher());
			
			emp.ajouterCours(2,lu11,"ASM");
			emp.ajouterCours(2,ma08,"ASM");
			emp.ajouterCours(2,ma10,"ASM");
			emp.ajouterCours(2,me08,"ASM");
			emp.ajouterCours(2,me10,"ASM");
			emp.ajouterCours(2,je08,"ASM");
			emp.ajouterCours(2,je10,"ASM");

			System.out.println(emp);
			System.out.println("BestMorningTeacher: "+emp.bestMorningTeacher());			
			System.out.println("bestVariousTeacher: "+emp.bestVariousTeacher());
			
			emp.ajouterCours(5,je08,"Matiere 1");
			emp.ajouterCours(5,je10,"Matiere 2");
			emp.ajouterCours(5,ve10,"Matiere 3");

			System.out.println(emp);
			System.out.println("bestVariousTeacher: "+emp.bestVariousTeacher());
			
		}
		catch (Exception e) {
			System.out.println("bouh");
			System.out.println(e.getMessage());
		}
		
	}// main
	
}// EmploiDuTemps