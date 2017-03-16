import java.util.HashSet;
import java.util.Scanner;

public class tulips {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int numofbed = scanny.nextInt();
		
		HashSet<Integer> hash = new HashSet<Integer>();
		for(int x = 0; x < numofbed; x++){
			hash.add(scanny.nextInt());
			
			
		}
		System.out.println(15000 - hash.size());
	
	
	}

}
