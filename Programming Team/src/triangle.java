import java.util.Scanner;

public class triangle {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int a = scanny.nextInt();
		while(a!=0){
			int b = scanny.nextInt();
			int c = scanny.nextInt();
			boolean state = false;
			if(a%3 == 0 && b%4==0 && c%5==0){
				state = true;
				
			}
			else{
				state = false;
			}
			
			if(!state){
			double a1 = 0.0, a2 = 0.0, a3 = 0.0, a4 = 0.0, a5 = 0.0, a6 = 0.0;
			int hypo = Math.max(Math.max(a, b), c);
			int opp = 0, adj = 0;
			if(hypo == a){
				opp = b; adj = c;
			}
			else if(hypo == b){
				opp = a; adj = c;
			}
			else if(hypo == c){
				opp = a; adj = b;
			}
			// get arcsin of all possible combos.
			a1 = Math.toDegrees(Math.asin(Math.toRadians(opp/hypo)));
			a2 = Math.toDegrees(Math.acos(Math.toRadians(adj/hypo)));
			a3 = Math.toDegrees(Math.atan(Math.toRadians(opp/adj)));
		// sin = oppo / hyp
			System.out.println(a1 + " " + a2 + " " + a3);
			System.out.println((double)adj);
			if(Double.compare(a1, 90) < 0.1 || Double.compare(a2, 90) < 0.1  || Double.compare(a3, 90) < 0.1  || 
					Double.compare(a4, 90) < 0.1){
				state = true;
			}
			
			// Law of sines sin x / l = sin y / l2
			}
			if(state){
				System.out.println("right");
				
			}
			else{
				System.out.println("wrong");
			}
			
			a = scanny.nextInt();
		}
	}

}
