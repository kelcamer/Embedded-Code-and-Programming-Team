import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// still not working
public class dan {
	public static final int oo = 9999999;
// Sawyer is double the speed of dan so you have to check the final distances at the end and half them.
	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int target = 1;
		int loop = scanny.nextInt();
		for(int x = 0; x < loop; x++){
			int numofnod = scanny.nextInt();
			node[] list = new node[numofnod+1];
			for(int y = 1; y < numofnod+1; y++){
				list[y] = new node(y);
				list[y].target = oo;
			}
			int numedge = scanny.nextInt();
			int dan = scanny.nextInt();
			int saw = scanny.nextInt();
			
			for(int z = 0; z < numedge; z++){
				int from = scanny.nextInt();
				int to = scanny.nextInt();
				int weight = scanny.nextInt();
				
				
				
				list[from].edgelist.add(new edge(list[to], weight));
				list[to].edgelist.add(new edge(list[from], weight));
				if(from == target){
					if(weight < list[to].target){
						list[to].target = weight;
					}
				}
				if(to == target){
					if(weight < list[from].target){
						list[from].target = weight;
					}
				}
				
			//	System.out.println(from + " " + to + " " + list[to].target);
				
			}
			list[1].target = 0;
			
			for(int y = 1; y < list.length; y++){
			updateTarget(list[y]);
			}
			//System.out.println(list[2].target);
			
			
			boolean state = true;
			// true means saw wins false means dan wins
			
			if(list[dan].target*2 < list[saw].target){
				state = false;
			}
			
		/*	for(int a = 1; a < list.length; a++){
				System.out.println("Target for node " + list[a].num + " is " + list[a].target);
			}*/
			
			if(state){
				System.out.println("Sawyer’s style is undeniable!");
			}
			else{
				System.out.println("I can’t believe Danny won!");
			}
			
		}
	}
	public static node updateTarget(node cur){
		Queue<node> q = new LinkedList<node>();
		q.offer(cur);
		
		while(!q.isEmpty()){
			node mynode = q.poll();
			
			for(edge thisedge: mynode.edgelist){
				//System.out.println("Current edge from " + mynode.num + " to " + thisedge.dest.num);
				//System.out.println("Comparing " + (int)(mynode.target + thisedge.weight) + " to " + thisedge.dest.target);
				if(mynode.target + thisedge.weight < thisedge.dest.target){
					q.remove(thisedge.dest);
					thisedge.dest.target = mynode.target + thisedge.weight;
				//	System.out.println("\nChoosing " + thisedge.dest.target);
					q.offer(thisedge.dest);
				}
			}
		}	
		return cur;
	}

}

/*
3 1 2 3
1 2 5
3 1 3 2
1 2 5




*/


