package main.java;

import java.util.ArrayList;

public class Board {
	//Instance Variables
	private ArrayList<Space> occupiedSpaces; //List of occupied spaces
	private ArrayList<Edge> occupiedEdges; //List of wall locations
	
	public Board(int numberOfPlayers){
		this.occupiedSpaces = new ArrayList<Space>();
		this.occupiedEdges = new ArrayList<Edge>();
		if(numberOfPlayers == 2) {
			this.occupiedSpaces.add(new Space(5,0));
			this.occupiedSpaces.add(new Space(5,9));
		} else if (numberOfPlayers == 4) {
			this.occupiedSpaces.add(new Space(5,0));
			this.occupiedSpaces.add(new Space(5,9));
			this.occupiedSpaces.add(new Space(0,5));
			this.occupiedSpaces.add(new Space(9,5));
		}
	}
}
