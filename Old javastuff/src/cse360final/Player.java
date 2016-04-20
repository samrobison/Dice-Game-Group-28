package cse360final;

import java.util.Scanner;

/** Class to create a player and functionalities for that player to
 * partake in a game of dice-bluffing and guessing
 * @author Group 28
 * @version April 15, 2016
 * 
 * 
 */

public class Player 
{
	private String userName;
	private int[] dice;
	public int diceValue;
	public int claimValue;
	private boolean human;
	public int score;
	public boolean immunity;
	public Scanner scanner = new Scanner(System.in);
	
	private final int CALL_BLUFF = -1;
	
	/** Create a player
	 * 
	 * @param name is the Player's name
	 * @param human is a boolean describing whether the player
	 *		  is controlled by a human or not
	 */
	Player(String name, boolean human)
	{
		this.human = human;
		dice = new int[2];
		this.userName = name;
		this.immunity = true;
	}
	
	/** roll dice for a Player at the beginning of their turn */
	void rollDice()
	{
		diceValue = 0;
		for(int index = 0; index < dice.length; index++)
		{
			dice[index] = (int)(Math.random() * 5 +1);
			diceValue += dice[index];
		}
	}
	
	/**
	 * @return human, the boolean describing whether the Player
	 * 		   is controlled by a human 
	 *   
	 */
	boolean isHuman()
	{
		return human;
	}
	

	/**
	 * @return score, the integer that holds the Player's score
	 *         currently in the game
	 *  
	 */
	int getScore()
	{
		return score;
	}
	
	/**
	 * @return userName, the Player's identifying name in-game
	 */
	String getName()
	{
		return userName;
	}
	
	/**
	 * alters the Player's score by an integer value
	 * note: Player's score cannot go below zero
	 * 
	 * @param points is the value by which to change the Player's score
	 *  
	 */
	void changeScore(int points)
	{
		score += points;
		
		if(score < 0)
		{
			score = 0;
		}
	}
	
	/** contains the functionality for a human Player to make
	 *  a claim about the value of their dice
	 */
	void claimHuman()
	{
		rollDice();
		System.out.println("The total value of your dice is: " + diceValue);
		System.out.println("What would you like to claim as your value? (integer between 1 and 12): ");
		int potentialClaim = scanner.nextInt();
		
		while(potentialClaim < 1 || potentialClaim > 12)
		{
			System.out.println("I'm sorry-- your claim must be an integer between 1 and 12. Please enter your claim: ");
			potentialClaim = scanner.nextInt();
		}
		
		claimValue = potentialClaim;
		
		System.out.println(userName + " claims that their value is " + claimValue);
	}
	
	/**
	 * contains the functionality for a human Player to choose
	 * to call a bluff for another Player
	 * 
	 * @return true if the Player wants to call a bluff, and false otherwise
	 *  
	 */
	boolean callBluffHuman()
	{
		System.out.println(userName + "-- do you want to call a bluff? Enter 'yes' if you do, and 'no' if you don't.");
		
		String bluffCall = scanner.next();
		
		while(!bluffCall.equalsIgnoreCase("yes") && !bluffCall.equalsIgnoreCase("no"))
		{
			System.out.println("I'm sorry-- that input was invalid. Please enter 'yes' if you want to call a bluff, and"
								+ " 'no' if you do not.");
			bluffCall = scanner.next();
		}
		if(bluffCall.equalsIgnoreCase("yes"))
		{
			return true;
		}
		
		else
		{
			return false;
		}
		
	}
	
	/** provides the functionality for an AI Player to
	 *  make a claim about its dice
	 *  
	 *  */
	void claimAI()
	{
		rollDice();
		
		double chanceElement = Math.random();
		
		if(diceValue <= 4)
		{
			
			if(chanceElement > 0.2)
			{
				claimValue = diceValue + (int) Math.random() * 7 + 1;
			}
			
			else{
				claimValue = diceValue;
			}
		}
		
		else if(diceValue <= 8)
		{
			if(chanceElement > 0.4)
			{
				claimValue = diceValue + (int) Math.random() * 4 + 1;
			}
			
			else
			{
				claimValue = diceValue;
			}
		}
		
		else
		{
			claimValue = diceValue;
		}
		
		System.out.println(userName + " claims that their value is " + claimValue);
		
	}
	
	/** provides the functionality for an AI Player to
	 *  choose whether to call a bluff on another Player
	 *  
	 *  @return true if the Player decides to call a bluff; false otherwise
	 *  
	 *  */
	boolean callBluffAI(int claimMade, int claimerScore)
	{
		double chanceElement = Math.random();
		
		if(chanceElement > 0.8)
		{
			if(claimMade > 10 && claimerScore < score)
			{
				return true;
			}
			
			else
			{
				return false;
			}
		}
		
		else if(chanceElement > .5)
		{
			if(claimMade > 8 && claimerScore > score)
			{
				return true;
			}
			
			else
			{
				return false;
			}
		}
		
		else if(score == 0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
}