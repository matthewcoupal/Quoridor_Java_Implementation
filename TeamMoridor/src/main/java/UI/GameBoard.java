package main.java.UI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
  *@author: Brabim Baral, Joseph Santantasio, Matthew Coupal
  */
public class GameBoard{
    
    private JFrame frame;
    private JPanel panel;
    private final int BUTTON_SIZE = 8; //size of intersection button
    private final int BOARD_SIZE = 9; 	//number of spaces that the board is wide
    private final int BUTTON_SCALE = 10; //ratio of intersection button : playerbutton

    //ActionListener for buttons.
    private final ActionListener action = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		            System.out.println("Button Works YAY!");
		        }
		    };
    
    //Main
    public static void main( String[] args){
	new GameBoard();
    }
    
    /**
      *Constructor: Creates the Board with player buttons, horizontal and vertical walls
      **/
    public GameBoard(){
	frame = new JFrame();
	panel = new JPanel();
	FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 0, 0);
	panel.setLayout(flow);
	panel.setPreferredSize( new Dimension(( ((BUTTON_SIZE*BUTTON_SCALE)*BOARD_SIZE) 
						+ (BUTTON_SIZE*(BOARD_SIZE-1)) ) , 
					      ( ((BUTTON_SIZE*BUTTON_SCALE)*BOARD_SIZE) + 
						(BUTTON_SIZE*(BOARD_SIZE-1)) )) );
	
	addRow_PlayerB_WallBvert(0);
	for( int i=0; i<BOARD_SIZE-1; i++){
	    addRow_WallBHorizont_IntersectionB(i);
	    addRow_PlayerB_WallBvert(i);
	}
	frame.add( panel );
	frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	frame.setResizable( false );
	frame.setLocation( 100, 100 );
	frame.setVisible( true );
	frame.pack();
    }
    
    // Adds buttons for space nodes and vertical walls in the panel
    public void addRow_PlayerB_WallBvert(int index){
	
	int i = 0;
	panel.add( new PlayerButton( BUTTON_SIZE, BUTTON_SCALE, index, 0, action ) );
	for( i=1; i<=BOARD_SIZE-1; i++){
	    panel.add( new WallButton_Vertical( BUTTON_SIZE, BUTTON_SCALE, index, i, action ) ) ;
	    panel.add( new PlayerButton( BUTTON_SIZE, BUTTON_SCALE, index, i, action ) );
	}
    }
    
    // Adds buttons for Horizontal wall  and intersection buttons in the panel
    public void addRow_WallBHorizont_IntersectionB(int index){
	
	panel.add( new WallButton_Horizontal( BUTTON_SIZE, BUTTON_SCALE, index, 0, action ) );
	int i =0;
	for( i=1; i<= BOARD_SIZE -1; i++){
	    panel.add( new IntersectionButton( BUTTON_SIZE ) );
	    panel.add( new WallButton_Horizontal( BUTTON_SIZE, BUTTON_SCALE, index, i, action ) );
	}
    }
    
    
}

 //Constructs buttons for as board tiles with ActionListener
class PlayerButton extends JButton{
    
    public int xl;
    public int yl;
    
    public PlayerButton( int BUTTON_SIZE, int BUTTON_SCALE, int x, int y, ActionListener action ){
	super();
	this.setPreferredSize( new Dimension( BUTTON_SIZE*BUTTON_SCALE, BUTTON_SIZE*BUTTON_SCALE ));
	this.xl = x;
	this.yl = y;
	this.addActionListener(action);
	this.setBorder(null);
	this.setBorderPainted(false);
	this.setMargin(new Insets(0,0,0,0));
	this.setBackground( Color.RED);
    }
}

//Constructs buttons for vertical wall with ActionListener
class WallButton_Vertical extends JButton{
    
    public int xl;
    public int yl;
   
    public WallButton_Vertical( int BUTTON_SIZE, int BUTTON_SCALE, int x, int y, ActionListener action ){
	super();
	this.setPreferredSize( new Dimension( BUTTON_SIZE, BUTTON_SIZE*BUTTON_SCALE ));
	this.xl = x;
	this.yl = y;	
	this.addActionListener(action);
	this.setBorder(null);
	this.setBorderPainted(false);
	this.setMargin(new Insets(0,0,0,0));
	this.setBackground( Color.WHITE );
	
    }
}

/*
 *Constructs buttons for Horizontal Wall with ActionListener
 */
class WallButton_Horizontal extends JButton{
    
    public int xl;
    public int yl;
    
    public WallButton_Horizontal( int BUTTON_SIZE, int BUTTON_SCALE, int x, int y, ActionListener action ){
	super();
	this.setPreferredSize( new Dimension( BUTTON_SIZE*BUTTON_SCALE, BUTTON_SIZE ));
	this.xl = x;
	this.yl =y;
	this.addActionListener(action);
	this.setBorder(null);
	this.setBorderPainted(false);
	this.setMargin(new Insets(0,0,0,0));
	this.setBackground( Color.WHITE );
	
    }
}

/**
 * Construct instersection of Horizontal and Vertical wall as a button.
 * Dont Know if we need this yet but the intersection of walls acts as a button
 * as well.
 */

class IntersectionButton extends JButton{
    
    public IntersectionButton( int BUTTON_SIZE ){
	super();
	this.setPreferredSize( new Dimension( BUTTON_SIZE, BUTTON_SIZE ));
	this.setBorder(null);
	this.setBorderPainted(false);
	this.setMargin(new Insets(0,0,0,0));
	this.setBackground( Color.WHITE );
    }
}
