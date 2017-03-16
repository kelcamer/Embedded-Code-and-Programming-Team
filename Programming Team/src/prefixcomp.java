import java.util.ArrayList;
import java.util.Scanner;


public class prefixcomp {
static ArrayList<Boolean> mainlist = new ArrayList<Boolean>();
	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		
		int loop = scanny.nextInt();
		for(int x = 0; x < loop; x++){
			int number = scanny.nextInt();
			loopHowMany(Math.pow(10, number-1), Math.pow(10, number));
	
			
		}
	
		scanny.close();
	}
	// take 14125 as an example.
	public static void getDiv(int num, int counter, int length, ArrayList<Boolean> list, ArrayList<Integer> cache){
		String temp = num + "";
		// we want to loop as many times as there are digits
		if(counter < length){
			int testnum = Integer.parseInt(temp.substring(0, counter));
			if(testnum % counter == 0){
				
				counter++;
				list.add(true);
				if(!cache.contains(testnum)){
					System.out.println("Adding " + testnum + " to the cache. ");
					getDiv(num, counter, length, list, cache);
					cache.add(num);
					
				}
				else{
					return;
				}
				// add to cache
				mainlist = list;
				
			}
			else{
				list.add(false);
			}
		}
		
		
		//cache.add(num);
	}
	public static boolean checkList(){
		if(!mainlist.contains(false)){
			mainlist.clear();
			return true;
		}
		else{
			mainlist.clear();
			return false;
		}
	}
	// count starts at 1
	public static void loopHowMany(double start, double d){
		for(int x = (int)start; x < d; x++){
			String temp = x + "";
		
			getDiv(x, 1, temp.length() + 1, new ArrayList<Boolean>(), new ArrayList<Integer>());
			if(checkList() == true){
				System.out.println(x);
			}
		}
		
		
		
		
	}
	
}
