import java.util.ArrayList;
import java.util.Scanner;

public class e_countyourcousins {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		int nodeofinterest = scanny.nextInt();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int x = 0; x < loop; x++){
			list.add(scanny.nextInt());
		}
		int ind1 = 0;
		int ind2 = 0;
		/*for(int y = loop-1; y >=1; y--){
			if(list.get(y) - 1 == list.get(y-1)){
				// consecutive
				if(!list.contains(list.get(y))){
					list.add(y);
				}
				y--;
				
			}
			else{
				// non consecutive
				
				
				
				
				
			}
			
			
			
			
		}
		
		*/
		ArrayList<Integer> test = getConsec(list);
		for(int x = 0; x < test.size(); x++){
			System.out.println(test.get(x));
		}
		
		
	}
	public static ArrayList<Integer> getConsec(ArrayList<Integer> list){
		ArrayList<Integer> newlist = new ArrayList<Integer>();
		for(int x = list.size()-1; x>=1; x--){
			if(list.get(x) - 1 == list.get(x-1)){
				if(!list.contains(list.get(x))){
					newlist.add(x);
					newlist.add(x-1);
				}
				x--;
			}
			else{
				newlist.add(x);
				return newlist;
			}
		}
		return newlist;
		
	}
}
/*
 class node{
	ArrayList<Integer> list;
	int curval;
	
	public node(int c, ArrayList<Integer> l){
		this.curval = c;
		this.list = l;
	}
	
}*/