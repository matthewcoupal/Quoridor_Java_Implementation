package main.java;


public class SpaceNode {
	
	//Coordinates that are outside the board, which will be the default
	//    positions for the reference fields.
	
	//Instance Variables
	private Space coordinates;
	private SpaceNode top;
	private SpaceNode right;
	private SpaceNode bottom;
	private SpaceNode left;
	
	/**
	 * Constructor that assigns all reference nodes with null values.
	 * @param spaceCoordinates    The coordinates of the node itself.
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
	 * @return The coordinates of the node.
	 */
	public Space getCoordinates() {
		return this.coordinates;
	}


	/**
	 * Set the coordinates of this node object
	 * @param spaceCoordinates The coordinates that are being assigned to the node.
	 */
	public void setCoordinates(Space spaceCoordinates) {
		this.coordinates = spaceCoordinates;
	}
	

	/**
	 * Gets the reference of the Top Node
	 * @return The top field's Reference.
	 */
	public SpaceNode getTopNode() {
		return this.top;
	}


	/**
	 * Sets the reference of the top node.
	 * @param nodeReference    The node being linked to the top field.
	 */
	public void setTopNode(SpaceNode nodeReference) {
		this.top = nodeReference;
	}


	/**
	 * Sets the reference of the right node.
	 * @param nodeReference    The node being linked to the right field.
	 */
	public void setRightNode(SpaceNode nodeReference) {
		this.right = nodeReference;
		
	}


	/**
	 * Gets the reference of the right field.
	 * @return The right field's reference
	 */
	public SpaceNode getRightNode() {
		return this.right;
	}

	
	public void setBottomNode(SpaceNode nodeReference) {
		this.bottom = nodeReference;
		
	}

	public SpaceNode getBottom() {
		return this.bottom;
	}

}
