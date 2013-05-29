import Piles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicPileVider implements ActionListener {
	
	MaPile p;
	JTextArea output;

	public GraphicPileVider(JTextArea jtextarea, MaPile mapile)  {
		p = mapile;
		output = jtextarea;
	}// GraphicPileVider()
	
	/**
	 * actionPerformed() vide totalement la pile puis renvoie dans la
	 * jtextarea ouput la string renvoyée par le toString de p
	 *@param void
	 *@return void
	 */
	public void actionPerformed(ActionEvent a) {
		p.viderPile();
		output.setText(p.toString()); // setText() inherited from javax.swing.text.JTextComponent			
	}
}// GraphicPileVider