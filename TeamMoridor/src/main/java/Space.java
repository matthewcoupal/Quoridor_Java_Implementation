/**
 * @author coupalme198
 */
package main.java;


public class Space {
	//Fields
	private int xCoordinate;
	private int yCoordinate;
	
	public Space (int x, int y){
		this.xCoordinate = x;
		this.yCoordinate = y;
	}
	
	// Returns x coordinate
	public int getX() {
		return this.xCoordinate;
	}
	
	// Returns y coordinate
	public int getY() {
		return this.yCoordinate;
	}

	public void setX(int x) {
		this.xCoordinate = x;
	}

	public void setY(int y) {
		this.yCoordinate = y;
	}
	
	/**
	 * Tests to see if one space is equal to another given space.
	 * @param otherSpace The other space.
	 * @return True if the spaces are equal; false if not.
	 */
	public boolean equals(Space otherSpace) {
		return this.getX() == otherSpace.getX() && this.getY() == otherSpace.getY();
	}
}
