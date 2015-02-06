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
}
