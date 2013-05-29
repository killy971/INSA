import Piles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicPileDepiler implements ActionListener {
	
	MaPile p;
	JTextArea output;

	public GraphicPileDepiler(JTextArea jtextarea, MaPile mapile)  {
		p = mapile;
		output = jtextarea;
	}// GraphicPileDepiler()
	
	/**
	 * actionPerformed() tente de depiler un objet de la pile p, puis
	 * met à jour la zone d'affichage output avec la chaine renvoyer par
	 * le toString de p
	 * Si la tentative échoue, le message d'erreur correspondant à la raison
	 * pour laquelle l'exception a étée déclenchée est renvoyeé en S.o.println()
	 *@param void
	 *@return void
	 */
	public void actionPerformed(ActionEvent a) {
		try {
			Object n = p.depiler();
			output.setText(p.toString()); // setText() inherited from JTextComponent			
		}// try
		catch(PileVideException e) {
			System.out.println(e.getMessage());
		}// catch()
	}
}// GraphicPileDepiler