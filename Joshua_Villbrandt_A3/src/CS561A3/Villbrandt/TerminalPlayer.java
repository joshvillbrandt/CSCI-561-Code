/**
 * Allows a human to play the Connect N game through a terminal.
 */
package CS561A3.Villbrandt;

import java.util.Scanner;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class TerminalPlayer implements Player {
	private String name;
	int moveCounter = 0;

	public TerminalPlayer(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see CS561A3.Villbrandt.Player#takeTurn(CS561A3.Villbrandt.GameState)
	 */
	public int takeTurn(State state) {
		moveCounter++;
		System.out.println(name + "'s Turn");
		System.out.print(state.toString(true));

		Scanner scan = new Scanner(System.in);
		int column = -1;
		while(column < 0 || column >= state.boardSize() || state.board[column][state.boardSize() - 1] != null) {
			try {
				System.out.print("Please select the column for Move " + moveCounter + ": ");
				String input = scan.next();
				column = Integer.parseInt(input);
			}
			catch(NumberFormatException e) {
				
			}
		}
		System.out.println("\n");
		
		return column;
	}

	/* (non-Javadoc)
	 * @see CS561A3.Villbrandt.Player#nodesExamined()
	 */
	public int nodesExamined() {
		return 0;
	}

	public String getName() {
		return name;
	}

}
