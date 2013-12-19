Project Meta Data
--------------------------------
Class:		USC CSCI 561 Fall 2011, Swamy
Assignment:	A3, ConnectN.pdf
Name:		Josh Villbrandt
USCID:		2964080774
Email:		josh.villbrandt@usc.edu


Description
--------------------------------
The object of the Connect-N game is to connect N of one's own game pieces before the opponent connect N of their own game pieces. Pieces can connect up, down, and diagonally. The game is played on a shared board so that players can attempt to block their opponent from reach their goal.


Implementation Details
--------------------------------
Two algorithms have been implemented for a computer player of Connect-N. The first is a minimax algorithm. Minimax expands a tree fully or to the desired depth (specified via a command line argument.) A score is assigned for leaf nodes (no further states by game completion or depth limitation) and scores are propagated up the tree by picking the best move for each level. Since this game is turned based, the best score for each successive move switches from being most negative to most positive depending on the which player's turn it is. (This is for a shared scoring algorithm which gives the same score regardless of turn.)

The second algorithm is alpha-beta. This algorithm carries information about previously explored branches to successive branches. Simply alpha-beta will not explore branches that are garenteed to be worse than a previously explored node. For a given node, this decision can only be made after checking at least one child node - otherwise there is no value for the current node.

The scoring/utility function for both algorithms awards exponentially higher utility for longer lines. Specifically, the utility for a player at a given state is the sum of the square of the length of all of that player's lines. The net utility for a given state when considering both players is one player minus the other.

Two areas for improvement have been identified. The first would be to save the tree for successive moves. This could have a large performance benefit if the search depth is large. It does, however, come at the cost of an increase memory footprint (per unit time.)

The second area is for a better utility function for shallow depth searches. A better function would apply higher utility for moves that showed the most potential to win and not just the longest line so far.


Installation
--------------------------------
unzip Joshua_Villbrandt_A3.zip
cd Joshua_Villbrandt_A3
javac -d bin src/CS561A3/Villbrandt/*.java


Usage
--------------------------------
java -cp bin CS561A3.Villbrandt.game {minimax|alpha-beta|random} [-m boardSize] [-n pieceToConnect] [-d depthOfSearch] [-c] [-g] outputFile

{minimax|alpha-beta|random}	the algorithm to be used for computer players
[-m boardSize]				board size sets the game board to m*m; must be at least 1
[-n pieceToConnect]			the number of connected pieces required to win; must be at least 1 and less than the board size
[-d depthOfSearch]			limits the depth of the search tree for computer players
[-c] 						starts a computer vs. computer game instead of a computer vs. human game
[-v]						enable verbose error output for development
outputFile					file to dump moves into at the end of the game


Examples
--------------------------------
java -cp bin CS561A3.Villbrandt.game minimax -m 4 -n 2 -d 2 output.txt
java -cp bin CS561A3.Villbrandt.game alpha-beta output.txt