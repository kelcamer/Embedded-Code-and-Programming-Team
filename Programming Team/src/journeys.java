import java.util.Arrays;
import java.util.Scanner;

public class journeys {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int numoftown = scanny.nextInt();
		int loop = scanny.nextInt();
		int bitcity = scanny.nextInt();
		int[][] matrix = new int[numoftown+1][numoftown+1];
		for (int[] row: matrix){
		    Arrays.fill(row, Integer.MAX_VALUE);
		}
		for(int x = 0; x < loop; x++){
		
			int[] connectors = new int[4];
			for(int y = 0; y < 4; y++){
				connectors[y] = scanny.nextInt();
			}
			
			// you may only need one of these connectors not two
			matrix[connectors[0]][connectors[2]] = 1;
			matrix[connectors[2]][connectors[0]] = 1;
			matrix[connectors[1]][connectors[3]] = 1;
			matrix[connectors[3]][connectors[1]] = 1;
			
			
		}
		
		
		for(int h = 1; h < numoftown; h++){
		if(h == bitcity){
			System.out.println(0);
			break;
		}
		else if(matrix[bitcity][h] == 1){
			System.out.println(1);
		}
		else{
			System.out.println(getMin(bitcity, h, matrix, 0));
			
			
			
		}
			
			
		}
		
		
		
		
	}
	public static int getMin(int from, int to, int matrix[][], int count){
		System.out.println("From " + from + " to " + to);
		if(from == to){
			return count;
		}
		int small = Integer.MAX_VALUE;
		for(int x = 1; x < matrix[from].length; x++){
			if(matrix[from][x] == 1){
				count+= getMin(x, to, matrix, count+1);
				
			}
			if(count < small){
				small = count;
			}
			count = 0;
		}
		
		
		
		
		return small;
	}

}
/*
5 3 4
1 2 4 5
5 5 4 4
1 1 3 3
*/
