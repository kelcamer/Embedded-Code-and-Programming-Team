import java.util.ArrayList;
import java.util.Scanner;

public class sudoku_solver {

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
		
		
		// conflict has a problem approach is entirely wrong.
		// I need to scan the rest of the rows and columns to check for the number
		// then check each of the 3 by 3 squares.... dang it
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
		 */
	//	board[0][0] = 9;
	//	printList(board);
	//	System.out.println(conflict2(board, 1,2, 8));
		solve(board, getNumbers(board), 0);
		printList(board);
		
	}
	private static int getNumbers(int[][] board) {
		int num = 0;
		for(int y = 0; y < board.length; y++){
		for(int x = 0; x < board.length; x++){
			if(board[x][y] != 0){
				num++;
			}
		}
		}
		
		return num;
	}
	private static void solve(int[][] board, int numplaced, int y) {
		if(numplaced == 81){
			return;
		}
		
		if(y == 9){
			y = 0;
			//return;
		}
		// the number you pick for each square
		for(int x = 0; x < 9; x++){
			if(board[y][x] == 0){
			
				for(int c = 1; c < 10 && board[y][x] == 0; c++){
				if(board[y][x] == 0){
						board[y][x] = c;
						if(conflict2(board, y, x, c)){
							System.out.println("CONFLICT ");
							board[y][x] = 0;
						}
						System.out.println("BEFORE " + numplaced);
						printList(board);
					}
				}
				
				System.out.println("AFTER ");
				printList(board);
				
		}
			else{
				solve(board, numplaced+1, y+1);			
			}
		}
		
		
		
		
	}
	
	private static ArrayList<point> getListOfZero(int[][] board) {
		ArrayList<point> list = new ArrayList<point>();
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board.length; y++){
				if(board[y][x] == 0){
					point p = new point(y, x);
					list.add(p);
					
				}
				
			}
			
		}
		return list;
		
		
	}
	private static void solve(int[][] board, ArrayList<point> pointstocheck, int count){
		if(count == 9){
		//	printList(board);
			return;
		}
		
		while(pointstocheck.size() != 0){
			int tempx = pointstocheck.get(count).x;
			int tempy = pointstocheck.get(count).y;
			int tempval = board[tempx][tempy];
			for(int x = 1; x < 10; x++){
				board[tempx][tempy] = x;
				if(conflict2(board, tempx, tempy, x)){
				//	System.out.println("CONFLICT ");
					board[tempx][tempy] = 0;
					pointstocheck.add(new point(tempx, tempy));
				}
				else{
					pointstocheck.remove(count);
				}
				
			}
			
			count++;
			
		}
		
		
		
	}
	private static boolean conflict2(int[][] board, int x, int y, int searchNum){
		// 3 functions.  Check Row, checkCol, and checkSquares
		
		
	//	System.out.println(x + " " + y);
		for(int a = 0; a < 9; a++){
			// a will represent row index
			if(board[a][y] == searchNum && a!=x){
				return true;
			}
		}
		
		for(int b = 0; b < 9; b++){
			// b will represent row index
			if(board[x][b] == searchNum && b!=y){
			//	System.out.println("COL CONFLICT");
				return true;
			}
		}
		
		
		// No this section won't work... hint, use index values to determine which box to check, 
		// reduces runtime too.
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
					System.out.println("SQUARE CONFLICT");
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
class point{
	int x; int y;
	
	point(int a, int b){
		this.x = a;
		this.y = b;
	}
	private void setPoint(int c, int d){
		this.x = c;
		this.y = d;
	}
	private int getX(){
		return this.x;
	}
	private int getY(){
		return this.y;
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