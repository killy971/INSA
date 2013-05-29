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
	 * Méthode vérifiant si une Map passée en paramètre contient parmis ses Key
	 * l'horaire passée en deuxième paramètre.
	 * Permet de remplacer la méthode containsKey() qui n'a l'air de vérifier
	 * que l'égalité des adresses (et pas l'égalité de la structure des objets)
	 *@param m : Map
	 *@param h : horaire du cours dont on souhaite savoir si elle est présente
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
	 * Méthode pour vérifier si l'enseignent e est libre à l'horaire h
	 *@param e : numéro de l'enseignant
	 *@param h : horaire à vérifier
	 *@return boolean : rend true si l'enseignant e est libre à l'horaire h,
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
	 * Méthode pour ajouter dans l'emploi du temps un cours présenté par
	 * l'enseignant e, à l'heure h, dans la matière m.
	 *@param e : numéro de l'enseignant
	 *@param h : horaire du cours
	 *@param m : matière
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
	 * Méthode pour calculer le nombre de cours commençants à l'horaire h
	 *@param h : horaire
	 *@return int : nombre de cours dispensés à l'horaire h
	 */
	public int nbCours(HoraireDeCours h) {
		int nbCours = 0;
		for(int i=0 ; i<nbEns ; i++) {
			// if(enseign[i].containsKey((HoraireDeCours)h)) {
			// Pose problème car vérifie simplement si une clé pointe vers
			// le même objet que h
			// Ne marche donc pas avec 2 objets différents codant pourtant
			// la même horaire.
			if(contientHoraire(enseign[i],h)) {
				nbCours++;
			}
		}// for i
		return nbCours;
	}// nbCours(HoraireDeCours h)

	/**
	 * Méthode pour calculer le nombre de cours durant le jour j
	 *@param j : jour
	 *@return int : nombre de cours dispensés le jour j
	 */
	public int nbCoursv1(String j) {
		// version 1, coûteuse en complexité
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
		// version 2, moins coûteuse en complexité
		int nbCours = 0;
		// ...
		return nbCours;
	}// nbCoursv2(String j)
	
	/**
	 * Renvoie le nombre de cours dispensés le matin par un enseignant passé
	 * en paramètre. Un cours est considéré comme effectué le matin si son
	 * heure de début est inférieure à 12.
	 *@param enseignant
	 *@return int : nombre de cours dispensés le matin par l'enseignant 
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
	 * Méthode pour calculer quel est l'enseignant qui dispense le plus de
	 * cours le matin
	 *@param void
	 *@return int : numéro de l'enseignant qui dispense le plus de cours
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
	 * Méthode pour calculer le nombre de matière différentes qu'enseigne
	 * l'enseignant passé en paramètre
	 *@param enseignant
	 *@return int : nombre de matière qu'enseigne l'enseignant
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
	 * Méthode pour calculer quel est l'enseignant qui enseigne le plus de
	 * matières différentes
	 *@param void
	 *@return int : numéro de l'enseignant enseignant le plus de matières diffs
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
	 * Méthode pour rendre une String faisant état de l'emploi du temps
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