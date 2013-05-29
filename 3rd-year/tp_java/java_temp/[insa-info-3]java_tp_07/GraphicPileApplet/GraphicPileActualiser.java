import Piles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicPileActualiser implements ActionListener {
	
	JTextField input; // champ d'entrée de données
	JTextArea output; // champ d'affichage du contenu de la pile
	MaPile p;		  // pile utilisée

	public GraphicPileActualiser(JTextField jtextfield, JTextArea jtextarea, MaPile mapile)  {
		input = jtextfield;
		output = jtextarea;
		p = mapile;
	}// GraphicPileActualiser()
	
	/**
	 * actionPerformed() met à jour l'affichage du contenu de la pile et
	 * remet à blanc le champ d'entrer de données
	 *@param void
	 *@return void
	 */
	public void actionPerformed(ActionEvent a) {
		input.setText(""); // setText() inherited from JTextComponent
		output.setText(p.toString()); 			
	} // actionPerformed()
}// GraphicPileActualiser