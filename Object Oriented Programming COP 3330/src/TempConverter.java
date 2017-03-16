/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Kelsey Cameron
 * F = [ ( 9 * C ) / 5 ] + 32
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TempConverter {

	public static void main(String[] args) throws IOException {
		//File io = new File("temp.txt");
		File inFile = null;
		if (0 < args.length) {
		   inFile = new File(args[0]);
		   //System.out.println("Attempting to read from file in: "+inFile.getCanonicalPath());

		}
			if(args.length != 1) {
				  System.err.println("Invalid command line, exactly one argument required");
				  System.exit(1);
				}
		  
		   //System.exit();
		
			Scanner scanny = new Scanner(new FileInputStream(args[0]));
			char[] input = new char[6];
		double[] in = new double[6];
		char[] outchar = new char[6];
		double[] out = new double[6];
		for(int x = 0; x < 6; x++){
			in[x] = scanny.nextDouble();
			input[x] = scanny.next().charAt(0);
			
			if(input[x] == 'F'){
				// convert to centigrade
				out[x] = (5*(in[x] -32)) / 9;
				outchar[x] = 'C';
			}
			else if(input[x] == 'C'){
				// convert to fahrenheit
				out[x] = ( ( 9 * in[x]) / 5 ) + 32;
				outchar[x] = 'F';
			}
		}
		System.out.println("Temperature Converter by Kelsey Cameron");
		System.out.println("  Input   Type  Conversion");
		System.out.println("------------------------");
		for(int a = 0; a < 6; a++){
		System.out.println(String.format("%7.2f", in[a]) + "  " + input[a] + "     " + String.format("%7.2f", out[a]) + " " + outchar[a]);
		//	System.out.printf("%.2lf. %-20c $%.2f", );
		
		
		scanny.close();
		
		}
	}

}
