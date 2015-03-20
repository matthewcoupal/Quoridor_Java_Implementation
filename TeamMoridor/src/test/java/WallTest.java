package test.java;

import static org.junit.Assert.*;
import main.java.Space;
import main.java.Wall;

import org.mockito.Mockito;

import org.junit.Test;

public class WallTest {

	@Test
	public void wallCanBeConstructed() {
		Space S1 = Mockito.mock(Space.class);
		Space S2 = Mockito.mock(Space.class);
		Space E1 = Mockito.mock(Space.class);
		Space E2 = Mockito.mock(Space.class);
		Wall wall = new Wall(S1, S2, E1, E2);
	}
	
	@Test
	public void wallCanSetandGetSurroundingSpaces() {
		Space S1 = Mockito.mock(Space.class);
		Space S2 = Mockito.mock(Space.class);
		Wall wall = new Wall(S1,S1,S1,S1);
		wall.setWallSurroundingSpace(2, S2);
		assertEquals(S2, wall.getWallSurroundingSpace(2));
	}

}
