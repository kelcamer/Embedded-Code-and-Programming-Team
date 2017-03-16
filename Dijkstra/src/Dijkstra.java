import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dijkstra
{
	
    public static void computePaths(Vertex source)
    {
        source.minDistance = 0.0;
        // creates a priority queue / heap
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        // add the source to the heap.
      	vertexQueue.add(source);

      	while (!vertexQueue.isEmpty()) 
      	{
      		// get the first index
      		Vertex orig_vert = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge edge : orig_vert.adjacencies)
            {
            	
                Vertex cur_vert = edge.target;
                double weight = edge.weight;
                // gets min distance through original and adds weight to get to cur node
                double distanceThroughU = orig_vert.minDistance + weight;
                // if going through the current node is less than the value listed
                if (distanceThroughU < cur_vert.minDistance) 
                {
                	// go through it
                	// updates the minimum distance
                	vertexQueue.remove(cur_vert);
                	cur_vert.minDistance = distanceThroughU ;
                	cur_vert.previous = orig_vert;
                	vertexQueue.add(cur_vert);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
        {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
    	String filename = "input.txt";
   		Scanner in = new Scanner(new File(filename));

   		int nNumVertices = in.nextInt();
   		// creates an array of vertices
   		Vertex v[] = new Vertex[nNumVertices];
   		
   		// for each vertice, initialize the vertex with the name of the vertex
   		for( int i=0; i<nNumVertices; i++ )
   		{
   			v[i] = new Vertex( in.next() );
   		}
   		// for each vertice, get number of edges
   		for( int i=0; i<nNumVertices; i++ )
   		{
   			int nNumEdges = in.nextInt();
   			// create a new array for the edges connecting each node.
   			v[i].adjacencies = new Edge[nNumEdges];
   			// for each edge,
   			for( int j=0; j<nNumEdges; j++ )
   			{
   				// get the index and the weight, and
   				int nVertexIndex = in.nextInt();
   				int nEdgeWeight = in.nextInt();
   				v[i].adjacencies[j] = new Edge( v[nVertexIndex], nEdgeWeight );
   			}
   		}
   		
   		in.close();
   		
        computePaths(v[0]);
        for (int i=0; i<v.length; i++)
        {
        	System.out.println("Distance to " + v[i] + ": " + v[i].minDistance);
        	List<Vertex> path = getShortestPathTo(v[i]);
        	System.out.println("Path: " + path);
        }
    }
}
