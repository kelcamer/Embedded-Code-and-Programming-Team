import java.util.Arrays;
import java.util.Scanner;

public class kcoins {
	public static int memo[][] = new int[3][3];
	public static void main(String[] args) {
		 int grid[][] = {{1, 2, 3},
                 {4, 6, 5},
                 {3, 2, 1}
               };
		// Scanner scanny = new Scanner(System.in);
		 //int coins = scanny.nextInt();
		 
		 for(int x = 0; x < memo.length; x++){
			 Arrays.fill(memo[x], -1);
		 }
		 
		
		 System.out.println(getPaths(grid, 12, 0,0,0));
	}

	private static int getPaths(int[][] grid, int coins, int x, int y, int count) {
		if(x == grid.length || y == grid[0].length){
			return 0;
		}
		if(memo[x][y] ==0 && x == grid.length-1 && y == grid[0].length-1){
			return 1;
		}
		
		coins-=grid[x][y];
		memo[x][y] = coins;
		
		if(coins == 0 && x == grid.length-1 && y == grid[0].length-1){
			return 1;
		}
		if(coins < 0){
			return 0;
		}
		
		
	
		return getPaths(grid, coins, x+1, y, count) + getPaths(grid, coins, x, y+1, count);
	}

}
