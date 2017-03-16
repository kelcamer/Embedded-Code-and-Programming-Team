import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class readgraph {

    public static int v;
    public static ArrayList[] graph;

    public static void main(String[] args) throws FileNotFoundException {

    	File io = new File("graph.in");
        Scanner stdin = new Scanner(io);
        v = stdin.nextInt();
        ArrayList<Integer> names = new ArrayList<Integer>();
        graph = new ArrayList[v];
        for (int i=0; i<v; i++)
            graph[i] = new ArrayList<Integer>();

        int e = stdin.nextInt();
        for (int i=0; i<e; i++) {
            int v1 = stdin.nextInt();
            int v2 = stdin.nextInt();
            if(!names.contains(v1)){
            	names.add(v1);
            }
            if(names.contains(v2)){
            	names.add(v2);
            }
            graph[v1].add(v2);
            

            // Do this only if undirected...
            graph[v2].add(v1);
        }
        
       // DFS(graph);
        BFS(graph, names);
        
        
    }

	private static void BFS(ArrayList[] graph2, ArrayList<Integer> names) {
		boolean[] filled = new boolean[graph2.length];
		// TODO Auto-generated method stub
		//for(int a = 0; a < graph2.length; a++){
	
		System.out.println("At node " + names.get(1));
			BFS(graph2, 1, filled, names);
		//}
		
	}

	private static void BFS(ArrayList[] graph, int loc, boolean[] filled, ArrayList<Integer> names) {
		if(graph[loc].size() <=0){
			return;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(loc);
		while(!q.isEmpty()){
			int num =  q.poll();
			for(int x = 0; x < graph[num].size(); x++){
				if(!filled[x]){
					filled[x] = true;
					q.offer(x);
					System.out.println("Adding node " + graph[num].get(x));
				}
				
			}
		}
		
		
	}

	private static void DFS(ArrayList[] graph2, ArrayList<Integer> names) {
		for(int x = 0; x < graph2.length; x++){
			System.out.println("At node " + x + " in the DFS " + graph[x]);
			DFS(x, new boolean[graph2.length], graph2[x], names);
			
		}
		
		
	}

	private static void DFS(int x, boolean[] filled, ArrayList list, ArrayList<Integer> names) {
			filled[x] = true;
			System.out.println("Starting at " + names.get(x));
			
			for(int i = 0; i < list.size(); i++){
				if(!filled[i]){
					System.out.println("Going to " + list.get(i));
				DFS(i, filled, list, names);
				}
			}
			System.out.println("Done processing " + x);
	}
}
