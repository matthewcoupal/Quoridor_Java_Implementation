package main.java;


public class SpaceNode {
	
	//Coordinates that are outside the board, which will be the default
	//    positions for the reference fields.
	private SpaceNode defaultRefNode;
	private Space coordinates;
	private SpaceNode top;
	private SpaceNode right;
	private SpaceNode bottom;
	private SpaceNode left;
	
	
	public SpaceNode(Space spaceCoordinates) {
		coordinates = spaceCoordinates;
		top = defaultRefNode = null;
		right = defaultRefNode = null;
		bottom = defaultRefNode = null;
		left = defaultRefNode = null;
	}

	
	public Space getCoordinates() {
		return this.coordinates;
	}


	public void setCoordinates(Space spaceCoordinates) {
		this.coordinates = spaceCoordinates;
	}
	

	public SpaceNode getTopNode() {
		return this.top;
	}


	public void setTopNode(SpaceNode nodeReference) {
		this.top = nodeReference;
	}


	public void setRightNode(SpaceNode nodeReference) {
		this.bottom = nodeReference;
		
	}


	public SpaceNode getRightNode() {
		return this.bottom;
	}

}
