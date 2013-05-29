import Piles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicPileMenuQuitter implements ActionListener {
		
	public GraphicPileMenuQuitter() {}
	
	/**
	 * Stop l'execution du programme et quitte la fenêtre
	 */
	public void actionPerformed(ActionEvent a) {
		System.exit(0);
	}// actionPerformed()
	
}// GraphicPileMenuQuitter