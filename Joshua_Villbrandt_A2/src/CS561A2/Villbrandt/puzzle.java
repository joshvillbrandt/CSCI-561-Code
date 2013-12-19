/**
 * A constraint satisfaction approach to the n Queens puzzle.
 */
package CS561A2.Villbrandt;

import java.io.*;
import java.util.Vector;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class puzzle {
	static int n;
	static Vector<Node> solutions;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String outputFile;
			
			// process input arguments
			if(args.length < 2) {
				throw new IOException("Not enough arguments. Check README.txt.");
			}
			else {
				n = Integer.parseInt(args[0]);
				
				if(n < 0) 
					throw new IOException("N must be an integer greater than 0. Check README.txt.");
				
				outputFile = args[1];
			}
			
			// initialize search variables
			solutions = new Vector<Node>();
			Node start = new Node(n);
			
			// execute search
			search(start);
			System.out.println("Total number of solutions: " + solutions.size());
			
			// save results
			writeOutputFile(n, solutions, outputFile);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void search(Node node) {
		if(node.nextQueen >= n) {
			// a complete assignment that is valid
			solutions.add(node);
		}
		else {
			int currentQueen = node.nextQueen;
			node.nextQueen++;
			node.queens[currentQueen] = new Point2D(0, currentQueen);
			
			// try all positions of this queen
			for(int i = 0; i < n; i++) {
				node.queens[currentQueen].x = i;
				
				// check if this is a valid move
				if(node.isSafe(node.queens[currentQueen])) {
					Node newNode = new Node(node);
					newNode.expandGrid(node.queens[currentQueen]);
					
					// continue search (might be complete, but search will immediately "return"
					search(newNode);
				}
				
			}
		}
	}

	private static void writeOutputFile(int n, Vector<Node> solutions, String outputFile) throws IOException {
		FileWriter fstream = new FileWriter(outputFile);
		BufferedWriter out = new BufferedWriter(fstream);

		out.write("Number of Queens: " + n + "\n");
		
		// print out all solutions
		for(int i = 0; i < solutions.size(); i++) {
			StringBuilder str = new StringBuilder();
			str.append("Solution ");
			str.append(i + 1);
			str.append(": ");
			
			// print out each queen in the solution
			for(int j = 0; j < solutions.elementAt(i).queens.length; j++) {
				str.append(solutions.elementAt(i).queens[j]);
				if(j < solutions.elementAt(i).queens.length - 1)
					str.append(", ");
			}
			
			out.write(str + "\n");
		}
		
		out.write("Total number of solutions: " + solutions.size() + "\n");
		
		out.close();
	}

}
