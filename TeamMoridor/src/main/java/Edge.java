package main.java;

public class Edge {
	private int beginXCoordinate;
	private int endXCoordinate;
	private int beginYCoordinate;
	private int endYCoordinate;
	
	// Parameter: beginX - Starting x coordinate
	//			  beginY - Starting y coordinate
	//			  endX - Ending x coordinate
	//			  endY - Ending y coordinate
	//Precondition: >= 0;
	//Postconditon: Assigns Instance Variables their respective parameters.
	public Edge(int beginX, int endX, int beginY, int endY){
		this.beginXCoordinate = beginX;
		this.beginYCoordinate = beginY;
		this.endXCoordinate = endX;
		this.endYCoordinate = endY;
	}

	public int getBeginX() {
		return this.beginXCoordinate;
	}

	public int getBeginY() {
		return this.beginYCoordinate;
	}

	public int getEndX() {
		return 0;
	}
}
