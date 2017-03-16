/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  Huffman Compression
 * Date: April 20, 2016
 */
// Internal to the heap you will have an array of data structures.
// bst - inorder traversal, insert, search according to letter

public class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {
	// has a left and a right, a frequency, a letter, and an id
	HuffmanTreeNode left, right;
	int freq;
	char letter;
	String id;
	// initializes values
	HuffmanTreeNode(char w, int f, HuffmanTreeNode l, HuffmanTreeNode r) {
		this.freq = f;
		this.letter = w;
		this.left = l;
		this.right = r;
		id = "";

	}
	// alternate constructor
	HuffmanTreeNode(char w, int f) {
		this.freq = f;
		this.letter = w;
		id = "";
	}
	// sets and get are self explanatory
	void setLeft(HuffmanTreeNode temp) {
		this.left = temp;
	}

	void setRight(HuffmanTreeNode temp) {
		this.right = temp;
	}

	HuffmanTreeNode getLeft() {
		return this.left;
	}

	HuffmanTreeNode getRight() {
		return this.right;
	}

	char getLetter() {
		return this.letter;
	}

	int getFreq() {
		return this.freq;
	}

	@Override
	public int compareTo(HuffmanTreeNode o) {
		if (o.freq > this.freq) {
			// if the object is greater, put it in front
			return 1;
		}
		// else put in back
		if (o.freq < this.freq) {
			return -1;
		}

		return 0;
	}

	
	 HuffmanTreeNode insert(HuffmanTreeNode combined, HuffmanTreeNode first, HuffmanTreeNode second) {
		 // put the smaller freq on the left
		 if(first.freq < second.freq){
				combined.left = first;
				combined.right = second;
				
			}
			else{
				combined.left = second;
				combined.right = first;		
			}
		 // set ids
		 combined.left.id+="0";
		 combined.right.id+="1";
		 
		
		return combined;
	}
	 
	 // updates strings for root codes
	 HuffmanTreeNode updateRootCodes(HuffmanTreeNode root, String last){
		 if(root != null){
			 root.id += last;
			 last = "";
			 if(root.getLeft() != null){
				 
				updateRootCodes(root.left, root.id);
				last = "";
			 }
			 if(root.getRight()!=null){
				 
				updateRootCodes(root.right, root.id);
				last = "";
			 }
		 }
		 return root;
	 
	 }
	 // if root is not null, check letter, search left, search right
	 HuffmanTreeNode search(HuffmanTreeNode root, char letter, HuffmanTreeNode cur){
		 if(root!=null){
				if(root.letter == letter){
					cur = root;
					return root;
				}
				if(root.getLeft()!=null){
					cur =search(root.getLeft(),letter,cur);
				}
				if(root.getRight()!=null){
					cur= search(root.getRight(),letter,cur);
				}
			}
		 return cur;
	 }
}
