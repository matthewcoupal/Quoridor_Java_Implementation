
package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Random;
import main.java.SpaceNode;

/**
 * The artificial intelligence working to beat its opponents. Extends board rather than player since the methods of the board are much more valuable.
 * @author Matthew Coupal
 *
 */
public class AI extends Board {
    //Instance Variables

    private int playerNumber; //The AI's player number
    ArrayList<SpaceNode> goal = new ArrayList<SpaceNode>();
    int xMultiplier = 0;// The values pertaining on the x direction
    int yMultiplier = 0;// The values pertaining on the y direction
    
    
    
    /**
     * Generates the board with the default player number of 2 and creates the AI's win spaces.
     * @param playerNumber The player number that the AI should be.
     */
    public AI(int playerNumber) {
	super(2);
	this.setPlayerNumber(playerNumber);
	switch (this.getPlayerNumber()) {
	case 1:  for (int i = 0; i < 9; i++) {
	    goal.add(this.boardConfiguration.spaceAt(this.occupiedSpaces.get(0).getWinSpace(i).getX(), this.occupiedSpaces.get(0).getWinSpace(i).getY()));
	}
	yMultiplier = 1;
	break;
	case 2: for (int i = 0; i < 9; i++) {
	    goal.add(this.boardConfiguration.spaceAt(this.occupiedSpaces.get(1).getWinSpace(i).getX(), this.occupiedSpaces.get(1).getWinSpace(i).getY()));
	}
	yMultiplier = -1;
	break;
	default: ;
	break;
	}
    }

    /**
     * Generates the board with the specified number of players and finds the AI's win spaces.
     * @param numberOfPlayers The number of players in the game.
     * @param playerNumber The player number that the AI should be.
     */
    public AI(int numberOfPlayers, int playerNumber) {
	super(numberOfPlayers);
	this.setPlayerNumber(playerNumber);

	// Assign the winning spaces based on the player number
	switch (this.getPlayerNumber()) {
	case 1:  for (int i = 0; i < 9; i++) {
	    goal.add(this.boardConfiguration.spaceAt(this.occupiedSpaces.get(0).getWinSpace(i).getX(), this.occupiedSpaces.get(0).getWinSpace(i).getY()));
	}
	yMultiplier = 1;
	break;
	case 2: for (int i = 0; i < 9; i++) {
	    goal.add(this.boardConfiguration.spaceAt(this.occupiedSpaces.get(1).getWinSpace(i).getX(), this.occupiedSpaces.get(1).getWinSpace(i).getY()));
	}
	yMultiplier = -1;
	break;
	case 3:
		for (int i = 0; i < 9; i++) {
	    goal.add(this.boardConfiguration.spaceAt(this.occupiedSpaces.get(2).getWinSpace(i).getX(), this.occupiedSpaces.get(2).getWinSpace(i).getY()));
	}
	xMultiplier = 1;
	break;
	case 4:  for (int i = 0; i < 9; i++) {
	    goal.add(this.boardConfiguration.spaceAt(this.occupiedSpaces.get(3).getWinSpace(i).getX(), this.occupiedSpaces.get(3).getWinSpace(i).getY()));
	}
	xMultiplier = -1;
	break;
	default: ;
	break;
	}
    }
    
    /**
     * Calculates the average number of walls every player has on hand.
     * @return The average of walls left on the board (includes the AI itself).
     */
    public int averageWallCount() {
	int Walls = 0;
	for(int i = 0; i < this.occupiedSpaces.size(); i++) {
	    if(i != this.getPlayerNumber()) {
		Walls = Walls + this.occupiedSpaces.get(i).getWalls();
	    }
	}
	return Walls/(this.occupiedSpaces.size() - 1);
    }

    /**
     * Sets the AI's player number.
     * @param number The player number being assigned.
     */
    public void setPlayerNumber(int number) {
	this.playerNumber = number;
    }

    /**
     * Gets the AI's currently set player number.
     * @return the player number of the AI.
     */
    public int getPlayerNumber() {
	return this.playerNumber;
    }

