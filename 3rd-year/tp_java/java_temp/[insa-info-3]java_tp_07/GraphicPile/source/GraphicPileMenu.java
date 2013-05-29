import Piles.MaPile;
import java.awt.*;
import javax.swing.*;

public class GraphicPileMenu extends JMenuBar {
	
	public GraphicPileMenu(JTextArea jtextarea, MaPile mapile) {
		JMenu menuPile = new JMenu("Pile");

		// item nouvelle pile
		JMenuItem menuPileItemNew = new JMenuItem("Nouvelle Pile");
		menuPileItemNew.addActionListener(new GraphicPileVider(jtextarea, mapile));
		menuPileItemNew.setToolTipText("Cliquez ici pour creer une nouvelle pile vide.");
		
		// item aide
		JMenuItem menuPileItemAide = new JMenuItem("Aide");
		menuPileItemAide.addActionListener(new GraphicPileMenuAide());
		menuPileItemAide.setToolTipText("Cliquez ici pour obtenir de l'aide.");
		
		// item quitter
		JMenuItem menuPileItemQuitter = new JMenuItem("Quitter");
		menuPileItemQuitter.addActionListener(new GraphicPileMenuQuitter());
		menuPileItemQuitter.setToolTipText("Cliquez ici pour quitter.");

		menuPile.add(menuPileItemNew);
		menuPile.add(menuPileItemAide);
		menuPile.add(new JSeparator());
		menuPile.add(menuPileItemQuitter);
		this.add(menuPile);
	}// GraphicPileMenu()
	
}