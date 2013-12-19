/**
 * Holds the game state for Connect N.
 */
package CS561A3.Villbrandt;

import java.util.HashMap;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class State {
	public enum Players {PLAYER1, PLAYER2};
	private static int boardSize;
	private static int pieceToConnect;
	protected Players board[][]; // [column, row], origin at bottom left
	private Players winner = null;

	public State(int boardSize, int pieceToConnect) {
		State.boardSize = boardSize;
		State.pieceToConnect = pieceToConnect;
		this.board = new Players[boardSize][boardSize];
	}
	
	public State(State source) {
		board = new Players[boardSize][boardSize];
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				board[j][i] = source.board[j][i];
			}
		}
	}

	public boolean isTerminal() {
		boolean concluded = false;

		// check for winner
		for(int i = boardSize - 1; i >= 0 && !concluded; i--) {
			for(int j = 0; j < boardSize && !concluded; j++) {
				if(board[j][i] != null) {
					// check up
					if(calculateLineLength(board[j][i], j, i, 0, 1) >= pieceToConnect) concluded = true;
					// check diagonal up to the right
					else if(calculateLineLength(board[j][i], j, i, 1, 1) >= pieceToConnect) concluded = true;
					// check across to the right
					else if(calculateLineLength(board[j][i], j, i, 1, 0) >= pieceToConnect) concluded = true;
					// check diagonal down to the right
					else if(calculateLineLength(board[j][i], j, i, 1, -1) >= pieceToConnect) concluded = true;
					
					// save winner for later function
					if(concluded) winner = board[j][i];
				}
			}
		}
		
		// check to see if board is full
		if(!concluded) {
			boolean full = true;
			for(int i = 0; i < boardSize; i++) {
				if(board[i][boardSize - 1] == null) {
					full = false;
					break;
				}
			}
			if(full) concluded = true;
		}
		
		// check to see if it is still possible to win
		//eh, waste of time
		
		return concluded;
	}
	
	private int calculateLineLength(Players player, int column, int row, int deltaColumn, int deltaRow) {
		if(column < boardSize && row >= 0 && row < boardSize && board[column][row] == player) {
			return 1 + calculateLineLength(player, column + deltaColumn, row + deltaRow, deltaColumn, deltaRow);
		}
		else return 0;
	}

	public int applyMove(Players player, int column) {
		// find first open row
		int row = -1;
		for(int i = 0; i < boardSize; i++) {
			if(board[column][i] == null) {
				row = i;
				break;
			}
		}
		
		// apply move
		if(row > -1) board[column][row] = player;
		
		// return row
		// TODO Auto-generated method stub
		return row;
	}

	public Players getWinner() {
		return winner; // only works if isTerminal() has been called since the game ended
	}
	
	public int boardSize() {
		return boardSize;
	}
	
	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean printColumns) {
		StringBuilder str = new StringBuilder();
		
		for(int i = boardSize - 1; i >= 0; i--) {
			for(int j = 0; j < boardSize; j++) {
				if(board[j][i] == Players.PLAYER1) str.append("R\t");
				else if(board[j][i] == Players.PLAYER2) str.append("Y\t");
				else str.append("-\t");
			}
			str.append("\n\n");
		}
		
		if(printColumns) {
			for(int i = 0; i < boardSize; i++) {
				str.append(i + "\t");
			}
			str.append("\n");
		}
		
		return str.toString();
	}

	public int utility(Players me, Players them) {
		HashMap<Players, Integer> scores = new HashMap<Players, Integer>();
		scores.put(me, 0);
		scores.put(them, 0);
		
		// scan board
		outerloop:
		for(int i = boardSize - 1; i >= 0; i--) {
			for(int j = 0; j < boardSize; j++) {
				if(board[j][i] != null) {
					int length;
					
					// check up
					length = calculateLineLength(board[j][i], j, i, 0, 1);
					if(length >= State.pieceToConnect) {
						scores.put(board[j][i], 2^32-1);
						break outerloop;
					}
					else scores.put(board[j][i], scores.get(board[j][i]) + length*length);
					
					// check diagonal up to the right
					length = calculateLineLength(board[j][i], j, i, 1, 1);
					if(length >= State.pieceToConnect) {
						scores.put(board[j][i], 2^32-1);
						break outerloop;
					}
					else scores.put(board[j][i], scores.get(board[j][i]) + length*length);
					
					// check across to the right
					length = calculateLineLength(board[j][i], j, i, 1, 0);
					if(length >= State.pieceToConnect) {
						scores.put(board[j][i], 2^32-1);
						break outerloop;
					}
					else scores.put(board[j][i], scores.get(board[j][i]) + length*length);
					
					// check diagonal down to the right
					length = calculateLineLength(board[j][i], j, i, 1, -1);
					if(length >= State.pieceToConnect) {
						scores.put(board[j][i], 2^32-1);
						break outerloop;
					}
					else scores.put(board[j][i], scores.get(board[j][i]) + length*length);
				}
			}
		}
		
		return scores.get(me) - scores.get(them);
	}
}
