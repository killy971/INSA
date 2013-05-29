public class Annuaire{
  private final int CAPACITE = 50;
  
  private int _cardinal;
  private Personne _elements[];

  /* constructeur */
  public Annuaire() {
    ...;
  }

  /* donne le nombre d'éléments de l'ensemble */
  public int cardinal() {
    return ...;
  }

  /* rend vrai si l'ensemble est vide */ 
  public boolean estVide() {
    return ...;
  }

  /* rend vrai si le tableau représentant l'ensemble est plein */ 
  public boolean estPlein() {
    return ...;
  }
  
  /* rend vrai si la personne p est dans l'annuaire 
     même avec un autre numéro de tel */
  public boolean appartient(Personne p) {
    ...;
    return ...;
  } 

  /* rend le contenu de l'ensemble dans une chaîne 
     avec des retours a la ligne entre chaque enrgistrement */
  public String toString() {
    ...;
    return ...;
  }

  /* ajoute la personne p dans l'ensemble si elle n'est pas déja présente
     (avec le même numéro ou non), 
     sinon affiche un message d'erreur explicite.
     Si le tableau est plein affiche un message d'erreur 
  */
  public void ajoute(Personne p) {
    if ...;
  }

  /* enlève la personne p si elle est présente, même avec un autre numéro
     sinon ne fait rien */
  public void enleve(Personne p) {
    ...;
  }

  /* donne le numéro de téléphone qui est dans l'annuaire pour la personne 
     passée en paramètre */
  public int tel(Personne p){
    ...;
    return ...;
  }

  /* donne la personne de numéro de tel passé en paramètre */
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
