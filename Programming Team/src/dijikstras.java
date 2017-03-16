// Danny Wasserman
// 7/14/2014
// Implementation of Dijkstra's Algorithm with a Priority Queue.

import java.util.ArrayList;
import java.util.PriorityQueue;

public class dijikstras {

  // Everyone has access to these.
	// safe number. Integer.max is dangerous because if you add one more to it you get
	// a negative number.
	// int goes to 2.1 billion
  static int oo = (int) 1e9;
  static int n;
  // an array of arraylists that stores the neighbors
  static ArrayList<Edge>[] listofedges;
  public static void main(String args[]){
	  
	  
	  
	  
  }
  // Returns the shortest distance from vertex s to d.
  public static int dijkstras(int from, int to) {

    // Set up the priority queue.
    boolean[] visited = new boolean[n];
    PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
    
    // to go from where you are to yourself = 0
    queue.add(new Edge(from, 0));

    // Go till empty.
    while (!queue.isEmpty()) {

      // Get the next edge.
      Edge at = queue.poll();
      
      if (visited[at.edge_dest]) continue;
      visited[at.edge_dest] = true;

      // We made it, return the distance.
      if (at.edge_dest == to){
    	  return at.weight;
      }

      // Enqueue all the neighboring edges.
      for (Edge adj : listofedges[at.edge_dest]){
        if (!visited[adj.edge_dest]){
        	// updates array
        	queue.add(new Edge(adj.edge_dest, adj.weight + at.weight));
        }
      }
    }
    return oo;
  }

  // Stores where an edge is going to and its weight.
  static class Edge implements Comparable<Edge> {
    int edge_dest, weight;

    public Edge(int e, int w) {
      this.edge_dest = e;
      this.weight = w;
    }

    public int compareTo(Edge o) {
      return weight - o.weight;
    }
  }
}