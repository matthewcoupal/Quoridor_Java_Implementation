package main.java;

import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * Is used to keep track of the positioning of players as well as check 
 * whether a new configuration of the board (a player move or a wall placement)
 * is an illegal move.
 * 
 * @author coupalme198
 *
 */
public class Board implements BoardInterface, RulesInterface, MasterInterface{
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
		this.makeGrid(9);
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
	
	/**
	 * @return The total number of spaces the board contains.
	 */
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
	
	/**
	 * Checks to see if a wall can be placed between the surrounding spaces.
	 * @param startingSpace1    A space representing the first half of one side of the wall
	 * @param startingSpace2    A space representing the first half of the other side of the wall
	 * @param endingSpace3      A space representing the second half of one side of the wall
	 * @param endingSpace4      A space representing the second half of the other side of the wall
	 * @return True if a wall can be placed in between the two spaces; False otherwise.
	 * @throws Exception 
	 */
	public boolean canPlaceWall(Space startingSpace1, Space startingSpace2, Space endingSpace3, Space endingSpace4) {
		if(this.isWallHere(startingSpace1, startingSpace2) || this.isWallHere(endingSpace3, endingSpace4)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks to see if a wall is between the two spaces.
	 * @param startingSpace1    A space on one side of the wall-half being tested
	 * @param startingSpace2    A space on the opposite of the wall-half being tested
	 * @return True if a wall is between the spaces; False if not.
	 */
	public boolean isWallHere(Space startingSpace1, Space startingSpace2) {
		int startingX = startingSpace1.getX();
		int startingY = startingSpace1.getY();
		int otherSideX = startingSpace2.getX();
		int otherSideY = startingSpace2.getY();
		if(startingSpace2.getX() - startingSpace1.getX() == 1) {
			if(boardConfiguration.spaceAt(startingX, startingY).getRightNode() == null)
				return true;
		} else if(startingSpace2.getX() - startingSpace1.getX() == -1) {
			if(boardConfiguration.spaceAt(startingX, startingY).getLeftNode() == null)
				return true;
		} else if(startingSpace2.getY() - startingSpace1.getY() == 1) {
			if(boardConfiguration.spaceAt(startingX, startingY).getTopNode() == null)
				return true;
		} else if(startingSpace2.getY() - startingSpace1.getY() == -1) {
			if(boardConfiguration.spaceAt(startingX, startingY).getBottomNode() == null)
				return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if moving two spaces is a legal.
	 * @param currentPosition    The current space the player moving is positioned.
	 * @param potentialPosition  The space the player wants to move to.
	 * @return True if moving two spaces is legal; False otherwise.
	 */
	public boolean isLegalDoubleMove(Space currentPosition,
			Space potentialPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Checks if a move meets all the move criteria
	 * @param currentPosition    The current space the player moving is positioned
	 * @param potentialPosition  The space the player wants to move to
	 * @return True if the move to the specified space from the current space is legal; False otherwise.
	 */
	public boolean isLegalMove(Space currentPosition, Space potentialPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	* Checks if the player's current position is within a victory space
	* @param player    The player whose position is going to be tested
	* @return True if a specified player is located at a winning space; False otherwise.
	*/
	public boolean isWinner(Player player) {
		Space current = new Space (player.getX(), player.getY());
		for (int i = 0; i < 9; i++) {
		    if (player.getWinSpace(i).getX() == current.getX() && player.getWinSpace(i).getY() == current.getY()) {
		        return true;
		    }
		}
		return false;
	}
	
	/**
	 * Checks to see if a player can make a legal double jump.
	 * @param player    The player that wants to make the jump.
	 * @param space    The space the player wants to move to.
	 * @return    True if the move space is exactly 2 in one direction and another player is in the direction.
	 * 			   False otherwise.
	 */
	public boolean isDoubleJumpLegal(Player player, Space space) {
		if(space.getX() - player.getX() == 2 && (space.getY() - player.getY()) == 0)
			if(isPlayerHere(this.boardConfiguration.spaceAt(player.getX() + 1, player.getY()).getCoordinates()))
				return true;
		else if(space.getX() - player.getX() == -2 && (space.getY() - player.getY()) == 0)
			if(isPlayerHere(this.boardConfiguration.spaceAt(player.getX() - 1, player.getY()).getCoordinates()))
				return true;
		else if(space.getY() - player.getY() == 2 && (space.getX() - player.getX()) == 0)
			if(isPlayerHere(this.boardConfiguration.spaceAt(player.getX(), player.getY() + 1).getCoordinates()))
				return true;
		else if (space.getY() - player.getY() == -2 && (space.getX() - player.getX()) == 0)
			if(isPlayerHere(this.boardConfiguration.spaceAt(player.getX(), player.getY() - 1).getCoordinates()))
				return true;
		else
			return false;
		return false;
	}
	
	
	public void makeMove(Player player, Space potentialPosition) throws Exception {
		if(!this.isLegalMove(player, potentialPosition)) {
			this.bootPlayer(player);
			return;
		}
		for(int i = 0; i < this.occupiedSpaces.size(); i++ ) {
			if((this.occupiedSpaces.get(i).getX() == player.getX()) && (this.occupiedSpaces.get(i).getY() == player.getY())) {
				this.occupiedSpaces.get(i).setX(potentialPosition.getX());
				this.occupiedSpaces.get(i).setY(potentialPosition.getY());
				return;
			}
		}
		throw new IllegalArgumentException("The Player specified is not in this game or the servers and client are out of sync!");
	}
	
	
	public void placeWall(Player player, Space starting1, Space starting2,
			Space ending1, Space ending2) {
		if(!this.canPlaceWall(starting1, starting2, ending1, ending2)) {
			this.bootPlayer(player);
			return;
		}
	}
	
	public void bootPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCurrentPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}
    
    
}
