package test.java;

/**
 * @author Matthew Coupal
 */

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
import main.java.SpaceNode;
import main.java.Wall;

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
		assertTrue(board.isOutOfBounds(new Space(-1, 1)));
	}

	@Test
	public void boardCanTestForPlayerMovingDiagonally() {
		Board board = new Board();
		assertFalse(board.isMoveLegalDiagonal(new Space(0, 0), new Space(1, 0)));
		assertFalse(board.isMoveLegalDiagonal(new Space(0, 0), new Space(1, 1)));
	}

	@Test
	public void boardCanTestForPlayerMovingIntoAnOccupiedSpace() {
		Board board = new Board();
		assertTrue(board.isPlayerHere(new Player(4, 8, 10)));
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
		assertEquals(list.spaceAt(3, 4), list.spaceAt(4, 4).getLeftNode());
		assertEquals(list.spaceAt(5, 4), list.spaceAt(4, 4).getRightNode());
		assertEquals(list.spaceAt(4, 3), list.spaceAt(4, 4).getBottomNode());

		assertEquals(list.spaceAt(8, 7), list.spaceAt(8, 8).getBottomNode());
		assertEquals(list.spaceAt(7, 8), list.spaceAt(8, 8).getLeftNode());
		assertNull(list.spaceAt(8, 8).getTopNode());
		assertNull(list.spaceAt(-1, 7));

		assertEquals(list.spaceAt(8, 8), list.spaceAt(8, 7).getTopNode());
		assertEquals(list.spaceAt(8, 8), list.spaceAt(7, 8).getRightNode());

		assertNull(list.spaceAt(4, 8).getTopNode());
		assertNull(list.spaceAt(4, 0).getBottomNode());
		assertEquals(list.spaceAt(4, 1), list.spaceAt(4, 0).getTopNode());
		assertEquals(list.spaceAt(4, 0), list.spaceAt(4, 1).getBottomNode());
	}

	@Test
	public void boardCanCheckIfPlayerCanReachTheEnd() {
		Board board = new Board();
		Player player = new Player(4, 0, 10);
		assertTrue(board.canReachEnd(player));
		player.setX(9);
		player.setY(9);
		assertFalse(board.canReachEnd(player));
	}

	@Test
	public void boardCanSeeIfAWallIsBetweenTwoNodes() {
		Board board = new Board();
		Space space1 = new Space(4,4);
		Space space2 = new Space(4,5);
		assertFalse(board.isWallHere(space1, space2, 0));
		board.placeWall(space1, space2);
		assertTrue(board.isWallHere(new Space(4,4), new Space(5,4), 0));
		// Make more asserts when more methods are completed.
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

	@Test(expected = NoSuchElementException.class)
	public void boardCanMakeProperPlayerMoveCalls() throws Exception {
		Board board = Mockito.spy(new Board());
		Player player = Mockito.mock(Player.class);
		Space space = Mockito.mock(Space.class);
		when(space.getX()).thenReturn(1);
		when(space.getY()).thenReturn(1);
		when(player.getY()).thenReturn(2);
		when(player.getX()).thenReturn(1);
		board.makeMove(player, space);
		Mockito.verify(board, times(1)).isLegalMove(player, space);
		// Mockito.verify(board, times(0)).bootPlayer(player);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void boardCanSetAndGetTheCurrentPlayer() throws Exception {
		Board board = PowerMockito.spy(new Board());
		ArrayList<Player> innerPlayer = Whitebox.getInternalState(board,
				"occupiedSpaces");
		board.setCurrentPlayer(-1);
		for (int i = 0; i < innerPlayer.size(); i++) {
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
	public void boardCanCallAllNeededMethodsCheckIfAMoveIsLegal()
			throws Exception {
		Board board = Mockito.spy(new Board());
		Player player = Mockito.mock(Player.class);
		Space space = Mockito.mock(Space.class);
		board.makeMove(player, space);
		Mockito.verify(board, times(1)).isLegalMove(player, space);

	}

	@Test
	public void boardCanCheckIfAMoveIsFullyLegal() {
		Board board = Mockito.spy(new Board());
		Space space = new Space(1, 1);
		Player player = new Player(2, 1, 10);
		assertTrue(board.isLegalMove(player, space));
		space.setX(8);
		space.setY(7);
		player.setX(1);
		player.setY(3);
		assertFalse(board.isLegalMove(player, space));
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
		Mockito.verify(board, times(1)).isWallHere(player, space, 0);
	}

	@Test
	public void boardCanTranslateStringToSpaceObject() {
		Board board = new Board();
		assertEquals(new Space(5, 7).getX(), board.StringtoCoordinates("VI-H")
				.getX());
		assertEquals(new Space(5, 7).getY(), board.StringtoCoordinates("VI-H")
				.getY());
	}

	@Test
	public void boardCanTranslateSpaceObjectToString() {
		Board board = new Board();
		assertTrue(board.spaceToString(new Space(7, 5)).compareTo("VIII-F") == 0);
	}

	// Wall Tests
	@Test
	public void canAddToAndRetrieveFromPlacedWallsField() {
		Board board = Mockito.spy(new Board());
		// Space space1 = Mockito.mock(Space.class);
		// Space space2 = Mockito.mock(Space.class);
		// Space space3 = Mockito.mock(Space.class);
		// Space space4 = Mockito.mock(Space.class);
		Space space5 = new Space(6, 7);
		Space space6 = new Space(6, 8);
		Space space7 = new Space(2, 7);
		Space space8 = new Space(2, 8);

		Wall wall1 = new Wall(space6, space5);
		Wall wall2 = new Wall(space8, space7);
		board.setPlacedWalls(wall1);
		board.setPlacedWalls(wall2);
		assertTrue(board.getPlacedWalls(0).isEqual(wall1));
		assertFalse(board.getPlacedWalls(0).isEqual(wall2));
	}

	@Test
	public void boardCanPlaceAWall() {
		// Board board = Mockito.spy(new Board());
		// Space space = Mockito.mock(Space.class);
		// Player player = Mockito.mock(Player.class);
		// board.placeWall(space, space, space, space);
		// Mockito.verify(board, times(1)).canPlaceWall(space, space, space,
		// space);
		Board board = new Board();
		Space space5 = new Space(6, 7);
		Space space6 = new Space(6, 8);
		Space space7 = new Space(2, 7);
		Space space8 = new Space(2, 8);
		Wall wall1 = new Wall(space6, space5);
		Wall wall2 = new Wall(space8, space7);
		board.placeWall(space6, space5);
		board.placeWall(space8, space7);
		assertFalse(board.getPlacedWalls(0).isEqual(wall2));
		assertTrue(board.getPlacedWalls(0).isEqual(wall1));
	}

	@Test
	public void boardCanSeeIfAWallIsBetweenTwoSpaces() {
		Board board = new Board();
		Space space5 = new Space(6, 7);
		Space space6 = new Space(6, 8);
		Space space7 = new Space(2, 7);
		Space space8 = new Space(2, 8);
		Space space9 = new Space(5, 7);
		board.placeWall(space6, space5);
		assertFalse(board.isWallHere(space8, space7, 0));
		assertFalse(board.isWallHere(space6, space9, 0));
	}

	@Test
	public void boardCanCheckIfAWallPlacementIsLegalWhenCurrentPlayerHasNoWallsLeft() {
		Board board = new Board();
		Space space1 = new Space(2, 2);
		Space space2 = new Space(2, 1);
		assertTrue(board.canPlaceWall(space1, space2));
		board.currentPlayer().setWalls(0);
		assertFalse(board.canPlaceWall(space1, space2));
	}

	@Test
	public void boardCanCheckIfAWallPlacementIsLegalWhenThereIsAlreadyAWallOnOneOfTheEdges() {
		Board board = new Board();

		// Case 1: testing vertical wall placement
		Space space0 = new Space(2, 0);
		Space space1 = new Space(2, 1);
		Space space2 = new Space(2, 2);
		Space space3 = new Space(2, 3);
		Space space4 = new Space(2, 4);
		assertTrue(board.canPlaceWall(space2, space1));
		board.placeWall(space2, space1);
		assertFalse(board.canPlaceWall(space3, space2));
		assertFalse(board.canPlaceWall(space1, space0));
		assertTrue(board.canPlaceWall(space4, space3));

		// Case 2: testing horizontal wall placement
		Space space00 = new Space(4, 6);
		Space space5 = new Space(5, 6);
		Space space6 = new Space(6, 6);
		Space space7 = new Space(7, 6);
		Space space8 = new Space(8, 6);
		assertTrue(board.canPlaceWall(space5, space6));
		board.placeWall(space5, space6);
		assertFalse(board.canPlaceWall(space6, space7));
		assertFalse(board.canPlaceWall(space00, space5));
		assertTrue(board.canPlaceWall(space7, space8));

	}

	@Test
	public void boardCanCheckIfAWallPlacementIsLegalWhenThereIsACrossingWallAlreadyPlaced() {
		Board board = new Board();

		// Case 1: testing vertical wall placement
		Space space1 = new Space(2, 2);
		Space space2 = new Space(2, 1);
		Space space3 = new Space(1, 1);
		assertTrue(board.canPlaceWall(space1, space2));
		board.placeWall(space1, space2);
		assertTrue(board.canPlaceWall(space3, space2)); // can't place a wall
		// that crosses a placed
		// wall.

		// Case 2: testing horizontal wall placement
		Space space5 = new Space(5, 6);
		Space space6 = new Space(6, 6);
		Space space7 = new Space(5, 7);
		assertTrue(board.canPlaceWall(space5, space6));
		board.placeWall(space5, space6);
		assertFalse(board.canPlaceWall(space5, space7)); // can't place a wall
		// that crosses a
		// placed wall.
	}

	@Test
	public void boardCanCheckIfAWallPlacementIsLegalWhenItBlocksAplayerFromReachingTheEnd() {
		Board board = new Board();

		Space space1 = new Space(0, 4);
		Space space2 = new Space(1, 4);
		Space space3 = new Space(2, 4);
		Space space4 = new Space(3, 4);
		Space space5 = new Space(4, 4);
		Space space6 = new Space(5, 4);
		Space space7 = new Space(6, 4);
		Space space8 = new Space(7, 4);
		Space space9 = new Space(8, 4);
		Space space10 = new Space(7, 5);
		Space space11 = new Space(8, 5);

		assertTrue(board.canPlaceWall(space1, space2));
		board.placeWall(space1, space2);
		assertTrue(board.canPlaceWall(space3, space4));
		board.placeWall(space3, space4);
		assertTrue(board.canPlaceWall(space5, space6));
		board.placeWall(space5, space6);
		assertTrue(board.canPlaceWall(space7, space8));
		board.placeWall(space7, space8);
		assertFalse(board.canPlaceWall(space11, space9));
		// board.placeWall(space11, space9);
		assertTrue(board.canPlaceWall(space10, space11)); // can't place a wall
		// that blocks
		// passage to end.
	}

	@Test
	public void TestForHomeRowWallPlacement() {
		Board board = new Board();
		Space space1 = new Space(4, 7);
		Space space2 = new Space(3, 7);
		Space space3 = new Space(4, 8);
		// Space space4 = new Space(5,7);
		/*
		 * System.out.println(board.boardConfiguration.spaceAt(4, 7));
		 * System.out.println(board.boardConfiguration.spaceAt(3, 7));
		 * System.out.println(board.boardConfiguration.spaceAt(5, 7));
		 * System.out.println(board.boardConfiguration.spaceAt(5, 8));
		 */
		SpaceNode current = board.boardConfiguration.spaceAt(4, 8);
		System.out.println(current);
		board.placeWall(space1, space2);
		System.out.println(current);
		SpaceNode middle = board.boardConfiguration.spaceAt(4, 7);
		System.out.println(middle);
		SpaceNode leftOfPlayer = board.boardConfiguration.spaceAt(3, 8);
		System.out.println(leftOfPlayer);
		/*
		 * System.out.println(board.boardConfiguration.spaceAt(3, 7));
		 * System.out.println(board.boardConfiguration.spaceAt(5, 7));
		 * System.out.println(board.boardConfiguration.spaceAt(5, 8));
		 */
		// assertTrue(board.canPlaceWall(space3, space4));
		board.placeWall(space3, space1);
		System.out.println(leftOfPlayer);
		System.out.println(middle);
		System.out.println(current);
		/*
		 * System.out.println(board.boardConfiguration.spaceAt(4, 7));
		 * System.out.println(board.boardConfiguration.spaceAt(3, 7));
		 * System.out.println(board.boardConfiguration.spaceAt(5, 7));
		 * System.out.println(board.boardConfiguration.spaceAt(5, 8));
		 */
	}

	@Test
	public void TestForCrossingWalls() {
		Board board = new Board();
		Space space1 = new Space(3, 4);
		Space space2 = new Space(4, 4);
		//Space space2AndAHalf = new Space();
		Space space3 = new Space(3, 5);
		//board.placeWall(space1, space2);
		System.out.println(board.boardConfiguration.spaceAt(space1.getX(), space1.getY()));
		board.placeWall(space1, space3);
	}

	@Test
	public void TestForProperWallPlacementForFirstPlayer() {
		Board board = new Board();
		board.setCurrentPlayer(0);
		Player player1 = board.currentPlayer(); 
		Space space1 = new Space(4, 0);
		Space space2 = new Space(4, 1);
		System.out.println(board.boardConfiguration.spaceAt(4, 0));
		board.placeWall(space1, space2);
		board.makeMove(player1, board.boardConfiguration.spaceAt(5, 0).getCoordinates());
		System.out.println(board.boardConfiguration.spaceAt(4, 0));
		System.out.println();
	}
	
	@Test
	public void TestforDiamondError () {
		Board board = new Board();
		//Player player1 = new Player(2,0,10);
		board.setCurrentPlayer(0);
		Player player2 = board.currentPlayer();
		board.makeMove(player2, new Space(3,0));
		board.makeMove(player2, new Space(2,0));
		board.makeMove(player2, new Space(1,0));
		//board.makeMove(player1, new Space (2, 1));
		System.out.println(board.boardConfiguration.spaceAt(1, 0));
		System.out.println(board.boardConfiguration.spaceAt(0, 1));
		System.out.println(board.boardConfiguration.spaceAt(2, 1));
		System.out.println(board.boardConfiguration.spaceAt(1, 2));
	}
}
