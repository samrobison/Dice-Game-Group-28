package cse360project;

import java.util.Scanner;

public class Game {
	public boolean firstPlay = true;
	public int round;
	public int turn = 0;
	public int claim;
	public Player[] players;
	public String[] aiNames = {"Computer1", "Computer2", "Computer3", "Computer4"};
	public int numOfPlayers;
	public int numOfHumans;
	
	Scanner in = new Scanner(System.in);
	
	Game(int numPlayers, int numHumans){
		numOfPlayers = numPlayers;
		numOfHumans = numHumans;
	}
	
	void init(){
		players = new Player[numOfPlayers];	//creates array of size players
		if(numOfHumans < numOfPlayers){	
			for(int i = 0; i < numOfHumans; i++){ //create human player
				System.out.print("Please Enter Your Name: ");
				String temp = in.next();
				players[i] = new Player(temp, true);	
				players[i].score = 0;
				System.out.print(temp + " has entered the game\n");
			}
			for(int j = numOfHumans; j < numOfPlayers; j++){	//create all AI
				String temp2 = aiNames[j-1];
				players[j] = new Player(temp2, false);
				players[j].score = 0;
				System.out.print(temp2 + " has entered the game\n");
			}
			
		}
	}
	void startRound()
	{	
		if (firstPlay)
		{
		System.out.println("You rolled"+ players[turn].rollDice());
		System.out.println("What is your claim?");
		claim = in.nextInt();	
		firstPlay = false;
		}	
		else
		{
			System.out.println("You rolled"+ players[turn].rollDice());
			System.out.println("What is your claim?");
			claim = in.nextInt();	
		}
	}
	
	public static void main (String[] args){
		Game game1 = new Game(4,1);
		game1.init();
	}
}