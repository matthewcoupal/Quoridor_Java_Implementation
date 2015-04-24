package test.java;

import static org.junit.Assert.*;

import main.java.Space;
import main.java.SpaceLinkedList;
import main.java.SpaceNode;

import org.junit.Test;

public class SpaceNodeTest {

	@Test
	public void spaceNodeCanBeConstructed() {
		SpaceNode spacenode = new SpaceNode(new Space(1,1));
	}
	
	@Test
	public void spaceNodeCanGetCoordinates() {
		Space space = new Space(1,1);
		SpaceNode node = new SpaceNode(space);
		assertEquals(space, node.getCoordinates());
	}
	
	@Test
	public void spaceNodeCanSetCoordinates() {
		Space space = new Space(1,1);
		SpaceNode node = new SpaceNode(new Space(2,2));
		node.setCoordinates(space);
		assertEquals(space, node.getCoordinates());
	}
	
	//Coupled test for setting and getting coordinates. Hope to change this.
	@Test
	public void spaceNodeCanGetandSetTopNode() {
		SpaceNode node = new SpaceNode(new Space(1,1));
		SpaceNode node2 = new SpaceNode(new Space(2,2));
		node.setTopNode(node2);
		assertEquals(node2, node.getTopNode());
	}
	
	@Test
	public void spaceNodeCanGetandSetRightNode() {
		SpaceNode node = new SpaceNode(new Space(1,1));
		SpaceNode node2 = new SpaceNode(new Space(2,2));
		node.setRightNode(node2);
		assertEquals(node2, node.getRightNode());
	}
	
	@Test
	public void spaceNodeCanGetandSetBottomNode() {
		SpaceNode node = new SpaceNode(new Space(1,1));
		SpaceNode node2 = new SpaceNode(new Space(2,2));
		node.setBottomNode(node2);
		assertEquals(node2, node.getBottomNode());
	}
	
	@Test
	public void spaceNodeCanGetAndSetLeftNode() {
		SpaceNode node = new SpaceNode(new Space(1,1));
		SpaceNode node2 = new SpaceNode(new Space(2,2));
		node.setLeftNode(node2);
		assertEquals(node2, node.getLeftNode());
	}
	
	@Test
	public void spaceNodeHasToStringForNodeReferences() {
		SpaceNode node = new SpaceNode(new Space(1,1));
		node.setBottomNode(new  SpaceNode(new Space(2,2)));
		node.setTopNode(new SpaceNode(new Space(3,3)));
		node.setRightNode(new SpaceNode(new Space(4,4)));
		node.setLeftNode(new SpaceNode( new Space(5,5)));
		assertEquals("Node: 1,1\nTop: 3,3\nRight: 4,4\nBottom: 2,2\nLeft: 5,5", node.toString());
	}
	
	@Test
	public void spaceNodeCanSetReferencesToNull() {
		SpaceNode node = new  SpaceNode(new Space(1,1));
		node.setBottomNode(new  SpaceNode(new Space(2,2)));
		node.setTopNode(new SpaceNode(new Space(3,3)));
		node.setRightNode(new SpaceNode(new Space(4,4)));
		node.setLeftNode(new SpaceNode( new Space(5,5)));
		node.setBottomNode(null);
		node.setTopNode(null);
		node.setRightNode(null);
		node.setLeftNode(null);
		assertNull(node.getBottomNode());
		assertNull(node.getTopNode());
		assertNull(node.getRightNode());
		assertNull(node.getLeftNode());
		assertTrue(node.getRightNode() == null);
	}
}
