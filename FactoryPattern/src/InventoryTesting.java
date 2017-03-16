/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  FactoryDesign
 * Date: March 24, 2016
 */
import java.util.Scanner;

public class InventoryTesting {
/*
 * Input will be formatted like this:
 * First number = number of cars
 * Second number = number of boats
 * Third number = number of airplanes.
 * 
 * 
 * 
 */
	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		
		vehicle[] list = new vehicle[3];
		System.out.println("How many cars do you want?");
		int cars = scanny.nextInt();
		System.out.println("How many boats do you want?");
		int boats = scanny.nextInt();
		System.out.println("How many airplanes do you want?");
		int airplanes = scanny.nextInt();
		list[0] = new car(cars);
		list[1] = new boat(boats);
		list[2] = new airplane(airplanes);
		
	
		
		System.out.println("Report for Factory Design Vehicle Class"
				+		 "\nBy Kelsey Cameron\n\n");
		System.out.println("Types of Vehicles\tWheel Amount\tEngine Amount\n\n"
						 +  "Airplane\t\t" + list[2].getWheels() + "\t\t" + list[2].getEngines() + "\n\n"
						 +  "Boat\t\t\t" + list[1].getWheels() + "\t\t" + list[1].getEngines() + "\n\n"
						 +  "Car\t\t\t" + list[0].getWheels() + "\t\t" + list[0].getEngines() + "\n\n");
			
		}
		
	

}
