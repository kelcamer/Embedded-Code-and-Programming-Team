import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class dolphin {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int lo = scanny.nextInt();
		for(int x = 0; x < lo; x++){
			int size = scanny.nextInt();
			ArrayList<Integer> nums = new ArrayList<Integer>();
			for(int y = 0; y < size; y++){
				nums.add(scanny.nextInt());
			}
			ArrayList<Integer> inc = getIncList(nums);
			Collections.reverse(nums);
			ArrayList<Integer> dec = getIncList(nums);
			Collections.reverse(dec);
			int max = 0;
			for(int a = 0; a < dec.size(); a++){
			//	System.out.println(inc.get(a) + " " + dec.get(a));
				if(max < dec.get(a) + inc.get(a)-1){
					max = dec.get(a) + inc.get(a)-1;
				}
			
			}
			System.out.println(max);
			
		}
		
	}

	private static ArrayList<Integer> getIncList(ArrayList<Integer> nums) {
		int[] inc = new int[nums.size()];
		ArrayList<Integer> list = new ArrayList<Integer>();
		Arrays.fill(inc, 1);
		
		for(int x = 1; x < inc.length; x++){
			for(int k = x-1; k >=0; k--){

				if(nums.get(x) >= nums.get(k)){
					inc[x] = Math.max(inc[k]+1, inc[x]);
				}
				
			}
			
			
		}
		for(int a = 0; a < inc.length; a++){
			list.add(inc[a]);
		}
		return list;
	}

	
}
/*
1
10
1
10
2
9
3
8
4
7
5
6



1
9
1
2
3
4
5
4
3
2
1

1
19
1
2
7
6
5
4
5
6
7
8
9
8
7
6
5
4
3
2
1

*
*
*/
