import java.util.Scanner;

public class pacman {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		for (int x = 0; x < loop; x++) {
			int row = scanny.nextInt();
			int col = scanny.nextInt();
			int startx = 0, starty = 0, endx = 0, endy = 0;
			int[][] board = new int[row][col];
			for (int a = 0; a < row; a++) {
				for (int b = 0; b < col; b++) {
					String str = scanny.next();
					if(str.charAt(0) == 'P'){
						startx = a; starty = b;
					}
					else if(str.charAt(0) == 'E'){
						endx = a; endy = b;
					}
					else{
						board[a][b] = Integer.parseInt(str);
					}

					
					
				}
			}
			
			
			int[][] memo = new int[row][col];
			memo[startx][starty] = 0;
			System.out.println(getAns(board,memo));
			
			

		}
	}
private static int getAns(int[][] board, int[][] memo) {
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board[0].length; y++){
				if(x > 0 && y>0){
					memo[x][y] = Math.max(memo[x-1][y], memo[x][y-1]) + board[x][y];
				}
				else if(x > 0){
					memo[x][y] = board[x][y] + memo[x-1][y];
				}
				else if(y>0){
					memo[x][y] = board[x][y] + memo[x][y-1];

				}
				
			}
		}
		
		
		
		return memo[memo.length-1][memo[0].length-1];
	}
	// A beautiful recursive function that gets the answer but is apparently too slow and memoizing fails ;'(
	/*private static int getAns(int[][] board, int x, int y, int endx, int endy, int count, int[][] memo) {
		
		//System.out.println(count);
		if(!inBounds(x,y,board)){
			return count;
		}
		
			
		if(x == endx && y == endy){
			
			return count;
		}
	
		count+=board[x][y];
	
		memo[x][y]= Math.max(getAns(board,x,y+1,endx,endy,count,memo), getAns(board,x+1,y,endx,endy,count,memo));
		return memo[x][y];
	
	}
*/
	private static boolean inBounds(int x, int y, int[][] board) {
		if(x < 0 || y < 0 || x >= board.length || y >= board[0].length){
			return false;
		}
		return true;
		
		

	}
}


/*
2
3 4
P 3 2 8
1 4 9 3
6 2 2 E
2 2
P 5
401 E
*/