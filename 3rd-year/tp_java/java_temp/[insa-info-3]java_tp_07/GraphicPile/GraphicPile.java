/**
 * La classe GraphicPile est une interface graphique utilisant la classe
 * MaPile afin de rendre son utilisation plus interactive
 *@author Nargeot Guillaume
 *@author Leroy Vincent
 *@version TP 7 05/10/2004
 */

import Piles.MaPile;
import java.awt.*;
import javax.swing.*;

public class GraphicPile {
	
	static JFrame frameMaPile = new JFrame("MaPile GUI - ver 1");
    
	public GraphicPile() {} // constructeur vide
	
	/**
	 * Processus de création de l'interface graphique
	 *@param void
	 *@return void
	 */
	public static void setupFrame() {
		// pile utilisée
		MaPile mapile = new MaPile();
				
		// definition des jpanel
		JPanel jpanelTotal =  new JPanel(); // contient le bloc total
		JPanel jpanelGauche = new JPanel(); // contient le bloc vertical gauche
		JPanel jpanelDroite = new JPanel(); // contient le bloc vertical droite
		JPanel jpanelInputPileElement = new JPanel(); // contient le bloc d'input
		JPanel jpanelDepiler = new JPanel(); // contient le boutton pour depiler
		JPanel jpanelVider = new JPanel(); // contient le boutton pour vider
		JPanel jpanelActualiser = new JPanel(); // contient le boutton pour actualiser
				
		//JPanel jpanelDisplay  = new JPanel(); // contient le bloc d'affichage

		// JPanel jpanelDisplay
		JTextArea jtextareaDisplay = new JTextArea("Pile vide\ninitialisee");
		JScrollPane jscrollpaneDisplay = new JScrollPane(jtextareaDisplay);
		//jpanelDisplay.add(jtextareaDisplay);
		//jtextareaDisplay.add(jscrollpaneDisplay);

		// JPanel jpanelInputPileElement
		JLabel jlabelInputPileElement = new JLabel(" Valeur : ");
		JTextField jtextfieldInputPileElement = new JTextField("");
		jpanelInputPileElement.setLayout(new GridLayout(0,2));
		jtextfieldInputPileElement.addActionListener(new GraphicPileEmpiler(jtextfieldInputPileElement, jtextareaDisplay, mapile));
		jpanelInputPileElement.add(jlabelInputPileElement);
		jpanelInputPileElement.add(jtextfieldInputPileElement);	
		
		// JPanel jpanelDepiler
		JButton jbuttonDepiler = new JButton("Depiler");
		jbuttonDepiler.setToolTipText("Cliquez ici pour enlever un element de la pile.");
		jbuttonDepiler.addActionListener(new GraphicPileDepiler(jtextareaDisplay, mapile));
		jpanelDepiler.add(jbuttonDepiler);

		// JPanel jpanelVider
		JButton jbuttonVider = new JButton("Vider");
		jbuttonVider.setToolTipText("Cliquez ici pour enlever vider la pile.");
		jbuttonVider.addActionListener(new GraphicPileVider(jtextareaDisplay, mapile));
		jpanelVider.add(jbuttonVider);
		
		// JPanel jpanelActualiser
		JButton jbuttonActualiser = new JButton("Actualiser");
		jbuttonActualiser.setToolTipText("Cliquez ici pour actualiser l'affichage du contenu de la pile");
		jbuttonActualiser.addActionListener(new GraphicPileActualiser(jtextfieldInputPileElement, jtextareaDisplay, mapile));
		jpanelActualiser.add(jbuttonActualiser);

		// JPanel jpanelGauche
		jpanelGauche.setLayout(new GridLayout(4,0));
		jpanelGauche.add(jpanelInputPileElement);
		jpanelGauche.add(jpanelDepiler);
		jpanelGauche.add(jpanelVider);
		jpanelGauche.add(jpanelActualiser);
		
		// JPanel jpanelDroite
		jpanelDroite.setLayout(new GridLayout(1,0));
		jpanelDroite.add(jscrollpaneDisplay); // on met le jtextarea directement
											// sans passer par un jpanel de
											// façon à ce que la zone de display
											// occupe complètement le
											// jpanel jpanelDroite
		
		// JPanel jpanelTotal
		frameMaPile.getContentPane().setLayout(new BorderLayout());
		jpanelTotal.setLayout(new GridLayout(0,2));
		jpanelTotal.add(jpanelGauche); // ajout du bloc vertical gauche
		jpanelTotal.add(jpanelDroite); // ajout du bloc vertical droite
		frameMaPile.getContentPane().add(jpanelTotal, "Center");
		
		// Ajout du menu
		frameMaPile.setJMenuBar(new GraphicPileMenu(jtextareaDisplay, mapile));

		//ici on oblige à utiliser le menu pour quitter le programme
		//EXIT_ON_CLOSE si l'ont veut un comportement normal
		frameMaPile.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// definition de la taille et de la position de la fenêtre
		// frameMaPile.setSize(200,200); si l'on ne veut spécifier que la taille
		frameMaPile.setBounds(100,100,300,300);
		
		// redimension auto pour occuper le moins de place possible
		frameMaPile.pack();
	}// setupFrame()
	
	public static void main(String[] args) {
		setupFrame();
		frameMaPile.setVisible(true); // rendre la fenêtre visible
	}// main()
}// GraphicPile