/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  Huffman Compression
 * Date: April 20, 2016
 */
// stores a root and searches
public class HuffmanBinaryTree {
	HuffmanTreeNode root;
	
	HuffmanBinaryTree(HuffmanTreeNode r){
		this.root = r;
	}
	HuffmanBinaryTree(){
		root = null;
	}
	
	public HuffmanTreeNode getRoot(){
		return root;
	}
	
	public HuffmanTreeNode search(HuffmanTreeNode rot, char c){
		if(rot.letter == c){
			return rot;
		}
		if(rot.getLeft()!=null){
			return search(rot.left, c);
		}
		if(rot.getRight()!=null){
			return search(rot.right, c);
		}
		
		return null;
		
	}
	public HuffmanTreeNode insert(HuffmanTreeNode n){
		if(root==null){
			root = n;
			return root;
		}
		else{
			
		root.insert(root,n,n);
		return root;
		}
	}

}
