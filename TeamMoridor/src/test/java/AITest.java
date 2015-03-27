package test.java;

import static org.junit.Assert.*;

import main.java.AI;

import org.junit.Before;
import org.junit.Test;

public class AITest {

	/*@Before
	public void setup() {
		AI Travis = new AI(4);
	}*/
	
	@Test
	public void aiCanTakeAnAverageOfTheWallsLeftPerPlayer() {
		AI Travis = new AI(4);
		assertEquals(5, Travis.averageWallCount());
		AI Emily = new  AI(2);
		assertEquals(10, Emily.averageWallCount());
	}
	
	@Test
	public void aiCanSetandGetItsPlayerNumber() {
		AI Travis = new AI(4);
		AI Emily = new AI();
		Travis.setPlayerNumber(3);
		Emily.setPlayerNumber(1);
		assertEquals(3, Travis.getPlayerNumber());
		assertEquals(1, Emily.getPlayerNumber());		
	}

}
