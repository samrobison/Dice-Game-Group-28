package cse360final;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void constructorTestHuman() {
		Player player = new Player("Jennifer", true);
		assertEquals(player.isHuman(), true);
	}
	
	@Test
	public void constructorTestUsername() {
		Player player = new Player("Alfredo", false);
		assertEquals("Alfredo", player.getName());
	}
	
	@Test
	public void testScore() {
		Player player = new Player("Grigoryth", false);
		assertEquals(player.score, 0);
	}
	
	@Test
	public void testAddScore() {
		Player player = new Player("Marissu", true);
		player.changeScore(20);
		assertEquals(player.score, 20);
	}
	
	@Test
	public void testSubtractScore() {
		Player player = new Player("Emiloy", false);
		player.changeScore(30);
		player.changeScore(-20);
		assertEquals(player.score, 10);
	}
	
	@Test
	public void belowZeroScore() {
		Player player = new Player("Tryler", true);
		player.changeScore(-4);
		assertEquals(player.score, 0);
	}
	
	@Test
	public void isHumanTest() {
		Player player = new Player("Ryam", false);
		assertEquals(player.isHuman(), false);
	}
	
	@Test
	public void getNameTest() {
		Player player = new Player("Giraffe", false);
		assertEquals("Giraffe", player.getName());
	}

}
