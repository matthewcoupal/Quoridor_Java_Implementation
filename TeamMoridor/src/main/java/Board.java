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
	private ArrayList<Player> occupiedSpaces; //List of occupied spaces
	//private ArrayList<Edge> occupiedEdges; //List of wall locations
	public SpaceLinkedList boardConfiguration;
	
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
		this.occupiedSpaces = new ArrayList<Player>();
		//this.occupiedEdges = new ArrayList<Edge>();
		if(numberOfPlayers == 2) {
			this.occupiedSpaces.add(new Player(4,0,10));
			this.occupiedSpaces.add(new Player(4,9,10));
		} else if (numberOfPlayers == 4) {
			this.occupiedSpaces.add(new Player(4,0,10));
			this.occupiedSpaces.add(new Player(4,9,10));
			this.occupiedSpaces.add(new Player(0,5,10));
			this.occupiedSpaces.add(new Player(9,5,10));
		}
		makeGrid(9);
	}
	
	/**
	 * Creates a grid of requested n by n size.
	 * @param size    The length and width of the grid requested.
	 */
	public void makeGrid(int size) {
		this.boardConfiguration = new SpaceLinkedList();
		
		// Creates a string of nodes with the proper numbering
		// (Columns are stacked on each other)
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				SpaceNode node = new SpaceNode(new Space(i, j));
				this.boardConfiguration.add(node);
			}
		}
		
		// Starting from the beginning of the list and moving up, assign the 
		// bottom, left, and right nodes, depending on whether or not the
		// spaces are on the edges.
		SpaceNode current = this.boardConfiguration.spaceAt(0, 0);
		int x = current.getCoordinates().getX();
		int y = current.getCoordinates().getY();
		
		if(y != 0)
			current.setBottomNode(boardConfiguration.spaceAt(x, y - 1));
		if(x != 0)
			current.setLeftNode(boardConfiguration.spaceAt(x-1, y));
		if(x != size - 1)
			current.setRightNode(boardConfiguration.spaceAt(x + 1, y));
		
		while (current.getTopNode() != null) {
			current = current.getTopNode();
			x = current.getCoordinates().getX();
			y = current.getCoordinates().getY();
			
			if(y != 0)
				current.setBottomNode(boardConfiguration.spaceAt(x, y - 1));
			if(x != 0)
				current.setLeftNode(boardConfiguration.spaceAt(x-1, y));
			if(x != size - 1)
				current.setRightNode(boardConfiguration.spaceAt(x + 1, y));
		}
		
		//Goes to each node and sets the top node to null since the linked list
		// adds nodes from the top.
		for(int i = 0; i < size; i++) {
			current = this.boardConfiguration.spaceAt(i, size - 1);
			current.setTopNode(null);
		}
		
	}
	
	public int size() {
		return this.boardConfiguration.size();
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
	 * Checks to see whether the space being moved is a legal diagonal or not.
	 * 
	 * @param currentPosititon    The current position of the player.
	 * @param potentialPosition    The position the player wants to move to.
	 * 
	 * @return    True if the jump is diagonal and a player on the space needed to jump to, and is false otherwise.
	 */
    public boolean  isMoveLegalDiagonal(Space currentPosition, Space potentialPosition) {
    	int slope = (potentialPosition.getY() - currentPosition.getY()/ potentialPosition.getX() - currentPosition.getX());
    	if(Math.abs(slope) == 1) {
    		if(slope == 1)
    			return isPlayerHere(new Space(potentialPosition.getX() - 1, potentialPosition.getY()));
    		else if(slope == -1)
    			return isPlayerHere(new Space(potentialPosition.getX() + 1, potentialPosition.getY()));
    	}
    	return false;
    }
    
    /**
     * Checks to see whether the space being moved is a diagonal jump or not.
     * 
     * @param currentPosition    The current position of the player.
     * @param potentialPosition    The position the player wants to move to.
     * @return    True the space is diagonal; False otherwise.
     */
    public boolean isMoveDiagonal(Space currentPosition, Space potentialPosition) {
    	if(Math.abs((potentialPosition.getY() - currentPosition.getY()/ potentialPosition.getX() - currentPosition.getX())) == 1) {
    		return true;
    	}
        return false;
    }
    
    /**
     * Checks to see if a player is at a given position
     * @param potentialPosition    The space the current player wants to move to.
     * @return    True if a player is located on the space; False if there is no player located at that space.
     */
	public boolean isPlayerHere(Space potentialPosition) {
		for(int i = 0; i < occupiedSpaces.size(); i++) {
			if(occupiedSpaces.get(i).getX() == potentialPosition.getX() && occupiedSpaces.get(i).getY() == potentialPosition.getY()) {
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * Passes the grid. 
	 * (Feel like this should be looked at closer. The reference will be 
	 * passed, therefore I feel like anyone can edit the board.)
	 * @return   The grid (linked list) the board is using.
	 */
	public SpaceLinkedList getGrid() {
		return this.boardConfiguration;
		
	}
	
	/**
	 * Checks if Player can reach their winning spaces.
	 * @param player    The player to check for a winning path
	 * @return   True if at least one winning space has an open path to it;
	 *            False if no paths can be found;
	 */
	public boolean canReachEnd(Player player) {
		SpaceNode node = new SpaceNode(player);
		for(int i = 0; i < Math.sqrt(this.boardConfiguration.size()); i++) {
			int x = player.getWinSpace(i).getX();
			int y = player.getWinSpace(i).getY();
			if(this.boardConfiguration.spaceAt(x, y, node) != null)
				return true;
		}
		return false;
	}
    
    
}
