/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  lcss.java
 * Date: April 7, 2016
 * this assignment took me so long
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class lcss {

	public static void main(String[] args) {
		// Scanner
		Scanner scanny = new Scanner(System.in);
		// gets how many times you want to loop
		int loop = scanny.nextInt();
		// loops
		for (int x = 0; x < loop; x++) {
			// takes care of sucky scanner java crap
			scanny.nextLine();
			// gets next string
			String s = scanny.nextLine();
			// tokenizes it
			StringTokenizer tok1 = new StringTokenizer(s);
			StringTokenizer tok2 = new StringTokenizer(scanny.nextLine());
			ArrayList<String> first = new ArrayList<String>();
			ArrayList<String> second = new ArrayList<String>();
			
			// get size of array
			int words1 = tok1.countTokens();
			int words2 = tok2.countTokens();
			
			// add words to list
			while (tok1.hasMoreTokens()) {
				String next = tok1.nextToken();
				first.add(next);
			}
			// add words to second list
			while (tok2.hasMoreTokens()) {
				String next = tok2.nextToken();
				second.add(next);
			}
			
			// creates a matrix with a zero buffer
			// IF YOU DO NOT HAVE A ZERO BUFFER IT WILL NOT WORK
			int[][] matrix = new int[words1+1][words2+1];
			// initializes array to 0
			for (int b = 0; b < words1; b++) {
				for (int c = 0; c < words2; c++) {
					matrix[b][c] = 0;
				}
			}
			// loops through array
			for (int y = 1; y < words1+1; y++) {
				for (int a = 1; a < words2+1; a++) {
					// if you find a match, get the diagonal and add 1
					if (first.get(y-1).equals(second.get(a-1))) {
						matrix[y][a] = matrix[y-1][a-1] +1;
					} else {
						// else get max of left, left top, and top
						matrix[y][a] = getMax(y, a, matrix);
					}

				}
			}
	
		// prints everything
				System.out.print("LCSS Length = " + getLastLength(matrix) + ". ");
				System.out.print("LCSS = ");
				printList(getList(matrix, first,second));
			
			
		}
		scanny.close();
	}

	
// prints a list
	private static void printList(ArrayList<String> list) {
		for (int x = list.size()-1; x>=0; x--) {
			System.out.print(list.get(x) + " ");
		}

	}
	
	
	private static ArrayList<String> getList(int[][] matrix, ArrayList<String> list, ArrayList<String> list2) {
		// a list to store the words
		ArrayList<String> words = new ArrayList<String>();
		// starting bounds, bottom right corner
		int i = matrix.length-1;
		int j = matrix[0].length-1;
		// while you haven't reached 0
		while(i > 0 && j > 0){
			// if the adjacent ones are the same take the diagonal
			if(matrix[i][j]-1 == matrix[i-1][j-1]
					&& matrix[i-1][j-1] == matrix[i-1][j]
							&& matrix[i-1][j-1] == matrix[i][j-1]){
				words.add(list.get(i-1));
				i--; 
				j--;
			
			}
			// else take the max of top and left
			else if(matrix[i-1][j] > matrix[i][j-1]){
				i--;
			}
			else{
				j--;
			}
			
			
		}
			
		// return list
		return words;
	}
	
	private static int getLastLength(int[][] matrix) {
		// gets last length
		return matrix[matrix.length - 1][matrix[0].length - 1];
	}

	private static void printMatrix(int[][] matrix) {
		// prints matrix
		for (int y = 0; y < matrix[0].length; y++) {
		for (int x = 0; x < matrix.length; x++) {
				System.out.print(matrix[x][y]);
			}
			System.out.println();
		}
	}

	private static int getMax(int y, int a, int[][] matrix) {
		// initalizes max
		int max = 0;
		if (a - 1 < matrix[0].length && a - 1 >= 0 && y - 1 < matrix.length && y - 1 >= 0) {
			// if in bounds, check top left, if bigger, set max to it
			max = Math.max(max, matrix[y - 1][a - 1]);
		}
		if (y - 1 < matrix.length && y - 1 >= 0) {
			// if in bounds, check  left, if bigger, set max to it

			max = Math.max(max, matrix[y - 1][a]);
		}
		if (a - 1 < matrix[0].length && a - 1 >= 0) {
			// if in bounds, check top , if bigger, set max to it

			max = Math.max(max, matrix[y][a - 1]);
		}

		return max;
	}

}



/*
1
rat apple cat ear cat apple rat
cat rat ear apple mush



1
Rat rat rat rat rat
Rat rat
*/
