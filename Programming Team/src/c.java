import java.util.Scanner;
//kelsey dev team 5

public class c {
	static String completeBin = "";
	static int mastercount = 0;
	static boolean[][] truefalse = null;
	static int cur_row = 0;
	static int cur_col = 0;
	/*
		A = 00001
		C = 00011
		M = 13 = 01101
	 */
	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
	
		for(int x = 0; x < loop; x++){
			truefalse = null;
			int row = scanny.nextInt();
			System.out.println("Row " + row);
			int col = scanny.nextInt();
			System.out.println("Col " + col);
			truefalse = new boolean[row][col];
			String word = scanny.next();
			System.out.println("Word " + word);
			for(int c = 0; c < word.length(); c++){
				char thisChar = word.charAt(c);
				int realNum = (int)thisChar - 64;
				
				String binary = convert(realNum);
			
				binary = fiveDig(binary);
				
				completeBin = completeBin + binary;
			}
			
			// place all into matrix
			
			char[][] matrix = new char[row][col];
			
			completeBin = fill(completeBin, row, col);
			// do until boolean array is filled
			
				for(int y = 0; y < completeBin.length(); y++){
					
				goRight(matrix, col);
			
				cur_col--;
				cur_row++;

				goDown(matrix, row);
				
				cur_row--;
				cur_col--;

				

				goLeft(matrix, col);
				cur_col++;
				cur_row--;
				
				goUp(matrix, row);
				
				cur_row++;
				cur_col++;
		
				
				if(isDone(row-1, col-1)){
					//truefalse = null;
					x++;
				}
				
				}
				
				
			
				System.out.print((x+1) + " ");
				printArray(matrix);
			}
			
		//}
	}
	public static String fiveDig(String num){
		
		while(num.length() != 5){
			num = "0" + num;
		}
		return num;
		
	}
	public static void p(){
		System.out.println(" at row " + cur_row + " col " + cur_col);
	}
	public static String convert(int num){
		int rem = -99;
		String word = "";
		while(num!=0){
			rem = num%2;
			num/=2;
			word = rem + word;
		}
		
		return word;
	}
	public static void printArray(boolean g[][]){
		System.out.println();
		for(int y = 0; y < g.length; y++){
		for(int x = 0; x < g[0].length; x++){
			//	System.out.print(g[y][0]);
				System.out.print(g[y][x] + " ");
			}
		System.out.println();
		}
	}
	public static void printArray(char g[][]){
	// 	System.out.println();
		for(int y = 0; y < g.length; y++){
		for(int x = 0; x < g[0].length; x++){
			//	System.out.print(g[y][0]);
				System.out.print(g[y][x]);
			}
	//	System.out.println();
		}
	}
	// 0000110100101100 Right one
	// wrong one 00001  01  00101
	public static void goRight(char[][] grid, int totalcol){
		for(int x = cur_col; x < totalcol; x++){
			if(!truefalse[cur_row][cur_col]){
				if(mastercount < completeBin.length()){
				grid[cur_row][cur_col] = completeBin.charAt(mastercount);
				truefalse[cur_row][cur_col] = true;
				cur_col = x+1;
				mastercount++;
				}
			}
			else{
			
				break;
			}
		}
		
		
	}
	
		
	
	public static void goDown(char[][] grid, int totalrow){
		for(int x = cur_row; x < totalrow; x++){
			//System.out.println(cur_row + " " + cur_col);
			if(!truefalse[cur_row][cur_col]){
				if(mastercount < completeBin.length()){
				grid[cur_row][cur_col] = completeBin.charAt(mastercount);
				mastercount++;
				truefalse[cur_row][cur_col] = true;
				cur_row = x+1;
				}
			}
			else{
				
				break;
			}
		}
		
	}
	public static void goLeft(char[][] grid,  int totalcol){
		// keep row constant
		// decrease col I AM HERE
		for(int x = cur_col; x >= 0; x--){
			if(!truefalse[cur_row][cur_col]){
				if(mastercount < completeBin.length())
				{
				grid[cur_row][cur_col] = completeBin.charAt(mastercount);
				truefalse[cur_row][cur_col] = true;
				
				cur_col = x-1;
				mastercount++;
				}
			}
			else{
				
				break;
			}
			
			
		}
	
		
	}
	public static void goUp(char[][] grid, int totalcol){
		for(int x = cur_row; x >= 0; x--){
			if(!truefalse[cur_row][cur_col]){
				if(mastercount < completeBin.length()){
				grid[cur_row][cur_col] = completeBin.charAt(mastercount);
				truefalse[cur_row][cur_col] = true;
				cur_row = x-1;
				mastercount++;
				}
			}
			else{
				break;
			}
			
			
		}
		
		
	}
	public static String fill(String word, int r, int c){
		while(word.length() != r*c){
			word = word + "0";
		}
		
		return word;
		
	}
	public static void check(){
		if(cur_row == 4){
			cur_row = 3;
		}
		if(cur_row < 0){
			cur_row = 0;
		}
		if(cur_col == 4){
			cur_col = 3;
		}
		if(cur_col < 0){
			cur_col = 0;
		}
	}
	public static boolean isDone(int row, int col){
		
		
		for(int y = 0; y < col; y++){
		for(int x = 0; x < row; x++){
			if(truefalse[row][col] == false){
				return false;
			}
			}
		
		}
		return true;
		
		
	}
}
/*

4
4 4 ACM
5 2 HI
2 6 HI
5 5 HI HO




1 0000110100101100
2 0110000010
3 010000001001
4 0100001000011010110000010



for(int q = 0; q < row; q++){
curx = q;
matrix[q][cury] = completeBin.charAt(mastercount);
truefalse[q][cury] = true;
mastercount++;
}
for(int q = col-1; q >=0; q++){
cury = q;
System.out.println(cury);
matrix[curx][q] = completeBin.charAt(mastercount);
truefalse[curx][q] = true;
mastercount++;
}
for(int q = 0; q < row-2; q++){
curx = q;
matrix[q][cury] = completeBin.charAt(mastercount);
truefalse[q][cury] = true;
mastercount++;
}
for(int q = col-2; q >=0; q++){
cury = q;
matrix[curx][q] = completeBin.charAt(mastercount);
truefalse[curx][q] = true;
mastercount++;
}
*/