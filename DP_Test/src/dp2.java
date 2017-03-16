import java.util.Scanner;

public class dp2 {
	public static int n;
	public static void main(String[] args) {
		System.out.println("Please enter a value for N.");
		Scanner scanny = new Scanner(System.in);
		n = scanny.nextInt();
		System.out.println("Please enter a mod value.");
		int mod = scanny.nextInt();

		int[] array = new int[n];
		for (int x = 0; x < n; x++) {
			array[x] = (x + 1);
		}

		System.out.println(getAns(array, mod, 0, Integer.MAX_VALUE,0));
	}

	private static int getAns(int[] array, int mod, int ind, int min, int state) {
		int[] copy = array;
	
		if(ind >=n+2) return getSum(copy)%mod; 
		if(ind >=2 && Math.abs(copy[ind-2] - copy[ind-1]) > 2){
			return min;
		}
		switch (state) {
		case 1:
			//System.out.println("Case 1");
			copy[ind-2]++;
			copy[ind-1]++;
			break;

		case 2:
			//System.out.println("Case 2");
			copy[ind-2]--;
			copy[ind-1]++;
			break;
		case 3:
			//System.out.println("Case 3");
			copy[ind-2]++;
			copy[ind-1]--;
			break;
		case 4:
			//System.out.println("Case 4");

			copy[ind-2]--;
			copy[ind-1]--;
			break;
		case 0:
			break;
		}
		//printArray(copy);
		
		if(ind >=2) min = Math.min(min, getSum(copy)%mod);
	//System.out.println();
		//System.out.println("Min " + min);
				
		min = Math.min(min,getAns(copy, mod, ind + 2, min, 1)); // add add
		min = Math.min(min,getAns(copy, mod, ind + 2, min, 2)); // subtract add
		min = Math.min(min,getAns(copy, mod, ind + 2, min, 3)); // add subtract
		min = Math.min(min,getAns(copy, mod, ind + 2, min, 4)); // subtract subtract
		return min;

	}

	private static void printArray(int[] array) {
		for(int x = 0; x < array.length; x++){
			System.out.print(array[x] + " ");
		}
	}

	private static int getSum(int[] array) {
		int total = 0;
		for (int x = 0; x < array.length; x++) {
			total += array[x];
		}
		return total;
	}
	/*
	 * private static int getAns(int[] array, int mod, int ind, int min) {
	 * if(ind < 0 || ind > array.length-1){ return Integer.MAX_VALUE; } if(min
	 * == 0){ return min; } int[] copy1 = array; int[] copy2 = array; int[]
	 * copy3 = array; int[] copy4 = array;
	 * 
	 * copy1[ind]++; copy1[ind + 1]++;
	 * 
	 * copy2[ind]--; copy2[ind + 1]++; copy3[ind]++; copy3[ind + 1]--;
	 * copy4[ind]--; copy4[ind + 1]--;
	 * 
	 * 
	 * 
	 * if(min != 0){ return Math.min(Math.min(getAns(copy1, mod, ind + 1, min) %
	 * mod, getAns(copy2, mod, ind + 1, min) % mod), Math.min(getAns(copy3, mod,
	 * ind + 1, min) % mod, getAns(copy4, mod, ind + 1, min) % mod)); } return
	 * min;
	 * 
	 * }
	 */

}
