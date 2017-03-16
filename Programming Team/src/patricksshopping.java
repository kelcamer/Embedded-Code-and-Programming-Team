import java.util.Scanner;

public class patricksshopping {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanny = new Scanner(System.in);
		int d1 = 0, d2 = 0, d3 = 0;
		d1 = scanny.nextInt();
		d2 = scanny.nextInt();
		d3 = scanny.nextInt();
		
		
		System.out.println(Math.min(2*d1 + 2*d2, d1+d2+d3));

	}

}
