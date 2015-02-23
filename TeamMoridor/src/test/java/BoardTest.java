package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import main.java.Board;
import main.java.Player;
import main.java.Space;
import main.java.SpaceLinkedList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
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
        assertFalse(board.isMoveLegalDiagonal(new Space(0,0), new Space(1,0)));
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
    public void boardCanSeeIfAWallIsBetweenTwoNodes() {
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

    @Test(expected=NoSuchElementException.class)
    public void boardCanMakeProperPlayerMoveCalls() throws Exception {
    	Board board = Mockito.spy(new Board());
    	Player player = Mockito.mock(Player.class);
    	Space space = Mockito.mock(Space.class);
    	when(space.getX()).thenReturn(1);
    	when(space.getY()).thenReturn(1);
    	when(player.getY()).thenReturn(2);
    	when(player.getX()).thenReturn(1);
    	board.makeMove(player, space);
    	//board.makeMove(player, space);
    	Mockito.verify(board, times(1)).isLegalMove(player, space);
    	Mockito.verify(board, times(0)).bootPlayer(player);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void boardCanSetAndGetTheCurrentPlayer() throws Exception {
    	Board board = PowerMockito.spy(new Board());
    	ArrayList<Player> innerPlayer = Whitebox.getInternalState(board, "occupiedSpaces");
    	board.setCurrentPlayer(-1);
    	for(int i = 0; i < innerPlayer.size(); i++) {
    		board.setCurrentPlayer(i);
    		Player player = board.currentPlayer();
    		assertEquals(innerPlayer.get(i), player);
    	}
    }
    
    @Test
    public void boardCanSeeIfADoubleJumpIsLegal() throws Exception {
    	
    	Board board = Mockito.spy(new Board());
    	Space space = Mockito.mock(Space.class);
    	board.setCurrentPlayer(0);
    	assertFalse(board.isDoubleJumpLegal(board.currentPlayer(), space));
    	board.setCurrentPlayer(1);
    	when(space.getX()).thenReturn(4);
    	when(space.getY()).thenReturn(2);
    	board.currentPlayer().setX(4);
    	board.currentPlayer().setY(1);
    	assertEquals(4, board.currentPlayer().getX());
    	assertEquals(1, board.currentPlayer().getY());
    	board.setCurrentPlayer(0);
    	assertTrue(board.isDoubleJumpLegal(board.currentPlayer(), space));
    }
    
    @Test
    public void boardCanCheckIfAWallPlacementIsLegal() {
    	Board board = Mockito.spy(new Board());
    	Space space1 = new Space(2,2);
    	Space space2 = new Space(2,1);
    	Space space3 = new Space(3,2);
    	Space space4 = new Space(3,1);
    	Player player = new Player(4,0,10);
    	board.placeWall(player, space1, space2, space3, space4);
    	Mockito.verify(board, times(0)).bootPlayer(player);
    	//Need to add another method to be able to test a call to the boot player method.
    }
    
    @Test
    public void boardCanSeeIfAWallCanBePlaced() {
    	Board board = Mockito.spy(new Board());
    	Space space = Mockito.mock(Space.class);
    	Player player = Mockito.mock(Player.class);
    	board.placeWall(player, space, space, space, space);
    	Mockito.verify(board, times(1)).canPlaceWall(space, space, space, space);
    }
    
    @Test
    public void boardCanCallAllNeededMethodsCheckIfAMoveIsLegal () throws Exception  {
    	Board board = Mockito.spy(new Board());
    	Player player = Mockito.mock(Player.class);
    	Space space = Mockito.mock(Space.class);
    	board.makeMove(player, space);
    	Mockito.verify(board, times(1)).isLegalMove(player, space);
    	
    }

    
    @Test
    public void boardCanCheckIfAMoveIsFullyLegal() {
    	Board board = Mockito.spy(new Board());
    	Space space = new Space(1,1);
    	Player player = new Player(2,1,10);
    	assertTrue(board.isLegalMove(player, space));
    	space.setX(8);
    	space.setY(7);
    	player.setX(1);
    	player.setY(3);
    	assertFalse(board.isLegalMove(player,space));
    	Mockito.verify(board, times(2)).isOutOfBounds(space);
    	Mockito.verify(board, times(2)).isLegalSingleMove(player, space);
    	Mockito.verify(board, times(1)).isDoubleJumpLegal(player, space);
    	Mockito.verify(board, times(1)).isMoveLegalDiagonal(player, space);
    }
    
    @Test
    public void boardCanCheckIfASingleMoveIsLegal() {
    	Board board = Mockito.spy(new Board());
    	Space space = Mockito.mock(Space.class);
    	Player player = Mockito.mock(Player.class);
    	when(player.getX()).thenReturn(1);
    	when(player.getY()).thenReturn(1);
    	when(space.getX()).thenReturn(0);
    	when(space.getY()).thenReturn(1);
    	assertTrue(board.isLegalSingleMove(player, space));
    	Mockito.verify(board, times(1)).isWallHere(player, space);
    }
}
