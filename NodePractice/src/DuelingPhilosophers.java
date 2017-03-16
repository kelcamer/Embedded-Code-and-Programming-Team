/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  Dueling philosophers
 * Date: February 18-25, 2016
 * Adjusted with a stack like Leinecker Requested
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class DuelingPhilosophers {

	// main function
	public static void main(String[] args) throws FileNotFoundException {
		// creates a file
		File io = new File("input.in");
		// creates a scanner
		Scanner scanny = new Scanner(io);
		// gets max value
		int max = scanny.nextInt();
		
		// while there are more test cases
		while(max!=0){
			// create a list of nodes
			node[] list = new node[max];
			
			// initialize the list of nodes to a new node of 0-max
			for(int x = 0; x < max; x++){
				list[x] = new node(x);
			}
			// gets number of relationships
			int relations = scanny.nextInt();
			
			// for each relationship get the number that is defined, and the number that is used
			for(int x = 0; x < relations; x++){
				int a = scanny.nextInt()-1;
				int b = scanny.nextInt()-1;
				
				// go to your node list at the one that is defined and increment inDegree by 1
				// this means that you have 1 prerequisite for it.
				list[b].inDegree++;
				
				// go to that node at the first location and add a new edge from node b.
				list[a].outEdges.add(new edge(list[b]));
				
			}
			
			
			/*for(int x = 0; x < max; x++){
				System.out.println("Node " + list[x].value);
				System.out.println("In Degree " +  list[x].inDegree);
				System.out.println("Outgoing " + list[x].outEdges.toString());
			}
		*/
			// creates a stack
			Stack<node> zeros = new Stack<node>();
			
			// get initial amounts of zeros, and push it onto the stack.
			for(int x = 0; x < list.length; x++){
				if(list[x].inDegree == 0){
					zeros.push(list[x]);
				}
			}
			// set default print value to 1
			int print = 1;
			// loop through the entire list because there should be a spot for each number.
			for(int x = 0; x < list.length; x++){
				// if you have zero zeros, that means you have a loop because everything has a prerequisite.
				if(zeros.size() == 0){
					// stores result = 0 and break
					print = 0;
					break;
				}
				// you have more than 1 entry point but you can't break, there might be a loop later.
				else if(zeros.size() > 1){
					print = 2;
				}
				// gets the first node, and pops it off the stack
				node first = zeros.pop();
				
				// for each node coming out of first,
				for(int y = 0; y < list[first.value].outEdges.size(); y++){
					// subtract 1 from the inDegree
					list[first.outEdges.get(y).dest.value].inDegree--;
					// if your inDegree is zero
					if(list[first.outEdges.get(y).dest.value].inDegree== 0){
						// push it back onto the stack
						zeros.push(list[first.outEdges.get(y).dest.value]);
					}
				}
				
			}
			// prints
			PrintStream printing = new PrintStream("output.txt");
			//System.out.println(print);
			printing.print(print);
			max = scanny.nextInt();
		}
		
		
	}

}
/*
class node{
	int value;
	int inDegree;
	ArrayList<edge> outEdges;
	
	public node(int v){
		this.value = v;
		outEdges = new ArrayList<edge>();
	}
	public node(int v, ArrayList<edge> out){
		this.outEdges = out;
	}
	
}
//Stores where an edge is going to and its weight.
class edge{

	node dest;

 public edge(node d) {
	 this.dest = d;
 }

 
}
*/
