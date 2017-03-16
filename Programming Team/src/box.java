import java.util.ArrayList;
import java.util.Scanner;

/*
 * Kelsey Cameron
 * October 6, 2015
 */
public class box {
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
					
					if (array[y][a] == 'B') {
						indexofPx = y;
						indexofPy = a;
					}

					numGrid[y][a] = 0;
				}
			
			}

		//	ref output_tele = floodfill_tele(numGrid, indexofPx, indexofPy, array, 0, false);
			int c = floodfill(numGrid, indexofPx, indexofPy, array, 0);
			System.out.println("Normal count " + c);
		//	System.out.println("Tele count " + output_tele.count+1);
			
		//	System.out.println(Math.min(output.count, output_tele.count));
			
			
		

		}

		
	}

	public static boolean inBounds(int x, int y, int maxx, int maxy) {
		if (x >= 0 && x < maxx && y >= 0 && y < maxy) {
			return true;
		}
		return false;

	}

	public static boolean isValid(char letter, int num) {

		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int x = 1; x < 10; x++){
			numbers.add(x);
		}
		if (letter != '#' && num != 1 && !numbers.contains(letter)) {
			return true;
		}
		return false;
	}

	
	public static int floodfill(int grid[][], int x, int y, char[][] chargrid, int count) throws StackOverflowError{
		boolean flag = false;
		grid[x][y] = 1;

		for (int a = 0; a < dx.length; a++) {
			int nextx = x + dx[a];
			int nexty = y + dy[a];

				if (inBounds(nextx, nexty, chargrid.length, chargrid[0].length)
						&& isValid(chargrid[nextx][nexty], grid[nextx][nexty])) {
				if (chargrid[nextx][nexty] == 'X') {
					flag = true;
				} 
				//System.out.println(count);
				count = floodfill(grid, nextx, nexty, chargrid, count++);
				
				
				
				}
				//else{
				//	count--;
				//}
				
				

		}
		
		count++;
		
		return count;
	}
	/*
	public static ref floodfill_tele(int grid[][], int x, int y, char[][] chargrid, int count, boolean seenOne) throws StackOverflowError{
		boolean flag = false;
		grid[x][y] = 1;

		for (int a = 0; a < dx.length; a++) {
			int nextx = x + dx[a];
			int nexty = y + dy[a];

				if (inBounds(nextx, nexty, chargrid.length, chargrid[0].length)
						&& isValid(chargrid[nextx][nexty], grid[nextx][nexty])) {
				if (chargrid[nextx][nexty] == 'X') {
					flag = true;
				} 
				if(chargrid[nextx][nexty] == '1' && seenOne == false){
					seenOne = true;
					nexty+=1;
					while(chargrid[nextx][nexty] != 1){
						nextx++;
					}
				}
				
				ref next = floodfill(grid, nextx, nexty, chargrid, count++);
				flag |= next.flag;
				count += next.count;
				
			}
				
				

		}
		ref send = new ref(flag, count);
		
		return send;
	}
*/
}

/*
1
5 5
####B
.....
#.##1
..###
...1x




*/
