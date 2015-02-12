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
	
	/*@Test
	public void SpaceNodeCanGetCoordinates() {
		SpaceNode node = new SpaceNode(new Space(1,1));
		
	}*/

}
