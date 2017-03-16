
class Vertex implements Comparable<Vertex>
{
	// each vertex has a name
    public final String name;
    // a vertex has a list that holds edges - an edge is just a vertex with a weight
    public Edge[] adjacencies;
    // sets min distance to +oo
    public double minDistance = Double.POSITIVE_INFINITY;
    // previous vertex, to track path you've followed?
    public Vertex previous;
    
    public Vertex(String argName) 
    { 
    	name = argName; 
    }
    
    public String toString() 
    { 
    	return name; 
    }
    
    public int compareTo(Vertex other)
    {
    	// to sort a list of vertices, put the ones with the min distance first
        return Double.compare(minDistance, other.minDistance);
    }
}
