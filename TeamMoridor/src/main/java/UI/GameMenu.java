package main.java.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GameMenu {

	public static void main(String [] args ){
		new GameMenu();
	}

		private JFrame frame;
	
	public GameMenu(){
		DemoScreen1();
	}

	public void DemoScreen1(){
		frame = new JFrame( "HELLO");
		JPanel panel = new JPanel();
		GridLayout grid = new GridLayout(3, 3, 0, 0);
		panel.setLayout( grid );
		panel.setPreferredSize( new Dimension( 500, 200 ) );
			
		frame.add( panel );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setResizable( false );
		frame.setLocation( 100, 100 );
		frame.setVisible( true );
		frame.pack();
	}
}

	