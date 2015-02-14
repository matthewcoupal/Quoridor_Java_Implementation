package main.java;

import java.util.ArrayList;
import java.util.Stack;

public class SpaceLinkedList {

	private SpaceNode front = null;
	private int count = 0;
	
	public int size() {
			return count;
	}
	
	
	/**
	 * Adds a new node to the linked list and adds one to the count.
	 * @param newNode    The node to be added to the list.
	 */
	public void add(SpaceNode newNode) {
		if(front == null) {
			front = newNode;
			count = count + 1;
		}else {
			SpaceNode current = front;
			while(current.getTopNode() != null) {
				current = current.getTopNode();
			}
			current.setTopNode(newNode);
			count = count + 1;
		}
	}

	// @Deprecated Version
	/*public SpaceNode spaceAt(int x, int y) {
		SpaceNode current = front;
		for(int i = 0; i < count; i++) {
			if (current.getCoordinates().getX() == x && current.getCoordinates().getY() == y) {
				return current;
			}
			current = current.getTopNode();
		}
		return null;
	}*/
	
	
	/**
	 * Traversed through the linked list, even with multiple node references,
	 * to find the space matching the x and y coordinates.
	 * @param x    The x coordinate to find
	 * @param y    The y coordinate to find
	 * @return     The SpaceNode which matched the coordinates specified; null
	 * if not found.
	 */
	public SpaceNode spaceAt(int x, int y) {
		return spaceAt(x, y, front);
	}
	
	
	
	/**
	 * Traverses through the a linked list, even with multiple node references,
	 * to find the space matching the x and y coordinates. Note that if this is
	 * used in a default linked list, the search cannot see the nodes behind
	 * its starting point.
	 * @param x    The x coordinate to find
	 * @param y    The y coordinate to find
	 * @param startingNode    The node to begin searching at
	 * @return     The SpaceNode which matched the coordinates specified; null
	 * if not found.
	 */
	public SpaceNode spaceAt(int x, int y, SpaceNode startingNode) {
		
		//Added this so that if startingNode is passed a SpaceNode not in the 
		//string, but within the coordinates, it will still run. Will return
		//null if not in the coordinates.
		if(startingNode != front) {
			startingNode = this.spaceAt(startingNode.getCoordinates().getX(), startingNode.getCoordinates().getY());
		}
		
		Stack<SpaceNode> nodesToVisit = new Stack<SpaceNode>();
		ArrayList<SpaceNode> nodesVisited = new ArrayList<SpaceNode>();
		SpaceNode current = startingNode;
		
		// Adds the first references to the stack if the references are not 
		// null
		if(current != null) {
			if(current.getCoordinates().getX() == x && current.getCoordinates().getY() == y)
				return current;
			if(current.getTopNode() != null)
				nodesToVisit.push(current.getTopNode());
			if(current.getBottomNode() != null)
				nodesToVisit.push(current.getBottomNode());
			if(current.getRightNode() != null)
				nodesToVisit.push(current.getRightNode());
			if(current.getLeftNode() != null)
				nodesToVisit.push(current.getLeftNode());
			// Adds current to the list of visited nodes
			nodesVisited.add(current);
		}
		
		// Takes the next reference off the stack if the node has not been
		// visited, then added its references to the stack if not null
		while(!nodesToVisit.empty()) {
			current = nodesToVisit.pop();
			if(current != null && !nodesVisited.contains(current)) {
				if(current.getCoordinates().getX() == x && current.getCoordinates().getY() == y)
					return current;
				if(current.getTopNode() != null || !nodesVisited.contains(current.getTopNode()))
					nodesToVisit.push(current.getTopNode());
				if(current.getBottomNode() != null || !nodesVisited.contains(current.getBottomNode()))
					nodesToVisit.push(current.getBottomNode());
				if(current.getRightNode() != null || !nodesVisited.contains(current.getRightNode()))
					nodesToVisit.push(current.getRightNode());
				if(current.getLeftNode() != null || !nodesVisited.contains(current.getLeftNode()))
					nodesToVisit.push(current.getLeftNode());
				// Adds current to the list of visited nodes
				nodesVisited.add(current);
			}	
		}
		return null;
	}
}