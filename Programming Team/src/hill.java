import java.util.Scanner;

public class hill {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanny = new Scanner(System.in);
		int count = 0;
		// So the challenge here will be going through all numbers.
		// So I shall make a function that decreases a string integer by 1.
		
		String temp = "1";
		temp = scanny.next();
		if(!isHillNumber(temp)){
			System.out.println(-1);
		}
		else{
		while(!temp.equals("0")){
			if(isHillNumber(temp)){
				count++;
			}
			temp = decreaseOne(temp, temp.length()-1, "");
			temp = removeExtraZeros(temp);
			//System.out.println(temp);
		}
		if(count != 0){
		System.out.println(count);		
		}
		else{
			System.out.println(-1);
		}
		}
	}
	public static String removeExtraZeros(String str){
		while(str.charAt(0) == '0' && str.length() > 1){
			str = str.substring(1);
		}
		
		return str;
		
	}
	public static boolean isHillNumber(String num){
	
		int prevd = 0;
		int dec = 0;
		int count = 0;
		for(int x = 0; x < num.length()-1; x++){
		
			if(parse(num.charAt(x)) < parse(num.charAt(x+1))){
				// if the index you are at has a number that is smaller 
				//than the number after  1 2  you're increasing
				dec = 0;
			}
			else if(parse(num.charAt(x)) > parse(num.charAt(x+1))){
				// you're decreasing
				dec = 1;
				
			}
			if((prevd == 0 && dec == 1) || (prevd == 1 && dec == 0)){
				count++;
			}
			prevd = dec;
			//System.out.println(count);
		}
		// You don't want to have switchcount be greater than 2.
		if(count < 2){
			return true;
		}
	
		return false;
	}
	public static int parse(char c){
		return Integer.parseInt(c + "");
	}
	// initialize to string.length()-1
	public static String decreaseOne(String data, int pos, String append){
		if(pos < 0 || data.equals("0")){
			return "0";
		}
		int lastdig = 0;
		if(pos != data.charAt(0)){
			
		if(data.charAt(pos) != '0'){
			lastdig = parse(data.charAt(pos));
			lastdig--;
			data = data.substring(0, pos) + lastdig + append;
			return data.substring(0, pos) + lastdig + append;
			
		}
		else{
			pos--;
			append = append + "9";
			data = decreaseOne(data, pos, append);
			return data;
		}
		
		
	}
		return data;
	}
}
