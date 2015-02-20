package test.java;

import static org.junit.Assert.*;

import main.java.Board;
import main.java.Player;
import main.java.Space;
import main.java.SpaceLinkedList;

import org.junit.Test;

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
		board.makeGrid(3);
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
    
   /* @Test
    public void boardCanSeeIfADoubleJumpIsLegal() {
    	Board board = new Board();
    	Player player = new Player(2,5,10);
    	assertFalse(board.isDoubleJumpLegal(player, new Space(4, 5)));
    	Player player2 = new Player(3,5,10);
    	assertTrue(board.is)
    	
    } */

}
