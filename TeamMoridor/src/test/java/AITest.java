package test.java;

import static org.junit.Assert.*;

import main.java.AI;
import main.java.Board;

import org.junit.Before;
import org.junit.Test;

public class AITest {

	/*@Before
	public void setup() {
		AI Travis = new AI(4);
	}*/
	
	@Test
	public void aiCanTakeAnAverageOfTheWallsLeftPerPlayer() {
		AI Travis = new AI(4,1);
		assertEquals(5, Travis.averageWallCount());
		AI Emily = new  AI(2,1);
		assertEquals(10, Emily.averageWallCount());
	}
	
	@Test
	public void aiCanSetandGetItsPlayerNumber() {
		AI Travis = new AI(4);
		AI Emily = new AI(5);
		Travis.setPlayerNumber(3);
		Emily.setPlayerNumber(1);
		assertEquals(3, Travis.getPlayerNumber());
		assertEquals(1, Emily.getPlayerNumber());
	}
	
	@Test
	public void aiCanChooseItsShortestPath() {
	    //Board board = new Board(4);
	    AI Travis = new AI(4,1);
	    assertEquals(Travis.spaceToString(Travis.boardConfiguration.spaceAt(4,1).getCoordinates()), Travis.considerMove(Travis));
	    AI Emily = new AI(4,2);
	    assertEquals(Emily.spaceToString(Emily.boardConfiguration.spaceAt(4,7).getCoordinates()), Emily.considerMove(Emily));
	    AI Danielle = new AI(4,3);
	    assertEquals(Danielle.spaceToString(Danielle.boardConfiguration.spaceAt(1, 4).getCoordinates()), Danielle.considerMove(Danielle));
	    AI Popo = new AI(4,4);
	    assertEquals(Popo.spaceToString(Popo.boardConfiguration.spaceAt(7, 4).getCoordinates()), Popo.considerMove(Popo));
	    AI Terry = new AI(2);
	    assertEquals(Terry.spaceToString(Terry.boardConfiguration.spaceAt(4,7).getCoordinates()), Terry.considerMove(Terry));
	}

}
