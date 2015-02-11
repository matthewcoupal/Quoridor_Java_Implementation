package main.java;

import java.util.ArrayList;


/**
 * Is used to keep track of the positioning of players as well as check 
 * whether a new configuration of the board (a player move or a wall placement)
 * is an illegal move.
 * 
 * @author coupalme198
 *
 */
public class Board {
	//Instance Variables
	private ArrayList<Space> occupiedSpaces; //List of occupied spaces
	private ArrayList<Edge> occupiedEdges; //List of wall locations
	
	//If no board size is given, a 2-player setup is initiated.
	public Board() {
		this(2);
	}
/**
 * Creates a new listing of players on a board based on the number of players
 * 
 * @param numberOfPlayers    The total number of players using this board. 
 */
	public Board(int numberOfPlayers){
		this.occupiedSpaces = new ArrayList<Space>();
		this.occupiedEdges = new ArrayList<Edge>();
		if(numberOfPlayers == 2) {
			this.occupiedSpaces.add(new Space(5,0));
			this.occupiedSpaces.add(new Space(5,9));
		} else if (numberOfPlayers == 4) {
			this.occupiedSpaces.add(new Space(5,0));
			this.occupiedSpaces.add(new Space(5,9));
			this.occupiedSpaces.add(new Space(0,5));
			this.occupiedSpaces.add(new Space(9,5));
		}
	}

	/**
	 * Checks to see whether the space being moved to is outside the 
	 * scope/range of the board.
	 * 
	 * @param potentialPosition    The position the player wants to move to.
	 * 
	 * @return    true if the space is out of bounds (one of the coordinates
	 * is less than 0 or greater than 9), and is false otherwise.
	 */
	public boolean isOutOfBounds(Space potentialPosition) {
		if(potentialPosition.getX() < 0 || potentialPosition.getX() > 9 && potentialPosition.getY() < 0 || potentialPosition.getY() > 9 ) {
			return true;
		}
		return false;
	}

	/**
	 * Checks to see whether the space being moved is diagonal or not.
	 * 
	 * @param currentPosititon    The current position of the player.
	 * @param potentialPosition    The position the player wants to move to.
	 * 
	 * @return    True if the absolute value of the slope of the two spaces is 1, and is false otherwise.
	 */
    public boolean  isMoveDiagonal(Space currentPosititon, Space potentialPosition) {
    	if(Math.abs((potentialPosition.getY() - currentPosititon.getY()/ potentialPosition.getX() - currentPosititon.getX())) == 1) {
    		return true;
    	}
        return false;
    }
	public boolean isPlayerHere(Space potentialPosition) {
		for(int i = 0; i < occupiedSpaces.size(); i++) {
			if(occupiedSpaces.get(i).getX() == potentialPosition.getX() && occupiedSpaces.get(i).getY() == potentialPosition.getY()) {
				return true;
			}
		}
		return false;
	}
    
    
}
