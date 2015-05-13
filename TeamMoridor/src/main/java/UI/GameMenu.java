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
		
		/*If More Buttons needed*/
		panel.add( new NumbButton() );
		panel.add( new NumbButton() );
		panel.add( new NumbButton() );

		/*Row 2*/
		panel.add( new NumbButton() );
		JButton start = new JButton( "Start" );
		start.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		panel.add( start );
		panel.add( new NumbButton() );

		/*Row 3*/
		panel.add( new NumbButton() );
		panel.add( new NumbButton() );
		panel.add( new NumbButton() );
			
		frame.add( panel );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setResizable( false );
		frame.setLocation( 100, 100 );
		frame.setVisible( true );
		frame.pack();
	}
}

class NumbButton extends JButton{

	private String name = "";
	private int colorIndex;
	private Color[] bckgrndColor = new Color[] {
			Color.RED		/*0*/, 
			Color.ORANGE	/*1*/,
			Color.YELLOW	/*2*/,
			Color.GREEN		/*3*/,
			Color.CYAN		/*4*/,
			Color.MAGENTA	/*5*/,
			Color.BLACK, 	/*6*/
			new Color( 150, 75, 0 )/*7 BROWN*/,
			Color.LIGHT_GRAY/*8*/,
			Color.WHITE		/*9*/ 
	}; 

	public NumbButton(){
		super();
		colorIndex = 8;
	}

	public NumbButton( String name){
		super("Name");
		this.name = name;
		colorIndex = 7;
	}

	public void paint( Graphics g ){
		g.setColor( bckgrndColor[ colorIndex ] );
		g.fillRect(0, 0, this.getWidth(), this.getHeight() );
		g.setColor(Color.BLACK);
		g.drawString( name, this.getWidth()*1/3, this.getHeight()*2/3 );
	}
} 