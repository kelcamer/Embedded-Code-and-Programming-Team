import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class node_practice {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int max = scanny.nextInt();
		while(max!=0){
			node[] list = new node[max];
			for(int x = 0; x < max; x++){
				list[x] = new node(x);
			}
			int relations = scanny.nextInt();
			
			for(int x = 0; x < relations; x++){
				int a = scanny.nextInt()-1;
				int b = scanny.nextInt()-1;
				list[b].inDegree++;
				list[a].outEdges.add(new edge(list[b]));
			}
			
			DFS(list);
		}
		
		
	}
	public static void DFS(node[] list){
	//	for(int x = 0; x <list.length; x++){
			System.out.println("Starting at node " + (0+1));
			DFS(0, new boolean[list.length], list);
		//}
	}
	private static void DFS(int loc, boolean[] filled, node[] list) {
		filled[loc] = true;
		System.out.println("At node " + (loc+1));
		for(int x = 0; x < list[list[loc].value].outEdges.size(); x++){
			
			if(!filled[x]){
				System.out.println("Going to node " + (x+1));
				DFS(x, filled, list);
			}
			
		}
		
		
	}

}
class node{
	int value;
	int inDegree;
	ArrayList<edge> outEdges;
	
	public node(int v){
		this.value = v;
		outEdges = new ArrayList<edge>();
	}
	public node(int v, ArrayList<edge> out){
		this.outEdges = out;
	}
	
}
//Stores where an edge is going to and its weight.
class edge{

	node dest;

 public edge(node d) {
	 this.dest = d;
 }

 
}
