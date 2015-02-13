package main.java;

public class SpaceLinkedList {

	private SpaceNode front = null;
	private int count = 0;
	
	public int size() {
			return count;
	}
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

}
