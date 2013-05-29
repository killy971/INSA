//v 1.3
// known bugs : il manque cruellement insérer ...
// donner seulement a faire la gestion des évènements

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
import sdd.*;

public class Visionneuse implements ActionListener{

    /***********************************************************************
     *                   ATTRIBUTS UTILES                                  *
     ***********************************************************************/
    //la liste qui contient les noms de fichiers
    SimpleList diapos;            // La liste elle meme
    SimpleListIterator parcours;  // itérateur sur la liste
    int noImageCourante =0; //le no de l'image sélectionnée [0..tailledefaut[

    final static int NUM_ICONS = 4; // nbre d'icones affichées
    ImageIcon[] images = new ImageIcon[NUM_ICONS]; // les icones affichées
    int noIconCourant =0; //le no de l'icone sélectionnée [0 .. NUM_ICONS[

    ImageIcon imageBuf ; // l'image coupée/à coller
    String imageBufRef=""; // élément coupé dans la liste
    /***********************************************************************/


    //layout et toutes ces sortes de choses
    //qui vous importent peu pour le TP
    JLabel[] iconLabel = new JLabel[NUM_ICONS];
    JLabel iconBufLabel;
    JButton bouton = null;
    JButton bouton2 = null;
    JButton bouton3 = null;
    JButton bouton4 = null;
    JPanel mainPanel, selectPanel, displayPanel;
    JFrame frame;

