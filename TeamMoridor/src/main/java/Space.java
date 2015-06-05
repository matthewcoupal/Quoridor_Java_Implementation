package main.java;

/**
 * Logical Representation of the spaces of a Board.
 * 
 * @author Matthew Coupal
 *
 */
public class Space {
  // Fields
  private int xCoordinate;
  private int yCoordinate;

  public Space(int x, int y) {
    this.xCoordinate = x;
    this.yCoordinate = y;
  }

  /**
   * Accessor for the space's x coordinate.
   * 
   * @return The space object's x coordinate.
   */
  public int getX() {
    return this.xCoordinate;
  }

  /**
   * Accessor for the space's y coordinate.
   * 
   * @return The space object's y coordinate.
   */
  public int getY() {
    return this.yCoordinate;
  }

  /**
   * Setter for the space's x coordinate.
   * 
   * @param x The space's new x coordinate.
   */
  public void setX(int x) {
    this.xCoordinate = x;
  }

  /**
   * Setter for the space's y coordinate.
   * 
   * @param y The space's new y coordinate.
   */
  public void setY(int y) {
    this.yCoordinate = y;
  }

  /**
   * Tests to see if one space is equal to another given space.
   * 
   * @param otherSpace The other space.
   * @return True if the spaces are equal; False if not.
   */
  public boolean equals(Space otherSpace) {
    return this.getX() == otherSpace.getX() && this.getY() == otherSpace.getY();
  }
}
