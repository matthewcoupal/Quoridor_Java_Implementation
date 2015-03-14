package UI;

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
    JFrame frame = new JFrame(); // Instance for Frame
    String currentMove = "";
    // Creates the BoardGrid.

    /**
     * Creates a 9x9 Grid Board with frame size of 1000*1000.
     * @param breadth the size of the board in the y direction.
     * @param length the size of the board in the x direction
     */
    public BoardGrid(int breadth, int length){
    	super();
        //Set frame size to 1000x1000
        frame.setSize(800,800);

        //Create 9x9 Board Grid with each grid as a button
        frame.setLayout(new GridLayout(breadth,length,10,10));

        boardgrid = new JButton[breadth][length];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < breadth; j++){
            final String coordinates = "(" + j + "," + i + ")";
            final int x = j;
            final int y = i;
            boardgrid[j][i] = new JButton(coordinates);
            boardgrid[j][i].setBackground(Color.CYAN);
            boardgrid[j][i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Space space = new Space(x,y);
                    Board board = new Board();
                    String protocolString = board.spaceToString(space);
                    currentMove = protocolString;
		    updatePositions();
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

    public static void main(String[] args) {
	BoardGrid bg = new BoardGrid(9, 9);
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
    private void setBackground (JButton button, int player) {
	if(player == 0)
	  button.setBackground(Color.BLUE);
	else if(player == 1)
	  button.setBackground(Color.RED);
	else if(player == 2)
	  button.setBackground(Color.MAGENTA);
	else if(player == 3)
	  button.setBackground(Color.GREEN);
    }

    /**
     * Checks the occupied spaces on the board and changes the color of those spaces.
     */
    public void updatePositions () {
    	for(int i = 0; i < this.occupiedSpaces.size(); i++) {
    		int xCoordinate = this.occupiedSpaces.get(i).getX();
    		int yCoordinate = this.occupiedSpaces.get(i).getY();
    		setBackground(boardgrid[xCoordinate][yCoordinate], i);
    	}
	update();
    	this.frame.repaint();
    }

    public void update () {
	for(int i = 0; i < 9; i++) {
	    for(int j = 0; j < 9; j++) {
		boardgrid[i][j].repaint();
	    }
	} 
    }
}


