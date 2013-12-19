Project Meta Data
--------------------------------
Josh Villbrandt
2964080774
josh.villbrandt@usc.edu

Description
--------------------------------
This program uses A* to solve the classic 8 puzzle problem. The heuristic used is the Manhattan Distance. An input file of form:
3 4 6
0 1 2
7 5 8
is provided. The best sequence is provided via the output screen. An output file can be used to verify the solution of exactly:
1 2 3
8 0 4
7 6 5
when the solution can be found.

Installation
--------------------------------
unzip Joshua_Villbrandt_A1.zip
cd Joshua_Villbrandt_A1/CS561A1.Villbrandt
javac -d classes src/CS561A1/Villbrandt/*.java

Usage
--------------------------------
java -cp classes CS561A1.Villbrandt.puzzle -i inputFile -o outputFile

Notes
--------------------------------
The output file required by the assignment seems rather useless to me. To save a useful output (the solution sequence printed to the screen), execute the program like this:

java -cp classes CS561A1.Villbrandt.puzzle -i inputFile -o outputFile > usefulOutputFile
