/**
 *La classe Annuaire représente un annuaire de personnes
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 4 02/10/2004
 */

public class Annuaire extends EnsembleTrieP {

	/* constructeur */
	public Annuaire() {
		super();
	}// Annuaire()

	/* donne le numéro de téléphone qui est dans l'annuaire pour la personne passée en paramètre */
	public int tel(Personne p){
		return p.tel();
	}// tel()

	/* donne la personne de numéro de tel passé en paramètre */
	public Personne telInv(int num) {
		for (int i=0 ; i<cardinal ; i++) {
			if(tab[i].tel() == num) {
				return tab[i];
			}// if
		}// for
		System.out.println("Le numero n'a pas ete trouve");
		return null;
	}// telInv()

	public static void main (String[] args) {
		Annuaire a1 = new Annuaire();
		Annuaire a2 = new Annuaire();

		System.out.println(a1);
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

		a1.ajoute(p1);
		a1.ajoute(p2);
		a1.ajoute(p3);
		a1.ajoute(p5);
		a1.ajoute(p9);

		a2.ajoute(p1);
		a2.ajoute(p2);
		a2.ajoute(p4);
		a2.ajoute(p6);
		a2.ajoute(p7);
		a2.ajoute(p9);

		System.out.println("Annuaire 1:"+a1);
		System.out.println("Annuaire 2:"+a2);
		System.out.println(a1.telInv(590812915));
		System.out.println(a1.telInv(590812944));
		System.out.println("Annuaires 1 et 2 égaux ?");
		System.out.println(a1.egal(a2));
		System.out.println("Intersection annuaires 1 et 2:");
		System.out.println(a1.intersection(a2));
		System.out.println("Union annuaires 1 et 2:");
		System.out.println(a1.union(a2));

	}// main()
}// Annuaire

/* Traces d'execution

Annuaire 1:
Antoine Doisnel : 998
Antoine2 Doisnel2 : 0
 
Annuaire 2:
Antoine Doisnel : 998
Bob Marley : 0
 
Antoine Doisnel : 998
Le numéro n'a pas été trouvé
null
Annuaires 1 et 2 égaux ?
false
Intersection annuaires 1 et 2:
Antoine Doisnel : 998
 
Union annuaires 1 et 2:
Cette personne est déjà dans la liste
Antoine Doisnel : 998
Antoine2 Doisnel2 : 0
Bob Marley : 0

*/
