/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Kelsey Cameron
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class jaccard {



		public static void main(String[] args) throws IOException {
			File io = new File("temp.txt");
			ArrayList<jaccards> jacarrays = new ArrayList<jaccards>();
////////////////DO NOT DELETE
		
		File inFile = null;
		if (0 < args.length) {
		   inFile = new File(args[0]);
		   //System.out.println("Attempting to read from file in: "+inFile.getCanonicalPath());

		}
			if(args.length != 1) {
				  System.err.println("Invalid command line, exactly one argument required");
				  System.exit(1);
				}
		  
			Scanner scanny = new Scanner(new FileInputStream(args[0]));
			
			//Scanner scanny = new Scanner(io);
			ArrayList<String> sentences = new ArrayList<String>();
			
			while(scanny.hasNextLine()){
				sentences.add(scanny.nextLine());
			}
			System.out.println("Input Sentences:");
			for(int x = 0; x < sentences.size(); x++){
				TreeSet<String> newmap = new TreeSet<String>();
				System.out.println(x + " : " + sentences.get(x));
				for(int y = 0; y < sentences.get(x).length()-1; y++){
					newmap.add(sentences.get(x).charAt(y) + "" + sentences.get(x).charAt(y+1));
				}
				
				jaccards each = new jaccards(newmap, x);
				
				jacarrays.add(each);
				
			}
			System.out.println();
			System.out.println("Sorted Shingle Arrays: ");
			for(int z = 0; z < jacarrays.size(); z++){
				System.out.print(z + " : ");
				for(int q = 0; q < jacarrays.get(z).hash.size(); q++){
					System.out.print(jacarrays.get(z).hash.toArray()[q] + " ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("Jacard Similarity Matrix:");
			
			double prev = 0.0;
			int count = 0;
			ArrayList<Double> array = new ArrayList<Double>();
			int sen1 = 0, sen2 = 0;
			double temp = 0.0;
			for(int b = 0; b < jacarrays.size(); b++){
			for(int a = 0; a < jacarrays.size(); a++){
				double num = 0.0; 
				num = temp = getJacNum(jacarrays.get(b).hash, jacarrays.get(a).hash);
				array.add(num);
				if((prev > temp) && temp!=1.0){
					temp = prev;
					//System.out.println("Sen1 " + b + " Sen2 " +a);
				}
				
				System.out.print(String.format("%.4f",num) + " ");
				if(temp!=1.0){
				//	sen1 = b;
					//sen2 = a;
				prev = temp;
				}
			}
			System.out.println();
			
			}
			int locationoffirst = getFirstIndexFound(array, prev, 0);
			System.out.println("Most similar sentences " + locationoffirst + " and " + getFirstIndexFound(array, prev, locationoffirst+1) + " with Jaccard value = " + String.format("%.4f", prev) + "");
		
			
			
			
			
			
			scanny.close();
	
	}
		public static double getJacNum(TreeSet<String> first, TreeSet<String> second){
			int count = 0;
			double total = 0.0;
			TreeSet<String> combined = new TreeSet<String>();
			combined.addAll(first);
			combined.addAll(second);
			
				for(int c = 0; c < second.size(); c++){
					if(first.contains(second.toArray()[c])){
						count++;
					}
					total = (double)count/ combined.size();
			}
				//System.out.println("Count: " + count);
				//System.out.println(count + " / " + combined.size() + " = " + total);
				return total;
			
			
		}
		
		public static int getFirstIndexFound(ArrayList<Double> list, double num, int start){
			for(int a = start; a < list.size(); a++){
				for(int b = a; b < 3; b++){
				if(list.get(b) == num){
					return b;
				}
				}
			}
			return 0;
		}
}
class jaccards{
	TreeSet<String> hash;
	int sentencenum;
	
	public jaccards(TreeSet<String> h, int sn){
		this.hash = h;
		this.sentencenum = sn;
		
	}
}
