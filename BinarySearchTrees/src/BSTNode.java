/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  BST
 * Date: February 10, 2016
 */
public class BSTNode 
{
	BSTNode left, right;
	// stores value
	int value;
	// 1+ # on left
	int rank;
	
	// keeps track of size in tree.
	int subtreesize;
	BSTNode parent;
	public BSTNode()
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		left = right = null;
		parent = null;
		// Set this node's key value to default of 0.
		value = Integer.MAX_VALUE;
		rank = 1;
		subtreesize = 0;
	}

	public BSTNode(int nKeyValue)
	{
		rank = 1;
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		left = right = null;
		parent = null;
		// Set this node's key value
		value = nKeyValue;
	}

	// Accessor method to set the left node.
	public void SetLeftNode( BSTNode objLeftNode)
	{
		// Assign the left node object reference.
		left = objLeftNode;
		// increment rank.
		rank += 1;
	}
	
	// Accessor method to set the right node.
	public void SetRightNode( BSTNode objRightNode)
	{
		// Assign the right node object reference.
		right = objRightNode;
	}
	public void SetParentNode(BSTNode parent){
		this.parent = parent;
	}
	// Accessor method to get parent object
	public BSTNode GetParentNode(){
		return this.parent;
	}
	// Accessor method to get the left node object.
	public BSTNode GetLeftNode()
	{
		// Return the object.
		return( left );
	}
	
	// Accessor method to get the right node object.
	public BSTNode GetRightNode()
	{
		// Return the object.
		return( right );
	}

	// Accessor method to set the node's key value.
	public void SetKeyValue( int nKeyValue )
	{
		// Set the value.
		value = nKeyValue;
	}
	// Method to get the min int of the tree
    public int GetMax()
    {
    	// if nothing on right, you are max
    	if(right == null){
    		return value;
    	}
    	// else go right
    	return right.GetMax();
	}
    public int GetRank(){
    	// returns rank
    	return rank;
    }
    public int GetMin()
    {
    	// if you have nothing to left, you must be smallest.
    	if(left == null){
    		return value;
    	}
    	// else go left
    	return left.GetMin();
    	
	}
   
    public void removeNode(BSTNode current, int val){
    	current.subtreesize--;
    	// if the value you are removing is less than the value of this node
    	if(val < this.value){
    		// if left isn't empty
    		if(left!=null){
    			// search and delete it on the left side
    			left.removeNode(this, val);
    		}
    	}
    	// if the value you are removing is gretaer than the value of this node
    	else if(val > this.value){
    		// if right isn't null
    		if(right!=null){
    			// remove the value on the right side
    			right.removeNode(this, val);
    		}
    	}
    	else{
    		// you found correct value
    		if(right!= null && left!= null){
    			// set the current value to the minimum on the right side
    			this.value = right.GetMin();
    			// remove's the right's value. This would be equivalent to right.GetMinNode = null
    			right.removeNode(current, this.value);
    		}
    		// if this is the first time inside the function,
    		// if the left of the current is equal to the root.
    		else if(current.left == this){
    			if(left!= null){
    				// adds the removed node's left children in there.
    				current.left = left;
    				// sets the new parent for current.left
    				current.left.parent = current;
    			}
    			
    		}
    		else if(current.right == this){
    			if(right!= null){
    				// adds the removed node's right children in there.
    				current.right = right;
    				// sets the new parent for current.right
    				current.right.parent = right;
    			}
    		}
    		
    		
    	}
    	
    }
  
    public int getSubtreeSize(BSTNode n){
    	// if root is null, return 0
    	if(n == null){
    		return 0;
    	}
    	// increment each time function is called for left and right.
    	return getSubtreeSize(n.left) + getSubtreeSize(n.right) + 1;
    	
    }
	// Accessor method to get the node's key value.
	public int GetKeyValue()
	{
		// Return the value.
		return( value );
	}
	// optional function to get the parent of everything
	public BSTNode getMasterParent(BSTNode child){
		while(child.GetParentNode()!= null){
			child = child.GetParentNode();
		}
		return child;
	}
}


