import java.util.Scanner;

public class toolong {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		
		for(int x = 0; x < loop; x++){
			String word = scanny.next();
			if(word.length() > 10){
				System.out.println(word.charAt(0) + "" + (word.length()-2) + word.charAt(word.length()-1));
			}
			else{
				System.out.println(word);
			}
			
		}
		
		
	}

}
