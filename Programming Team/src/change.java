import java.util.Scanner;

public class change {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		for(int x = 0; x < loop; x++){
			int num = scanny.nextInt();
			int q = 0, d = 0, n = 0, p = 0;
			while(num >= 25){
				num-=25;
				q++;
			}
			while(num>=10){
				num-=10;
				d++;
			}
			while(num>=5){
				num-=5;
				n++;
			}
			while(num>=1){
				num--;
				p++;
			}
		System.out.println((x+1) + " " + q + " QUARTER(S), " + d + " DIME(S), " + n + " NICKEL(S), " +  p + " PENNY(S)");
		
		}
		
		
	}

	
}
