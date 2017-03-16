import java.util.Scanner;

public class simple_gcd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanny = new Scanner(System.in);
		int num1 = scanny.nextInt();
		int num2 = scanny.nextInt();
		int num3 = scanny.nextInt();
		
		
		System.out.println(gcd(num1*num2, num3*num3));
	}

	public static int gcd(int a, int b){
		if(b > a){
			int temp = b;
			b = a;
			a = temp;
		}
		if(b == 0){
			return a;
		}
		
		return gcd(b, a%b);
		
	}
}
