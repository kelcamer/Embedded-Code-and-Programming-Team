import java.util.ArrayList;
import java.util.Scanner;

public class squarefree {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int x = 1; x < 1000000; x++){
			list.add((int) Math.pow(x, 2));
		}
		for(int x = 0; x < loop; x++){
			int start = scanny.nextInt(); 
			int end = scanny.nextInt();
			int count = 0;
			for(int y = start; y <= end; y++){
				if(!list.contains(y)){
					count++;
				}
			}
			System.out.println(count);
		
		}		

	}

}
