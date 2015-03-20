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
	 * @param side0 Bottom-Left corner: Starting2 if Horizontal, Starting1 if Vertical
	 * @param side1 Bottom-Right corner: Ending2 if Horizontal, Starting2 if Vertical
	 * @param side2 Top-Left corner:   Starting1 if Horizontal, Ending1 if Vertical
	 * @param side3 Top-Right corner:   Ending1 if Horizontal, Ending2 if Vertical
	 *
	 * Horizontal Wall:
	 * S1        E1
	 * ------------
	 * S2        E2
	 * 
	 * Vertical Wall:
	 * E1 | E2
	 *    |
	 *    |
	 *    | 
	 * S1 | S2
	 * 
	 */
	public Wall(Space side0, Space side1, Space side2, Space side3) {
		surroundingSpaces[0] = side0; 
		surroundingSpaces[1] = side1; 
		surroundingSpaces[2] = side2; 
		surroundingSpaces[3] = side3; 
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

	/**
	 * Checks if a wall is equal to a given wall (i.e. is placed at the same location)
	 * @param otherWall The other wall
	 * @return True if the walls are in the same location, false otherwise
	 */
	public boolean isEqual(Wall otherWall) {
		for (int i = 0; i < 4; i++){
			if (	this.surroundingSpaces[i].getX() != otherWall.surroundingSpaces[i].getX() 
				||  this.surroundingSpaces[i].getY() != otherWall.surroundingSpaces[i].getY() ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the wall is horizontal
	 * @return True if the wall is horizontal, false otherwise.
	 */
	public boolean isHorizontal() {
		return surroundingSpaces[0].getY() != surroundingSpaces[1].getY();
	}
}
