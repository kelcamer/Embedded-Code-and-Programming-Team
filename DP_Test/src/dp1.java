import java.util.Arrays;

public class dp1 {

	public static int[][] board = new int[6][6];
	public static int[][] memo = new int[6][6];
	public static int[][] memo1 = new int[6][6];
	public static boolean[][] states = new boolean[6][6];

	public static void main(String[] args) {
		for(int x = 0; x < 6; x++){
			for(int y = 0; y < 6; y++){
				board[x][y] = 0;
				memo[x][y] = 0;
				memo1[x][y] = 0;
			}
		}
		board[1][2] = -1;
		board[3][3] = -1;
		
		System.out.print("There are ");
		System.out.print(getAns(board, 0, 0));
		System.out.print(" paths from one corner to another. There are ");
		System.out.print(getBonus(board,0,0,false));
		System.out.print(" paths from one corner to another which go through the bonus square.");
		
	}

	private static void printMemo(int[][] memo_temp) {
		for(int x = 0; x < 6; x++){
			for(int y = 0; y < 6; y++){
				System.out.print(memo_temp[x][y] + " ");
			}
			System.out.println();
		}
	}

	private static int getBonus(int[][] board, int x, int y, boolean state) {
		if(!inBounds(x,y)){
			return 0;
		}
		
		if(x == 3 && y == 1){
			state = true;
			states[x][y] = true;
		}
		if(x == 5 && y == 5 && state){
		
			return 1;
		}
		if(states[x][y] && memo[x][y] > 0){
			return memo[x][y];
		}
		memo[x][y] = getBonus(board, x+1, y,state) + getBonus(board, x, y+1,state);
		return memo[x][y];
	}
	
	private static int getAns(int[][] board, int x, int y) {
		if(!inBounds(x,y)){
			return 0;
		}
		if(memo[x][y] >0){
			return memo[x][y];
		}
		if(x == 5 && y == 5){
			return 1;
		}
		
		memo1[x][y] =  getAns(board, x+1, y) + getAns(board, x, y+1);
		return memo1[x][y];
	}
	private static boolean inBounds(int x, int y){
		if(x < 0 || x >=6 || y < 0 || y >=6){
			return false;
		}
		if(board[x][y] == -1){
			return false;
		}
		return true;
	}

}
