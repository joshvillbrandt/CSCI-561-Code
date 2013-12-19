/**
 * Connect N is a Connect 4 style of game implementing minimax and alpha-beta Algorithmss.
 */
package CS561A3.Villbrandt;

import java.io.*;
import java.util.Vector;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class game {
	static ComputerPlayer.Algorithms algorithm = ComputerPlayer.Algorithms.MINIMAX;
	static String outputFile = "";
	static int boardSize = 10;
	static int pieceToConnect = 5;
	static int depthOfSearch = 5;
	static boolean humanPlayer = true;
	static boolean guiEnabled = false;
	static boolean verboseMode = false;
	static private Vector<String> moves;
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		try {
			// initialize game
			parseArguments(args);
			State state = new State(boardSize, pieceToConnect);
			moves = new Vector<String>();
			
			// initialize players
			Player player1 = new ComputerPlayer("Player 1", algorithm, depthOfSearch, State.Players.PLAYER1, State.Players.PLAYER2);
			Player player2;
			if(humanPlayer && guiEnabled) player2 = new GraphicalPlayer("Player 2");
			else if(humanPlayer) player2 = new TerminalPlayer("Player 2");
			else player2 = new ComputerPlayer("Player 2", algorithm, depthOfSearch, State.Players.PLAYER2, State.Players.PLAYER1);
			
			// run game
			boolean player1sTurn = true;
			int move, row, column;
			while(!state.isTerminal()) {
				move = moves.size() / 2 + 1;
				if(player1sTurn) {
					column = player1.takeTurn(state);
					row = state.applyMove(State.Players.PLAYER1, column);
					if(row > -1) moves.add(player1.getName() + " Move " + move + ": (" + column + ", " + row + ") - Number of nodes examined: " + player1.nodesExamined());
					else moves.add(player1.getName() + " Move " + move + ": invalid - Number of nodes examined: " + player1.nodesExamined());
				}
				else  {
					column = player2.takeTurn(state);
					row = state.applyMove(State.Players.PLAYER2, column);
					if(row > -1) moves.add(player2.getName() + " Move " + move + ": (" + column + ", " + row + ") - Number of nodes examined: " + player2.nodesExamined());
					else moves.add(player2.getName() + " Move " + move + ": invalid - Number of nodes examined: " + player2.nodesExamined());
				}
			
				player1sTurn = !player1sTurn;
			}
			
			// post game
			System.out.println(state.toString());
			if(state.getWinner() == State.Players.PLAYER1)
				System.out.println("Congradulations " + player1.getName() + ", you have won the game.");
			else if(state.getWinner() == State.Players.PLAYER2)
				System.out.println("Congradulations " + player2.getName() + ", you have won the game.");
			else System.out.println("The game is a tie - no one won.");
			writeOutputFile(outputFile);
		}
		catch(Exception e) {
			if(verboseMode) throw e;
			else System.err.println(e.getMessage());
		}
	}

	private static void parseArguments(String[] args) throws IOException {
		// parse arguments
		if(args.length < 2) {
			throw new IOException("Not enough arguments. Check README.txt.");
		}
		else {
			// algorithm
			if(args[0].equals("minimax")) algorithm = ComputerPlayer.Algorithms.MINIMAX;
			else if(args[0].equals("alpha-beta")) algorithm = ComputerPlayer.Algorithms.ALPHABETA;
			else if(args[0].equals("random")) algorithm = ComputerPlayer.Algorithms.RANDOM;
			else throw new IOException("Invalid algorithm \"" + args[0] + "\". Check README.txt.");
			
			// options
			for(int i = 1; i < args.length - 1; i++) {
				// boardSize
				if(args[i].equals("-m")) {
					if(args.length >= i + 3) {
						i++;
						boardSize = Integer.parseInt(args[i]);
						if(boardSize < 1) throw new IOException("Value out of range for option \"" + args[i-1] + "\". Check README.txt.");
					}
					else throw new IOException("No option value present for \"" + args[i] + "\". Check README.txt.");
				}
				
				// pieceToConnect
				else if(args[i].equals("-n")) {
					if(args.length >= i + 3) {
						i++;
						pieceToConnect = Integer.parseInt(args[i]);
						if(pieceToConnect < 1) throw new IOException("Value out of range for option \"" + args[i-1] + "\". Check README.txt.");
					}
					else throw new IOException("No option value present for \"" + args[i] + "\". Check README.txt.");
				}
				
				// depthOfSearch
				else if(args[i].equals("-d")) {
					if(args.length >= i + 3) {
						i++;
						depthOfSearch = Integer.parseInt(args[i]);
						if(depthOfSearch < 1) throw new IOException("Value out of range for option \"" + args[i-1] + "\". Check README.txt.");
					}
					else throw new IOException("No option value present for \"" + args[i] + "\". Check README.txt.");
				}
				
				// humanPlayer
				else if(args[i].equals("-c")) {
					humanPlayer = false;
				}
				
				// humanPlayer
				else if(args[i].equals("-g")) {
					guiEnabled = true;
				}
				
				// humanPlayer
				else if(args[i].equals("-v")) {
					verboseMode = true;
				}
				
				// default
				else throw new IOException("Invalid option \"" + args[i] + "\". Check README.txt.");
			}
			
			// output file
			outputFile = args[args.length - 1];
		}
		
		// enforce dependent constraints
		if(pieceToConnect > boardSize)
			throw new IOException("Invalid options: -n cannot be greater than -m. Check README.txt.");
		if(guiEnabled && !humanPlayer)
			throw new IOException("Invalid options: -g and -c cannot be used at the same time. Check README.txt.");
	}

	public static void writeOutputFile(String outputFile) throws IOException {
		FileWriter fstream = new FileWriter(outputFile);
		BufferedWriter out = new BufferedWriter(fstream);
		
		out.write("Total Number of Moves: " + moves.size() + "\n");
		
		// print out all solutions
		for(int i = 0; i < moves.size(); i++) {
			out.write(moves.get(i) + "\n");
		}
		
		out.close();
	}
}
