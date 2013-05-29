import Piles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicPileEmpiler implements ActionListener {
	
	MaPile p;
	JTextField input;
	JTextArea output;
		
	public GraphicPileEmpiler(JTextField jtextfield, JTextArea jtextarea, MaPile mapile)  {
		p = mapile;
		input = jtextfield;
		output = jtextarea;
	}// GraphicPileEmpiler()
	
	/**
	 * actionPerformed() tente de récuperer le texte entré dans le jtextfield
	 * input, tente de dépiler de p l'entier qu'il aurait transcrit de input,
	 * puis efface le contenu de input et affiche le toString de p dans output
	 *@param void
	 *@return void
	 */
	public void actionPerformed(ActionEvent a) {
		try {
			int i = Integer.parseInt(input.getText()); // récupère l'entier
			p.empiler(new Integer(i));
			output.setText(p.toString());// setText() and getText() inherited
										 // from JTextComponent
			input.setText("");
		}// try
		catch (NumberFormatException numberformatexception) {
			output.setText("Entrez un\nnombre");
			input.setText("");
		}// catch()
		catch(PilePleineException e) {
			System.out.println(e.getMessage());
		}// catch()
	}// actionPerformed()
	
}// GraphicPileEmpiler