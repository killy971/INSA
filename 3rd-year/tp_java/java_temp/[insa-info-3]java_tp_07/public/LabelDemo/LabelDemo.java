import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LabelDemo{

    static JFrame frame = new JFrame("Ma deuxième Frame");

    public static void setupFrame(){
	JLabel jl1 = new JLabel("Bonjour");
	JLabel jl2 = new JLabel(" * ");
	frame.getContentPane().setLayout(new FlowLayout());
	frame.getContentPane().add(jl1);
	frame.getContentPane().add(jl2);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();frame.setSize(200,50);
    }

    public static void main(String[] args){
	setupFrame();
	
	frame.setVisible(true);

  }
}
