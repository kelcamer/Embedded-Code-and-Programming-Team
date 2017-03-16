/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  candy.java
 * Date: March 22, 2016
 */

import java.util.Arrays;
import java.util.Scanner;

public class candy {
	// creates an epsilon value to make sure that adding doubles actually works because Java is stupid sometimes.
	public final static double ep = 0.000000001;
	// has two public arrays, one to store the calories of each candy, one to store the price of each candy
	static int[] calories;
	static int[] price;
	// the memo makes sure you don't recalculate stuff
	static int[][] memo;
	public static void main(String[] args) {
		
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		
		// gets number of iterations
		while(loop!=0){
		// stores how much money you have to buy candy, and multiplies by 100 to get rid of decimals.
		int totalM = (int) (scanny.nextDouble() * 100 + ep);
		// initalizes the calories and price arrays with
		calories = new int[loop];
		price = new int[loop];
		memo = new int[totalM+1][totalM+1];
		// stores the calories and price for each candy
		for(int x = 0; x < loop; x++){
			calories[x] = scanny.nextInt();
			price[x] = (int)(scanny.nextDouble() * 100 + ep);
		}
		// if you have only one type of candy, buy as much as it as you can and get calories.
		if(loop == 1){
			// you must cast to an int because you can only buy whole pieces of candy.
			System.out.println((int)(totalM/price[0])*calories[0]);
		}
		else{
			// initializes memo
		for(int[] m: memo){
			Arrays.fill(m, -1);
		}
		
			// gets the answer
		int ans = solve(totalM, 0, 0);
		// prints the answer
			System.out.println(ans);
		}
		
		loop = scanny.nextInt();
		}
	}
	
// we want to return the max amount of calories
	public static int solve(int totalM, int count, int result){
	
		// if you have no money, you buy no candy so return zero.
		if(totalM == 0){
			result = 0;
			return 0;
		}
		// if you have negative money, return something very small so you won't use it.
		if(totalM < 0){
			return Integer.MIN_VALUE;
		}
		// if you reach the end of the calories array, return the result of the array
		if(count == calories.length){
			return result;
		}
		if(memo[totalM][count] > -1){
			// if there is a stored value in that location already for max calories.
			// return it.
			return memo[totalM][count];
		}
		// get the result for the next candy (not taking the current one)
		result = solve(totalM, count+1, 0);
		// if you have enough money to buy the candy,
		if(totalM >= price[count]){
			// take it and set result equal to the max of taking and not taking the candy.
			result = Math.max(result, calories[count] + solve(totalM - price[count], count, result+calories[count]));
		}
		// memoizes it.
		memo[totalM][count] = result;
		// returns
		return result;
		
	}
	
}

/*
2 8.00
700 7.00
199 2.00 
3 8.00 
700 7.00 
299 3.00 
499 5.00
0 0.00

1 10.00
100 4

*/
