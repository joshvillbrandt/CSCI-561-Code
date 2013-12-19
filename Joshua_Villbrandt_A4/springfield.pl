% Supplied by assignment
next_to(X, Y, List) :- iright(X, Y, List).
next_to(X, Y, List) :- iright(Y, X, List).
iright(L, R, [L | [R | _]]).
iright(L, R, [_ | Rest]) :- iright(L, R, Rest).

% Custom utility predicates
%at_end(X, List) :- 
isomeright(L, R, [L | [_ | [R | _]]]).
isomeright(L, R, [L | [_ | [_ | [R | _]]]]).
isomeright(L, R, [_ | Rest]) :- isomeright(L, R, Rest).

% Program
springfield(Students) :-
% build house list, clues 8 and 9
=(Students, [boatend, _, _, _, _, jimbo, _, _, boatend]),
% clue 1
next_to(nelson, boatend, Students),
% clue 2
isomeright(milhouse, bart, Students),
% clue 4
next_to(milhouse, martin, Students),
next_to(milhouse, ralph, Students),
% clue 5
iright(lisa, ralph, Students).

% springfield(Students).
