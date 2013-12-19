/**
 * Node structure for puzzle.
 */
package CS561A1.Villbrandt;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class Coord {
	public int x;
	public int y;

	public Coord(int i, int j) {
		x = i;
		y = j;
	}
	
	public Coord(Coord c, int i, int j) {
		x = c.x + i;
		y = c.y + j;
	}

	public int manhattanDistance(Coord c) {
		return Math.abs(c.x - x) + Math.abs(c.y - y);
	}

	public Coord minus(Coord b) {
		return new Coord(x - b.x, y - b.y);
	}
}
