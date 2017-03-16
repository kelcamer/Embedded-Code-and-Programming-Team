/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  BST
 * Date: February 10, 2016
 */
public class BST 
{
	// This is the root node, which starts off as null
	//   when the BST is empty.
	BSTNode m_objRootNode;
	int k;
	// Class constructor.
	public BST()
	{
		// default k value set to 3
		k = 3;
		// Not really necessary, provided for clarity.
		m_objRootNode = null;
	}
	// sets the k value
	public void setK(int k){
		this.k = k;
	}
	// gets k
	public int getK(){
		return this.k;
	}
	// Method to see if the tree is empty.
	public boolean IsEmpty()
	{
		// Return a boolean indicating whether the
		//   three is empty or not.
		return( m_objRootNode == null );
	}

	/* Functions to search for an element */
    public BSTNode Search( int nKeyValue )
    {
        return( Search( m_objRootNode, nKeyValue ) );
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null )
    	{
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int nThisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if( nKeyValue < nThisKeyValue )
    	{
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if( nKeyValue > nThisKeyValue )
    	{
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else
    	{
    		return( objNode );
    	}
            
    	// Now call Search recursively.
    	return( Search( objNode, nKeyValue ) );
	}
    
    public void Delete(BSTNode nodetoremove){
    	// calls the delete for an integer
    	Delete(nodetoremove.value);
    }
    // This method deletes a node and then fixes the tree
    public BSTNode Delete(int num){
    	// as long as node isn't null
    	if(m_objRootNode == null){
    		return m_objRootNode;
    	}
    	// if you got the exact key value, remove node
    	if(m_objRootNode.GetKeyValue() == num){
    		m_objRootNode.removeNode(m_objRootNode, num);
    	}
    	// if num is less than key, go left
    	else if(m_objRootNode.GetKeyValue() > num){
    		m_objRootNode.left.removeNode(m_objRootNode, num);
    	}
    	// if num is greater than key, go right.
    	else{
    		m_objRootNode.right.removeNode(m_objRootNode, num);
    	}
		return m_objRootNode;
    }


	// This method inserts a node based on the key value.
    public void Insert( int nKeyValue ) 
    {
    	// The root node is returned to m_objRootNode from Insert()
    	m_objRootNode = Insert( nKeyValue, m_objRootNode );
    }    

    // This method returns the root node.
    public BSTNode GetRootNode(){
    	return m_objRootNode;
    }
    
    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected BSTNode Insert( int nKeyValue, BSTNode objNode ) 
    {
    	boolean state = false;
    	// This node is null and simply needs to be allocated.
        if( objNode == null )
        {
        	// initialized with a null parent
        	objNode = new BSTNode( nKeyValue );
        	state = true;
        }
      
        // Here we need to walk left.
        else if( nKeyValue < objNode.GetKeyValue() && Math.abs(objNode.GetKeyValue() - nKeyValue) > this.k)
        {
        	// Set the left node of this object by recursively walking left.
        	objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode() ) );
        	// sets left node's parent to current node
        	objNode.left.SetParentNode(objNode);
        	state = true;

        }
        
        // Here we need to talk right.
        else if( nKeyValue > objNode.GetKeyValue() && Math.abs(objNode.GetKeyValue() - nKeyValue) > this.k)
        {
        	// Set the right node of this object by recursively walking right.
        	objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode() ) );
        	// sets right node's parent to current node
        	objNode.right.SetParentNode(objNode);
        	state = true;
        }
        // if you inserted something, increment subtree size.
        if(state)
        	objNode.subtreesize++;
        return( objNode );
    }
    
}
