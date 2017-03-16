import java.util.ArrayList;
import java.util.Scanner;

public class traffic2 {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		// java automatically fills array
		
		for(int x = 0; x < loop; x++){
			int matrix[][] = new int[51][51];
			System.out.println("City #" + (x+1));
			int grids = scanny.nextInt();
			
			// y is your current intersection
			// The matrix must be symmetric because it's a road - it goes both ways.
			for(int y = 1; y < grids+1; y++){
				int loop2 = scanny.nextInt();
				for(int z = 1; z < loop2+1; z++){
					int temp = scanny.nextInt();
					matrix[y][temp] = 1;
				}
				
				}
			//printList(matrix);
			int loop3 = scanny.nextInt();
			for(int z = 0; z < loop3; z++){
				boolean state = false;
				int temp = scanny.nextInt();
			//	System.out.println("Destroying node " + temp);
				ArrayList<Integer> listToLookAt = getListFromDestroyed(matrix, temp);
				boolean flag = false;
				boolean[][] filled = new boolean[matrix[0].length][matrix.length];
				for(int q = 0; q < listToLookAt.size(); q++){
					
				//	System.out.println("------------------------------");
					
				//	System.out.println("Searching at node " + listToLookAt.get(q));
					flag |= interferes(listToLookAt.get(q), matrix, filled, false, temp, 0);
					
				}
				//System.out.println("Flag " + flag);
				
				if(flag){
					System.out.println("yes");
				}
				else{
					System.out.println("no");
				}
			}
		}
	}
	/*
1
5
1 2
4 1 3 4 5
1 2
1 2
1 2
2 1 2
	 */
	public static void print(String message){
		System.out.println(message);
	}
	public static void printList(int[][] grid){
		for(int x = 1; x < 6; x++){
			for(int y = 1; y < 6; y++){
				System.out.print(grid[y][x]);
			}
			System.out.println();
			
			
		}
	}
	
	// if I do not find any 1's at all, I return false.  If I do, I return true
public static boolean interferes(int num, int[][] graph, boolean[][] filled, boolean flag, int orig, int state){
	
	for(int x = 0; x < graph[num].length; x++){
		//System.out.println("Node " + x + " has a value of " + graph[num][x]);
		if(graph[num][x] == 1 && x!=num && !filled[num][x] && x!=orig){
			//System.out.println("Going to node " + x);
			filled[num][x] = true;
		//	System.out.println("FLAG EACH " + flag);
			state = 1;
			flag |= interferes(x, graph, filled, flag, orig, state);
		//	System.out.println("Result of function " + flag);
			//return true;
			
		}
				
	}
	
	
	
	
	return state==1?false:true;
}
public static void print(int[][] array){
	for(int x = 1; x < 6; x++){
		//for(int y = 1; y < 6; y++){
			System.out.print(array[x][1] + " ");
		//}
		//System.out.println();
	}
	System.out.println();
	for(int x = 1; x < 6; x++){
		//for(int y = 1; y < 6; y++){
			System.out.print(array[1][x] + " ");
		//}
		//System.out.println();
	}
	
	
}
public static ArrayList<Integer> getListFromDestroyed(int[][] array, int placetocheck){
	ArrayList<Integer> listToCheck = new ArrayList<Integer>();
	for(int q = 1; q < array.length; q++){
		
		if(array[q][placetocheck] == 1){
			// list of roads that will be affected from destroying this one.
			listToCheck.add(q);
		}
	}
	return listToCheck;
	
}
}
/*
1
5
1 2
2 1 3
2 2 4
2 3 5
1 4
2 4 5




*/