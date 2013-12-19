/**
 * Implements a minimax and alpha-beta computer player for Connect N.
 */
package CS561A3.Villbrandt;

import java.util.Random;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class ComputerPlayer implements Player {
	public enum Algorithms {MINIMAX, ALPHABETA, RANDOM};
	private String name;
	private Algorithms algorithm;
	private int depthOfSearch;
	private int nodesExamined = 0;
	State.Players me;
	State.Players them;

	public ComputerPlayer(String name, Algorithms algorithm, int depthOfSearch, State.Players me, State.Players them) {
		this.name = name;
		this.algorithm = algorithm;
		this.depthOfSearch = depthOfSearch;
		this.me = me;
		this.them = them;
	}

	private void minimaxMax(Node node, int remainingDepth) {
		nodesExamined++;
		
		// are we a leaf node (end of the line)
		if(remainingDepth <= 0 || node.state.isTerminal()) {
			node.score = node.state.utility(me, them);
		}
		else {
			// expand children
			int topRow = node.state.boardSize() - 1;
			node.score = -2^32-1; // -infinity
			for(int i = 0; i < node.state.boardSize(); i++) {
				// is this a valid move?
				if(node.state.board[i][topRow] == null) {
					Node child = new Node(node, me, i);
					
					minimaxMin(child, remainingDepth); // only decrease depth after min
					
					if(child.score >= node.score) {
						node.favoriteChild = child;
						node.score = child.score;
					}
				}
			}
		}
	}

	private void minimaxMin(Node node, int remainingDepth) {
		nodesExamined++;
		
		// are we a leaf node (end of the line)
		if(remainingDepth <= 0 || node.state.isTerminal()) {
			node.score = node.state.utility(me, them);
		}
		else {
			// expand children
			int topRow = node.state.boardSize() - 1;
			node.score = 2^32-1; // infinity
			for(int i = 0; i < node.state.boardSize(); i++) {
				// is this a valid move?
				if(node.state.board[i][topRow] == null) {
					Node child = new Node(node, them, i);
					
					minimaxMax(child, remainingDepth-1);
					
					if(child.score <= node.score) {
						node.favoriteChild = child;
						node.score = child.score;
					}
				}
			}
		}

		System.out.print("min chose: ");
		System.out.println(node.score);
	}

	private void alphabetaMax(Node node, int remainingDepth, int beta) {
		nodesExamined++;
		
		// are we a leaf node (end of the line)
		if(remainingDepth <= 0 || node.state.isTerminal()) {
			node.score = node.state.utility(me, them);
		}
		else {
			// expand children
			int topRow = node.state.boardSize() - 1;
			node.score = -2^32-1; // alpha - -infinity
			for(int i = 0; i < node.state.boardSize(); i++) {
				// is there space in this column?
				if(node.state.board[i][topRow] == null) {
					Node child = new Node(node, me, i);
					
					alphabetaMin(child, remainingDepth-1, node.score); // only decrease depth after min
					
					if(child.score >= beta) {
						node.favoriteChild = child;
						node.score = child.score;
						break;
					}
					else if(child.score >= node.score) {
						node.favoriteChild = child;
						node.score = child.score;
					}
				}
			}
		}
	}

	private void alphabetaMin(Node node, int remainingDepth, int alpha) {
		nodesExamined++;
		
		// are we a leaf node (end of the line)
		if(remainingDepth <= 0 || node.state.isTerminal()) {
			node.score = node.state.utility(me, them);
		}
		else {
			// expand children
			int topRow = node.state.boardSize() - 1;
			node.score = 2^32-1; // beta - infinity
			for(int i = 0; i < node.state.boardSize(); i++) {
				// is there space in this column?
				if(node.state.board[i][topRow] == null) {
					Node child = new Node(node, them, i);
					
					alphabetaMax(child, remainingDepth, node.score);
					
					if(child.score <= alpha) {
						node.favoriteChild = child;
						node.score = child.score;
						break;
					}
					else if(child.score <= node.score) {
						node.favoriteChild = child;
						node.score = child.score;
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see CS561A3.Villbrandt.Player#takeTurn(CS561A3.Villbrandt.GameState)
	 */
	public int takeTurn(State state) {
		nodesExamined = 0;
		
		if(algorithm == Algorithms.MINIMAX) {
			// construct root
			Node root = new Node(state);
			
			// expand tree
			minimaxMax(root, depthOfSearch);
			
			// return best move
			return root.favoriteChild.move;
		}
		else if(algorithm == Algorithms.ALPHABETA) {
			// construct root
			Node root = new Node(state);
			
			// expand tree
			int beta = 2^31-1;
			alphabetaMax(root, depthOfSearch, beta);
			
			// return best move
			return root.favoriteChild.move;
		}
		else {
			Random generator = new Random();
		    return generator.nextInt(state.boardSize());
		}
	}

	/* (non-Javadoc)
	 * @see CS561A3.Villbrandt.Player#nodesExamined()
	 */
	public int nodesExamined() {
		return nodesExamined;
	}

	public String getName() {
		return name;
	}

}
