import java.util.Arrays;
import java.util.Scanner;

public class alaska {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int num = scanny.nextInt();
		
		while(num!=0){
			int[] list = new int[num];
			for(int x = 0; x < num; x++){
				list[x] = scanny.nextInt();
			}
			
			Arrays.sort(list);
			
			boolean state = false;
			if((Math.abs(1422- list[list.length-1]) > 200) || (list[0] > 200)){
				state = true;
			}
			for(int x = 0; x < num-1; x++){
				if(Math.abs(list[x] - list[x+1]) > 200){
					state = true;
					break;
				}
				
			}
			if(state){
				System.out.println("IMPOSSIBLE");
			}
			else{
				System.out.println("POSSIBLE");
			}
			
			num = scanny.nextInt();
		}
	}

}
