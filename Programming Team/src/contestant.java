import java.util.Scanner;

public class contestant {
	public static void main(String args[]){
		Scanner scanny = new Scanner(System.in);
		
		int loop = scanny.nextInt();
		int placetocheck = scanny.nextInt();
		int[] list = new int[loop];
		for(int x = 0; x < loop; x++){
			list[x] = scanny.nextInt();
			
		}
		int ans = count(list, placetocheck)-1;
		if(list.length == 1){
			System.out.println(1);
		}
		else{
		if(ans==-1){
			System.out.println(0);
		}
		else{
			System.out.println(ans);
		}
		}
		
	}
	public static int count(int[] list, int placetocheck){
		int count = 0;
		if(list[0] == 1){
			return list.length+1;
		}
		
		for(int x = 0; x < list.length-1; x++){
			count++;
			if(list[x] != list[x+1]){
				placetocheck--;
			}
			
			if(placetocheck == 0){
				break;
			}
			if(list[x] == 0){
				count = 0;
			}
			
			
		}
		return count;

		
	}
	
}
