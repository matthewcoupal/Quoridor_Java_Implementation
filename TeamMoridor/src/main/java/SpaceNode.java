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
		return coordinates;
	}


	public void setCoordinates(Space spaceCoordinates) {
		this.coordinates = spaceCoordinates;
	}
	

	public SpaceNode getTopNode() {
		return top;
	}


	public void setTopNode(SpaceNode nodeSpace) {
		this.top = nodeSpace;
	}

}
