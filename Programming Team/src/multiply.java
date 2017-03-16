import java.util.ArrayList;
import java.util.Scanner;

public class multiply {
	public static void main(String[] args){
	Scanner scanny = new Scanner(System.in);
	int num1 = 0, num2 = 0;
	num1 = scanny.nextInt();
	while(num1!=0){
		num2 = scanny.nextInt();
		ArrayList<String> nums = new ArrayList<String>();
		String n2 = num2 + "";
		String n1 = num1 + "";

		for(int x = n2.length()-1; x >=0; x--){
			StringBuilder app = new StringBuilder();
			
			int carry = 0;
			int sum = 0;
			int c = 0;
			for(int y = n1.length()-1; y >=0; y--){
				int digit1 = Integer.parseInt(n2.charAt(x) + "");
				int digit2 = Integer.parseInt(n1.charAt(y) + "");
				
				sum = digit1 * digit2;
				sum+=carry;
				carry = 0;
				if(sum >= 10)
					carry = sum/10;
				while((sum) >= 10){
					sum %= 10;
				}
			//	System.out.println("DIGIT1 " + digit1);
			//	System.out.println("DIGIT2 " + digit2);
			//	System.out.println("SUM " + sum);
			//	System.out.println("CARRY " + carry);
				
				app.append(sum+ "");
			}
			if(carry!=0){
				nums.add(carry + app.reverse().toString());
			}
			else{
			nums.add(app.reverse().toString());
			}
			
		
		}
		long sum = getSum(nums);
		String sumstring = sum + "";
		for(int x = 0; x < sumstring.length() - n1.length(); x++){
			System.out.print(" ");
		}
		System.out.println(n1);
		for(int x = 0; x < sumstring.length() - n2.length(); x++){
			System.out.print(" ");
		}
		System.out.println(n2);
		for(int x = 0; x < sumstring.length(); x++){
			System.out.print("-");
		}
		System.out.println();
		int q = 0;
		boolean state = false;
		String zeros = "";
		for(int x = 0; x < nums.size(); x++){
			//if(!state){
			//}
			if(Long.parseLong(nums.get(x)) != 0){
				for(int b = 0; b < q; b++){
					zeros+="0";
				}
			for(int y = 0; y < sumstring.length() - nums.get(x).length() - q; y++){
				System.out.print(" ");
			}
			state = true;
			System.out.print(nums.get(x) + zeros);
			zeros = "";
			System.out.println();
			}
			q++;
			
		}
		for(int x = 0; x < sumstring.length(); x++){
			System.out.print("-");
		}
		System.out.println();
		System.out.println(sum);
		
		
		num1 = scanny.nextInt();
	}
	}

	private static long getSum(ArrayList<String> nums) {
		long sum = 0;
		for(int x = 0; x < nums.size(); x++){
			String numAt = nums.get(x);
			for(int c = 0; c < x; c++){
				numAt+= "0";
			}
			sum+=Long.parseLong(numAt);
			
		}
		return sum;
		
		
		
	}
	
}

