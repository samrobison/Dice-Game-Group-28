package cse360final;

import java.util.Scanner;

/** Class to create a game to allow Players to partake in
 * a game of dice-bluffing and guessing
 * @author Group 28
 * @version April 15, 2016
 * 
 * 
 */
public class Game {
	
	//public boolean firstPlay = true;
	public int round;
	public int turn = 0;
	public int claim;
	public Player[] players;
	public String[] aiNames = {"Computer1", "Computer2", "Computer3", "Computer4", "Computer5", "Computer6", "Computer7", "Computer8"};
	public int numOfPlayers;
	public int numOfHumans;
	
	Scanner in = new Scanner(System.in);
	
	/** Create an empty game */
	Game ()
	{
		numOfPlayers = 0;
		numOfHumans = 0;
	}
	
	/** 
	 * Creates a nonempty game
	 * 
	 * @param numPlayers specifies the number of players in the game
	 * @param numHumans specifies the number of humans in the game
	 */
	Game (int numPlayers, int numHumans)
	{
		numOfPlayers = numPlayers;
		numOfHumans = numHumans;
	}
	
	/** 
	 * initializes conditions for the game to begin
	 */
	void init()
	{
		players = new Player[numOfPlayers];	//creates array of size players
		
		if(numOfHumans < numOfPlayers)
		{	
			for(int index = 0; index < numOfHumans; index++)
			{ //create human player
				System.out.print("Please Enter Your Name: ");
				String temp = in.next();
				players[index] = new Player(temp, true);	
				players[index].score = 0;
				players[index].immunity = true;
				System.out.print(temp + " has entered the game\n");
			}
			
			for(int index = numOfHumans; index < numOfPlayers; index++)
			{	//create all AI
				if(numOfHumans > 0)
				{
					String temp2;
					temp2 = aiNames[index];
					players[index] = new Player(temp2, false);
					players[index].score = 0;
					players[index].immunity = true;
					System.out.print(temp2 + " has entered the game\n");
				}
				
				else
				{
					String temp2;
					temp2 = aiNames[index];
					players[index] = new Player(temp2, false);
					players[index].score = 0;
					System.out.print(temp2 + " has entered the game\n");
				}
			}
		}
		
		round = 1;	//set round to 1
		
	}
	
	/** 
	 * sets in motion the mechanisms to allow the players to 
	 * play the game to its completion
	 */
	void playGame()
	{	
		while(round < 6)
		{
			turn = 0;
			
			while(turn < numOfPlayers)
			{
				Player currentClaimant;
				Player currentBluffCaller;
				
				if(turn == numOfPlayers - 1) //to keep the index of currentBluffCaller from going out of bounds
				{
					currentClaimant = players[turn];
					currentBluffCaller = players[0];
				}
				
				else
				{
					currentClaimant = players[turn];
					currentBluffCaller = players[turn + 1];
				}
				
				
				if(currentClaimant.isHuman())
				{
					currentClaimant.claimHuman();
				}
				
				else
				{
					currentClaimant.claimAI();
				}
				
				boolean bluffCalled;
				
				if(currentBluffCaller.isHuman())
				{
					bluffCalled = currentBluffCaller.callBluffHuman();
				}
				
				else
				{
					bluffCalled = currentBluffCaller.callBluffAI(currentClaimant.claimValue, currentClaimant.score);
				}
								
				if(bluffCalled)
				{
					if(currentClaimant.diceValue == currentClaimant.claimValue) //if bluff was wrong
					{
						currentClaimant.changeScore(3 * currentClaimant.claimValue / 2);
						currentBluffCaller.changeScore(currentClaimant.claimValue * -1);
						
						System.out.println(currentBluffCaller.getName() + " said " + currentClaimant.getName()
											+ " was bluffing.\n..." + currentBluffCaller.getName() + " was wrong!\n"
											+ currentBluffCaller.getName() + "'s score is now " + currentBluffCaller.getScore()
											+ ", and " + currentClaimant.getName() + "'s score is now " + currentClaimant.getScore() + "\n");
					}
					
					else //if bluff was correct
					{
						currentClaimant.changeScore(currentClaimant.claimValue * -1);
						currentBluffCaller.changeScore(currentClaimant.claimValue);
						
						System.out.println(currentBluffCaller.getName() + " said " + currentClaimant.getName()
								+ " was bluffing.\n..." + currentBluffCaller.getName() + " was right!\n"
								+ currentBluffCaller.getName() + "'s score is now " + currentBluffCaller.getScore()
								+ ", and " + currentClaimant.getName() + "'s score is now " + currentClaimant.getScore() + "\n");
					}
				}
				
				else //if bluff never happened
				{
					currentClaimant.changeScore(currentClaimant.claimValue);
					
					System.out.println(currentBluffCaller.getName() + " did not say that " + currentClaimant.getName()
										+ " was bluffing.\n" + currentClaimant.getName() + "'s score is now " + 
										currentClaimant.getScore() + "\n");
				}
				
				turn++;
				
			}
			
			round++;

		}
		
	}
	
	/** 
	 * determines the winner of a game after one has been played
	 */
	void endGame(){
		int highScore = -1;
		boolean tie = false;
		int tiePlayer[] = new int[numOfPlayers];
		int numOfTie = 0;
		if(round > 5)
		{
			for(int index = 0; index < numOfPlayers; index++)
			{
				if(players[index].score > highScore)
				{		//absolute high score
					highScore = players[index].score;
					tiePlayer[numOfTie] = index;
					tie = false;
				}
				else if(players[index].score == highScore)
				{	//two of same score
					tie = true;
					tiePlayer[++numOfTie] = index;
				}
			}
			if(tie == true)
			{
				highScore = -1;
				int winner = -1;
				while(tie)
				{
					for(int index = 0; index < numOfTie; index++)
					{
						players[tiePlayer[index]].rollDice();
						if(players[tiePlayer[index]].diceValue > highScore)
						{
							highScore = players[tiePlayer[index]].diceValue;
							winner = index;
							tie = false;
						}
						
						else if(players[tiePlayer[index]].diceValue == highScore)
						{
							tie = true;
						}
						numOfTie = winner;
					}
					
				}
					
			}
		}
			
		System.out.println(players[tiePlayer[numOfTie]].getName() + " is the winner with " + players[tiePlayer[numOfTie]].score + " points!\n");
	}
		
	
	public static void main (String[] args){
		Game game1 = new Game();
		System.out.println("Please enter number of total players (max 8): ");
		int tempPlayer = game1.in.nextInt();
		System.out.println("Please enter number of human players: ");
		int tempHuman = game1.in.nextInt();
		game1 = new Game(tempPlayer, tempHuman);
		game1.init();
		game1.playGame();
		game1.endGame();
	}
}