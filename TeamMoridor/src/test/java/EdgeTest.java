package test.java;

import static org.junit.Assert.*;

import main.java.Edge;

import org.junit.Test;

public class EdgeTest {

	@Test
	public void edgeCanBeConstructedWithValues() {
		Edge line = new Edge(1,2,3,2);
	}
	
	@Test
	public void edgeCanGetBeginX() {
		Edge wall = new Edge(1,2,3,2);
		assertEquals(1, wall.getBeginX());
	}
	
	@Test
	public void edgeCanGetBeginY() {
		Edge wall = new Edge(1,2,3,2);
		assertEquals(3, wall.getBeginY());
	}
	
	@Test
	public void edgeCanGetEndX() {
		Edge wall = new Edge(1,2,3,2);
		assertEquals(2, wall.getEndX());
	}
	
	@Test
	public void edgeCanGetEndY() {
		Edge wall = new Edge(1,1,3,2);
		assertEquals(2, wall.getEndY());
	}
	
	@Test
	public void edgeCanSetBeginX() {
		Edge wall = new Edge(1,2,3,2);
		wall.setBeginX(7);
		assertEquals(7, wall.getBeginX());
	}
	
	@Test
	public void edgeCanSetBeginY() {
		Edge wall = new Edge(1,2,3,2);
		wall.setBeginY(7);
		assertEquals(7, wall.getBeginY());
	}
	
	@Test
	public void edgeCanSetEndX() {
		Edge wall = new Edge(1,5,3,2);
		wall.setEndX(7);
		assertEquals(7, wall.getEndX());
	}
	
	@Test
	public void edgeCanSetEndY() {
		Edge wall = new Edge(1,5,3,2);
		wall.setEndY(7);
		assertEquals(7, wall.getEndY());
	}

}
