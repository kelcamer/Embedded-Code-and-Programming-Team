import java.util.ArrayList;
import java.util.Scanner;


public class traffic3 {

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
			print(matrix);
			int loop3 = scanny.nextInt();
			for(int z = 0; z < loop3; z++){
				boolean state = false;
				int temp = scanny.nextInt();
				ArrayList<Integer> listToLookAt = getListFromDestroyed(matrix, temp);
				// see if the first one in the list connects to all the others.
				//doesConnect(listToLookAt, matrix, matrix[listToLookAt.get(0)], listToLookAt.get(0), new boolean[grids+1]);
				DFS(listToLookAt.size(), matrix);
			/*	
				printList(listToLookAt);
				for(int a = 0; a < listToLookAt.size(); a++){
					state = false;
				//	System.out.println("Columns " + checkColumns(matrix, listToLookAt.get(a), temp) + " for " + listToLookAt.get(a));
				//	System.out.println("Rows " + checkRows(matrix, listToLookAt.get(a), temp)+ " for " + listToLookAt.get(a));

					if(checkColumns(matrix, listToLookAt.get(a), temp) && checkRows(matrix, listToLookAt.get(a), temp)){
						state = true;
					}
				//	System.out.println("State " + state);
					
				}
				
				if(state){
					System.out.println("no");
				}
				else{
					System.out.println("yes");
				}
				
				*/
			}
			
			/*for(int z = 0; z < loop3; z++){
				int temp = scanny.nextInt();
				if(ones(matrix, temp) == 1){
					System.out.println("no");
				}
				else{
					System.out.println("yes");
				}
			}*/
			
			
		}
		
		//ones(matrix);
		

	}
	public static void doesConnect(ArrayList<Integer> list, int[][] matrix, int[] rowAtFirst, int pos, boolean[] filled) {
		System.out.println("Entering Does Connect " + pos);
		filled[pos] = true;
		System.out.println(pos);
		 rowAtFirst = matrix[pos];
		for(int y  = 0; y < rowAtFirst.length; y++){
			//System.out.println("Hi");
			if(rowAtFirst[y] == 1){
				
				pos = y;
				System.out.println("Y " + y);
				if(!filled[pos] && pos < rowAtFirst.length)
				doesConnect(list, matrix, rowAtFirst, pos, filled);
			}
			
		}
		
		
		
		
		
		
	}
	public static void print(int[][] array){
		for(int x = 1; x < 6; x++){
			for(int y = 1; y < 6; y++){
				System.out.print(array[y][x] + " ");
			}
			System.out.println();
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
	public static boolean checkColumns(int[][] array, int placetocheck, int coltoskip){
	
		for(int q = 1; q < array.length; q++){
			
			if(array[q][placetocheck] == 1 && q!=coltoskip){
				// you found at least one 1 in the connector for 2 in the columns.
				return true;
			}
		}
		return false;
	}
	public static boolean checkRows(int[][] array, int placetocheck, int rowtoskip){
		
		for(int q = 1; q < array.length; q++){
			
			if(array[placetocheck][q] == 1 && q!=rowtoskip){
				// you found at least one 1 in the connector for 2 in the rows
				return true;
			}
		}
		return false;
	}
	public static void printList(ArrayList<Integer> list){
		for(int x = 0; x < list.size(); x++){
			System.out.println(list.get(x));
		}
	}

//perform a DFS on the graph G
	static void DFS(int size, int[][] matrix)
	{
		boolean[] filled=new boolean[size]; // a visited array to mark which vertices have been visited while doing the DFS
		
		int numComponets=0; // the number of components in the graph
		
		// do the DFS from each node not already visited
		for (int i=0; i<size; ++i)
			if (!filled[i])
			{
				++numComponets;
				System.out.printf("Starting a DFS for component %d starting at node %d%n",numComponets,i);
				
				DFS(i,filled, matrix, size);
			}
		
		System.out.println();
		System.out.printf("Finished with DFS - found %d components.%n", numComponets);
	}
	
	// perform a DFS starting at node at (works recursively)
	static void DFS(int at, boolean[] V, int[][] graph,int size)
	{
		System.out.printf("At node %d in the DFS%n",at);
		
		// mark that we are visiting this node
		V[at]=true;
		
		// recursively visit every node connected to this that we have not already visited
		for (int i=0; i<size; ++i)
			if (graph[at][i] == 1 && !V[i])
			{
				System.out.printf("Going to node %d...",i);
				DFS(at, V, graph, size);
			}
		
		System.out.printf("Done processing node %d%n", at);
	}
}


/*

1
3
2 2 3
2 1 3
2 1 2
1 3



1
5
1 2
4 1 3 4 5
1 2
1 2
1 2
2 1 2



1
3
2 2 3
2 1 3
2 1 2
2 2 1



Input will start with 2 grids the program will be accepting. 
Following that are 5 traffic descriptions.
Each traffic grid begins with 1 intersection in the city.
Following that are 2 intersections.
There are 4 roads that meet at that intersection.
2 is connected to 1, 3, 4, and 5.
Intersection numbers start at 1.
Test 1 and 2's collision.

2
5
1 2
4 1 3 4 5
1 2
1 2
1 2
2 1 2
5
1 2
2 1 3
2 2 4
2 3 5
1 4
2 4 5







2 = n number of traffic grids
----------------
5 = k intersections in the city
 1 roads that meet at  2
L = 4   1 3 4 5
L = 1   2
L = 1   2
L = 1   2
 
 
 2 test cases     destroy 1 then 2
---------------------
5
1 2
2 1 3
2 2 4
2 3 5
1 4
2 4 5



*/