package main.java;

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
			while(current.getBottomNode() != null) {
				current = current.getBottomNode();
			}
			current.setBottomNode(newNode);
			count = count + 1;
		}
	}


	public SpaceNode spaceAt(int x, int y) {
		SpaceNode current = front;
		for(int i = 0; i < count; i++) {
			if (current.getCoordinates().getX() == x && current.getCoordinates().getY() == y) {
				return current;
			}
			current = current.getBottomNode();
		}
		return null;
	}
}