package main.java;

import main.java.Space;
/**
 * 
 * @author coupalme198 
 * @author weaverss195
 *
 */
public class Wall {
//Instance Variables
	//private Space[] surroundingSpaces = new Space[2];
	private Space s0;
	private Space s1;
	
// Constructor
	/**
	 * Constructs a wall object and ensures validity of the wall.
	 * @param side0 the first end-point corresponding to the wall being constructed.
	 * @param side1 the second end-point corresponding to the wall being constructed.
	 * @throws IllegalArgumentException if the end-points don't correspond to a valid wall.
	 */
	public Wall(Space side0, Space side1) throws IllegalArgumentException{
		this.s0 = side0;
		this.s1 = side1;
		if (!isLegalWall())
			throw new IllegalArgumentException("This isn't an actual wall");
		this.s0 = side0;
		this.s1 = side1;
		// surroundingSpaces[0] = side0; 
		// surroundingSpaces[1] = side1; 
	}

// Methods
	/**
	 * Tests if a wall is valid.
	 * @return true if the wall is a legal wall, false otherwise.
	 */
	public boolean isLegalWall() {
			if 		( !this.isHorizontal() && !this.isVertical() )
					return false;	
			else   
					return true;
	}
	
	/*
	/**
	 * Sets a specific surrounding space to the space specified
	 * @param index The surrounding space to be changed
	 * @param surroundingSpace The new surrounding space being referenced
	 */
	/*
	public void setWallSurroundingSpace(Space s0, Space s1) {
		
		// surroundingSpaces[index] = surroundingSpace;
	}
	*/
	
	/*
	/**
	 * Retrieves the walls surrounding space specified
	 * @param index the surrounding space to be returned
	 * @return the reference to the surrounding space
	 */
	/*
	public Space getWallSurroundingSpace(int index) {
		return surroundingSpaces[index];
	}
	*/

	/**
	 * Checks if a wall is equal to a given wall (i.e. is placed at the same location)
	 * @param otherWall The other wall
	 * @return True if the walls are in the same location, false otherwise
	 */
	public boolean isEqual(Wall otherWall) {
		if ( 	(this.s0.getX() != otherWall.s0.getX()) ||
				(this.s0.getY() != otherWall.s0.getY()) ||
				(this.s1.getX() != otherWall.s1.getX()) ||
				(this.s1.getY() != otherWall.s1.getY())
			)	
				return false;
		else
				return true;
	}

	/**
	 * Checks if is a horizontal wall.
	 * @return True if is a horizontal wall, false otherwise.
	 */
	public boolean isHorizontal() {
		if ( 	(s0.getY() != s1.getY()) || // y-coordinates must be equal
				(s1.getX() - s0.getX() != 1) || // x-coordinate of the second space below must be 1 greater than the x-coordinate of the first space below.
				(s0.getY() == 8) || // out of bounds
				(s1.getY() == 8) || // out of bounds
				(s0.getX() == 8) || // out of bounds
				(s1.getX() == 0)	// out of bounds
			)
			return false;
		else
			return true;
		// return surroundingSpaces[0].getY() != surroundingSpaces[1].getY();
	}
	/**
	 * Checks if is a Vertical wall.
	 * @return True if is a Vertical wall, false otherwise.
	 */
	public boolean isVertical() {
		if (	(s0.getY() - s1.getY() != 1) || // y-coordinate of the first space to the right must be 1 greater than the y-coordinate of the second space to the right.
				(s1.getX() != s0.getX()) || // x-coordinates must be equal
				(s0.getX() == 0)  || // out of bounds
				(s0.getY() == 0)  || // out of bounds
				(s1.getX() == 0)  || // out of bounds
				(s1.getY() == 8)     // out of bounds
			)
			return false;
		else
			return true;
		// return surroundingSpaces[0].getY() != surroundingSpaces[1].getY();
	}
}
