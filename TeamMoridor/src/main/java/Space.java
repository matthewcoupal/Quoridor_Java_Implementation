/*
 * Author: Matthew Coupal
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

	public int setX(int x) {
		return 0;
	}

	public int setY(int y) {
		return 0;
	}
}
