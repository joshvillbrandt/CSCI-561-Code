/**
 * 
 */
package CS561A3.Villbrandt;

import CS561A3.Villbrandt.State.Players;

/**
 * @author josh
 *
 */
public class Node {
	public State state;
	public int move; // column
	public int score; // computed from utility function during tree construction
	public Node favoriteChild;
	
	public Node(State source) {
		this.state = new State(source);
		this.score = 0;
	}
	
	public Node(Node parent, Players me, int move) {
		this.state = new State(parent.state);
		this.move = move;
		this.state.applyMove(me, move);
		this.score = 0;
	}
}
