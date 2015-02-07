package test.java;

import static org.junit.Assert.*;

import main.java.Board;

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

}
