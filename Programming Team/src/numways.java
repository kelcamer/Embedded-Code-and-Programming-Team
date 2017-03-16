import java.util.Arrays;
import java.util.Scanner;

public class numways {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		for(int x = 0; x < loop; x++){
			int cents = scanny.nextInt();
			int loop2 = scanny.nextInt();
			int[] coins = new int[loop2];
			for(int y = 0; y < loop2; y++){
				coins[y] = scanny.nextInt();
			}
			int[] memo = new int[cents];
			Arrays.fill(memo, -1);
			Arrays.sort(coins);
			System.out.println(makeChange(cents,cents, coins,0,memo));
			for(int a = 0; a < memo.length; a++){
				System.out.print(memo[a] + " ");
				
			}
			
		}
	}

	private static int makeChange(int cents, int orig, int[] coins, int ind,int[] memo) {
		
		if(ind == coins.length){
			return 0;
		}
		if(cents < 0){
			return 0;
		}
		
		if(cents == 0){
			cents = orig;
			return 1;
		}
		if(memo[cents-1]!=-1){
			return memo[cents-1];
		}
		int val = makeChange(cents - coins[ind], orig, coins, ind,memo) + makeChange(cents, orig, coins, ind+1,memo);

		memo[cents-1] = val;
		return val;

		
	
	}
}
/*
1
6 4 1 5 10 25 





2
11 4 1 5 10 25
3 9 1 2 3 4 5 6 7 8 9
*/