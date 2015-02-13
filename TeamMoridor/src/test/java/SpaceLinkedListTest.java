package test.java;

import static org.junit.Assert.*;

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

}
