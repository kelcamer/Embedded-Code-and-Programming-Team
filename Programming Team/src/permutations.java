import java.util.Scanner;

public class permutations {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
	//	p("Please enter a string that you would like a list of all permuations for.");
		
		/*
		 * In a permutation
		 * 1. Order matters.  Each string combination is different.
		 * 2. Repetition is not allowed.
		 * 
		 * 
		 */
		int[] temp = new int[100];
		runPerms("ABC", 0);
		
		
		
	}
	static void runPerms(String msg, int numtoprint){
		int size = msg.length();
		int perm[] = new int[size];
		int used[] = new int[size];
		for(int i = 0; i < size; i++){
			printPerms(perm, used, 0, msg, msg.length());
			System.out.println();
		}
	}
	static void printPerms(int perm[], int used[], int k, String msg, int n){
		if(k == msg.length()){
			print(perm, n, msg);
		
		}
		
		else{
			 for (int i=0; i<n; i++) {
		            if (used[i] == 0) {
		                used[i] = 1;
		                perm[k] = i;
		                printPerms(perm, used, k+1, msg, n);
		                used[i] = 0;
		            }
		        }
		}
	}
	
	public static void print(int[] perm, int n, String msg) {
		for(int x = 0; x < n; x++){
			if(perm[x] == 1){
				System.out.print(msg.charAt(x));
			}
			
		}
		
		
	}
	/*

// Prints all permutations of 0,1,...,n-1 where the first k items of perm are fixed.
void printPerms(int perm[], int used[], int k, int n) {

    // Base case.
    if (k == n) print(perm, n);

    // Recursive case - fill in slot k.
    else {
        int i;

        // Only fill slot k with items that have yet to be used. If i hasn't been used,
        // put it in slot k and recursively print all permutations with these k+1 starting items.
        for (i=0; i<n; i++) {
            if (!used[i]) {
                used[i] = 1;
                perm[k] = i;
                printPerms(perm, used, k+1, n);
                used[i] = 0;
            }
        }
    }
}
	 */
	public static void getPermutations(int[] list, String msg, int lengthofperm, int numtoprint){
	//System.out.println("hi");
		if(lengthofperm == msg.length()){
			printSubsets(list, msg, lengthofperm, numtoprint);
		}
		else{
			 for (int i=0; i<lengthofperm; i++) {
		            if (list[i] ==0) {
			// For combinations we have two choices.  Take the item or don't take the item.
			getPermutations(list, msg, lengthofperm+1, numtoprint);
			list[lengthofperm] = 1;
			getPermutations(list, msg, lengthofperm+1, numtoprint);
			list[lengthofperm] = 0;
			
		            }
		}}
		
		
	}
	public static void printSubsets(int[] list, String msg, int lengthofperm, int num){
		for(int x = 0; x < msg.length(); x++){
			if(list[x] == 1){
				//if(getOnes(list) == num)
				System.out.print(msg.charAt(x));
			}
		}
		System.out.println();
	}
	public static int getOnes(int[] list){
		int ones = 0;
		for(int x = 0; x < list.length; x++){
			if(list[x] == 1){
				ones++;
			}
			
		}
		return ones;
	}
	public static void p(String msg){
		System.out.println(msg);
	}
}
