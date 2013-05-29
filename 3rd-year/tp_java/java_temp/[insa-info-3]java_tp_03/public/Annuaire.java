public class Annuaire{
  private final int CAPACITE = 50;
  
  private int _cardinal;
  private Personne _elements[];

  /* constructeur */
  public Annuaire() {
    ...;
  }

  /* donne le nombre d'�l�ments de l'ensemble */
  public int cardinal() {
    return ...;
  }

  /* rend vrai si l'ensemble est vide */ 
  public boolean estVide() {
    return ...;
  }

  /* rend vrai si le tableau repr�sentant l'ensemble est plein */ 
  public boolean estPlein() {
    return ...;
  }
  
  /* rend vrai si la personne p est dans l'annuaire 
     m�me avec un autre num�ro de tel */
  public boolean appartient(Personne p) {
    ...;
    return ...;
  } 

  /* rend le contenu de l'ensemble dans une cha�ne 
     avec des retours a la ligne entre chaque enrgistrement */
  public String toString() {
    ...;
    return ...;
  }

  /* ajoute la personne p dans l'ensemble si elle n'est pas d�ja pr�sente
     (avec le m�me num�ro ou non), 
     sinon affiche un message d'erreur explicite.
     Si le tableau est plein affiche un message d'erreur 
  */
  public void ajoute(Personne p) {
    if ...;
  }

  /* enl�ve la personne p si elle est pr�sente, m�me avec un autre num�ro
     sinon ne fait rien */
  public void enleve(Personne p) {
    ...;
  }

  /* donne le num�ro de t�l�phone qui est dans l'annuaire pour la personne 
     pass�e en param�tre */
  public int tel(Personne p){
    ...;
    return ...;
  }

  /* donne la personne de num�ro de tel pass� en param�tre */
  public Personne telInv(int num){
    ...;
    return ...;
  }

  public static void main (String[] args) {
    Annuaire annuaire = new Annuaire();
    System.out.println(annuaire);
    Personne p = new Personne("Antoine","Doisnel");
    p.affecteTel(998);
    annuaire.ajoute(p);
    System.out.println(annuaire);
    // etc.
  }
}
