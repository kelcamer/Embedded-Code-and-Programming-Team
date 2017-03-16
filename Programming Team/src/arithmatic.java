import java.util.Arrays;
import java.util.Scanner;

public class arithmatic {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);

		int[] array = new int[4];
		int[] operations = { 1, 2, 3, 4 };
		array[0] = scanny.nextInt();
		while (array[0] != 0) {
			array[1] = scanny.nextInt();
			array[2] = scanny.nextInt();
			array[3] = scanny.nextInt();
			System.out.println(getM(array, 0, 0));
			array[0] = scanny.nextInt();
		}

	}
	
	
	
	public static int getM(int[] array, int c, int max){
		if(c == array.length-1){
			return max;
		}
		switch (c) {
		case 1:
			// add
			max = Math.max(max, (array[c] + array[c+1]) + getM(array, c+1, max));
			
			System.out.println("Adding " + max);
			break;
		case 2:
			// subtract
			max = Math.max(max, (array[c] - array[c+1]) + getM(array, c+1, max));
			
			System.out.println("Subtracting " + max);

		
			break;
		case 3:
			// multiply
			max = Math.max(max, (array[c] * array[c+1]) + getM(array, c+1, max));
			
			System.out.println("Multiplying " + max);

			break;
		case 4:
			// divide
			if(array[c] % array[c-1] == 0){
				max = Math.max(max, (array[c] / array[c+1]) + getM(array, c+1, max));
				
			}
			else{
				max = Math.max(max, getM(array, c, max));
			}
			System.out.println("Dividing " + max);

			
			break;
	}
		
		
		

		
		return max;
		
		
		
	}

	public static int getMax(int[] array, int c, int max) {
		if (c == array.length - 1) {
			return max;
		}

		for(int x = 1; x <= 4; x++){
			switch (x) {
			case 1:
				// add
				max = Math.max(max, max + (array[c] + array[c+1]));
				break;
			case 2:
				// subtract
				max = Math.max(max, max + (array[c] - array[c+1]));
			
				break;
			case 3:
				// multiply
				max = Math.max(max, max + (array[c] * array[c+1]));
				break;
			case 4:
				// divide
				if(array[c] % array[c-1] == 0){
					max = Math.max(max, max + (array[c] / array[c+1]));
				}
				
				break;
		}
			max = getMax(array, c+1, max);
		}
		return max;
		
	}
	public static int getMaxim(int a, int b, int c, int d){
		if(a > b && a > c && a > d){
			return a;
		}
		if(b > a && b > c && b > d){
			return b;
		}
		if(c > a && c > b && c > d){
			return c;
		}
		if(d > a && d > b && d > c){
			return d;
		}
		return 0;
		}
	public static int getMin() {

		return 0;
	}

}
/*
 * class maxGroup{ int[] group1; int[] group2;
 * 
 * public maxGroup(int[] totalgroups){ int state = 0; int prevstate = 0; // 0
 * means increasing, 1 means decreasing for(int x = 0; x < totalgroups.length-1;
 * x++){ if(totalgroups[x] < totalgroups[x+1]){ state = 0; } else{ state = 1; }
 * // direction changes if((prevstate == 0 && state == 1) || (prevstate == 1 &&
 * state == 0) && x>=2){ for(int y = x; y >=0; y++){ group1[y] = totalgroups[x];
 * 
 * 
 * }
 * 
 * 
 * }
 * 
 * 
 * 
 * prevstate = state; }
 * 
 * 
 * 
 * 
 * }
 */
