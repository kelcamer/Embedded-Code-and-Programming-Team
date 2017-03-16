import java.util.Scanner;

public class profits {
/*
 *   Day 1: -3
     Day 2: 4
     Day 3: 9
     Day 4: -2
     Day 5: -5
     Day 6: 8
Their maximum profit over any span would be 14, from days 2 to 6.
 */
	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		while(loop!=0){
			int[] array = new int[loop];
			
			for(int x = 0; x < loop; x++){
				array[x] = scanny.nextInt();
			}
			System.out.println(getMax(array));
			
			loop = scanny.nextInt();
		}
		
		
		
		
		
	}
	public static int getMax(int[] list){
		int sum = 0;
		int i = 0;
		int max = 0;
		
		for(int x = 0; x < list.length; x++){
			sum+=list[x];
			
			
			
			
			
			
		}
		return 0;
		
		
	}
}
