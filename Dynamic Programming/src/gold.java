import java.util.Arrays;
import java.util.Scanner;

public class gold {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		for(int x = 0; x < loop; x++){
			int n = scanny.nextInt();
			int[] array = new int[n];
			for(int y = 0; y < n; y++){
				array[y] = scanny.nextInt();
			}
			
			System.out.println(getAns(array, 0,0, array.length-1,1) - getAns(array, 0,0, array.length-1,2));
		}
		
	
		
	}
	public static int getAns(int[] list, int res, int forw, int back, int person){
		
		// you're going forward and backward so if forward ever is greater than back, you know you've reached the end.
		if(forw > back){
			return 0;
		}
		if(person == 1){
			// person 1 wants to maximize his score
			res = Math.max(list[forw] + getAns(list, res, forw+1, back, 2), list[back] + getAns(list, res, forw, back-1, 2));
		}
		else{
			// person 2 wants to minimize person 1's score.
			res = Math.min(getAns(list, res, forw+1, back, 1), getAns(list, res, forw, back-1, 1));
		}
		return res;
		
	}

}
