package main.java;

import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * Is used to keep track of the positioning of players as well as check 
 * whether a new configuration of the board (a player move or a wall placement)
 * is an illegal move.
 * 
 * @author Matthew Coupal
 *
 */
public class Board implements BoardInterface, RulesInterface, MasterInterface{
	//Instance Variables
	protected ArrayList<Player> occupiedSpaces; //List of occupied spaces
	private ArrayList<Wall> placedWalls; //List of wall locations
	public SpaceLinkedList boardConfiguration; //Current configuration of the board
	private Player currentPlayer = new Player(1,1,10); //Default current player

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
		this.placedWalls = new ArrayList<Wall>();
		if(numberOfPlayers == 2) {
			this.occupiedSpaces.add(new Player(4,0,10));
			this.occupiedSpaces.add(new Player(4,8,10));
		} else if (numberOfPlayers == 4) {
			this.occupiedSpaces.add(new Player(4,0,5));
			this.occupiedSpaces.add(new Player(4,8,5));
			this.occupiedSpaces.add(new Player(0,4,5));
			this.occupiedSpaces.add(new Player(8,4,5));
		}
		this.makeGrid(9);
		//currentPlayer = occupiedSpaces.get(0);
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
		//FIX IN FUTURE UPDATE: RULE ABOUT MOVING DIAGONALLY WHEN WALLS BECOME IMPLEMENTED
		/*if(potentialPosition.getX() - currentPosition.getX() != 0) {
    		int slope = (potentialPosition.getY() - currentPosition.getY()/ potentialPosition.getX() - currentPosition.getX());
    		if(Math.abs(slope) == 1) {
    			if(slope == 1) {
    				if(potentialPosition.getX() < currentPosition.getX()) {
    					return isPlayerHere(new Space(potentialPosition.getX() + 1, potentialPosition.getY()));
    				} else {
    					return isPlayerHere(new Space(potentialPosition.getX() - 1, potentialPosition.getY()));
    				}
    			}
    			else if(slope == -1){
    				if(potentialPosition.getX() < currentPosition.getX()) {
    					return (isPlayerHere(new Space(potentialPosition.getX() + 1, potentialPosition.getY())) || isPlayerHere(new Space(potentialPosition.getX(), potentialPosition.getY() + 1)));
    				}
    				else {
    					return isPlayerHere(new Space(potentialPosition.getX() + 1, potentialPosition.getY()));
    				}
    			}
    		}
    	}*/
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
	 * @param side0      A space to the bottom-left of the wall
	 * @param side1      A space to the bottom-right of the wall
	 * @param side2      A space to the top-left of the wall
	 * @param side3      A space to the top-right of the wall
	 * @return True if a wall can be placed in between the two spaces; False otherwise.
	 * @throws Exception 
	 */
	public boolean canPlaceWall(Space side0, Space side1, Space side2, Space side3) {
		//Does the player have enough walls left
		if(this.currentPlayer().getWalls() == 0)
			return false;

		//If there is already a wall segment on the two placement areas
		if(this.isWallHere(side0, side1) || this.isWallHere(side2, side3)) {
			return false;
		}

		//If it is crossing the wall
		Wall temp = new Wall(side0, side1, side2, side3);
		if(temp.isHorizontal()) {
			Wall temp2 = new Wall(side2, side0, side3, side1);
			for(int i = 0; i < this.placedWalls.size(); i++) {
				if(temp2.isEqual(placedWalls.get(i)))
					return false;
			}
		}
		else { // It is a vertical wall
			// Construct a perpendicular horizontal wall
			Wall temp2 = new Wall(side1, side3, side0, side2);
			for(int i = 0; i < this.placedWalls.size(); i++) {
				if(temp2.isEqual(placedWalls.get(i)))
					return false;
			}
		}

		SpaceNode node0 = this.boardConfiguration.spaceAt(side0.getX(), side0.getY());
		SpaceNode node1 = this.boardConfiguration.spaceAt(side1.getX(), side1.getY());
		SpaceNode node2 = this.boardConfiguration.spaceAt(side2.getX(), side1.getY());
		SpaceNode node3 = this.boardConfiguration.spaceAt(side3.getX(), side3.getY());


		if(temp.isHorizontal()) {
			/*
			 * Cutting the links from
			 * 0 to 2, and conversely
			 * 1 to 3, and conversely
			 * 
			 * 2        3
			 * X========X
			 * 0        1
			 */
			node0.setTopNode(null);
			node2.setBottomNode(null);
			node1.setTopNode(null);
			node3.setBottomNode(null);

			for(int i = 0; i < this.occupiedSpaces.size(); i++) {
				if(!this.canReachEnd(this.occupiedSpaces.get(i))) {
					//Player can't reach the end

					//Reconnecting the nodes
					node0.setTopNode(node2);
					node2.setBottomNode(node0);
					node1.setTopNode(node3);
					node3.setBottomNode(node1);

					return false;
				}
			}
		}
		else {
			/*
			 * Cutting the links from
			 * 0 to 1, and conversely
			 * 2 to 3, and conversely
			 * 
			 * 2    X   3
			 *      |
			 * 0    X   1
			 */	

			node0.setRightNode(null);
			node2.setRightNode(null);
			node1.setLeftNode(null);
			node3.setLeftNode(null);

			for(int i = 0; i < this.occupiedSpaces.size(); i++) {
				if(!this.canReachEnd(this.occupiedSpaces.get(i))) {
					//Player can't reach the end

					//Reconnecting the nodes
					node0.setRightNode(node1);
					node2.setRightNode(node3);
					node1.setLeftNode(node0);
					node3.setLeftNode(node2);

					return false;
				}
			}
		}

		if(temp.isHorizontal()) {
			//Reconnecting the nodes
			node0.setTopNode(node2);
			node2.setBottomNode(node0);
			node1.setTopNode(node3);
			node3.setBottomNode(node1);
		}
		else {
			node0.setRightNode(node1);
			node2.setRightNode(node3);
			node1.setLeftNode(node0);
			node3.setLeftNode(node2);
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
	 * Checks if a move meets all the move criteria
	 * @param currentPosition    The current space the player moving is positioned
	 * @param potentialPosition  The space the player wants to move to
	 * @return True if the move to the specified space from the current space is legal; False otherwise.
	 */
	public boolean isLegalMove(Player currentPlayer, Space potentialPosition) {
		if(!this.isOutOfBounds(potentialPosition) && !this.isPlayerHere(potentialPosition)) {
			if(this.isLegalSingleMove(currentPlayer, potentialPosition)) {
				return true;
			}else if(this.isDoubleJumpLegal(currentPlayer, potentialPosition)){
				return true;
			}else if(this.isMoveLegalDiagonal(currentPlayer, potentialPosition)) {
				return true;
			}
		}
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
		if((space.getX() - player.getX()) == 2 && (space.getY() - player.getY()) == 0) {
			if(isPlayerHere(new Space(player.getX() + 1, player.getY())))
				return true;
		}
		else if(space.getX() - player.getX() == -2 && (space.getY() - player.getY()) == 0) {
			if(isPlayerHere(new Space(player.getX() - 1, player.getY())))
				return true;
		}
		else if(space.getY() - player.getY() == 2 && space.getX() - player.getX() == 0) {
			if(isPlayerHere(new Space(player.getX(), player.getY() + 1)))
				return true;
		}
		else if (space.getY() - player.getY() == -2 && (space.getX() - player.getX()) == 0) {
			if(isPlayerHere(new Space(player.getX(), player.getY() - 1)))
				return true;
		}
		return false;
	}

	/**
	 * The main component to make an official move by a player.
	 * @param player The player will be moving.
	 * @param potentialPosition   The position the player wants to move to.
	 * @throws NoSuchElementException When a player makes a legal move, but the player is not found in the list of players.
	 */
	public void makeMove(Player player, Space potentialPosition) throws NoSuchElementException {
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
		throw new NoSuchElementException("The Player specified is not in this game or the servers and client are out of sync!");
	}

	//DO NOT USE -- WORK IN PROGRESS -- DO NOT COMMENT DUE TO MINIMAL TESTS

	/**
	 * Attempts to place a wall.
	 * @param side0 A space to the bottom-left of the wall
	 * @param side1 A space to the bottom-right of the wall
	 * @param side2 A space to the top-left of the wall
	 * @param side3 A space to the top-right of the wall
	 * @throws IllegalArgumentException
	 */
	public void placeWall(Space side0, Space side1,
			Space side2, Space side3) throws IllegalArgumentException {
		if(!this.canPlaceWall(side0, side1, side2, side3)) {
			throw new IllegalArgumentException("This move is ILLEGAL!");
		}
		Wall temp = new Wall(side0, side1, side2, side3);
		SpaceNode node0 = this.boardConfiguration.spaceAt(side0.getX(), side0.getY());
		SpaceNode node1 = this.boardConfiguration.spaceAt(side1.getX(), side1.getY());
		SpaceNode node2 = this.boardConfiguration.spaceAt(side2.getX(), side1.getY());
		SpaceNode node3 = this.boardConfiguration.spaceAt(side3.getX(), side3.getY());

		if(temp.isHorizontal()) {
			node0.setTopNode(null);
			node2.setBottomNode(null);
			node1.setTopNode(null);
			node3.setBottomNode(null);
		}
		else
		{
			node0.setRightNode(null);
			node2.setRightNode(null);
			node1.setLeftNode(null);
			node3.setLeftNode(null);
		}
		this.currentPlayer.setWalls(this.currentPlayer.getWalls()-1);
		return;
	}

	public void bootPlayer(Player player) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the current player to the one specified.
	 * @param playerNumber the player number in the current game.
	 * @throws IndexOutOfBoundsException When the player number is larger or smaller than the total number of players.
	 */
	public void setCurrentPlayer(int playerNumber) throws IndexOutOfBoundsException {	
		if(playerNumber < 0 || playerNumber > this.occupiedSpaces.size())
			throw new IndexOutOfBoundsException("This player does not exist in the current list of players");
		this.currentPlayer = this.occupiedSpaces.get(playerNumber);
	}

	/**
	 * Accesses the player whose turn it is currently.
	 * @return The current player.
	 * 
	 */
	public Player currentPlayer() {
		return this.currentPlayer;
	}
	@Override
	public boolean isLegalSingleMove(Player currentPlayer,
			Space potentialPosition) {
		int playerX = currentPlayer.getX();
		int playerY = currentPlayer.getY();
		int potentialX = potentialPosition.getX();
		int potentialY = potentialPosition.getY();

		if(Math.abs(potentialX - playerX) == 1 && potentialY - playerY == 0) {
			return !this.isWallHere(currentPlayer, potentialPosition);
		}else if(Math.abs(potentialY - playerY) == 1 && potentialX - playerX == 0) {
			return !this.isWallHere(currentPlayer, potentialPosition);
		}
		return false;
	}

	/**
	 * Converts Protocol String to Coordinates
	 * @param moveString the Protocol String.
	 * @return A space object with the respective coordinates; -1 if a coordinate is not found.
	 */
	public Space StringtoCoordinates(String moveString) {
		// Create the initial arrays to assign indexes to the string values.
		String[] xArray = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		String[] yArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

		//Find the index of the - separating the x and y coordinates.
		int dashIndex = moveString.indexOf("-");

		//Retrieve the x and y coordinates.
		String xCoordinate = moveString.substring(0, dashIndex);
		String yCoordinate = moveString.substring(dashIndex + 1);

		//Set default values for if the coordinate is not found.
		int translatedX = -1;
		int translatedY = -1;

		//Match string values to array values
		for(int i = 0; i < xArray.length; i++) {
			if(xCoordinate.compareTo(xArray[i]) == 0) {
				translatedX = i;
			}
			if(yCoordinate.compareTo(yArray[i]) == 0) {
				translatedY = i;
			}
		}
		//Return a new space object with the proper coordinates.
		return new Space(translatedX, translatedY);
	}

	/**
	 * Converts a given space object to the protocol move string.
	 * @param space Space to be converted
	 * @return the corresponding move string in the proper protocol form; X-X if the coordinate is out of range.
	 */
	public String spaceToString(Space space) {
		// Set up the proper space mappings.
		String[] xArray = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		String[] yArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

		String protocolString;
		if((space.getX() >= 0 && space.getX() <= 8) && (space.getY() >= 0 && space.getY() <= 8)) {
			// Get the respective x and y strings.
			protocolString = xArray[space.getX()] + "-" + yArray[space.getY()];
		}
		else {
			protocolString = "X-X";	
		}
		return protocolString;
	}

	
	public void setPlacedWalls(Space side0, Space side1, Space side2, Space side3) {
		Wall wall = new Wall (side0, side1, side2, side3);
		placedWalls.add(wall);
	}

	public void setPlacedWalls(Wall wall){
		placedWalls.add(wall);
	}

	public Wall getPlacedWalls(int index) {
		return placedWalls.get(index);
	}


}
