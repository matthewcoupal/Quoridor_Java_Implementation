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
	public Space[] end = new Space[9]; //Spaces where the player can win
	
	
	
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
		if(x == 0) {
			for(int i = 0; i < end.length; i++)
				end[i] = new Space(9, i);
		} else if(x == 9) {
			for(int i = 0; i < end.length; i++)
				end[i] = new Space(0, i);
		} else if(y == 0) {
			for(int i = 0; i < end.length; i++)
				end[i] = new Space(i,9);
		} else if(y == 9) {
			for(int i = 0; i < end.length; i++)
				end[i] = new Space(i, 0);
		}
	}

	/**
	 * Getter method to the wallsLeft field.
	 * @return    The number of wall the player has left to place.
	 */
	public int getWalls() {
		return wallsLeft;
	}
	
	//Has Methods:
	//getX
	//getY
	//setX
	//setY

}
