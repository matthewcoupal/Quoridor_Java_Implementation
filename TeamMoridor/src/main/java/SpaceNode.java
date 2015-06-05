package main.java;

/**
 * Container for the Space objects on the logical board. Object has the ability to know who its
 * neighbors are.
 * 
 * @author Matthew Coupal
 *
 */
public class SpaceNode {

  // Coordinates that are outside the board, which will be the default
  // positions for the reference fields.

  // Instance Variables
  private Space coordinates;
  private SpaceNode top;
  private SpaceNode right;
  private SpaceNode bottom;
  private SpaceNode left;

  /**
   * Constructor that assigns all reference nodes with null values.
   * 
   * @param spaceCoordinates The coordinates of the node itself.
   */
  public SpaceNode(Space spaceCoordinates) {
    coordinates = spaceCoordinates;
    top = null;
    right = null;
    bottom = null;
    left = null;
  }

  /**
   * Get coordinate of the node object
   * 
   * @return The coordinates of the node.
   */
  public Space getCoordinates() {
    return this.coordinates;
  }


  /**
   * Set the coordinates of this node object
   * 
   * @param spaceCoordinates The coordinates that are being assigned to the node.
   */
  public void setCoordinates(Space spaceCoordinates) {
    this.coordinates = spaceCoordinates;
  }


  /**
   * Gets the reference of the Top Node
   * 
   * @return The top field's Reference.
   */
  public SpaceNode getTopNode() {
    return this.top;
  }


  /**
   * Sets the reference of the top node.
   * 
   * @param nodeReference The node being linked to the top field.
   */
  public void setTopNode(SpaceNode nodeReference) {
    this.top = nodeReference;
  }


  /**
   * Sets the reference of the right node.
   * 
   * @param nodeReference The node being linked to the right field.
   */
  public void setRightNode(SpaceNode nodeReference) {
    this.right = nodeReference;

  }


  /**
   * Gets the reference of the right field.
   * 
   * @return The right field's reference.
   */
  public SpaceNode getRightNode() {
    return this.right;
  }

  /**
   * Sets the reference of the bottom field.
   * 
   * @param nodeReference The node being linked to the right field.
   */
  public void setBottomNode(SpaceNode nodeReference) {
    this.bottom = nodeReference;

  }

  /**
   * Gets the reference of the bottom field.
   * 
   * @return The bottom field's reference.
   */
  public SpaceNode getBottomNode() {
    return this.bottom;
  }

  /**
   * Sets the reference of the left field.
   * 
   * @param nodeReference The node being linked to the left field.
   */
  public void setLeftNode(SpaceNode nodeReference) {
    this.left = nodeReference;

  }

  /**
   * Gets the reference of the left field.
   * 
   * @return The left field's reference.
   */
  public SpaceNode getLeftNode() {
    return this.left;
  }

  /**
   * Overrides the Object's toString method and outputs the coordinates of the node and its
   * surrounding nodes.
   * 
   * @return Node coordinates, top, right, bottom, and left node coordinates.
   */
  @Override
  public String toString() {
    // Node
    int nodeX = this.coordinates.getX();
    int nodeY = this.coordinates.getY();
    // Top
    int topX;
    int topY;
    if (this.top != null) {
      topX = this.top.getCoordinates().getX();
      topY = this.top.getCoordinates().getY();
    } else {
      topX = -1;
      topY = -1;
    }
    // Right
    int rightX;
    int rightY;
    if (this.right != null) {
      rightX = this.right.getCoordinates().getX();
      rightY = this.right.getCoordinates().getY();
    } else {
      rightX = -1;
      rightY = -1;
    }
    // Bottom
    int bottomX;
    int bottomY;
    if (this.bottom != null) {
      bottomX = this.bottom.getCoordinates().getX();
      bottomY = this.bottom.getCoordinates().getY();
    } else {
      bottomX = -1;
      bottomY = -1;
    }
    // Left
    int leftX;
    int leftY;
    if (this.left != null) {
      leftX = this.left.getCoordinates().getX();
      leftY = this.left.getCoordinates().getY();
    } else {
      leftX = -1;
      leftY = -1;
    }
    return "Node: " + nodeX + "," + nodeY + "\nTop: " + topX + "," + topY + "\nRight: " + rightX
        + "," + rightY + "\nBottom: " + bottomX + "," + bottomY + "\nLeft: " + leftX + "," + leftY;
  }

  /**
   * Overrides the equals method in the Object class. Checks to see if the given node is equal to
   * this node. Nodes do not need to have the same memory address.
   * 
   * @param comparison The specified node to compare.
   * @return True if the nodes are equal, false otherwise
   */
  public boolean equals(SpaceNode comparison) {
    // int compX = comparison.getCoordinates().getX();
    // int compY = comparison.getCoordinates().getY();
    if (comparison == null) {
      return false;
    }
    return this.coordinates.equals(comparison.getCoordinates());
  }

}
