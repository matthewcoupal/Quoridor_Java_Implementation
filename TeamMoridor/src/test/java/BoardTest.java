package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import main.java.Board;
import main.java.Player;
import main.java.Space;
import main.java.SpaceLinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.testng.PowerMockTestCase;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.times;

@PrepareForTest({Board.class})
@RunWith(PowerMockRunner.class)

public class BoardTest {

	@Test
	public void boardCanBeConstructedWithValues() {
		Board board1 = new Board(2);
		Board board2 = new Board(4);
	}
	
	@Test
	public void boardCanBeConstructedWithoutValues() {
		Board board = new Board();
	}
	
	@Test
	public void boardCanBeConsturctedWithGrid() {
		Board board = new Board();
		assertTrue(board.size() == 81);
	}
	
	
	@Test
	public void boardWillTestForPlayerMovingOutOfBoundaries() {
		Board board = new Board();
		assertTrue(board.isOutOfBounds(new Space(-1,1)));
	}

    @Test
    public void boardCanTestForPlayerMovingDiagonally() {
        Board board = new Board();
        assertTrue(board.isMoveDiagonal(new Space(0,0), new Space(1,1)));
        assertFalse(board.isMoveDiagonal(new Space(0,0), new Space(1,0)));
        assertFalse(board.isMoveLegalDiagonal(new Space(0,0), new Space(1,1)));
    }
    
    @Test
    public void boardCanTestForPlayerMovingIntoAnOccupiedSpace() {
    	Board board = new Board();
    	assertTrue(board.isPlayerHere(new Player(4,9,10)));
    }
    
    @Test
    public void boardCanPassGrid() {
    	Board board = new Board();
    	SpaceLinkedList list = board.getGrid();
    	assertEquals(81, list.size());
    }
    
    
    @Test
    public void boardCanCreateGrid() {
    	Board board = new Board();
    	
    	SpaceLinkedList list = board.getGrid();
    	assertNull(list.spaceAt(9, 9));
    	assertEquals(list.spaceAt(1, 2), list.spaceAt(2, 2).getLeftNode());
    	assertEquals(list.spaceAt(4, 5), list.spaceAt(4, 4).getTopNode());
    	assertEquals(list.spaceAt(3, 4), list.spaceAt(4,4).getLeftNode());
    	assertEquals(list.spaceAt(5, 4), list.spaceAt(4, 4).getRightNode());
    	assertEquals(list.spaceAt(4, 3), list.spaceAt(4,4).getBottomNode());
    	
    	assertEquals(list.spaceAt(8, 7), list.spaceAt(8, 8).getBottomNode());
    	assertEquals(list.spaceAt(7, 8), list.spaceAt(8, 8).getLeftNode());
    	assertNull(list.spaceAt(8, 8).getTopNode());
    	assertNull(list.spaceAt(-1, 7));
    	
    	assertEquals(list.spaceAt(8, 8), list.spaceAt(8,7).getTopNode());
    	assertEquals(list.spaceAt(8,8), list.spaceAt(7, 8).getRightNode());

    	assertNull(list.spaceAt(4, 8).getTopNode());
    	assertNull(list.spaceAt(4, 0).getBottomNode());
    	assertEquals(list.spaceAt(4, 1), list.spaceAt(4, 0).getTopNode());
    	assertEquals(list.spaceAt(4,0), list.spaceAt(4, 1).getBottomNode());
    }
    
    @Test
    public void boardCanCheckIfPlayerCanReachTheEnd() {
    	Board board = new Board();
    	Player player = new Player(4,0,10);
    	assertTrue(board.canReachEnd(player));
    	player.setX(9);
    	player.setY(9);
    	assertFalse(board.canReachEnd(player));
    }
    
    @Test
    public void boardCanSeeIfAWallIsBetweenTwoNodes() throws Exception {
    	Board board = new Board();
    	assertFalse(board.isWallHere(new Space(4,4), new Space(4,5)));
    	//Make more asserts when more methods are completed.
    }
    
    @Test
    public void playerIsInWinSpace() {
        Board board = new Board();
	Player player1 = new Player(4, 0, 10);
	assertFalse(board.isWinner(player1));
	player1.setY(8);
	assertTrue(board.isWinner(player1));
	Player player2 = new Player(4, 8, 10);
	assertFalse(board.isWinner(player2));
	player2.setY(0);
	assertTrue(board.isWinner(player2));
	Player player3 = new Player(0, 4, 10);
	assertFalse(board.isWinner(player3));
	player3.setX(8);
	assertTrue(board.isWinner(player3));
	Player player4 = new Player(8, 4, 10);
	assertFalse(board.isWinner(player4));
	player4.setX(0);
	assertTrue(board.isWinner(player4));
    }


    /*@Test
    public void boardCanSeeIfADoubleJumpIsLegal() {
    	
    	Board board = new Board();
    	Player player = new Player(2,5,10);
    	assertFalse(board.isDoubleJumpLegal(player, new Space(4, 5)));
    	Player player2 = new Player(3,5,10);
    	assertTrue(board.isDoubleJumpLegal(player, new Space(4,5)));
    } */



}
