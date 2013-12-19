% Supplied by assignment
next_to(X, Y, List) :- iright(X, Y, List).
next_to(X, Y, List) :- iright(Y, X, List).
iright(L, R, [L | [R | _]]).
iright(L, R, [_ | Rest]) :- iright(L, R, Rest).

% Program
fivehouses(Houses, HasFish) :-
% build house list, clues 8 and 9
=(Houses, [[house, norwegian, _, _, _, _], _, [house, _, _, _, milk, _], _, _]),
% clue 1
member([house, british, _, _, _, red], Houses),
% clue 2
member([house, swedish, dog, _, _, _], Houses),
% clue 3
member([house, danish, _, _, tea, _], Houses),
% clue 4
iright([house, _, _, _, _, green], [house, _, _, _, _, white], Houses),
% clue 5
member([house, _, _, _, coffee, green], Houses),
% clue 6
member([house, _, bird, pallmall, _, _], Houses),
% clue 7
member([house, _, _, dunhill, _, yellow], Houses),
% clue 10
next_to([house, _, _, marlboro, _, _], [house, _, cat, _, _, _], Houses),
% clue 11
next_to([house, _, _, dunhill, _, _], [house, _, horse, _, _, _], Houses),
% clue 12
member([house, _, _, winfield, beer, _], Houses),
% clue 13
member([house, german, _, rothmans, _, _], Houses),
% clue 14
next_to([house, norwegian, _, _, _, _], [house, _, _, _, _, blue], Houses),
% clue 15
next_to([house, _, _, marlboro, _, _], [house, _, _, _, water, _], Houses),
% predicate to return the person who has the fish
member([house, HasFish, fish, _, _, _], Houses).

% fivehouses(Houses, HasFish).
