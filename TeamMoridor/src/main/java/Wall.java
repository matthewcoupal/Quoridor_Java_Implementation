package main.java;

import main.java.Space;
/**
 * 
 * @author coupalme198
 *
 */
public class Wall {
//Instance Variables
	private Space[] surroundingSpaces = new Space[4];
	
	/**
	 * Creates the logical representation of a wall.
	 * @param s1 Starting Space 1: Top-Left if Horizontal, Bottom-Left if Vertical
	 * @param s2 Starting Space 2: Bottom-Left if Horizontal, Bottom-Right if Vertical
	 * @param e1 Ending Space 1:   Top-Right if Horizontal, Top-Left if Vertical
	 * @param e2 Ending Space 2:   Bottom-Right if Horizontal, Top-Right if Vertical 
	 * 
	 * Horizontal Wall:
	 * S1        E1
	 * ------------
	 * S2        E2
	 * 
	 * Vertical Wall:
	 * S1 | S2
	 *    |
	 *    |
	 *    | 
	 * E1 | E2
	 * 
	 */
	public Wall(Space s1, Space s2, Space e1, Space e2) {
		surroundingSpaces[0] = s1; //S1
		surroundingSpaces[1] = s2; //S2
		surroundingSpaces[2] = e1; //E1
		surroundingSpaces[3] = e2; //E2
	}
	
	/**
	 * Sets a specific surrounding space to the space specified
	 * @param index The surrounding space to be changed
	 * @param surroundingSpace The new surrounding space being referenced
	 */
	public void setWallSurroundingSpace(int index, Space surroundingSpace) {
		surroundingSpaces[index] = surroundingSpace;
	}
	
	/**
	 * Retrieves the walls surrounding space specified
	 * @param index the surrounding space to be returned
	 * @return the reference to the surrounding space
	 */
	public Space getWallSurroundingSpace(int index) {
		return surroundingSpaces[index];
	}

}
