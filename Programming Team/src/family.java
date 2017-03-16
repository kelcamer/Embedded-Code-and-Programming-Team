import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class family {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = 0;
		int caseNum = 1;

		loop = scanny.nextInt();
		while(loop!=0){
			// hash map to store the indices of names.
			HashMap<Integer, String> hash = new HashMap<Integer, String>();
			HashMap<String, Integer> hash2 = new HashMap<String, Integer>();


			int[][] adj_matrix = new int[loop*3][loop*3];
			int c = 0;
			for(int x = 0; x < loop; x++){
				String parent = scanny.next();
				String parent2 = scanny.next();
				String child2 = scanny.next();
				int ind1 = -1, ind2 = -1, ind3 = -1;
				if(!hash.containsValue(parent)){
					hash.put(c, parent);
					hash2.put(parent, c);
					/*** Assign ind1 here ***/
					ind1 = c;
					c++;

				}
				else{
					ind1 = hash2.get(parent);
				}
				if(!hash.containsValue(parent2)){
					hash.put(c, parent2);
					hash2.put(parent2, c);
					/*** Assign ind2 here ***/
					ind2 = c;
					c++;
				}
				else{
					ind2 = hash2.get(parent2);
				}
				if(!hash.containsValue(child2)){
					hash.put(c, child2);
					hash2.put(child2, c);
					/*** Assign ind3 here ***/
					ind3 = c;
					c++;
				}
				else{
					ind3 = hash2.get(child2);
				}

				/*** Here is where your error was...you can't assume that all 3 were new.
				if(ind1 == -1){
					ind1 = c-3;
				}
				if(ind2 == -1){
					ind2 = c-2;
				}
				if(ind3 == -1){
					ind3 = c-1;
				}
				***/

				adj_matrix[ind3][ind2] = 1;
				adj_matrix[ind2][ind3] = 1;
				adj_matrix[ind3][ind1] = 1;
				adj_matrix[ind1][ind3] = 1;
			}

			/*** Read in input for search ***/
			String person1 = scanny.next();
			String person2 = scanny.next();

			// Just in case they try to be tricky and ask us about people not in the tree.
			if (!hash2.containsKey(person1) || !hash2.containsKey(person2))
				System.out.println("Family #"+caseNum+": Not related.");

			else {

				/*** This array is 1 D, either I have been to a vertex or I haven't been. I don't care about visiting edges... ***/
				boolean[] visit = new boolean[adj_matrix.length];

				/*** Run one DFS, not many. Can you see why??? In the sample code, we wanted to mark all connected components.
				     In this problem we don't. We just care to see who we can reach from person1.
				***/
				DFS(adj_matrix, hash2.get(person1), visit);

				// Output result.
				if (visit[hash2.get(person2)])
					System.out.println("Family #"+caseNum+": Related.");
				else
					System.out.println("Family #"+caseNum+": Not related.");
			}

			// Get to the next case.
			loop = scanny.nextInt();
			caseNum++;
		}
		scanny.close();
	}

	static void DFS(int[][] matrix,int x, boolean[] visited){

		/*** This is ALWAYS what we do in a DFS, mark the node before anything else ***/
		visited[x] = true;

		for(int a = 0; a < matrix.length; a++){

			/*** We only want to go if an edge exists, namely if matrix[x][a] == 1 ***/
			if(matrix[x][a] == 1 && !visited[a]){

				/*** We've already visited x, the edge goes from x to a, so we want to run the DFS from a, not x. ***/
				DFS(matrix, a, visited);
			}
		}
	}
}




/*
3
Marcie Chip Kelsey
Marcie Chip Amanda
Kelsey Florian John


0 0 1 1 0 1
0 0 1 1 0 1
1 1 0 0 0 1
1 1 0 0 0 0
0 0 0 0 0 1
1 1 1 0 1 0

*/