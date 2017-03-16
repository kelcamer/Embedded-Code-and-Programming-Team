import java.util.Scanner;

public class birdman {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		for(int x = 0; x < loop; x++){
			// b connects to s, p1 connects to p2.
			int bx = scanny.nextInt();
			int by = scanny.nextInt();
			int p1x = scanny.nextInt();
			int p1y = scanny.nextInt();
			int p2x = scanny.nextInt();
			int p2y = scanny.nextInt();
			int sx = scanny.nextInt();
			int sy = scanny.nextInt();
			// when x = 0, y = b
		
			double mbsslope = getMSlope(bx,by,sx,sy);
			// this is b
			double bs_yint = getYInt(bx,by,mbsslope);
			double deltax_b = sx-bx;
			double deltay_b = sy - by;
			
			// this is the other b
			double mpslope = getMSlope(p1x,p1y,p2x,p2y);
			double p_yint = getYInt(p1x,p2x,mpslope);
			double deltax_p = p2x-p1x;
			double deltay_p = p2y - p1y;
			
			// xt+x int
			// yt + yint
			
			// to get x-int, set y = 0 solve for x
			// when y = 0, b = -mx
			// thus x-int = b / -m
			
			
			
			
			// to get y-int set x = 0 solve for y
			// if x = 0, y = b
			
			// simulating t
			for(int t = -500; t < 500; t++){
				double num1 = deltax_b * t + (bs_yint/-mbsslope);
				double num2 = deltay_b * t + (bs_yint);
				double num3 = deltax_p * t + (p_yint / -mpslope);
				double num4 = deltay_p * t + p_yint;
				
					
				
				if(Math.abs(num1 - num3) < 5 && Math.abs(num2 - num4) < 5){
					System.out.println(Math.abs(num1 - num3));
					System.out.println(Math.abs(num2 - num4));
					
					System.out.println("Yes");
				}
				
			}
			
			
		}
		
	}

	
	public static double getYInt(int x, int y, double slope){
		return y-(slope * x);
		
	}
	public static double getMSlope(int x, int y, int x2, int y2){
		double slope = (y2-y) / (x2-x);
		return slope;
		
	}
	public static double getXSlope(){
		return 1.0;
	}
}
