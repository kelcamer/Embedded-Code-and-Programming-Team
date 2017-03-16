import java.util.ArrayList;
import java.util.Scanner;

public class c_ping {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		String word = scanny.next();
		int total = 1;
		ArrayList<Integer> allsat = new ArrayList<Integer>();
		allsat.add(1);
		while(!word.equals("0")){
	
			for(int x = 2; x < word.length(); x++){
			total = getDivisibleNum(x, allsat);
			if(x == 6){
				System.out.println("///////////// x = 6 ////////");
				System.out.println("Total" + total);
				System.out.println("char at 9 " + word.charAt(6));
			}
			if(word.charAt(x) == '0' && total%2!=0){
				// should be even, total is odd.
				System.out.println("Total is odd");
				if(!allsat.contains(x))
				allsat.add(x);
				
			}
			else if(word.charAt(x) == '1' && total%2==0){
				System.out.println("Total is even");
				// should be odd, total is even
				if(!allsat.contains(x))
				allsat.add(x);
				
				
			}
			
			
			}
			for(int y = 0; y < allsat.size(); y++){
				System.out.println(allsat.get(y));
			}
			
			word = scanny.next();
		}
		
		
		/*while(!word.equals("0")){
			
			
			if(word.charAt(0) == '1'){
				total = 1;
			}
			// starts at time t=1
			for(int x = 2; x < word.length(); x++){
				int num = Integer.parseInt(word.charAt(x) + "");
				/*
				 * 1 - odd
				 * 2 + 1 - odd
				 * 3 + 1 - even
				 */
				/*
				System.out.println("Total: " + total);
			
				total = getDivisibleNum(x, allsat);
				if(total%2 == 0){
					System.out.println("This total " + total + "is even");
					// even, there should be a 0 here.
					if(word.charAt(x) != '0'){
						// there is not a zero, so you have a new satellite 
						// this time interval
						
						
						allsat.add(x);
						
					}
				}
				else{
					System.out.println("This total " + total + "is odd");
					// odd, there should be a 1 here.
					if(word.charAt(x) != '1'){
						// new satellite?

						
						allsat.add(x);
						
					}
					
				}
		
			}
			
			
			for(int x = 0; x < allsat.size()-1;x++){
				System.out.print(x + " ");
			}
			System.out.print(allsat.get(allsat.size()-1));
			*/
			
		//}
		
		
		//scanny.close();
	}
	public static int getDivisibleNum(int num, ArrayList<Integer> list){
		if(list.isEmpty()){
			return 1;
		}
		int newnum = 0;
		int test = 0;
		//System.out.println("num " + num);
		//System.out.println("First element " + list.get(0));
		for(int x = 0; x < list.size(); x++){
			//System.out.println("list.get() x" + list.get(x));
			if(num%list.get(x) == 0 && num!= list.get(x)){
				newnum+= list.get(x);
				//System.out.println("Newnum: " + newnum);
			}
		}
		//System.out.println("Total " + newnum);
		return newnum;
		
		
	}

}
