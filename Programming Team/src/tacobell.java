import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class tacobell {
	static ArrayList<String> out = new ArrayList<String>();
	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		
		int loop = scanny.nextInt();
		for(int x = 0; x < loop; x++){
			int numofitems = scanny.nextInt();
			int numtobuy = scanny.nextInt();
			String[] list = new String[numofitems];
			int[] numbers = new int[numofitems];
			for(int y = 0; y < numofitems; y++){
				list[y] = scanny.next();
			}
			Arrays.sort(list);
			for(int z = 0; z < list.length; z++){
				numbers[z] = 0;
			}
			getCombos(numbers, 0, list, numtobuy);
			
			// print
			
			for(int k = out.size()-1;k>=0;k--){
				
				System.out.println(out.get(k));
		}
			System.out.println();
			out.clear();
			
		}
		
		
	}

	public static void getCombos(int[] list, int buy, String[] tacolist, int numtoprint){
		//System.out.println("Calling with buy = " + buy);
		if(buy == list.length){
			if(getOnes(list) == numtoprint){
				
				out.add(printSubsets(list, tacolist, numtoprint));
			}
		}
		else{
			getCombos(list, buy+1, tacolist, numtoprint);
			list[buy] = 1;
			getCombos(list, buy+1, tacolist, numtoprint);
			list[buy] = 0;
			
			
		
	}
		
		
		
		
	}
	public static int getOnes(int[] l){
		int total = 0;
		for(int v = 0; v < l.length; v++){
			if(l[v] == 1){
				total++;
			}
		}
		return total;
	}
	static String printSubsets(int subset[], String[] taco, int n) {
		ArrayList<String> out = new ArrayList<String>();
		int i;
	    int words = 0;
	    String temp = "";
	    for (i=0; i<subset.length; i++){
	    	//System.out.println(taco[i]);
	        if (subset[i] == 1){
	        	words++;
	        	if(temp == ""){
	        	temp = temp + taco[i];
	        	}
	        	else{
	        		temp = temp + " " + taco[i];
	        	}
	        }
	    }
	    out.add(temp);
	   return temp;
	    //Collections.reverse(out);
	   
	}
	

}
/*
2
3 2
taco
burrito
nachos
4 4
chalupa
softshelltaco
gordita
pizza

*/
