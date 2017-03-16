
/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  Huffman Compression
 * Date: April 20, 2016
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Huffman {

	public static void main(String[] args) throws FileNotFoundException {
		// get data from file
		File input = new File("input.dat");
		Scanner scanny = new Scanner(input);
		int[] freq = new int[256];
		String word = scanny.nextLine();
		byte[] data = new byte[256];
		// store data into byte array
		data=word.getBytes();
		
		// calculate the frequencies
		calculateFreqs(freq, data);
		
		// creates a heap
		BinaryMinHeap heap = new BinaryMinHeap();
		// creates a tree
		HuffmanBinaryTree bin_tree = new HuffmanBinaryTree();
		// for each letter and frequency, store into heap
		for(int x = 0; x < freq.length; x++){
			// store each letter and the frequency into the heap
			if(freq[x] != 0){
			HuffmanTreeNode temp = new HuffmanTreeNode((char)x, freq[x]);
			heap.add(temp);
			
			}
		}
	
		
		// sets master root to the bin tree root
		HuffmanTreeNode master_root = bin_tree.getRoot();
		// while you have at least two items in heap
		while(heap.getSize()>=2){
			
			
			// 1. Grab the first two
			HuffmanTreeNode first = heap.pop();
			HuffmanTreeNode second = heap.pop();
		
			// 2. Combine the first two
			HuffmanTreeNode combined = new HuffmanTreeNode('*', first.getFreq() + second.getFreq());
			combined.insert(combined, first, second);
			// 3. Add the combined one back to the heap
			heap.add(combined);
			heap.siftUp();
			
			// 4.  Create a binary tree with the combined as the root, and the first and second as children
			// combined is the root, and then you have a left and a right
			// Since you constantly update left and right in the insert function, it practically does it for you
			
			master_root = combined;
		}
		// update ID codes
		master_root.updateRootCodes(master_root, "");
		
		// count bits and print
		int count = 0;
		String test = "";
		for(int a = 0; a < word.length(); a++){
			HuffmanTreeNode tmp = master_root.search(master_root, word.charAt(a),null);
			 count+=tmp.id.length();
			 // gets the string
			test +=tmp.id;
		}
	
		
		System.out.println(test);
		System.out.println("Size of bit compression: " + count);
		//printTree(master_root);
	
		
		
		
		scanny.close();
		
		
	}
	
	// ignore this
	public static void printTree(HuffmanTreeNode root){
		if(root!=null){
			if(root.getLeft()!=null){
				printTree(root.getLeft());
			}
			if(root.getRight()!=null){
				printTree(root.getRight());
			}
		}
	}
	// calculates the frequencies and adds to the ASCII val in array
	public static void calculateFreqs(int[] array, byte[] data){
		for(int x = 0; x < data.length; x++){
			array[data[x]]++;
		}
		
	}

}
