import java.util.Scanner;

public class permuations {

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
		getPermutations(temp, "ABC", 3, 3);
		
		
		
	}
	public static void getPermutations(int[] list, String msg, int lengthofperm, int numtoprint){
		if(lengthofperm == msg.length()){
			printSubsets(list, msg, lengthofperm, numtoprint);
		}
		else{
			// For combinations we have two choices.  Take the item or don't take the item.
			getPermutations(list, msg, lengthofperm+1, numtoprint);
			list[lengthofperm] = 1;
			getPermutations(list, msg, lengthofperm+1, numtoprint);
			list[lengthofperm] = 0;
			
			
		}
		
		
	}
	public static void printSubsets(int[] list, String msg, int lengthofperm, int num){
		for(int x = 0; x < msg.length(); x++){
			if(list[x] == 1){
				//if(getOnes(list) == num)
				System.out.print(msg.charAt(x));
			}
		}
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
