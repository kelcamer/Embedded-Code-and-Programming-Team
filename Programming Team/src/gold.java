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
	public static int pickLeftOrRight(int[] list, int forw, int back, int num){
		System.out.println(num);
		if(forw < 0 || forw >= list.length || back < 0 || back >= list.length || forw == list.length/2 || back == list.length/2){
			return num;
		}
		
			// last time you subtracted or beginning
			int front = pickLeftOrRight(list, forw+1, back, num + list[forw]);
			int last =  pickLeftOrRight(list, forw, back-1, num + list[back]);
			System.out.println("First two " + front + " " + last);
			if((forw+1) < list.length && (back-1) >= 0){
				int secondfront =  pickLeftOrRight(list, forw+2, back, list[forw+1]);
				int secondlast =  pickLeftOrRight(list, forw, back-2, list[back-1]);
				System.out.println("Next two " + secondfront + " " + secondlast);
				if(secondfront ==-1 || secondlast == -1){
					System.out.println("HI");
					return 0;
				}	
				return Math.max(front, last) - Math.max(secondlast, secondfront);
			}
			
		
		
		return -1;
		
		
	}

}
