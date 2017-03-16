import java.util.Scanner;

public class sum {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		for(int x = 0; x < loop; x++){
			int num = scanny.nextInt();
			System.out.println((x+1) + " " + num + " "+ getResult(num));
		}
		
		
	}

	private static int getResult(int num) {
		int total = 0;
		for(int x = 1; x <= num; x++){
			total+= x* getTri(x+1);
		}
		
		
		return total;
	}
	private static int getTri(int num){
		int total = 0;
		for(int x = 1; x <= num; x++){
			total+=x;
		}
		
		return total;
	}

}
