package main.java.UI;

import javax.swing.JFrame;
import javax.swing.JButton; 

import java.awt.Color;
import java.awt.GridLayout; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import main.java.Board;
import main.java.Space;
//Joseph Santantasio and Brabim Baral


public class BoardGrid extends Board {
    JButton[][] boardgrid; // instance for Grid of Buttons
    JFrame frame=new JFrame(); // Instance for Frame
    String currentMove = "";
    //Board board = new Board();
    // Creates the BoardGrid.
    public static void main(String[] args) {
    	
	new BoardGrid(9,9);
    }
    
    
    /**
     * Creates a 9*9 Grid Board with frame size of 1000*1000.
     * @param breadth the size of the board in the y direction.
     * @param length the size of the board in the x direction
     */
    public BoardGrid(int breadth, int length){
    	super();
	//Set frame size to 1000*1000
	frame.setSize(1000,1000);
	
	//Create 9*9 Board Grid with each grid as a button  
	frame.setLayout(new GridLayout(breadth,length,10,10)); 
	
	boardgrid = new JButton[breadth][length];
	
	for(int i = 0; i < length; i++){
	    for(int j = 0; j < breadth; j++){
		final String coordinates = "(" + j + "," + i + ")";
		final int x = j;
		final int y = i;
		boardgrid[j][i] = new JButton(coordinates);
		//static String currentMove = "";
		boardgrid[j][i].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			    //System.out.println(coordinates);
			    Space space = new Space(x,y);
			    Board board = new Board();
				String protocolString = board.spaceToString(space);
				currentMove = protocolString;
			}
		});
		//Adds buttons to frame as grids
		frame.add(boardgrid[j][i]); 
	    }
	}
	
	//Program closes on exiting window.
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
	frame.setVisible(true);
	
	this.updatePositions();
    }
    
    public String getMove() {
    	String move = this.currentMove;
    	this.currentMove = "";
    	return move;
    }
    
    
    
    /**
     * Sets the background of a given button.
     * @param button The jButton that will change colors.
     */
    private void setBackground (JButton button) {
    	button.setBackground(Color.ORANGE);
    }
    
    /**
     * Checks the occupied spaces on the board and changes the color of those spaces.
     */
    public void updatePositions () {
    	for(int i = 0; i < this.occupiedSpaces.size(); i++) {
    		int xCoordinate = this.occupiedSpaces.get(i).getX();
    		int yCoordinate = this.occupiedSpaces.get(i).getY();
    		setBackground(boardgrid[xCoordinate][yCoordinate]);
    	}
    }
    

    
}


