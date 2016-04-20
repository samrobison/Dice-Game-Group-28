package cse360project;

public class Player 
{
	private String userName;
	private int[] dice;
	public int  dTotal;
	private boolean human;
	public int score;
	private boolean immunity;
	
	private final int CALL_BLUFF = -1;
	
	Player(String name, boolean human)
	{
		this.human = human;
		dice = new int[2];
		this.userName = name;
		this.immunity = true;
	}
	
	int rollDice()
	{
		for(int index = 0; index < dice.length; index++)
		{
			dice[index] = (int)(Math.random() * 5 +1);
			dTotal = dice[index]+dTotal;
		}
		return dTotal;
	}
	
	boolean isHuman()
	{
		return human;
	}
	
	int getScore()
	{
		return score;
	}
	void setScore(int score)
	{
		this.score = score;
	}
	
	String getName()
	{
		return userName;
	}
	
	void addScore(int points)
	{
		this.score += points;
	}
	
	int actHuman(int currentBid)
	{
		// Do GUI stuff
		return CALL_BLUFF;
	}
	
	int actAI(int currentBid)
	{
		int score = 0;
		
		for(int index = 0; index < dice.length; index ++)
			score += dice[index];
		
		if(currentBid >= score && currentBid >= (dice.length * 6 * .8))
			return CALL_BLUFF;
		else if (currentBid >= score && currentBid <=  (dice.length * 6 * .5))
			return currentBid += (int)(Math.random() * 3 +1);
		else if (currentBid >= score && (currentBid - score) > 3)
			return CALL_BLUFF;
		else if (currentBid >= score)
			return currentBid += (int)(Math.random() * 3 +1);
		else if (currentBid < score && (score - currentBid) < 5)
			return score;
		else if (currentBid <= score && (score - currentBid) >= 5)
			return currentBid += (int)(Math.random() * 4 +1);
		else
			return CALL_BLUFF;
	}
}
