import java.util.Arrays;
import java.util.Scanner;

public class combinations_C_translation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Combinations until which number?");
		Scanner scanny = new Scanner(System.in);
		int size = scanny.nextInt();
		runCombos(size + 1);
	}

	// Prints all combinations of 0,1,2,3,4.
	static void runCombos(int numofelements) {
		// create an int array that will hold five spaces
		int items[] = new int[numofelements];
		// make sure this array is set to all zeros initially.
		Arrays.fill(items, 0);
		printCombos(items, 0, numofelements);
		System.out.println();
	}

	static void printCombos(int subset[], int k, int n) {

		// Base case, subset filled in.  If all the numbers have been placed, then print.
		if (k == n)
			printSubsets(subset, n);

		// Recursive case - fill slot k.
		else {

			// First do subset without item k.
			printCombos(subset, k + 1, n);

			// Now do the subset with item k. Must return subset to original
			// setting!!!
			subset[k] = 1;
			printCombos(subset, k + 1, n);
			subset[k] = 0;
		}
	}

	// Prints out the subset of 0,1,2..,n-1 represented by subset. subset[i] is
	// 1 iff i is in the subset.
	static void printSubsets(int subset[], int n) {
		int i;
		for (i = 0; i < n; i++)
			if (subset[i] == 1)
				System.out.print(i);
		System.out.println();
	}

}
