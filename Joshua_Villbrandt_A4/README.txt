Project Meta Data
--------------------------------
Class:		USC CSCI 561 Fall 2011, Swamy
Assignment:	A4, Assignment4.pdf
Name:		Josh Villbrandt
USCID:		2964080774
Email:		josh.villbrandt@usc.edu


Description
--------------------------------
Prolog is a logic programming language that can use backwards chaining to solve a collection of Horn clauses. This assignment exercises the use of prolog using GNU prolog and specific emphasizes the use of lists.


Implementation Details
--------------------------------
Problem4a - The implementation uses the utility predicates described in the assignment. After that, a fivehouses predicate is created that creates a list of houses (which are also actually lists) and includes the knowledge contained from the fifteen clues. The individual house lists contain the nationality, pet, cigarettes, drink, and house color in that order.

Problem4b - The implementation uses the utility predicates described in the assignment. I also supplied one, rather hacky predicate called isomeright (for clue 2) that allows there to be gaps while still apply the left of / right of constraint. It is a hack though because it only works for one or two spaces between. I could have made it generic, but I am under the gun time-wise to turn this in. The other hacky solution was for clue 1 where I added "boatends" in the same list and applied the next_to constraint. The rest of the program uses the utility predicates that were suggested.


Installation
--------------------------------
unzip Joshua_Villbrandt_A4.zip
cd Joshua_Villbrandt_A4


Usage - Problem4a
--------------------------------
gprolog
consult(fivehouses).
fivehouses(Houses, HasFish).


Usage - Problem4b
--------------------------------
gprolog
consult(springfield).
springfield(Students).


Notes
--------------------------------
I took the liberty of changing the zip file name to much the format established by the first three assignments. Also, the assignment calls for two different file names for Problem4a and I chose the 5houses.pl file name. Although, Prolog didn't seem to like names starting with numbers, so I actually used fivehouses.pl as the file name.


Problem4a Output
--------------------------------
-bash-2.05b$ gprolog
GNU Prolog 1.3.0
By Daniel Diaz
Copyright (C) 1999-2007 Daniel Diaz
| ?- consult(fivehouses).
compiling /home/scf-12/villbran/CSCI561/Joshua_Villbrandt_A4/fivehouses.pl for byte code...
/home/scf-12/villbran/CSCI561/Joshua_Villbrandt_A4/fivehouses.pl compiled, 40 lines read - 8309 bytes written, 54 ms

(6 ms) yes
| ?- fivehouses(Houses, HasFish).

HasFish = german
Houses = [[house,norwegian,cat,dunhill,water,yellow],[house,danish,horse,marlboro,tea,blue],[house,british,bird,pallmall,milk,red],[house,german,fish,rothmans,coffee,green],[house,swedish,dog,winfield,beer,white]] ? a

(45 ms) no
| ?- 
Prolog interruption (h for help) ? e
-bash-2.05b$ 


Problem4b Output
--------------------------------
-bash-2.05b$ gprolog
GNU Prolog 1.3.0
By Daniel Diaz
Copyright (C) 1999-2007 Daniel Diaz
| ?- consult(springfield).
compiling /home/scf-12/villbran/CSCI561/Joshua_Villbrandt_A4/springfield.pl for byte code...
/home/scf-12/villbran/CSCI561/Joshua_Villbrandt_A4/springfield.pl compiled, 27 lines read - 2736 bytes written, 44 ms

(3 ms) yes
| ?- springfield(Students).

Students = [boatend,lisa,ralph,milhouse,martin,jimbo,bart,nelson,boatend] ? a

(3 ms) no
| ?- 
Prolog interruption (h for help) ? e
-bash-2.05b$ 