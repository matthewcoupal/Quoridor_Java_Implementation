//package main.java.UI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
  *@author: Brabim Baral
  */
public class SwingDemo{
    
    private JFrame frame;
    private JPanel panel;
    private final int BUTTON_SIZE = 8; //size of intersection button
    private final int BOARD_SIZE = 9; 	//number of spaces that the board is wide
    private final int BUTTON_SCALE = 10; //ratio of intersection button : playerbutton
    
    public static void main( String[] args){
	new SwingDemo();
    }
    
    public SwingDemo(){
	frame = new JFrame();
	panel = new JPanel();
	FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 0, 0);
	panel.setLayout(flow);
	panel.setPreferredSize( new Dimension(( ((BUTTON_SIZE*BUTTON_SCALE)*BOARD_SIZE) 
						+ (BUTTON_SIZE*(BOARD_SIZE-1)) ) , 
					      ( ((BUTTON_SIZE*BUTTON_SCALE)*BOARD_SIZE) + 
						(BUTTON_SIZE*(BOARD_SIZE-1)) )) );
	
	addRow_PB_WBV();
	for( int i=0; i<BOARD_SIZE-1; i++){
	    addRow_WBH_IB();
	    addRow_PB_WBV();
	}
	frame.add( panel );
	frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	frame.setResizable( false );
	frame.setLocation( 100, 100 );
	frame.setVisible( true );
	frame.pack();
    }
    public void addRow_PB_WBV(){
	
	// Adds buttons for space nodes and vertical walls in the panel
	panel.add( new PlayerButton( BUTTON_SIZE, BUTTON_SCALE ) );
	for( int i=0; i<BOARD_SIZE-1; i++){
	    panel.add( new WallButton_Vertical( BUTTON_SIZE, BUTTON_SCALE ) );
	    panel.add( new PlayerButton( BUTTON_SIZE, BUTTON_SCALE ) );
	}
    }
    
    
    public void addRow_WBH_IB(){
	// Adds buttons for Horizontal wall  and intersection buttons in the panel
	panel.add( new WallButton_Horizontal( BUTTON_SIZE, BUTTON_SCALE ) );
	for( int i=0; i<BOARD_SIZE-1; i++){
	    panel.add( new IntersectionButton( BUTTON_SIZE ) );
	    panel.add( new WallButton_Horizontal( BUTTON_SIZE, BUTTON_SCALE ) );
	}
    }
    
    
}
class PlayerButton extends JButton{
    
    //Constructs buttons for as board tiles
    public PlayerButton( int BUTTON_SIZE, int BUTTON_SCALE ){
	super();
	this.setPreferredSize( new Dimension( BUTTON_SIZE*BUTTON_SCALE, BUTTON_SIZE*BUTTON_SCALE ));
	this.setBorder(null);
	this.setBorderPainted(false);
	this.setMargin(new Insets(0,0,0,0));
	this.setBackground( Color.RED);
    }
}
class WallButton_Vertical extends JButton{
    
    //Constructs buttons for vertical wall
    public WallButton_Vertical( int BUTTON_SIZE, int BUTTON_SCALE ){
	super();
	this.setPreferredSize( new Dimension( BUTTON_SIZE, BUTTON_SIZE*BUTTON_SCALE ));
	this.setBorder(null);
	this.setBorderPainted(false);
	this.setMargin(new Insets(0,0,0,0));
	this.setBackground( Color.WHITE );
	
    }
}
class WallButton_Horizontal extends JButton{
    
    //Constructs buttons for Horizontal Wall
    public WallButton_Horizontal( int BUTTON_SIZE, int BUTTON_SCALE ){
	super();
	this.setPreferredSize( new Dimension( BUTTON_SIZE*BUTTON_SCALE, BUTTON_SIZE ));
	this.setBorder(null);
	this.setBorderPainted(false);
	this.setMargin(new Insets(0,0,0,0));
	this.setBackground( Color.WHITE );
	
    }
}

/**
 * Dont Know if we need this yet but the intersection of walls acts as a button
 * as well.
 */
class IntersectionButton extends JButton{
    
    // Construct instersection of Horizontal and Vertical wall as a button.
    public IntersectionButton( int BUTTON_SIZE ){
	super();
	this.setPreferredSize( new Dimension( BUTTON_SIZE, BUTTON_SIZE ));
	this.setBorder(null);
	this.setBorderPainted(false);
	this.setMargin(new Insets(0,0,0,0));
	this.setBackground( Color.WHITE );
    }
}
