
class Edge
{
	// holds where you're connected and the weight
    public Vertex target;
    public double weight;
    
    public Edge(Vertex argTarget, double argWeight)
    { 
    	target = argTarget; 
    	weight = argWeight; 
    }
}
