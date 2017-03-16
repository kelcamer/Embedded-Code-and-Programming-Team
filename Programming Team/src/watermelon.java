import java.util.Scanner;

public class watermelon {
	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		
		
		int num = scanny.nextInt();
		if(num %2 == 0 && num!=2){
			System.out.println("YES");
		}
		else{
			System.out.println("NO");
		}
	}
}
