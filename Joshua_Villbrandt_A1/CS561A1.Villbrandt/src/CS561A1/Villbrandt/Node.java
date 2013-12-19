/**
 * Node structure for puzzle.
 */
package CS561A1.Villbrandt;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class Node implements Comparable<Node> {
	public Node parent;
	public int depth;
	public int cost;
	public int[][] state;
	

	public Node() {
		parent = null;
		depth = 0;
		cost = 0;
		state = new int[3][3];
	}

	public Node(Node current, Coord zero, Coord move) {
		// infer parameters
		parent = current;
		depth = current.depth + 1;
		
		// copy state
		state = new int[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				state[j][i] = current.state[j][i];
			}
		}
		
		// move empty space
		state[zero.y][zero.x] = state[move.y][move.x];
		state[move.y][move.x] = 0;
		
		// re-score
		cost = depth + this.calculateFutureCost(); 
	}

	private int calculateFutureCost() {
		int sum = 0;

		sum += findCoord(1).manhattanDistance(new Coord(0, 0));
		sum += findCoord(2).manhattanDistance(new Coord(1, 0));
		sum += findCoord(3).manhattanDistance(new Coord(2, 0));
		sum += findCoord(4).manhattanDistance(new Coord(2, 1));
		sum += findCoord(5).manhattanDistance(new Coord(2, 2));
		sum += findCoord(6).manhattanDistance(new Coord(1, 2));
		sum += findCoord(7).manhattanDistance(new Coord(0, 2));
		sum += findCoord(8).manhattanDistance(new Coord(0, 1));
		
		return sum;
	}

	public boolean isGoal() {
		boolean isGoal = true;
		isGoal = isGoal && state[0][0] == 1 && state[0][1] == 2 && state[0][2] == 3;
		isGoal = isGoal && state[1][0] == 8 && state[1][1] == 0 && state[1][2] == 4;
		isGoal = isGoal && state[2][0] == 7 && state[2][1] == 6 && state[2][2] == 5;
		
		return isGoal;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(state[0][0] + " " + state[0][1] + " " + state[0][2] + "\n");
		str.append(state[1][0] + " " + state[1][1] + " " + state[1][2] + "\n");
		str.append(state[2][0] + " " + state[2][1] + " " + state[2][2] + "\n");
		
		return str.toString();
	}
	
	//@Override // java 1.6
	public int compareTo(Node other) {
		if(cost == other.cost) return 0;
		else if(cost > other.cost) return 1;
		else return -1;
	}

	public Coord findCoord(int value) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(state[j][i] == value) {
					return new Coord(i, j);
				}
			}
		}
		
		return null;
	}
}
