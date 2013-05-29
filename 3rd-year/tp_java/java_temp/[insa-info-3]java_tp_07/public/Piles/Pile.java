/**
* Interface de la classe Pile
* @authorr Jean-Louis Pazat
* @version 1 (pas de gestion d'exception)
* date de creation : 28/09/2001
* derniere modification : 
*/
package Piles;
public interface Pile {                 

    /** "enlève" tous les objets de la pile. Toutes les références sur les
	objets faites par la structure de donnée sont supprimées
    */
    public void    viderPile(); 

    /** met l'objet en sommet de pile. C'est la référence qui est mise 
	dans la structure, l'objet n'est pas copié 
	@param  item la référence à empiler
    */
    public void    empiler(Object item);

    /** "enlève" l'objet du sommet de pile. La structure de données
	ne conserve pas de référence sur l'objet enlevé
	@return la référence de l'objet du sommet de pile 
    */
    public Object  depiler();

    /** rend la référence de l'objet du sommet de pile. La structure
        de donnée n'est pas modifiée 
        @return la référence de l'objet du sommet de pile */
    public Object  sommetPile();

    /** dit si la pile est vide. 
    @return true si la pile est vide
     */
    public boolean pileVide();
}




