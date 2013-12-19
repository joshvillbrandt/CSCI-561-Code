Project Meta Data
--------------------------------
Name:	Josh Villbrandt
USCID:	2964080774
Email:	josh.villbrandt@usc.edu

Description
--------------------------------
The n queens problem tried to put n queens on an n-by-n board in a manor that no queen can attack another. The chess moves for the queen apply - queens can attack any piece in their row, column, or diagonals.

Implementation Details
--------------------------------
This problem is solved using a constraint satisfaction approach. The n queens are the variables. Each queen can take a valid coordinate on the board as the value. The constraints applied are that a queen cannot attack another queen given the set of possible moves by the rules of chess. Each variable is solved recursively, one at a time. At each variable, its value is tried, one at a time. The search increases in depth when a given value for a given variable has not entered a dangerous cell on the game board. If a complete solution is generated and valid, it is added to a solution list.

Two time-saving ideas have been implemented. First, queens are each initialized in their own row. Since queens cannot be placed in the same column as another queen, there is no point in trying a queen in any previous column. Second, a grid is passed with each node that contains the possible moves of the currently assigned queens. This makes testing new assignments extremely fast. It does, however, come at the cost of increased memory usage.

Two ideas for improvement have been realized. The first is to only store what is needed for the grid. If, for example, the last queen is being placed at the top of the board, the valid-move grid need not carry any information about previous rows, because they each already have queens assigned in them. This change would require lots behind-the-scenes index changes that would be possible sources of error. However, this would make the program less memory dependent. The second idea is to make the program multi-thread, which, on a 128 core machine like aludra.usc.edu, could have obvious speed advantages.

Installation
--------------------------------
unzip Joshua_Villbrandt_A2.zip
cd Joshua_Villbrandt_A2
javac -d bin src/CS561A2/Villbrandt/*.java

Usage
--------------------------------
java -cp bin CS561A2.Villbrandt.puzzle n outputFile; cat outputFile
#n is the size of the board, n*n, and the number of queens which is between 1 and 8 inclusive
