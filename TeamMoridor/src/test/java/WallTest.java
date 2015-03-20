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
	
	@Test
	public void wallCanSeeIfItIsEqualToAnotherWall() {
		Space S1 = new Space(0,0);
		Space S2 = new Space(1,1);
		Space S3 = new Space(0,0);
		Wall wall1 = new Wall(S1,S1,S1,S1);
		Wall wall2 = new Wall(S2,S2,S2,S2);
		Wall wall3 = new Wall(S3,S3,S3,S3);
		
		assertFalse(wall1.isEqual(wall2));
		assertTrue(wall1.isEqual(wall3));
	}
	
	@Test
	public void isHorizontalWall() {
		Space S1 = new Space(0,1);
		Space S2 = new Space(0,0);
		Space S3 = new Space(1,1);
		Space S4 = new Space(1,0);
		
		Wall horizontalWall = new Wall(S1, S2, S3, S4);
		
		Space S5 = new Space(0,0);
		Space S6 = new Space(1,0);
		Space S7 = new Space(0,1);
		Space S8 = new Space(1,1);
		
		Wall verticalWall = new Wall(S5, S6, S7, S8);
		
		assertFalse(verticalWall.isHorizontal());
		assertTrue(horizontalWall.isHorizontal());
	
	}

}
