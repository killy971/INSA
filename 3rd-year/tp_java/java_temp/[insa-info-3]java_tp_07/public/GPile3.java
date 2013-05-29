// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GPile3.java

import Piles.TPile;
import java.awt.*;
import javax.swing.*;

public class GPile3
{

	public GPile3()
	{
	}

	public static void setupFrame()
	{
		TPile tpile = new TPile();
		JPanel jpanel = new JPanel();
		JPanel jpanel1 = new JPanel();
		JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(new GridLayout(0, 2));
		JLabel jlabel = new JLabel("valeur");
		JTextField jtextfield = new JTextField("");
		jpanel2.add(jlabel);
		jpanel2.add(jtextfield);
		jpanel1.setLayout(new GridLayout(2, 0));
		jpanel1.add(jpanel2);
		JButton jbutton = new JButton("Depiler");
		jpanel1.add(jbutton);
		jpanel.setLayout(new GridLayout(0, 2));
		jpanel.add(jpanel1);
		JTextArea jtextarea = new JTextArea("init");
		frame.getContentPane().setLayout(new BorderLayout());
		jpanel.add(jtextarea);
		frame.getContentPane().add(jpanel);
		frame.setDefaultCloseOperation(3);
		frame.pack();
		frame.setSize(200, 200);
		Empile empile = new Empile(jtextfield, jtextarea, tpile);
		Depile depile = new Depile(jtextfield, jtextarea, tpile);
		jtextfield.addActionListener(empile);
		jbutton.addActionListener(depile);
	}

	public static void main(String args[])
	{
		setupFrame();
		frame.setVisible(true);
	}

	static JFrame frame = new JFrame("D\351mo de la Pile");

}
