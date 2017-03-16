import java.util.ArrayList;
import java.util.Scanner;

public class c_ping_try2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanny = new Scanner(System.in);
		String word = scanny.next();
		int total = 0;
		ArrayList<Integer> finallist = new ArrayList<Integer>();
		
		for(int x = 1; x < word.length(); x++){
			
			System.out.println("TOTAL " + total);
			total = getCircle(finallist, x);
			if(word.charAt(x) == '0' && !isEven(total)){
				finallist.add(x);
				
			}
			else if(word.charAt(x) == '1' && isEven(total)){
				finallist.add(x);
				//total = getCircle(finallist, x);
				
				
			}
			
			
			
		}
		
		for(int x = 0; x < finallist.size()-1; x++){
			System.out.print(finallist.get(x) + " ");
		}
		System.out.print(finallist.get(finallist.size()-1));
		
		
	}
	public static boolean isEven(int num){
		if(num%2 == 0){
			return true;
		}
		return false;
		
	}
	public static boolean isDivisibleBy(int num1, int num2){
		if(num1 % num2 == 0 && num1!=num2){
			return true;
		}
		return false;
	}
	public static int getCircle(ArrayList<Integer> list, int num){
		int total = 0;
		for(int x = 0; x < list.size(); x++){
			if(num % list.get(x) == 0){
				//System.out.println("Num " + num);
				total = total + list.get(x);
			}
		//	System.out.println("LIST " + list.get(x));
		}
		
		
		total+=num;
		
		return total;
		
	}

}
