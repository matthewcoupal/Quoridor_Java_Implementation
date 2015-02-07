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

}
