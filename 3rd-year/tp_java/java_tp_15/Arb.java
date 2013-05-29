// Version "allegée" de l'interface ArbreBinaire
// (sans gestion de freres ni ajouts dans sous-arbres non vides)
// generalisee a n fils (de 1 a n) pour le TP
//
// version 15/01/2003

public interface Arb {
    /**
     * Methode pour tester si un arbre est vide
     * @return un booleen indiquant si l'arbre est vide
     */
    public boolean arbreVide();
    /**
     * Methode pour tester si ec est en dehors de l'arbre
     * @return un booleen indiquant si ec est en dehors de l'arbre
     */
    public boolean horsArbre();
    /**
     * Methode pour placer l'ec a la racine
     */
    public void positRac();
    /**
     * Methode pour placer l'ec sur le pere
     */
    public void positPere();
    /**
     * Methode pour placer l'ec sur le i-eme fils 
     * (le premier fils est numeroté 1)
     */
    public void positFils(int i);

    /**
     * Methode pour tester si ec est sur la racine
     * @return un booleen indiquant si ec est sur la racine
     */
    public boolean racine();
    /**
     * Methode pour tester si ec est sur une feuille
     * @return un booleen indiquant si ec est sur une feuille
     */
    public boolean feuille();
    /**
     * Methode pour tester si ec dispose d'un i-eme fils
     * @return un booleen indiquant si ec dispose d'un fils numero i
     */
    public boolean fils(int i);

   /**
     * Methode pour acceder a la valeur l'ec
     * @return Object
     * rend la valeur de l'objet courant
     */
    public Object valNoeud();
   /**
     * Methode pour modifier la valeur de l'ec
     * @param v valeur du nouvel element
     */
    public void modifNoeud(Object v);

    /**
     * Methode pour ajouter un nouvel element comme fils i de l'ec
     * n'ayant pas un tel fils numero i
     * @param v valeur du nouvel element
     */
    public void ajoutFils(int i,Object v);

    /**
     * Methode pour placer un premier element a la racine d'un arbre vide.
     * @param v valeur du nouvel element
     */
    public void creerRac(Object v);

    /**
     * Methode pour supprimer  l'arbre commencant
     * a la position ec
     */
    public void oterArbre();
    
    /**
     * Methode pour supprimer l'ec n'ayant au plus qu'un fils
     */
    public void oterNoeud();

   /**
     * Methode pour afficher l'arbre
     * @return une chaine representant l'arbre
     */
    public String toString();
    
} // interface Arb