# Dice-Game-Group-28

###### Set up
To play our game, enter http://159.203.222.152:3000/ into your search bar. This will bring you to the game's home screen. Click the "sign up now" button and fill in the fields to make your account. Once you have an account you will be logged in. You should see a
"New Game" button appear in the top right corner of the homepage.

###### Overview of Rules

In our game, some number of players between two and eight join a game composed of three rounds. At the beginning of each round, the system rolls three six-sided dice for each player. The player who is first in the turn order then has two options: "make a call" or "call a bluff." If the player chooses to make a call, they have two potential courses of action: call a vlue less than or equal to their dice total, or call a greater value. Calling a less-than-or-equal value is considered being honest. Calling a higher value is considered bluffing. Alternatively, you can choose not to make a call. You can instead call a bluff, which means that you think the player who went before you was bluffing about the value of their dice. Of course, if the person who's first in the turn order chooses to call a bluff, this doesn't really make sense, because no one's made any call about the value of their dice, so no one can have bluffed. If the player has chosen to make a call instead of call a bluff, the value of the call is added to that player's score. If the player has called a bluff during their turn, then one of two things will happen. If you were correct about the person bluffing, your score increases by the value of the last call made, and bluffer's score decreases by twice the value of the last call made. (This call was made by the bluffer; in the case of the first person calling a bluff on their turn, "last call made" is the default value of zero, and no one's score changes). If the player was wrong about the last person bluffing, however, the player's score decreases by the amount of the last call made, and the not-actually-a-bluffer's score increases by three times the value of the last call made, divided by two. Note that under no circumstances will any player's score dip below zero. A player's turn ends when they have either made a call or called a bluff. If the player has made a call for their turn, then their turn ends and the player after them in the turn order takes a turn. If the player has called a bluff for their turn, though, the round ends after their turn ends, regardless of whether the last person was actually bluffing or not. Points are distributed as described above, and the round ends. One constraint of the game is that, if a player intends to make a call, that call must be greater than the value of the last call made (in the case of a round's first turn, then, that call must be at least one). If the previous player has called 18 (the highest possible value of the dice), the current player must call a bluff, as they cannot call a value. The game ends when three rounds have been played. Whoever has the most points at the end of three rounds wins. In the event of a tie for most points, all players who have tied for first are declared joint winners.

###### Playing the Game

Click on the new game button in the upper left corner. Enter a string to identify the game (this string must not have been used before by anyone, so a moderately long string of random letters is probably the best choice), the number of other players, and then choose whether to add any human players (outside of yourself. You don't need to explicitly add yourself). You can only add a human player if that human has signed up with the game. For each human you want to add, enter their email (separated by commas if multiple humans are added). Once you've done this, click the "Start Game" button. Other humans join a game they have been added to by entering the URL of the game you've created in their computer/browser (for example, http://159.203.222.152:3000/games/207). Note that the turn order of multiple-human games is such that one of the added human players will have the first turn-- not the human who created the game. The number of CPU's will be the total number of players minus the number of human players.

Again, each human player needs their own computer and account. 

A brief overview of gameplay is provided below for review.

Round x: 

Player1's turn. Player1 can choose to bluff or to make a call. 
<end of player1s turn>

Player2 can then choose to call a bluff on player1 or to make a call. 
<end of player2s turn>

If a player chooses to call a bluff it will end the round. 

The game ends with the end of the third round.

###### Other things

To view statistics (player rankings-- the number of wins by each player), either go directly to http://159.203.222.152:3000/static/stats , or navigate to the homepage, and then click the blue "here" in the sentence "View player rankings here" that is displayed on the homepage. To get a refresher on the rules, either go directly to http://159.203.222.152:3000/static/help , or navigate to the homepage, and then click the "Help" link in the top right of the website.
