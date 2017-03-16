import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class contractor {
	static offer temp;

	public static void main(String[] args) {

		Scanner scanny = new Scanner(System.in);

		int loop = scanny.nextInt();
		for (int x = 0; x < loop; x++) {
			int numberoffers = scanny.nextInt();
			int dayswillingtowork = scanny.nextInt();
			ArrayList<offer> listofoffers = new ArrayList<offer>();
			for (int y = 0; y < numberoffers; y++) {
				int daysreq = scanny.nextInt();
				int money = scanny.nextInt();

				listofoffers.add(new offer(daysreq, money));

			}
			// now find the right combination...
			runCombos(listofoffers.size(), listofoffers, dayswillingtowork);

		}

	}

	// Prints all combinations
	static void runCombos(int numofelements, ArrayList<offer> offlist, int days) {
		// create an int array that will hold spaces
		int items[] = new int[numofelements];
		// make sure this array is set to all zeros initially.
		Arrays.fill(items, 0);
		ArrayList<offer> bestoffers = printCombos(items, 0, numofelements, offlist, days, offlist);
		offer final_offer = null;
		for (int x = 0; x < bestoffers.size() - 1; x++) {
			if (bestoffers.get(x).money > bestoffers.get(x + 1).money) {
				final_offer = bestoffers.get(x);
			} else {
				final_offer = bestoffers.get(x + 1);
			}

		}
		System.out.print(final_offer.money);

		// System.out.println();
	}

	static ArrayList<offer> printCombos(int subset[], int k, int n, ArrayList<offer> olist, int days,
			ArrayList<offer> bestoffers) {

		// Base case, subset filled in. If all the numbers have been placed,
		// then print.
		// rather than immediately printing, lets create a function that
		// determines the ideal combination of offers.
		if (k == n) {
			offer temp2 = printSubsets(subset, n, olist, days);
			offer temp3 = getGreater(temp2, temp);
			if (temp3 != null) {
				// System.out.println("Best offer:");
				// System.out.println("Days " + temp3.daysreq);
				// System.out.println("Money " +temp3.money);
				bestoffers.add(temp3);
				temp = temp2;
			}
		}
		// Recursive case - fill slot k.
		else {

			// First do subset without item k.
			printCombos(subset, k + 1, n, olist, days, bestoffers);

			// Now do the subset with item k. Must return subset to original
			// setting!!!
			subset[k] = 1;
			printCombos(subset, k + 1, n, olist, days, bestoffers);
			subset[k] = 0;
		}
		return bestoffers;

	}

	// Prints out the subset of 0,1,2..,n-1 represented by subset. subset[i] is
	// 1 iff i is in the subset.
	static offer printSubsets(int subset[], int n, ArrayList<offer> oflist, int willingtowork) {
		int totalmoney = 0, temp = 0, daysreq = 0;
		for (int i = 0; i < n; i++)
			if (subset[i] == 1) {
				daysreq += oflist.get(i).daysreq;
				totalmoney += oflist.get(i).money;
				// System.out.print("$" + oflist.get(i).money + " for " +
				// oflist.get(i).daysreq+" ");
			}
		// System.out.println();
		if (daysreq <= willingtowork) {
			// System.out.println("A total of $" + totalmoney + " for " +
			// daysreq);
			return new offer(daysreq, totalmoney);
		}
		return null;
	}

	// if equal pick smaller amount of days
	static offer getGreater(offer of1, offer of2) {
		if (of1 != null && of2 != null) {
			if (of1.money == of2.money) {
				return of1.daysreq > of2.daysreq ? of1 : of2;
			}
			return of1.money > of2.money ? of1 : of2;
		}
		return new offer(0, 0);
	}
}

/*
2
2 5
3 10000
4 8000
3 100
20 20000
40 50000
40 30000

 */
class offer {
	int daysreq, money;

	public offer(int d, int m) {
		this.daysreq = d;
		this.money = m;
	}

}