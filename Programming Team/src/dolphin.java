import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class dolphin {

	public static void main(String[] args) {
			Scanner scanny = new Scanner(System.in);
			int loop = scanny.nextInt();
			for(int x = 0; x < loop; x++){
				int size = scanny.nextInt();
				ArrayList<Integer> nums = new ArrayList<Integer>();
				for(int y = 0; y < size; y++){
					nums.add(scanny.nextInt());
					}
				
				ArrayList<Integer> inc = getIncSub(nums);
				Collections.reverse(nums);
				ArrayList<Integer> dec = getIncSub(nums);
				Collections.reverse(dec);
				int max = 0;
				for(int a = 0; a < dec.size(); a++){
					if(dec.get(a) + inc.get(a) -1 > max){
						max = dec.get(a) + inc.get(a) -1;
					}
				}
				System.out.println(max);
			}
	}

	private static ArrayList<Integer> getIncSub(ArrayList<Integer> nums) {
			ArrayList<Integer> list = new ArrayList<Integer>();
		int dp[]= new int[nums.size()];
			Arrays.fill(dp, 1);
			
			
			for(int x = 1; x < nums.size(); x++){
				
				for(int k = x-1; k>=0; k--){
					if(nums.get(x) >= nums.get(k)){
						dp[x] = Math.max(dp[k]+1, dp[x]);
					}
					
					
					
				}
		
			}
			for(int x = 0; x < dp.length; x++){
				list.add(dp[x]);
			}
	
			return list;
	
	}

}
/*

2
5
12
11
13
12
11
6
10
20
30
20
40
20
*/