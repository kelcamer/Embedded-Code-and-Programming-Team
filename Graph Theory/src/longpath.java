import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class longpath {
	// start at 0 go to numofnod-1
	public final static int oo = Integer.MAX_VALUE;
	public static void main(String[] args) {
			Scanner scanny = new Scanner(System.in);
			int loop = scanny.nextInt();
			for(int x = 0; x < loop; x++){
				int numofnod = scanny.nextInt();
				node[] list = new node[numofnod+1];
				node[] list2 = new node[numofnod+1];
				for(int z = 0; z < list.length; z++){
					list[z] = new node(z);
					list2[z] = new node(z);
					if(list[z].num == 0){
						list[z].target = 0;
					}
					else{
						list[z].target = oo;
					}
				}
				int numofedge = scanny.nextInt();
				
				for(int y = 0; y < numofedge; y++){
					int from = scanny.nextInt();
					int to = scanny.nextInt();
					int weight = scanny.nextInt();
					if(!list[from].edgelist.contains(list[to])){						
						list[from].add(new edge(list[to], weight));
					}
					else{
						if(weight < list[from].edgelist.get(list[from].edgelist.indexOf(list[to])).weight){
							list[from].edgelist.get(list[from].edgelist.indexOf(list[to])).weight = weight;
						}
					}
					if(!list2[from].edgelist.contains(list2[to])){
						list2[from].add(new edge(list2[to], weight));
					}
					else{
						if(weight > list[from].edgelist.get(list[from].edgelist.indexOf(list[to])).weight){
							list[from].edgelist.get(list[from].edgelist.indexOf(list[to])).weight = weight;
						}
					}
					
				}
				
				for(int a = 0; a < numofnod; a++){
					
					updateShortestDistance(list[a]);
				}
				System.out.print(list[numofnod-1].target + " ");
				for(int a = 0; a < numofnod; a++){
					
					updateLongestDistance(list2[a]);
				}
				System.out.println(list2[numofnod-1].target);
			}
			scanny.close();
	}

	private static void updateShortestDistance(node cur) {
		Queue<node> q = new LinkedList<node>();
		q.offer(cur);
		
		while(!q.isEmpty()){
			node thisnode = q.poll();
			for(edge e: thisnode.edgelist){
				if(thisnode.target + e.weight < e.dest.target && thisnode.target != oo){
					q.remove(e.dest);
					e.dest.target = thisnode.target + e.weight;
					q.offer(e.dest);
					
				}
		
				
			}
			
		}
			
			
		}
		private static void updateLongestDistance(node cur) {
			Queue<node> q = new LinkedList<node>();
			q.offer(cur);
			
			while(!q.isEmpty()){
				node thisnode = q.poll();
				for(edge e: thisnode.edgelist){
					if(thisnode.target + e.weight > e.dest.target && e.dest.target!=oo){
						q.remove(e.dest);
						e.dest.target = thisnode.target + e.weight;
						q.offer(e.dest);
						
					}
			
					
				}
				
				
				
				
			}
		/*
2
6 7
0 1 1 
1 2 2 
2 5 3 
1 5 6 
0 3 5 
3 4 6 
4 5 7
3 3 
0 1 7
0 2 19 
1 2 8
		 */
		
	}

}
