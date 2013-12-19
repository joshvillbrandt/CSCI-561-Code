/**
 * A generalized player interface for Connect N.
 */
package CS561A3.Villbrandt;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public interface Player {
	String getName();
	int takeTurn(State state);
	int nodesExamined();
}
