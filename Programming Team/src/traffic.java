// Kelsey Cameron
// October 15, 2015
// this one took so freaking long to figure out
// in retrospect it wasn't that hard, but basically you get the first 1 and search, avoid the destroyed column, and see if you can get to all nodes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class traffic {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		// java automatically fills array

		for (int x = 0; x < loop; x++) {
			int matrix[][] = new int[51][51];
			System.out.println("City #" + (x + 1));
			int grids = scanny.nextInt();

			// y is your current intersection
			// The matrix must be symmetric because it's a road - it goes both
			// ways.
			for (int y = 1; y < grids + 1; y++) {
				int loop2 = scanny.nextInt();
				for (int z = 1; z < loop2 + 1; z++) {
					int temp = scanny.nextInt();
					matrix[y][temp] = 1;
				}

			}

			int loop3 = scanny.nextInt();
			for (int z = 0; z < loop3; z++) {
				boolean state = false;
				int temp = scanny.nextInt();
				boolean[] filled = new boolean[grids + 1];
				Arrays.fill(filled, false);
				filled[temp] = true;
				int first = getFirst(temp, matrix, grids);
				filled[first] = true;
				filled = interferes(first, matrix, filled, grids);
				boolean allVisited = allTrue(filled, grids);

				if (allVisited || first == 0) {
					System.out.println("no");
				} else {
					System.out.println("yes");
				}
			}
			System.out.println();
		}
	}

	/*
	 * 1 5 1 2 4 1 3 4 5 1 2 1 2 1 2 2 1 2
	 */
	public static void print(String message) {
		System.out.println(message);
	}

	public static void printList(int[][] grid) {
		for (int x = 1; x < 6; x++) {
			for (int y = 1; y < 6; y++) {
				System.out.print(grid[y][x]);
			}
			System.out.println();

		}
	}

	// if I do not find any 1's at all, I return false. If I do, I return true
	public static boolean[] interferes(int num, int[][] graph, boolean[] filled, int max) {

		for (int x = 1; x <= max; x++) {

			if (graph[num][x] == 1 && !filled[x]) {

				filled[x] = true;
				interferes(x, graph, filled, max);

			}
		}

		return filled;
	}

	public static int getFirst(int num, int[][] graph, int max) {

		for (int x = 1; x < max; x++) {

			if (graph[num][x] == 1) {
				return x;
			}
		}
		return 0;

	}

	public static boolean allTrue(boolean[] filled, int max) {
		for (int x = 1; x <= max; x++) {

			if (!filled[x]) {
				return false;
			}
		}
		return true;
	}

}
/*
 * 1 5 1 2 2 1 3 2 2 4 2 3 5 1 4 2 4 5
 * 
 * 
 * 5 4 2 3 4 5 4 1 3 4 5 4 1 2 4 5 4 1 2 3 5 4 1 2 3 4 5 1 2 3 4 5
 * 
 * This should return City #4 no no no no no
 * 
 * 
 */