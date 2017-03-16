package test;

import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		System.out.println("Second side or third?");
		int choice = scanny.nextInt();
		System.out.println("Enter first side, next side, then angle.");
		double side1 = scanny.nextDouble();
		double side2 = scanny.nextDouble();
		double angle = scanny.nextDouble();
		
		if(choice == 2){
			System.out.println(getSecondSide(side1, side2, angle));
		}
		else if(choice == 3){
			System.out.println(getThirdSide(side1, side2, angle));
		}
		
		
	}
	
	public static double getThirdSide(double side1, double side2, double theta){

		return Math.sqrt((side1*side1) + (side2*side2) - (2*side1*side2*Math.cos(Math.toRadians(theta))));
	}
	public static double getSecondSide(double side1, double side3, double theta){
		double loop = side3 - side1;
		
		for(double x = side1; x < side3; x+=0.1){
			//System.out.println(Math.sqrt((side1*side1) + (x*x) - (2*side1*x*Math.cos(theta))) - side3 );
			if(Math.sqrt((side1*side1) + (x*x) - (2*side1*x*Math.cos(Math.toRadians(theta)))) - side3 < 0.0001){
				return x;
			}
			
			
		}
		
		return 0.0;
	}

}
