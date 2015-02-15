package test.java;

import static org.junit.Assert.*;

import main.java.Player;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void playerCanBeConstructed() {
		Player player = new Player(4,5,10);
		assertEquals(player.getX(), 4);
		assertEquals(player.getY(), 5);
	}
	
	@Test
	public void playerCanGetWallsLeft() {
		Player player = new Player(4, 5, 10);
		assertEquals(10, player.getWalls());
	}
	
	@Test
	public void playerHasWinningSpaces() {
		Player player = new Player(4,0,10);
		for(int i = 0; i < 9; i++) {
			assertEquals(i, player.getWinSpace(i).getX());
		}
	}
	
	@Test
	public void playerCanDisplayWinningSpaceFromIndexNumber() {
		Player player = new Player(4,8,10);
		for(int i = 0; i < 9; i++)
			assertEquals(i, player.getWinSpace(i).getX());
	}

}
