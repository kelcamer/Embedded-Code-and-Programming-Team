import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class floyd {
	public static void main(String[] args){
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		int cas = 1;
		for(int x = 0; x < loop; x++){
			int numloc = scanny.nextInt();
		
			int[] locations = new int[numloc];
			for(int y = 0; y < numloc; y++){
				locations[y] = scanny.nextInt();
				
			}
			int[][] matrix = new int[numloc][numloc];
			int paths = scanny.nextInt();
			int large = Integer.MAX_VALUE;
			for(int x1 = 0; x1 < matrix.length; x1++){
				for(int y = 0; y < matrix.length; y++){
					if(matrix[x1][y] == 0 && x1!=y)
						matrix[x1][y] = large;
				}
			}

		
			scanny.nextLine();
			
			for(int y = 0; y < paths; y++){
		
				StringTokenizer tok = new StringTokenizer(scanny.nextLine(), "() ,\t");
				int firstnum = Integer.parseInt(tok.nextToken());
				int secondnum = Integer.parseInt(tok.nextToken());
				if((firstnum-1)!=(secondnum-1)){
				matrix[firstnum-1][secondnum-1] = Math.abs(locations[firstnum-1] - locations[secondnum-1]);
				matrix[secondnum-1][firstnum-1] = matrix[firstnum-1][secondnum-1];
				}
			}
				

			for(int k = 0; k < matrix.length; k++){
				for(int i = 0; i < matrix.length; i++){
					for(int j = 0; j < matrix.length; j++){
						
						if(matrix[i][j] > matrix[i][k] + matrix[k][j] && matrix[i][k] < large && matrix[k][j] < large){
							matrix[i][j] = matrix[i][k] + matrix[k][j];
							matrix[j][i] = matrix[i][j];
						}
					}
				}
			}
			

			ArrayList<Integer> choices = new ArrayList<Integer>();
			int sum = 0;
			int choi = scanny.nextInt();
			for(int a = 0; a < choi; a++){
				choices.add(scanny.nextInt());
			}
			for(int y = 0; y < choices.size()-1; y++){
			
				sum+=matrix[choices.get(y)-1][choices.get(y+1)-1];
			}
			System.out.println("The least amount of work on trip " + cas+ " is: " + sum);
			
			
			
			cas++;
		}
	}

public static void printArray(int[][] array){
	for(int x = 0; x < array.length; x++){
		for(int y = 0; y < array[0].length; y++){
			System.out.print(array[y][x] + " ");
		}
		System.out.println();
	}
	System.out.println();
}
}
