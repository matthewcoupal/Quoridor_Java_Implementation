package main.java;

/**
 * An extra class in case we needed to utilize a different coordinate system for the walls.
 * 
 * @author Sundiata Weaver
 * @author Matthew Coupal
 *
 */
public class Edge {
  private int beginXCoordinate;
  private int endXCoordinate;
  private int beginYCoordinate;
  private int endYCoordinate;


  // Precondition: >= 0;
  /**
   * Assigns the fields their respective values.
   * 
   * @param beginX Starting x coordinate.
   * @param endX Ending x coordinate.
   * @param beginY Starting y coordinate.
   * @param endY Ending y coordinate.
   */
  public Edge(int beginX, int endX, int beginY, int endY) {
    this.beginXCoordinate = beginX;
    this.beginYCoordinate = beginY;
    this.endXCoordinate = endX;
    this.endYCoordinate = endY;
  }

  /**
   * Accessor for the starting x coordinate.
   * 
   * @return The starting x coordinate.
   */
  public int getBeginX() {
    return this.beginXCoordinate;
  }

  /**
   * Accessor for the starting y coordinate.
   * 
   * @return The starting y coordinate.
   */
  public int getBeginY() {
    return this.beginYCoordinate;
  }

  /**
   * Accessor for the ending x coordinate.
   * 
   * @return The ending x coordinate.
   */
  public int getEndX() {
    return this.endXCoordinate;
  }

  /**
   * Accessor for the ending y coordinate.
   * 
   * @return The ending y coordinate.
   */
  public int getEndY() {
    return this.endYCoordinate;
  }

  /**
   * Mutator for the starting x coordinate.
   * 
   * @param x The specified x coordinate.
   */
  public void setBeginX(int x) {
    this.beginXCoordinate = x;
  }

  /**
   * Mutator for the starting y coordinate.
   * 
   * @param y The specified y coordinate.
   */
  public void setBeginY(int y) {
    this.beginYCoordinate = y;
  }

  /**
   * Mutator for the ending x coordinate.
   * 
   * @param x The specified x coordinate.
   */
  public void setEndX(int x) {
    this.endXCoordinate = x;
  }

  /**
   * Mutator for the ending y coordinate.
   * 
   * @param y The specified y coordinate.
   */
  public void setEndY(int y) {
    this.endYCoordinate = y;
  }
}