    /**
     * Chooses the best move based on the current board configuration
     * @param board The board to be analyzed.
     * @return The move-string for the best move.
     */
    public String considerMove (Board board) {
    SpaceNode current = board.boardConfiguration.spaceAt(board.occupiedSpaces.get(this.getPlayerNumber()).getX(), board.occupiedSpaces.get(this.getPlayerNumber()).getY());
	/*if(board.occupiedSpaces.get(this.getPlayerNumber()).getWalls() != 0) {
	SpaceNode moveSpace = this.makeMove(board.boardConfiguration.spaceAt(board.occupiedSpaces.get(this.getPlayerNumber()).getX(), board.occupiedSpaces.get(this.getPlayerNumber()).getY()));
	return this.spaceToString(moveSpace.getCoordinates());
	}*/
	Random random = new Random();
	int move = random.nextInt(4);
	SpaceNode node = board.boardConfiguration.spaceAt(currentPlayer().getX(), currentPlayer().getY());
	String movestring = "";
	System.out.println("******"+move+"******"	);
	switch(move) {
	case 0:
		if(this.isLegalMove(currentPlayer(), node.getLeftNode().getCoordinates())) {
			return this.spaceToString(node.getLeftNode().getCoordinates());
		}
		break;
	case 1:
		if(this.isLegalMove(currentPlayer(), node.getRightNode().getCoordinates())) {
			return this.spaceToString(node.getRightNode().getCoordinates());
		}
	break;
	case 2:
		if(this.isLegalMove(currentPlayer(), node.getTopNode().getCoordinates())) {
			return this.spaceToString(node.getTopNode().getCoordinates());
		}
	break;
	case 3:
		if(this.isLegalMove(currentPlayer(), node.getBottomNode().getCoordinates())) {
			return this.spaceToString(node.getBottomNode().getCoordinates());
		}
	break;
	default:
	return this.spaceToString(new Space(8, 8));
	}
	
    
    //Backup algorithm just in case something goes wrong.
    //int X = current.getCoordinates().getX()+ this.xMultiplier;
    //int Y = current.getCoordinates().getY()+ this.yMultiplier;
    //SpaceNode best = boardConfiguration.spaceAt(X , Y );
    //String move = board.spaceToString(best.getCoordinates());
	//return move;
	return "";
    }

    /*/**
     * 
     * @param currentNode
     * @return
     */
   /* private Space makeMove(SpaceNode currentNode) {
	// Set default values
	int left = 9001;
	int right = 9001;
	int top = 9001;
	int bottom = 9001;
	ArrayList<Space> visitedSpaces = new ArrayList<Space>();
	ArrayList<Space> currentVisited = new ArrayList<Space>();
	currentVisited.add(currentNode.getCoordinates());
	visitedSpaces.addAll(currentVisited);
	if (currentNode.getLeftNode() != null) {
	    if (this.isLegalMove(this.occupiedSpaces.get(getPlayerNumber() - 1), currentNode.getLeftNode().getCoordinates())) {
		left = this.makeMove(currentNode.getLeftNode().getCoordinates(), visitedSpaces);
	    currentVisited.add(currentNode.getLeftNode().getCoordinates());
	    visitedSpaces.clear();
	    visitedSpaces.addAll(currentVisited);
	    }
	}
	if (currentNode.getRightNode() != null) {
	    if (this.isLegalMove(this.occupiedSpaces.get(getPlayerNumber() - 1), currentNode.getRightNode().getCoordinates())) {
		right = this.makeMove(currentNode.getRightNode().getCoordinates(), visitedSpaces);
		currentVisited.add(currentNode.getRightNode().getCoordinates());
		    visitedSpaces.clear();
		    visitedSpaces.addAll(currentVisited);
	    }    
	}
	if (currentNode.getTopNode() != null) {
	    if (this.isLegalMove(this.occupiedSpaces.get(getPlayerNumber() - 1), currentNode.getTopNode().getCoordinates())) {
		top = this.makeMove(currentNode.getTopNode().getCoordinates(), visitedSpaces);
		currentVisited.add(currentNode.getTopNode().getCoordinates());
		    visitedSpaces.clear();
		    visitedSpaces.addAll(currentVisited);
	    }
	}
	if (currentNode.getBottomNode() != null) {
	    if (this.isLegalMove(this.occupiedSpaces.get(getPlayerNumber() - 1), currentNode.getBottomNode().getCoordinates())) {
		bottom = this.makeMove(currentNode.getBottomNode().getCoordinates(), visitedSpaces);
		currentVisited.add(currentNode.getBottomNode().getCoordinates());
		    visitedSpaces.clear();
		    visitedSpaces.addAll(currentVisited);
	    }
	}
	int min = Math.min(Math.min(left, right), Math.min(top, bottom));

	if (min == left) {
	    return currentNode.getLeftNode().getCoordinates();
	} else if (min == right) {
	    return currentNode.getRightNode().getCoordinates();
	} else if (min == top) {
	    return currentNode.getTopNode().getCoordinates();
	} else if (min == bottom){
	    return currentNode.getBottomNode().getCoordinates();
	} else {
	    return new Space(-1,-1);
	}
    }*/

    
    /**
     * Traverses the board until it reaches the first occurrence of a winning space.
     * @param currentSpace The space that the AI is currently on
     * @return The best available space to move to; Null if for some reason any goal position cannot be reached.
     */
    /*private SpaceNode makeMove(SpaceNode currentSpace) {//ArrayList<Space> visitedSpaces, ArrayList<Space> currentVisited) {
    	Stack<SpaceNode> nodesToBeVisited = new Stack<SpaceNode>();
    	ArrayList<SpaceNode> nodesVisited = new ArrayList<SpaceNode>();
    	ArrayList<SpaceNode> allNodesVisited = new ArrayList<SpaceNode>();

    	nodesToBeVisited.push(currentSpace);

    	while(!nodesToBeVisited.empty()) {
    		SpaceNode current = nodesToBeVisited.pop();

    		int X = current.getCoordinates().getX();
    		int Y = current.getCoordinates().getY();
    		SpaceNode worst =boardConfiguration.spaceAt(X - xMultiplier, Y - yMultiplier);
    		SpaceNode neutral1 = boardConfiguration.spaceAt(X + yMultiplier, Y + xMultiplier);
    		SpaceNode neutral2 = boardConfiguration.spaceAt(X - yMultiplier, Y - xMultiplier);
    		SpaceNode best = boardConfiguration.spaceAt(X + xMultiplier, Y + yMultiplier);
    		//the only way to get to a goal is your best current move

    		if(goal.contains(best)){
    			return nodesVisited.get(1);
    		}

    		//best didn't isn't a winning space start looking
    		if(worst!=null && !allNodesVisited.contains(worst)){
    			nodesToBeVisited.add(worst);
    			//Added items to the array list for all nodes
    			//allNodesVisited.add(worst);
    		}
    		if(neutral1!=null && !allNodesVisited.contains(neutral1) ){
    			nodesToBeVisited.add(neutral1);
    			//allNodesVisited.add(neutral1);
    		}

    		if(neutral2!=null && !allNodesVisited.contains(neutral2)){
    			nodesToBeVisited.add(neutral2);
    			//allNodesVisited.add(neutral2);
    		}

    		if(best!=null && !allNodesVisited.contains(best)){
    			nodesToBeVisited.add(best);
    			//allNodesVisited.add(best);
    		}

    		nodesVisited.add(current);
    		allNodesVisited.add(current);
    		int m = nodesVisited.indexOf(current);
    		for(int i = nodesVisited.size() - 1; i > m; i--) {
    			nodesVisited.remove(i);
    		}


    	}
    	//If can't reach goal
    	return new SpaceNode(new Space(8,8))
;

    }*/
    
