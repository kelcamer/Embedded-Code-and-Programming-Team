import java.util.ArrayList;
import java.util.Scanner;

/*
 * Kelsey Cameron
 * October 6, 2015
 */
public class bunnies {
	public static final int dx[] = { -1, 0, 0, 1 };
	public static final int dy[] = { 0, -1, 1, 0 };
	
	public static int state = 0;

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		ArrayList<String> outfin = new ArrayList<String>();
		for (int x = 0; x < loop; x++) {
		
			int numLines = scanny.nextInt();
			int numChar = scanny.nextInt();

			char array[][] = new char[numLines][numChar];
			int numGrid[][] = new int[numLines][numChar];
			int indexofPx = 0, indexofPy = 0;
			for (int y = 0; y < numLines; y++) {
				array[y] = scanny.next().toCharArray();
			}
			for (int y = 0; y < numLines; y++) {
				for (int a = 0; a < numChar; a++) {
					
					if (array[y][a] == 'P') {
						indexofPx = y;
						indexofPy = a;
					}

					numGrid[y][a] = 0;
				}
			
			}

			boolean output = floodfill(numGrid, indexofPx, indexofPy, array);
			if(output == true){
			
				outfin.add("yes");
			}
			else{
				outfin.add("no");
			
			}

		}

		for(int y = 0; y < outfin.size(); y++){
			System.out.println(outfin.get(y));
		}
		
	}

	public static boolean inBounds(int x, int y, int maxx, int maxy) {
		if (x >= 0 && x < maxx && y >= 0 && y < maxy) {
			return true;
		}
		return false;

	}

	public static boolean isValid(char letter, int num) {

	
		if (letter != '#' && num != 1) {
			return true;
		}
		return false;
	}

	
	public static boolean floodfill(int grid[][], int x, int y, char[][] chargrid) throws StackOverflowError{
		boolean flag = false;
		grid[x][y] = 1;

		for (int a = 0; a < dx.length; a++) {
			int nextx = x + dx[a];
			int nexty = y + dy[a];

				if (inBounds(nextx, nexty, chargrid.length, chargrid[0].length)
						&& isValid(chargrid[nextx][nexty], grid[nextx][nexty])) {
				if (chargrid[nextx][nexty] == 'C') {
					flag = true;
				} 
			
				flag |= floodfill(grid, nextx, nexty, chargrid);
			}
				
				

		}
		return flag;
	}

}

