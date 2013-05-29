import Piles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicPileMenuAide implements ActionListener {
		
	public GraphicPileMenuAide() {}
	
	/**
	 * Declenche l'ouverture d'une fenêtre d'aide
	 */
	public void actionPerformed(ActionEvent a) {
		JFrame frameMaPileAide = new JFrame("Aide");

		// jtextareaAideEmpiler
		JTextArea jtextareaAideEmpiler = new JTextArea();
		jtextareaAideEmpiler.setText("Pour empiler un objet, tappez un nombre dans le champ prevu a cet effet, puis appuyez sur la touche ENTRER de votre clavier.");
		jtextareaAideEmpiler.setBackground(Color.gray);
		jtextareaAideEmpiler.setLineWrap(true);
		
		// jtextareaAideDepiler
		JTextArea jtextareaAideDepiler = new JTextArea();
		jtextareaAideDepiler.setText("Pour depiler l'objet au sommet de la pile, cliquez sur le boutton 'Depiler'");
		jtextareaAideDepiler.setBackground(Color.gray);
		jtextareaAideDepiler.setLineWrap(true);
		
		// jtextareaAideVider
		JTextArea jtextareaAideVider = new JTextArea();
		jtextareaAideVider.setText("Pour vider completement la pile, cliquez sur le boutton 'Vider'");
		jtextareaAideVider.setBackground(Color.gray);
		jtextareaAideVider.setLineWrap(true);
		
		// jtextareaAideQuitter
		JTextArea jtextareaAideQuitter = new JTextArea();
		jtextareaAideQuitter.setText("Pour quitter le programme cliquez sur le menu 'Pile' pour sur 'Quitter'");
		jtextareaAideQuitter.setBackground(Color.gray);
		jtextareaAideQuitter.setLineWrap(true);
		
		Container cMaPileAide = frameMaPileAide.getContentPane();
		cMaPileAide.setLayout(new GridLayout(4,0));
		cMaPileAide.add(jtextareaAideEmpiler);
		cMaPileAide.add(jtextareaAideDepiler);
		cMaPileAide.add(jtextareaAideVider);
		cMaPileAide.add(jtextareaAideQuitter);

		
		// ne ferme que cette fenêtre si l'on clique sur la croix pour fermer
		frameMaPileAide.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// definition de la taille et de la position de la fenêtre d'aide
		frameMaPileAide.setBounds(100,100,400,300);
		
		// redimension auto pour occuper le moins de place possible
		//frameMaPileAide.pack();
		
		
		frameMaPileAide.setVisible(true);
		
	}// actionPerformed()
	
}// GraphicPileMenuAide