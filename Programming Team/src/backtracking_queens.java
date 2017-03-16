
public class backtracking_queens {
// conflict doesn't work.
	public static void main(String[] args) {
		int[][] board = new int[8][8];
		solveIt(board, 0, false);
		printBoard(board);
		
		
		

	}

	public static boolean solveIt(int board[][], int x, boolean state) {
		if(x == board.length){
			state = true;
		}
		if(state){
		return state;
		}
		else{
	
		/*
		 * 1. Place a queen in the first available spot. 2. Place a queen in the
		 * next available spot. 3. Continue until you reach a failure and go
		 * back to where you previously were. 4. Continue step #3 so a stack is
		 * created, and you know your problem is solved when all queens are on
		 * board. 5. To do step 4, make sure you have a count that keeps track
		 * of queens placed.
		 * 
		 */
		
		for(int a = 0; a < board.length && x < board.length && !state; a++){
			// there is not a conflict
			if(!conflict(board, x, a)){
				board[x][a] = 1;
				state |= solveIt(board, x+1, state);
				if(!state){
				board[x][a] = 0;
				}
			}
			
		}
		}
		return state;
		

		
		
		
	}
	// conflict has a problem
	public static boolean conflict(int board[][], int x_place, int y_place){
		//printBoard(board);
		if(board[x_place][y_place] == 1){
			return true;
		}
		int x_orig = x_place;
		int y_orig = y_place;
		
		
		while(x_place > 0){
			x_place--;
		}
		while(x_place < board.length){
			if(board[x_place][y_orig] == 1){
				return true;
			}
			x_place++;
		}

		while(y_place > 0){
			y_place--;
		}
		while(y_place < board.length){
			if(board[x_orig][y_place] == 1){
				return true;
			}
			y_place++;
		}
	
			x_place = x_orig;
			y_place = y_orig;
			while(x_place > 0 && y_place > 0){
				x_place--;
				y_place--;
			//	System.out.println(x_place + " " + y_place);
				if(board[x_place][y_place] == 1 && x_orig!=x_place && y_orig!= y_place){
					return true;
				}
			}
			while(x_place < board.length && y_place < board.length){
				if(board[x_place][y_place] == 1 && x_orig!=x_place && y_orig!= y_place){
					return true;
				}
				x_place++;
				y_place++;
			}
			x_place = x_orig;
			y_place = y_orig;
			while(x_place >= 0  && y_place > 0 && x_place < board.length-1){
				x_place++;
				y_place--;
				if(board[x_place][y_place] == 1 && x_orig!=x_place && y_orig!= y_place){
					return true;
				}
				
			}
			x_place = x_orig;
			y_place = y_orig;
			while(x_place > 0 && y_place >= 0 && y_place <board.length-1){

				x_place--;
				y_place++;
				if(board[x_place][y_place] == 1 && x_orig!=x_place && y_orig!= y_place){
					return true;
				}
				
			}
			
		return false;
	}
	public static boolean boundsCheck(int x, int max) {
		if (x >= 0  && x <= max ) {
			return true;
		}
		return false;
	}
	public static void printBoard(int board[][]){
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board[0].length; y++){
				System.out.print(board[y][x] + " ");
			}
			System.out.println();
		}
	}
}
