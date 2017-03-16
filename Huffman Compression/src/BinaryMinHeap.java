/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  Huffman Compression
 * Date: April 20, 2016
 */
import java.util.ArrayList;
import java.util.Collections;

// creates min heap
public class BinaryMinHeap {
	// defines variables
	int size;
	ArrayList<HuffmanTreeNode> list;
	BinaryMinHeap(){
		// initializes list and clears
		list = new ArrayList<HuffmanTreeNode>();
		list.clear();
	}
	
	// adds to array list and sorts based on my comparable
	public void add(HuffmanTreeNode cur){
		list.add(cur);
		Collections.sort(list);
		size = list.size();
	
	}
	// remove from back of heap
	public HuffmanTreeNode pop(){
		return list.remove(list.size()-1);
	}
	// when you add a node to the back of the heap, sift it up to the correct position
	public void siftUp(){
		
		Collections.sort(this.list);
		
	}
	// return size
	public int getSize(){
		return list.size();
	}
	
	
	

}



