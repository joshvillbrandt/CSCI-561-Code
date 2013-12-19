/**
 * Node structure for puzzle.
 */
package CS561A2.Villbrandt;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class Node {
	public Point2D queens[]; // variable assignments
	public int nextQueen; // we've assigned up to this number
	public boolean grid[][]; // safe = true
	
	public Node(int n) {
		// initialize variables
		queens = new Point2D[n];
		nextQueen = 0;
		grid = new boolean[n][n];
		
		// set blank validation grid
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				grid[i][j] = true;
			}
		}
	}
	
	public Node(Node node) {
		int n = node.queens.length;
		nextQueen = node.nextQueen;
		
		// copy queens
		queens = new Point2D[n];
		for(int i = 0; i < nextQueen; i++)
			queens[i] = new Point2D(node.queens[i].x, node.queens[i].y);
		
		// copy grid
		grid = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = nextQueen; j < n; j++) {
				grid[i][j] = node.grid[i][j];
			}
		}
	}

	public boolean isSafe(Point2D point) {
		return grid[point.x][point.y];
	}

	public void expandGrid(Point2D point) {
		int n = queens.length;
		
		// column
		for(int j = point.y + 1; j < n; j++)
			grid[point.x][j] = false;
		
		// left diagonal
		for(int i = 1; i < n; i++) {
			int x = point.x - i;
			int y = point.y + i;
			
			if(x >= 0 && y < n) {
				grid[x][y] = false;
			}
			else break;
		}
		
		// right diagonal
		for(int i = 1; i < n; i++) {
			int x = point.x + i;
			int y = point.y + i;
			
			if(x < n && y < n) {
				grid[x][y] = false;
			}
			else break;
		}
	}
	
	public String debugGrid() {
		int n = queens.length;
		StringBuilder str = new StringBuilder();
		
		for(int j = n - 1; j >= 0; j--) {
			for(int i = 0; i < n; i++) {
				str.append(i);
				str.append(j);
				str.append(" ");
				if(grid[i][j]) str.append("O"); // safe / empty
				else str.append("X"); // not safe
				str.append(" ");
			}
			str.append("\n");
		}
		
		return str.toString();
	}
}
