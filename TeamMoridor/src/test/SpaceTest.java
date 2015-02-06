package test;

import static org.junit.Assert.*;

import main.Space;

import org.junit.Test;

public class SpaceTest {

	@Test
	public void canBeConstructed() {
		Space space = new Space(0,0, false);
	}

}
