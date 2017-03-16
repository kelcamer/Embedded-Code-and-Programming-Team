/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  NutsBolts.java
 * Date: January 26, 2016
 */
import java.util.*;

public class NutsBolts {
	public static void main(String[] args) {
		Random r = new Random();
		Bolt[] b = makeRandomBolts(r, 5);
		Nut[] n = makeMatchingNuts(r, b);
	
		// calls matching method
		match(n, b);
	}

	public static void match(Nut[] nuts, Bolt[] bolts) {
		quickSort(nuts, bolts, 0, nuts.length - 1);
	}

	public static void p(Nut[] n, Bolt[] b) {
		for (int x = 0; x < n.length; x++) {
			n[x].print();
			System.out.print(" ");

		}
		System.out.println();
		for (int x = 0; x < b.length; x++) {
			b[x].print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println();
	}

	private static void quickSort(Nut[] nuts, Bolt[] bolts, int low, int high) {
		// high - low is the amount that needs to be sorted.
		if (low < high) {
			// Choose first of bolts array for nuts partition.
			int pivot = partition0(bolts, low, high, nuts[low]);

			// Now using the partition of nuts choose that for bolts
			// partition.
			partition01(nuts, low, high, bolts[pivot]);
			// Recur for [low...pivot-1] & [pivot+1...high] for nuts and
			// bolts array.
			quickSort(nuts, bolts, low, pivot - 1);
			quickSort(nuts, bolts, pivot + 1, high);
		}
	}

	//  Sends the pivot in to partition the opposite list according to that pivot.
	private static int partition0(Bolt[] bolts, int low, int high, Nut pivot) {
		// i is the starting index
		int i = low;
		// go from the low to the high.
		for (int j = low; j < high; j++) {
			// if the current index in bolts is too small, swap the last place you were
			// with the current position.
			if(bolts[j].compareTo(pivot) < 0) {
				swap(bolts, i, j);
				// incrementing i is very important because you want to check the next element
				// you don't go back from that.
				i++;
			} 
			else if (bolts[j].compareTo(pivot) == 0) {
				// Assume pivot is the highest one, so put it in the back at the end
				swap(bolts, j, high);
				// decrement j because that part is already sorted.
				j--;
			}
		}
		// puts the pivot back into place.
		swap(bolts, i, high);

		// Return the partition index of an array based on the pivot
		// element of other array.
		return i;
	}

	private static int partition01(Nut[] nuts, int low, int high, Bolt pivot) {
		// does the exact same thing as partition 0 but for nuts instead of bolts.
		int i = low;
		for (int j = low; j < high; j++) {
			if (nuts[j].compareTo(pivot) < 0) {
				swap2(nuts, i, j);
				i++;
			} else if (nuts[j].compareTo(pivot) == 0) {
				swap2(nuts, j, high);
				j--;
			}
		}
		// swaps the last item with the pivot.
		swap2(nuts, i, high);

		// Return the partition index of an array based on the pivot
		// element of other array.
		return i;
	}

	public static void p(Nut[] n, Bolt[] b, int low, int hi) {
		System.out.println();
		for (int x = low; x < hi; x++) {
			n[x].print();
			System.out.print(" ");

		}
		System.out.println();
		for (int x = 0; x < b.length; x++) {
			b[x].print();
			System.out.print(" ");
		}
		System.out.println();
		System.out.println();
	}

	public static void swap(Bolt[] bolt, int ind1, int ind2) {
		Bolt temp = bolt[ind1];
		bolt[ind1] = bolt[ind2];
		bolt[ind2] = temp;

	}

	public static void swap2(Nut[] nut, int ind1, int ind2) {

		Nut temp = nut[ind1];
		nut[ind1] = nut[ind2];
		nut[ind2] = temp;

	}

	// Returns true iff each nut and each corresponding bolt in the two
	// respective arrays match eacy other.
	public static boolean correctFit(Nut[] nuts, Bolt[] bolts) {

		// Try out each corresponding nut and bolt.
		for (int i = 0; i < nuts.length; i++)

			// See if these don't match.
			if (nuts[i].compareTo(bolts[i]) != 0)
				return false;

		// If we get here, they all matched.
		return true;
	}

	// Creates an array of Bolt objects with size number of elements utilizing
	// r.
	public static Bolt[] makeRandomBolts(Random r, int size) {

		// Allocate space for our bolts,
		Bolt[] tmp = new Bolt[size];

		// Just create each object here and return the array.
		for (int i = 0; i < size; i++)
			tmp[i] = new Bolt(r);
		return tmp;
	}

	// Creates an array of matching nuts to the array of bolts passed in.
	// It scrambles the nuts though, so that they are no longer in the correct
	// locations.
	public static Nut[] makeMatchingNuts(Random r, Bolt[] bolts) {

		// Create the array, and add in each matching nut.
		Nut[] tmp = new Nut[bolts.length];
		for (int i = 0; i < bolts.length; i++) {
			tmp[i] = bolts[i].getMatchingNut();
		}

		// Here we mix the array of nuts up.
		for (int i = 0; i < 3 * bolts.length; i++) {

			// Get two random indexes.
			int index1 = r.nextInt(bolts.length);
			int index2 = r.nextInt(bolts.length);

			// Swap them!
			Nut store = tmp[index1];
			tmp[index1] = tmp[index2];
			tmp[index2] = store;
		}

		// Return our array of mixed up, but matching nuts.
		return tmp;
	}
}