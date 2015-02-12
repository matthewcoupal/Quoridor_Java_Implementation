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


	public void setCoordinates(Space spaceCoordinates) {
		coordinates = spaceCoordinates;
	}
	
/*
	public Space getTopCoordinates() {
		return top.getCoordinates();
	}


	public void setTopCoordinates(Space space) {
		
	}*/

}
