package test.java;

import static org.junit.Assert.*;

import main.java.Board;
import main.java.Space;
import main.java.SpaceLinkedList;
import main.java.SpaceNode;

import org.junit.Test;

public class SpaceLinkedListTest {

	@Test
	public void spaceLinkedListCanBeConstructed() {
		SpaceLinkedList spaceLinkedList = new SpaceLinkedList();
	}
	
	@Test
	public void spaceLinkedListCanGetSize() {
		SpaceLinkedList list = new SpaceLinkedList();
		assertEquals(0, list.size());
	}
	
	
	@Test
	public void spaceLinkedListCanAddSpaceNodes() {
		SpaceLinkedList list = new SpaceLinkedList();
		list.add(new SpaceNode(new Space(1,1)));
		assertEquals(1, list.size());
		list.add(new SpaceNode(new Space(2,2)));
		assertEquals(2, list.size());
	}
	
	@Test
	public void spaceLinkedListCanReferenceNodeBasedOnCoordinates() {
		SpaceLinkedList list = new SpaceLinkedList();
		list.add(new SpaceNode(new Space(1,1)));
		SpaceNode node = new SpaceNode(new Space(2,2));
		list.add(node);
		assertEquals(node, list.spaceAt(2,2));
		assertNull(list.spaceAt(4, 4));
	}
	
	@Test
	public void spaceLinkedListCanReferenceNodeBasedOnCoordinatesInGrid() {
		Board board = new Board();
		SpaceLinkedList list = board.getGrid();
		assertEquals(8, list.spaceAt(8, 8).getCoordinates().getX());
		assertEquals(7, list.spaceAt(7, 7).getCoordinates().getY());
		assertNull(list.spaceAt(100, 100));
	}
	
	
	

}
