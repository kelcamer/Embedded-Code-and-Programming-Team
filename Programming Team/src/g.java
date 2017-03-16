import java.util.Scanner;

public class g {
	static char[][] grid;

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int rowcol = scanny.nextInt();
		while (rowcol != 0) {
			grid = new char[rowcol][rowcol];
			for (int y = 0; y < rowcol; y++) {
				grid[y] = scanny.next().toCharArray();
			}

			searchH(rowcol);
			searchV(rowcol);
			rowcol = scanny.nextInt();
		}

	}
/*
 * Kelsey,
 
I downloaded your last submission in contest and debugged it.
 
I am attaching the corrected code.
 
My main feedback is as follows: you overcomplicated the solution.
 
To fix it, I started deleting things and then I carefully put
 in only the logic that was helping solve the problem. All you 
 care about is count and printedalready. I do the minimum amount
  of work to maintain these two things.
 
Finally at the end, we want to see if we ended on a stretch of Xs
. So, we just add count to the list if printedalready is 0. The 
only exception is when count is 0 AND we already have other 
streaks going. So, the if statement is as follows:
 
                                if (printedalready == 0 && (result.length() == 0 || count > 0)) {
                                                result = result + count;
                                }
 
I think this is a pattern of yours. You overcomplicate things a little bit and add extra variables to a solution that aren’t serving a clear purpose. Solve only the subproblems that need to be solved and don’t use any more variables than necessary.
 */
	public static void getNum(char[] grid2) {
		int count = 0;
		
		int printedalready = 0;

		String result = "";

		for (int x = 0; x < grid2.length; x++) {

			if (grid2[x] == 'X') {
				
					count++;
					printedalready = 0;
	
			}

			if (grid2[x] == '.') {
				if (x != 0 && count!=0) {
					result = result + count + " ";
					printedalready = 1;
					count = 0;
				}
			
			}
		}

		if (printedalready == 0 && (result.length() == 0 || count > 0)) {
			result = result + count;
		}
		if(result.charAt(result.length()-1) == ' '){
			result = result.substring(0, result.length()-1);
		}
		System.out.println(result);
	}

	public static void searchH(int dim) {
		for (int x = 0; x < dim; x++) {
			getNum(grid[x]);
		}

	}

	public static void searchV(int dim) {
		for(int x = 0; x < dim; x++){
			char[] temp = new char[dim];
			for(int y = 0; y < dim; y++){
				temp[y] = grid[y][x];
			}
			getNum(temp);

		}




	}
	/*
3
XXX
.XX
.X. 
3 X.X 
..X X..
5
..X..
.XXX.
X.X.X
..X..
..X..
0
*/
	 
}
