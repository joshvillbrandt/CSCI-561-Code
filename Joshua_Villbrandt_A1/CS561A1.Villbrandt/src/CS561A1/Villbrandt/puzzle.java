/**
 * This program uses A* to solve the classic 8 puzzle problem. The heuristic used is the Manhattan Distance.
 */
package CS561A1.Villbrandt;

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Vector;
import java.io.*;

/**
 * @author Josh Villbrandt <josh.villbrandt@usc.edu>
 *
 */
public class puzzle {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String inputFile, outputFile;
		
		// process input arguments
		if(args.length < 4) {
			throw new IOException("Not enough arguments. Check README.txt.");
		}
		else {
			if(args[0].equals("-i")) {
				inputFile = args[1];
				outputFile = args[3];
			}
			else {
				inputFile = args[3];
				outputFile = args[1];
			}
		}
		
		// initialize search
		Node root = new Node();
		root.state = readPuzzleFile(inputFile);
		System.out.println(root);
		
		// execute search
		Node goal = search(root);
		
		// save results
		if(goal != null) {
			displaySolution(goal);
			System.out.println(goal);
			writePuzzleFile(outputFile, goal);
		}
		else {
			System.out.println("No solution found.");
		}
	}

	private static int[][] readPuzzleFile(String inputFile) throws FileNotFoundException, IOException {
		Scanner file = new Scanner(new File(inputFile));
		String line;
		int lineNumber = 0;
		int[][] puzzle = new int[3][3];
		
		// read contents of file
		while(file.hasNext() && lineNumber < 3) {
			line = file.nextLine();
			line = line.replaceAll(" ", ""); // project prompt unclear on spaces...
			
			if(line.length() < 3) {
				throw new IOException("Bad input file format. Check README.txt.");
			}
			else {
				puzzle[lineNumber][0] = Character.getNumericValue(line.charAt(0));
				puzzle[lineNumber][1] = Character.getNumericValue(line.charAt(1));
				puzzle[lineNumber][2] = Character.getNumericValue(line.charAt(2));
			}
			
			lineNumber++;
		}
		
		// verify that we filled up the puzzle
		if(lineNumber != 3) {
			throw new IOException("Bad input file format. Check README.txt.");
		}
		
		return puzzle;
	}

	private static Node search(Node root) {
		Node goal = null, current;
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(root);
		
		// primary loop, search until goal or empty
		while(goal == null && queue.size() > 0) {
			current = queue.remove();
			
			if(current.isGoal()) goal = current;
			else expandNode(current, queue);
		}
		
		return goal;
	}

	private static void expandNode(Node current, PriorityQueue<Node> queue) {
		// find the empty space (zero)
		Coord zeroCoord = current.findCoord(0);
		
		// move up
		if(zeroCoord.y > 0) {
			Node child = new Node(current, zeroCoord, new Coord(zeroCoord, 0, -1));
			queue.add(child);
		}
		
		// move right
		if(zeroCoord.x < 2) {
			Node child = new Node(current, zeroCoord, new Coord(zeroCoord, 1, 0));
			queue.add(child);
		}
		
		// move down
		if(zeroCoord.y < 2) {
			Node child = new Node(current, zeroCoord, new Coord(zeroCoord, 0, 1));
			queue.add(child);
		}
		
		// move left
		if(zeroCoord.x > 0) {
			Node child = new Node(current, zeroCoord, new Coord(zeroCoord, -1, 0));
			queue.add(child);
		}
	}

	private static void writePuzzleFile(String outputFile, Node goal) throws IOException {
		FileWriter fstream = new FileWriter(outputFile);
		BufferedWriter out = new BufferedWriter(fstream);
		
		out.write(goal.toString());
		
		out.close();
	}

	private static void displaySolution(Node goal) {
		Node current = goal;
		Vector<String> steps = new Vector<String>();
		
		// generate output sequence
		while(current.parent != null) {
			Coord move = current.findCoord(0).minus(current.parent.findCoord(0));

			if(move.y == -1) steps.add("move blank tile up");
			if(move.x == 1) steps.add("move blank tile right");
			if(move.y == 1) steps.add("move blank tile down");
			if(move.x == -1) steps.add("move blank tile left");
			
			current = current.parent;
		}
		
		// print output sequence in correct order
		System.out.println("Sequence of tiles to be moved:\n");
		for(int i = steps.size() - 1; i >= 0; i--) {
			System.out.println((steps.size() - i) + ": " + steps.get(i));
		}
		
		// print out number of moves
		System.out.println("\nNumber of moves: " + steps.size() + "\n");
	}

}