    // Constructeur
    public Visionneuse(SimpleList diapos) {
	/********************   IMPORTANT   ********************************/
	// accès à la base de donnees
	this.diapos = diapos;
	parcours = diapos.iterateur();
	/*******************************************************************/

	// Create  selection and display panels + remplissage
	selectPanel = new JPanel();
	displayPanel = new JPanel();
	addWidgets();
	// Create the main panel to contain the two sub panels.
	mainPanel = new JPanel();
	mainPanel.setLayout(new GridLayout(2,1,5,5));
	mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	// Add the select and display panels to the main panel.
	mainPanel.add(selectPanel);
	mainPanel.add(displayPanel);
	// Create a frame and container for the panels.
	frame = new JFrame("Visionneuse");
	// Set the look and feel.
	try {
	  UIManager.setLookAndFeel(
		UIManager.getCrossPlatformLookAndFeelClassName());
	} catch(Exception e) {}
	frame.setContentPane(mainPanel);
        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	
	/********************   IMPORTANT   ********************************/
	//selectionne la première image
	if (! diapos.estVide()){
	  selectionne(0);
          parcours.entete();
	}
	/*******************************************************************/
	// affichage de toutes ces belles choses
	frame.pack();
	frame.setVisible(true);

    }

   
    /** Create  the widgets to select and display
	ne vous intéresse pas sauf les 2 premières lignes */
    private void addWidgets() {

	// récupère les images grâce à la liste des images
	getImagesFromList(0);
	//on affiche une image "vide" sur le buffer
	imageBuf = getImageFromFile("images/none.jpg");

	// Create label for displaying images and put a border around it.

        iconBufLabel = new JLabel();
	iconBufLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
for (int i=0; i <NUM_ICONS; i++){ 
	iconLabel[i] = new JLabel();
	iconLabel[i].setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createRaisedBevelBorder(),
			    BorderFactory.createEmptyBorder(5,5,5,5)));
        }
	
	// Les boutons
	bouton = new JButton("prev");
	bouton2 = new JButton("next");
	bouton3 = new JButton("del");
	bouton4 = new JButton("add");

	// Display the first images.
	iconBufLabel.setIcon(imageBuf);
	for (int i=0; i<NUM_ICONS;i++){
	  iconLabel[i].setIcon(images[i]);
        }

    	// Add border around the select panel.
	selectPanel.setBorder(BorderFactory.createCompoundBorder(
		BorderFactory.createTitledBorder("Opération"), 
		BorderFactory.createEmptyBorder(5,5,5,5)));

    	// Add border around the display panel.
    	displayPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Diapos"), 
            BorderFactory.createEmptyBorder(5,5,5,5)));


	//add cut buffer
	selectPanel.add(iconBufLabel);
	// Add buttons
	selectPanel.add(bouton);
	selectPanel.add(bouton3);
	selectPanel.add(bouton4);
	selectPanel.add(bouton2);

	for (int i=0; i<NUM_ICONS;i++)
          displayPanel.add(iconLabel[i]);

	// Listen to events from all buttons
	bouton.addActionListener(this);
	bouton2.addActionListener(this);
	bouton3.addActionListener(this);
	bouton4.addActionListener(this);

    }

    /** récupère une image à partir d'un fichier
	VOUS EN AUREZ BESOIN POUR DEL et ADD
     */
    ImageIcon getImageFromFile(String imageName){    
	URL iconURL = ClassLoader.getSystemResource(imageName);
	return new ImageIcon(iconURL);
    }

    /**  récupère les images grâce à la liste
     (par un itérateur local à cette méthode
     images[0..NUM_ICONS[ <-- diapos[debut..debut+NUM_ICONS[
     si on n'a pas assez d'images à afficher on affiche l'image
     images/none.jpg 
     UTILE POUR METTRE A JOUR CE QU'ON VA AFFICHER APRES LA PLUPART
     DES OPERATIONS.
    */
    void getImagesFromList(int debut){
	SimpleListIterator vasA = diapos.iterateur();
	int ind =0;
	String imageName;
	try{
	    vasA.allera(debut);
	    for (;(!vasA.sorti()) && (ind<NUM_ICONS) ;vasA.suc()) {
		images[ind] = getImageFromFile((String) vasA.ec());
		ind++;
	    }
	}
	catch (SimpleListException e){System.out.println(e+" dans suc");}

	if (ind<NUM_ICONS){
	    imageName ="images/none.jpg";
	    URL iconURL = ClassLoader.getSystemResource(imageName);
	    ImageIcon icon = new ImageIcon(iconURL);
	    for (int j=ind;j<NUM_ICONS;j++){
		images[j] = icon;
	    }
	}
    }

    /* marque cette image sélectionnée
       UTILE */
    void selectionne(int i){
	iconLabel[i].setBorder(BorderFactory.createCompoundBorder(
	    BorderFactory.createLoweredBevelBorder(),
	    BorderFactory.createEmptyBorder(5,5,5,5)));
    }
    /* enleve la marque  image sélectionnée
       UTILE */
    void deselectionne(int i){
	iconLabel[i].setBorder(BorderFactory.createCompoundBorder(
	    BorderFactory.createRaisedBevelBorder(),
			    BorderFactory.createEmptyBorder(5,5,5,5)));
    }
    /**********************************************************************
     *              VOTRE TRAVAIL SE SITUE  I C I                         *
     **********************************************************************/
 
     // Implementation of ActionListener interface.
     // c'est ca qu'il faut faire

    public void actionPerformed(ActionEvent event) {
	// update the icon to display the new phase
      String message="";
	String action = event.getActionCommand();
	if (action.equals("add")){
	    //implementation de add a faire ICI	 
	    message="add non implémenté";
	}
	else
	    if (action.equals("next")){ 
		//implementation de next à faire ICI	 
		message= "next non implémenté";
	    }
	    else
		if (action.equals("del")){
		//implementation de del à faire ICI	 
		    message= "del non implémenté";
		}
		else
		    if (action.equals("prev")){
			//implementation de del à faire ICI	 
			message= "pred non implémenté";
		    }
		    else
			message= "fonction inexistante";

	// refresh Display 
	iconBufLabel.setIcon(imageBuf);
	for (int i=0; i<NUM_ICONS;i++){
	    iconLabel[i].setIcon(images[i]);
        }
	// display message if necessary
	if (message !="")
	  JOptionPane.showMessageDialog(frame, message);
    }


    // main method
    public static void main(String[] args) {

	SimpleListImpl diapos = new SimpleListImpl();
	SimpleListIterator ind = diapos.iterateur();
	ind.entete();
	try{
	    ind.ajouter("images/image1.jpg");
	    ind.ajouter("images/image2.jpg");
	    ind.ajouter("images/image3.jpg");
	    ind.ajouter("images/image4.jpg");
	    ind.ajouter("images/image5.jpg");
	    ind.ajouter("images/image6.jpg");
	    ind.ajouter("images/image7.jpg");
	    ind.ajouter("images/image8.jpg");
	    ind.ajouter("images/image9.jpg");
	    ind.ajouter("images/image10.jpg");
	    ind.ajouter("images/image11.jpg");
	    ind.ajouter("images/image12.jpg");
	}
	catch (SimpleListException e){System.out.println("débordement de liste");}
	Visionneuse v = new Visionneuse(diapos);
  
	
    }
}
