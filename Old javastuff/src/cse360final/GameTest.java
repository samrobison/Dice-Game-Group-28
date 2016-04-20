package cse360final;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest 
{

	@Test
	public void testEmptyConstructorPlayers() 
	{
		Game game = new Game();
		assertEquals(game.numOfPlayers, 0);
	}
	
	@Test
	public void testEmptyConstructorHumans() {
		Game game = new Game();
		assertEquals(game.numOfHumans, 0);
	}
	
	@Test
	public void testNonEmptyConstructorPlayers() {
		Game game = new Game(2, 1);
		assertEquals(2, game.numOfPlayers);
	}
	
	@Test
	public void testNonEmptyConstructorHumans() {
		Game game = new Game(3, 2);
		assertEquals(2, game.numOfHumans);
	}
	
	@Test
	public void testInitPlayers() {
		Game game = new Game(4, 1);
		game.init();
		assertEquals(4, game.players.length);
	}
	
	@Test
	public void testInitRound() {
		Game game = new Game(2, 1);
		game.init();
		assertEquals(game.round, 1);
	}

}
