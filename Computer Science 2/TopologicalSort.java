import java.io.File;
import java.util.*;

public class TopologicalSort
{

	static final String[] Clothes =
	{
		"Shirt", "Slacks", "Shoes", "Socks", "Belt", "Undergarments"
	};
	static int[] m_nPlaced = new int[6];
	int m_nPlacedIndex = 5;
	
	boolean [][] bMatrix;
	
	public TopologicalSort(String filename) throws Exception
	{
		Scanner in = new Scanner(new File(filename));
		int N = in.nextInt();
		
		bMatrix = new boolean[N][N];
		
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				bMatrix[i][j] = (in.nextInt() == 1);
			}
		}
		
		in.close();
	}
	
	// Iterative BFS method.
	public void BFS(int nStart)
	{
		// Iterative BFS places vertices in a queue. When we pull a vertex out of
		// the queue, we process it (in this case, print it to the screen), then
		// place all its unvisited neighbors in the queue. We mark each vertex as
		// visited as it goes into the queue, because that ensures we never place
		// a vertex into the queue more than once. (That can significantly reduce
		// the space complexity of this algorithm when dealing with a large, dense
		// graph.)
		
		// A queue is an abstract data type. We need to decide what underlying
		// data structure to use to implement it. Java will not allow you to do,
		// e.g.: Queue<Integer> q = new Queue<Integer>();
		Queue<Integer> objQueue = new LinkedList<Integer>();
		boolean [] bVisited = new boolean[bMatrix.length];
		
		// Start by adding the start vertex to the queue. It will be the first
		// thing dequeued, at which point we'll print it and add its neighbors
		// to the queue.
		objQueue.add(nStart);
		bVisited[nStart] = true;
		
		while (!objQueue.isEmpty())
		{
			// Remove a node from the queue and process it. If we were searching
			// for a particular node (a "goal"), this is where we would check
			// whether we had found it. If so, we would terminate the BFS. However,
			// since the goal of this BFS method is simply to print all nodes of a
			// graph in BFS order, we simply print this node and move on.
			int nNode = objQueue.remove();
			System.out.println(nNode);
			
			// Add all neighbors of 'node' to the queue (as long as they haven't
			// been visited already).
			for (int i = 0; i < bMatrix.length; i++)
			{
				if (bMatrix[nNode][i] && !bVisited[i])
				{
					bVisited[i] = true;
					objQueue.add(i);
				}
			}
		}
	}

	int m_nDepth;
	boolean [] bVisited;
	
	void ClearVisited()
	{
		bVisited = new boolean[bMatrix.length];
	}
	
	// Wrapper to our recursive DFS method. This one sets up the 'visited' array.
	public void DFS(int nStart)
	{
		if( bVisited[nStart] )
		{
			return;
		}
		
		// Recall that I mentioned the Arrays.fill() method here. If we want to
		// fill a boolean array with all 'true' values, we could use, e.g.,
		// Arrays.fill(myArray, true).
		DFS(nStart, bVisited);
	}
	
	private void DFS(int nNode, boolean [] bVisited)
	{
	
		m_nDepth++;
		
		// As soon as we encounter a node in our DFS traversal, we mark it as
		// visited. This ensures that we won't get stuck in an infinite loop if
		// our graph has a cycle.
		bVisited[nNode] = true;
		
		// If we were searching for a particular vertex (a "goal"), this is where we
		// would check whether we have found the goal. If so, we would terminate the
		// DFS. However, since the purpose of this particular method is simply to
		// print all vertices in DFS order, we just print this vertex and move on.
//		System.out.println(nNode);
		
		// Now call DFS() on all of this node's neighbors that haven't already
		// been visited.
		
		int nCountOfNodesVisitedFromHere = 0;
		
		for (int i = 0; i < bMatrix.length; i++)
		{
			if (bMatrix[nNode][i] && !bVisited[i])
			{
				nCountOfNodesVisitedFromHere++;
				DFS(i, bVisited);
			}
		}
		
		if( nCountOfNodesVisitedFromHere == 0 )
		{
			System.out.println( "Dead end node " + nNode + " at depth " + m_nDepth );
			m_nPlaced[m_nPlacedIndex--] = nNode;
		}
		else
		{
			System.out.println( "Complete probing of node " + nNode + " at depth " + m_nDepth );
			m_nPlaced[m_nPlacedIndex--] = nNode;
		}
		
		m_nDepth--;
	}
	
	public static void main(String [] args) throws Exception
	{
		TopologicalSort g = new TopologicalSort("input.txt");
//		System.out.println("DFS(0):"); g.DFS(0);
		
		g.ClearVisited();
		for( int i=0; i<6; i++ )
		{
			System.out.println(i);
			g.DFS( i );
		}

		for( int i=0; i<6; i++ )
		{
			System.out.println( Clothes[m_nPlaced[i]]);
		}
		
	}
}
