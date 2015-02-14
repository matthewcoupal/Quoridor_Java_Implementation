package main.java;

/**
 * Emulates  a player on the board by utilizing the same information as a 
 * space, but with extra behaviors and attributes.
 * @author coupalme198
 *
 */
public class Player extends Space {

	
	//Inherits coordinate x and coordinate y
	private int wallsLeft;
	/**
	 * Creates player class based on Space class constructor.
	 * @param x    The x coordinate of the player
	 * @param y    The y coordinate of the player
	 * @param wallstoStart    The number of walls the players starts the game 
	 *                         with
	 */
	public Player(int x, int y, int wallstoStart) {
		super(x, y);
		wallsLeft = wallstoStart;
	}

	/**
	 * Getter method to the wallsLeft field.
	 * @return    The number of wall the player has left to place.
	 */
	public int getWalls() {
		return wallsLeft;
	}

}
