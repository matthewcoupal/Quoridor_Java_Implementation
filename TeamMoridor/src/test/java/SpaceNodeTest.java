package test.java;

import static org.junit.Assert.*;

import main.java.Space;
import main.java.SpaceNode;

import org.junit.Test;

public class SpaceNodeTest {

	@Test
	public void SpaceNodeCanBeConstructed() {
		SpaceNode spacenode = new SpaceNode(new Space(1,1));
	}
	
	@Test
	public void SpaceNodeCanGetCoordinates() {
		Space space = new Space(1,1);
		SpaceNode node = new SpaceNode(space);
		assertEquals(space, node.getCoordinates());
	}

}
