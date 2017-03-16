import java.util.ArrayList;
import java.util.Scanner;

public class sudoku_solver2 {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		int[][] board = new int[9][9];
		for(int x = 0; x < loop; x++){
			for(int y = 0; y < 9; y++){
				for(int z = 0; z < 9; z++){
					board[z][y] = scanny.nextInt();
				}	
			}

		}
		
		solve(board, 0,0,0);
		printList(board);
		
	}
	private static void solve(int[][] board, int numplaced, int x, int y){
	
		if(x==9) return;
		
			if(board[x][y] == 0){
				for(int c = 1; c < 10; c++){
					if(!conflict(board, x, y, c)){
						board[x][y] = c;
						solve(board, numplaced+1, x+1, y);
					}
				//	board[x][y] = 0;
				}
			
			}
		
	}
	private static boolean conflict(int[][] board, int x, int y, int searchNum){
		// 3 functions.  Check Row, checkCol, and checkSquares
		
		
		for(int a = 0; a < 9; a++){
			// a will represent row index
			if(board[a][y] == searchNum && a!=x){
				return true;
			}
		}
		
		for(int b = 0; b < 9; b++){
			// b will represent col index
			if(board[x][b] == searchNum && b!=y){
				return true;
			}
		}
		
		
		int startx = 0, starty = 0, endy = 0, endx = 0;
		if(x >= 0 && x <= 2){
			startx = 0;
			endx = 2;
		}
		else if(x >= 3 && x <= 5){
			startx = 3;
			endx = 5;
		}
		else if(x >= 6 && x <= 8){
			startx = 6;
			endx = 8;
		}
		if(y >= 0 && y <= 2){
			starty = 0;
			endy = 2;
		}
		else if(y >= 3 && y <= 5){
			starty = 3;
			endy = 5;
		}
		else if(y >= 6 && y <= 8){
			starty = 6;
			endy = 8;
		}
		
		for(int y1 = starty; y1 <= endy; y1++){
			for(int x1 = startx; x1 <= endx; x1++){
				if(x!=x1 && y!= y1 && board[x1][y1] == searchNum){
					return true;
				}
				
			}
		}
		
		return false;
	}
	
	
	
	
	private static void printList(int[][] board){
		System.out.println();
		for(int y = 0; y < board.length; y++){
			for(int x = 0; x < board.length; x++){
				System.out.print(board[x][y] + " ");
				if((x+1) %3 == 0){
					System.out.print("|");
				}
						}
			System.out.println();
			
			}
	}
	
}


/*
0 6 0 1 0 4 0 5 0
0 0 8 3 0 5 6 0 0
2 0 0 0 0 0 0 0 1
8 0 0 4 0 7 0 0 6
0 0 6 0 0 0 3 0 0
7 0 0 9 0 1 0 0 4
5 0 0 0 0 0 0 0 2
0 0 7 2 0 6 9 0 0
0 4 0 5 0 8 0 7 0





9 6 3 1 7 4 2 5 8
1 7 8 3 2 5 6 4 9
2 5 4 6 8 9 7 3 1
8 2 1 4 3 7 5 9 6
4 9 6 8 5 2 3 1 7
7 3 5 9 6 1 8 2 4
5 8 9 7 1 3 4 6 2
3 1 7 2 4 6 9 8 5
6 4 2 5 9 8 1 7 3
*/