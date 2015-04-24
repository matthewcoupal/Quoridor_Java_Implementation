package test.java;

import static org.junit.Assert.*;
import main.java.Space;

import org.junit.Test;

public class SpaceTest {
	
	@Test
	public void canBeConstructed() {
		Space space = new Space(0, 0);
	}
	
	@Test
	public void canGetCoordinates(){
		Space square = new Space(1, 2);
		assertEquals(1, square.getX());
		assertEquals(2, square.getY());
	}
	
	@Test
	public void canSetCoordinates(){
		Space square = new Space(3, 4);
		square.setX(5);
		square.setY(6);
		assertEquals(5, square.getX());
		assertEquals(6, square.getY());
	}
}
