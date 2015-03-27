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

}
