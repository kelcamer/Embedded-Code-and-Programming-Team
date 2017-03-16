
public class QuickSort {
	public static void main(String[] args) {
		int[] main = { 66, 92, 45, 35, 59, 78, 16, 23, 56 };
		// after partition:
		// 23, 16, 45, 35, 56, 78, 66, 92, 59,

		DoQuickSort(main);
		printAr(main);
	

	}

	public static void DoQuickSort(int[] a) {
		DoQuickSort(a, 0, a.length - 1);
	}

	public static void DoQuickSort(int[] a, int lo, int hi) {
		if (lo < hi) {
			// the pivot is simply an index
			int pivot = partition(a, lo, hi, 0);
			DoQuickSort(a, lo, pivot - 1);
			DoQuickSort(a, pivot + 1, hi);
		}

	}

	public static void printAr(int[] ar) {
		for (int x = 0; x < ar.length; x++) {
			System.out.print(ar[x] + " ");

		}
	}

	static int partition(int[] list, int lo, int hi, int pivot) {

		// loop through rest of array
		while (lo < hi) {
			while (list[lo] < list[hi]) {
				System.out.println(list[lo] + " is less than " + list[hi]);
				lo++;
			}
			while (list[hi] < list[lo]) {
				System.out.println();
				System.out.println(list[hi] + " is less than " + list[lo]);
				printAr(list);
				hi--;
			}
			if (lo < hi) {
				swap(list, lo, hi);
			}
		}

		return lo;
	}

	static void swap(int[] ar, int ind1, int ind2) {
		int temp = ar[ind1];
		ar[ind1] = ar[ind2];
		ar[ind2] = temp;
	}
}
