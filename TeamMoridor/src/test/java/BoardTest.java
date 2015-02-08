package test.java;

import static org.junit.Assert.*;

import main.java.Board;
import main.java.Space;

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
	public void boardWillTestForPlayerMovingOutOfBoundaries () {
		Board board = new Board();
		assertFalse(board.isOutOfBounds(new Space(0,1), new Space(-1,1)));
	}

}
