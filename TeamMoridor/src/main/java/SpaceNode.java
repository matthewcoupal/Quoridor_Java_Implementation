package main.java;


public class SpaceNode {

	private Space coordinates;
	private SpaceNode top;
	private SpaceNode right;
	private SpaceNode bottom;
	private SpaceNode left;
	
	
	public SpaceNode(Space spaceCoordinates) {
		coordinates = spaceCoordinates;
	}

	
	public Space getCoordinates() {
		return coordinates;
	}

}
