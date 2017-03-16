import java.util.Scanner;

public class prob3 {
public static int[] dx = {0,-1,1,0};
public static int[] dy = {-1, 0, 0, 1};
	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int rows = scanny.nextInt();
		int cas = 1;
		while(rows!=0){
		int columns = scanny.nextInt();
		char[][] grid = new char[columns][rows];
		// Rows = left / right, columns = up/down
		for(int x = 0; x < columns; x++){
			String line = scanny.next();
			for(int y = 0; y < rows; y++){
				grid[x][y] = line.charAt(y);
			}
		}
		simplify(grid, 0);
		int count = 0;
		for(int x = 0; x < columns; x++){
			for(int y = 0; y < rows; y++){
				if(grid[x][y] != ' '){
					count++;
				}
			}
		}
		System.out.println(cas + ": " + count);
		
		cas++;
		rows = scanny.nextInt();
		}
		
	}


private static void simplify(char[][] grid, int count) {
	//print(grid);
	if(count == grid.length){
		return;
	}
	boolean[][] filled = new boolean[grid.length][grid[0].length];
	for(int x = 0; x < grid.length; x++){
		for(int y = 0; y < grid[0].length; y++){
			int numChain = countNumberInChain(grid, x, y, 0, grid[x][y], new boolean[grid.length][grid[0].length]);
		//	System.out.println("NUMCHAIN " + numChain + " for " + grid[x][y]) ;
			if(numChain >= 4){
				floodfill(grid, x, y, grid[x][y],  filled);
				
			}
		}
	}
	//print(grid);
	gravity(grid, 0);
	simplify(grid, count+1);
	}


public static int countNumberInChain(char[][] grid, int x, int y, int count, char c, boolean[][] filled){

	for(int a = 0; a < dx.length; a++){
		
		int nextx = x + dx[a];
		int nexty = y + dy[a];
		if(inBounds(nextx, grid.length) && inBounds(nexty, grid[0].length) && !filled[nextx][nexty]){
			if(grid[nextx][nexty] == c){
				count++;
				filled[nextx][nexty] = true;
				return countNumberInChain(grid, nextx, nexty, count, c, filled);
			}
			
			
		}
	}
	return count;
}
public static void floodfill(char[][] grid, int x, int y, char c, boolean[][] filled){

	for(int a = 0; a < dx.length; a++){
		
		int nextx = x + dx[a];
		int nexty = y + dy[a];
		if(inBounds(nextx, grid.length) && inBounds(nexty, grid[0].length)){
			if(grid[nextx][nexty] == c && !filled[nextx][nexty]){
				grid[nextx][nexty] = ' ';
				filled[nextx][nexty] = true;
				floodfill(grid, nextx, nexty, c, filled);
			}
		}
	}
	
}
public static void gravity(char[][] grid, int count){
	if(count == grid.length)return;
	for(int x = grid.length-1; x>=1; x--){
		for(int y = 0; y < grid[0].length; y++){
			//System.out.println("x " + x + " y " + y);
			if(grid[x][y] == ' ' && grid[x-1][y] != ' '){
				char temp = grid[x][y];
				grid[x][y] = grid[x-1][y];
				grid[x-1][y] = temp;
				gravity(grid, count+1);
			}
		}
	
	}
}
private static boolean inBounds(int nextx, int max) {
	// TODO Auto-generated method stub
	if(nextx < 0 || nextx >= max){
		return false;
	}
	return true;
}
public static void print(char[][] grid){
	for(int x = 0; x < grid.length; x++){
		for(int y = 0; y < grid[0].length; y++){
			System.out.print(grid[x][y] + " ");
		}
		System.out.println();
		}
	System.out.println();
}
}
/*
7 9 
YOOGYBY 
GRGYBbB 
OGRGOBY 
BBGBRRB 
YRYRROR 
BOYBOBO 
YGBYBBG 
OOBYBRG 
BBYRRBO
*/