	/*currentVisited.add(currentSpace);
	visitedSpaces.add(currentSpace);
	SpaceNode currentNode = this.boardConfiguration.spaceAt(currentSpace.getX(), currentSpace.getY());

	int left = 9002;
	int right = 9002;
	int top = 9002;
	int bottom = 9002;

	ArrayList<Space> leftList = new ArrayList<Space>();
	ArrayList<Space> rightList = new ArrayList<Space>();
	ArrayList<Space> topList = new ArrayList<Space>();
	ArrayList<Space> bottomList = new ArrayList<Space>();

	if(currentNode.getLeftNode() != null && !visitedSpaces.contains(currentNode.getLeftNode().getCoordinates())) {
	    for (SpaceNode winNode: goal) {
		if (currentNode.getLeftNode() == winNode) {
		    return 1;
		}
	    }
	    
	    
	    //leftList.addAll(visitedSpaces);
	    left = this.makeMove(currentNode.getLeftNode().getCoordinates(), visitedSpaces, currentVisited);
	}
	if (currentNode.getRightNode() != null && !visitedSpaces.contains(currentNode.getRightNode().getCoordinates())) {
	    for (SpaceNode winNode: goal) {
		if (currentNode.getRightNode() == winNode) {
		    return 1;
		}
	    }
	    rightList.addAll(visitedSpaces);
	    right = this.makeMove(currentNode.getRightNode().getCoordinates(), visitedSpaces);
	}
	if (currentNode.getTopNode() != null && !visitedSpaces.contains(currentNode.getTopNode().getCoordinates())) {
	    for (SpaceNode winNode: goal) {
		if (currentNode.getTopNode() == winNode) {
		    return 1;
		}
	    }
	    topList.addAll(visitedSpaces);
	    top = this.makeMove(currentNode.getTopNode().getCoordinates(), visitedSpaces);
	}
	if (currentNode.getBottomNode() != null && !visitedSpaces.contains(currentNode.getBottomNode().getCoordinates())) {
	    for (SpaceNode winNode: goal) {
		if (currentNode.getBottomNode() == winNode) {
		    return 1;
		}
	    }
	    bottomList.addAll(visitedSpaces);
	    bottom = this.makeMove(currentNode.getBottomNode().getCoordinates(), visitedSpaces);
	}

	int min = Math.min(Math.min(left, right), Math.min(top, bottom));

	return min + 1; 
	}*/
    
}
