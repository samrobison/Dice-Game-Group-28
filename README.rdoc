# Dice-Game-Group-28

###### Set up
To build our game, download the git repo file. Unzip the file, and then open up command prompt. 
Navigate to the directory containing the unzipped cse360final file, but do not navigate to the cse360final. For instance, if you
unzipped the file in C:\ , navigate to C:\ in command prompt. To run the program 
```
$ java -classpath ./bin cse360final.Game
```


###### Playing the Game

To play the game, you must first enter the number of players in the game. Once you've done that, you should enter the number of humanplayers. The remaining players will be AI. Currently, having more than one human player results in somewhat silly gameplay, because the 
summed value of each human player's dice is printed to standard output, and guessing the value of other players' dice is an integral part
of the game. We intend to alter this functionality when we have the database and the GUI set up. As such, we recommend that only one human
play, but the game will support multiple human players. Once you've entered the number of human players, human players must enter their
names, and then the AI players are populated. The game then begins in earnest. The first player in the order assigned (for now, we have
removed the dynamic ordering of players' turns; instead, one order of play is fixed throughout the game, beginning with human players and
then moving to AI players) will see the results of rolling two dice. That player must then make a claim about the summed value of their
dice. This claim can be true or false, but must be an integer between 1 and 12. The player who's after the first player in the established
order can then choose to say that the first player is bluffing, or decline to do so. If the first player was bluffing, and the next player
correctly accuses the first player of bluffing, then the accusing player's score is increased by the value that the first player claimed
to have, and the first player's score is reduced by that value. However, all players' scores start at zero, and in no case can a player's
score dip below zero. If the first player was not bluffing, but the next player accuses the first player of doing so, then the first
player's score is increased by three times the amount claimed (accurately) by the first player, divided by two using integer division. The
accuser's score is reduced by the amount that the first player rolled. If the next player does not accuse the first player of bluffing,
then the first player's score is increased by the amount claimed, regardless of its accuracy. The next player's score does not change in
this case. This complete's the first player's turn. Then the next player rolls dice, and the process begins over again, going through the
established order until each player has made a claim about their dice and either accused the player before them in the order of bluffing
or declined to do so. When each player has played these roles, the first round ends, and the second begins. The second, third, fourth, and
fifth rounds operate exactly as the first one did. The game ends after five rounds. Whichever player has the highest score at that point
wins. In the event of a tie, the players who were tied for the high score roll dice to see who wins. If there is a tie for high score in
this sudden-death roll-off, the results are scrapped, and the dice are rolled again until only one player has a high score (this is 
another change from before, to simplify tiebreaking).


###### Overview of Rules

In our game, some number of players between two and ten join a game composed of five rounds. In each round, the players are randomly assigned an order (this order will be displayed in the GUI as players in a circle), and whichever player is currently winning (or has the most wins in the case of tied highest score, or has been randomly selected in the case of tied highest score and tied most wins) begins the round. (This person is hereinafter referred to as the "leader.") Each player rolls two six-sided dice. The leader then makes a claim about the summed value of their two rolled dice. This claim can be true or false. (This player is hereinafter referred to as the "claimant.") The player next to the claimant in the counterclockwise direction in the assigned order can say that the claimant is bluffing, or can decline to do so. (The player next to the claimant in the counterclockwise direction is hereinafter referred to as the "bluff-caller.") If the bluff-caller declines to say that the claimant is bluffing, then the claimant's score is increased by the amount claimed as the summed value of the claimant's dice. (This claimed value is hereinafter referred to as "x.") If the bluff-caller does say that the claimant is bluffing, but the summed value is actually equal to x, then the claimant's score is increased by x*3/2 (using integer division), and the bluff-caller's score is reduced by x. Note that in no case can any player's score become less than 0. If the bluff-caller says that the claimant is bluffing, and the values of the claimant's dice do not in fact equal x, the claimant's score is reduced by x and the bluff-caller's score is increased by x. However, at the beginning of each game, each player comes into possession of an "immunity card." If the claimant's bluff if called correctly (the values of the claimant's dice do not add up to x), the claimant can play their immunity card, in which case the bluff-caller's score does not increase, nor does the claimant's score decrease. Each player can only use an immunity card once per game.

After the interaction between the first claimant (the leader) and the first bluff-caller has been resolved, the old bluff-caller becomes the new claimant (making a claim about the two dice that they rolled), and the player next to the old bluff-caller in the counterclockwise direction becomes the new bluff-caller. This interaction proceeds exactly as described above, and the counterclockwise rotation of claimant and bluff-caller continues until the player next to the leader in the clockwise position is the current claimant, and the leader is the current bluff-caller. After this interaction is resolved, the round ends, a new order of players is randomly chosen, and a new leader is determined to begin the next round. If the person winning the game has not changed during the last round, this new leader is the same as the old leader. After five rounds have finished, whoever has the highest score wins the game.

If players are tied for the highest score after five rounds, these players go into an extra round-- an elimination round. The elimination round proceeds in exactly the same way as any other round, except that at the end of an elimination round, whichever player has the highest score wins the game. In the case of a tie, another elimination round is played under the same conditions. If, after five elimination rounds, no winner can be determined, a winner will be randomly chosen among the remaining contenders.

Players will be able to access the number of games that their competitors have won, as well as their own scores and those of their competitors, in-game.

AlphonseFanon edited 4.1.2016