/**
 * 2D point structure for puzzle.
 */
package CS561A3.Villbrandt;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class Point2D {
	public int x;
	public int y;

	public Point2D(int i, int j) {
		x = i;
		y = j;
	}
	
	public Point2D(Point2D c, int i, int j) {
		x = c.x + i;
		y = c.y + j;
	}

	public int manhattanDistance(Point2D c) {
		return Math.abs(c.x - x) + Math.abs(c.y - y);
	}

	public Point2D minus(Point2D b) {
		return new Point2D(x - b.x, y - b.y);
	}
	
	public String toString() {
		return "(" + (x+1) + ", " + (y+1) + ")";
	}
}
