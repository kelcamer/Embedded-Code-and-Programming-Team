import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class cash_cow {
	// left, down, up, right
	static int[] xdir = {-1, 0, 0, 1};
	static int[] ydir = {0, -1, 1, 0};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanny = new Scanner(System.in);
		int numofchoices = scanny.nextInt();
		
		//System.out.println(numofchoices);
		
		while(numofchoices != 0){
			
		char[][] characters = new char[10][12];
		int[][] matrix = new int[10][12];
		for (int[] row: matrix){
		    Arrays.fill(row, -1);
		}
		scanny.nextLine();
		
		for(int x = 0; x < 12; x++){
			String nextline = scanny.nextLine();
			for(int y = 0; y < 10; y++){
				characters[y][x] = nextline.charAt(y);
			}
		}
		
		matrix = convert(characters);
		//printArr(matrix);
		//System.out.println();
		ArrayList<coor> choices = new ArrayList<coor>();
		boolean[][] fill = new boolean[10][12];
		// Fill each row with false
		for (boolean[] row: fill){
			Arrays.fill(row, false);
		}
		int count = 0;
		
		int xchoice = 0, ychoice = 0;
		for(int x = 0; x < numofchoices; x++){
			char c = scanny.next().charAt(0);
			ychoice = scanny.nextInt();
			xchoice = change(c);
			coor next = new coor(xchoice, ychoice);
			choices.add(next);
		}
		
		for(int a = 0; a < choices.size(); a++){
		//	System.out.println(choices.get(a).x + " " +  (int)(12 - choices.get(a).y));
		int num = matrix[choices.get(a).x][(int)(12 - choices.get(a).y)];
		matrix[choices.get(a).x][(int)(12 - choices.get(a).y)] = -1;
		count = floodfill(matrix, matrix, choices.get(a).x, (int)(12 - choices.get(a).y), fill, num, 0);
		System.out.println("\n");
		printArr(matrix);
		System.out.println("Shifting down: ");
		shiftDown(matrix, 0);
		printArr(matrix);
		System.out.println("Shifting left: ");
		shiftLeft2(matrix, 0);
		printArr(matrix);
		for (boolean[] row: fill){
			Arrays.fill(row, false);
		}
		}
		
		if(count <=2){
			System.out.println(countOnes(matrix) + count);
		}
		else{
			System.out.println(countOnes(matrix));
			
		}
		 numofchoices = scanny.nextInt();

		}
		scanny.close();
	}
	public static int change(char a){
		return (int)a - 97;
	}
	
	public static void shiftDown(int[][] grid, int c){
		if(c == 12){
			return;
		}
			for(int x = 0; x < 10; x++){
				for(int y = 0; y <11; y++){
					if(grid[x][y] != -1 && grid[x][y+1] == -1){
						swap(grid, x, y, x, y+1);
					}
				
				
				
				}
				
			}
			shiftDown(grid, c+1);
		
	}
	
	
	
	private static void swap(int[][] grid, int x, int y, int x2, int y2) {
		
		int temp = grid[x][y];
		grid[x][y] = grid[x2][y2];
		grid[x2][y2] = temp;
	}
	// initialize filled to be completely false.
	public static int floodfill(int[][] grid, int[][] gridcopy, int xco, int yco, boolean[][] filled, int place, int count){
	//	System.out.println("x " + xco + " y " + yco);
		if(filled[xco][yco] == true){
			return count;
		}
		if(count >= 3){
			for(int x = 0; x < gridcopy[0].length; x++){
				for(int y = 0; y < gridcopy.length; y++){
					grid[y][x] = gridcopy[y][x];
				}
			}
		
		}
		//if(count > 3){
			//grid[xco][yco] = -1;
		//}
		filled[xco][yco] = true;
		gridcopy[xco][yco] = -1;
		for(int x = 0; x < 4; x++){
			int nextx = xco + xdir[x];
			int nexty = yco + ydir[x];
			
			if(inBounds(nextx, nexty) && filled[nextx][nexty] == false){
				if(grid[nextx][nexty] == place){
					grid[nextx][nexty] = -1;
					count = floodfill(grid, gridcopy, nextx, nexty, filled, place, count+1);
				}
				
				
			}
			
		}
	return count;
		
	}
	
	public static boolean inBounds(int x, int y){
		if(x < 0 || y < 0){
			return false;
		}
		if(x > 9 || y > 11){
			return false;
		}
		return true;
	}
	public static int[][] convert(char[][] array){
		int[][] matrix = new int[10][12];
		
		for(int x = 0; x < 12; x++){
			for(int y = 0; y < 10; y++){
				switch(array[y][x]){
				case 'R':
					matrix[y][x] = 1;
					break;
				case 'Y':
					matrix[y][x] = 2;

					break;
				case 'B':
					matrix[y][x] = 3;
					break;
					
				}
			}
		}
		
		
		return matrix;
		
	}
	public static void shiftLeft2(int[][] grid, int c){
		
			for(int x = 0; x < 9; x++){
			
					if(grid[x+1][11] != -1 && grid[x][11] == -1){
						// swap columns
						for(int y = 0; y < 12; y++){
							swap(grid, x, y, x+1, y);
						}
						
						
					}
				
				
				
				
				
			}
			
		
	}
	
	public static int countOnes(int[][] list){
		int count = 0;
		for(int y = 0; y < 12; y++){
			for(int x = 0; x < 10; x++){
				if(list[x][y] != -1){
					count++;
				}
			}
		}
		return count;
	}
	
	public static void fillCol(int[][] list, int col){
		for(int x = 0; x < list[0].length; x++){
			list[col][x] = -1;
		}
		
		
	}
	public static void printArr(int[][] array){
		for(int x = 0; x < array[0].length; x++){
			for(int y = 0; y < array.length; y++){
				System.out.print(array[y][x] + "\t");
			}
			System.out.println();
		}
		
		System.out.println("\n");
	}
	public static void printBool(boolean[][] array){
		for(int x = 0; x < array[0].length; x++){
			for(int y = 0; y < array.length; y++){
				System.out.print(array[y][x] + "\t");
			}
			System.out.println();
		}
		
		
	}
}
class coor{
	int x, y;
	
	public coor(int xi, int yi){
		this.x = xi;
		this.y = yi;
	}
}

/*
3
RYBBRBYYRY
RRRBBBBBRR
YRRBRBBBBR
RYYBRYYRYY
BRBBRBRBRY
YYBYRBBRRB
RYBBBBRYYY
YBRBRBRYRB
RYBBBBBBBY
YBBRRRRRBB
RBBRRBRYRR
BBBRRYYYRR
h 10
j 1
g 2
3
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYBYYBBBBB
YYBYYBBBBB
c 2
c 12
g 1
2
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYYYYBBBBB
YYBYYBBBBB
YYBYYBBBBB
g 1
c 12
0








*/
