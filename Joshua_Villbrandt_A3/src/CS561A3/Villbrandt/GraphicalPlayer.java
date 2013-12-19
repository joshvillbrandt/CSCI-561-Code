/**
 * Allows a human to play the Connect N game through a graphical interface.
 */
package CS561A3.Villbrandt;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class GraphicalPlayer implements Player {
	private String name;

	public GraphicalPlayer(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see CS561A3.Villbrandt.Player#takeTurn(CS561A3.Villbrandt.GameState)
	 */
	public int takeTurn(State state) {
		// TODO Auto-generated method stub
		return 0;
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
