import java.util.Scanner;

/*
 * Kelsey Cameron
 * October 9, 2015
 */
public class cow {

	public static void main(String[] args) {
		/*
		 * 1. Keep track of how many C's you have.
		 * 2. Use how many C's to increment the number of CO's every time an O is encountered.
		 * 3. Use how many CO's to increment the number of COW's every time a W is encountered.
		 */
		
		
		Scanner scanny = new Scanner(System.in);
		long loop = scanny.nextLong();
		long numofC, numofCO, numofCOW = 0;
		for(long x = 0; x < loop; x++){
			numofC = 0; numofCO = 0; numofCOW = 0;
			long numberofletters = scanny.nextLong();
			String allletters = scanny.next();
			for(int y = 0; y < numberofletters; y++){
				if(allletters.charAt(y) == 'C'){
					numofC++;
				}
				if(allletters.charAt(y) == 'O'){
					numofCO += numofC;
				}
				if(allletters.charAt(y) == 'W'){
					numofCOW += numofCO;
				}
				
			}
			
			
			System.out.println(numofCOW);
		}
		
		scanny.close();
	}

}
/*
2
6
COOWWW
7
CWOWCOW
*/
