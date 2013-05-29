/**
* Interface de la classe Pile
* @authorr Jean-Louis Pazat
* @version 1 (pas de gestion d'exception)
* date de creation : 28/09/2001
* derniere modification : 
*/
package Piles;
public interface Pile {                 

    /** "enl�ve" tous les objets de la pile. Toutes les r�f�rences sur les
	objets faites par la structure de donn�e sont supprim�es
    */
    public void    viderPile(); 

    /** met l'objet en sommet de pile. C'est la r�f�rence qui est mise 
	dans la structure, l'objet n'est pas copi� 
	@param  item la r�f�rence � empiler
    */
    public void    empiler(Object item);

    /** "enl�ve" l'objet du sommet de pile. La structure de donn�es
	ne conserve pas de r�f�rence sur l'objet enlev�
	@return la r�f�rence de l'objet du sommet de pile 
    */
    public Object  depiler();

    /** rend la r�f�rence de l'objet du sommet de pile. La structure
        de donn�e n'est pas modifi�e 
        @return la r�f�rence de l'objet du sommet de pile */
    public Object  sommetPile();

    /** dit si la pile est vide. 
    @return true si la pile est vide
     */
    public boolean pileVide();
}




