import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class arithmatic2 {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);

		int[] array = new int[4];
		int[] operations = { 1, 2, 3, 4 };
		array[0] = scanny.nextInt();
		while (array[0] != 0) {
			array[1] = scanny.nextInt();
			array[2] = scanny.nextInt();
			array[3] = scanny.nextInt();
			TreeSet<Integer> result = solve(array, 0, new TreeSet<Integer>());
			//for(int x = 0; x < result.size(); x++){
				System.out.println(result.toString());
		//	}
			
			array[0] = scanny.nextInt();
		}

	}
	public static HashSet<TreeSet<Integer>> getSets(int[] array){
		HashSet<int[]> hash = new HashSet<int[]>();
		hash.add(new int[] {array[0]});
		hash.add(new int[] {array[1], array[2], array[3]});
		hash.add(new int[] {array[0], array[1]});
		hash.add(new int[] {array[2], array[3]});
		hash.add(new int[] {array[0], array[1], array[2]});
		hash.add(new int[] {array[3]});
		return null;
		
		}
	
	
	public static TreeSet<Integer> solve(int[] list, int c, TreeSet<Integer> result){
		if(c == list.length){
			return result;
		}
		
		
		result.add(list[c]);
		System.out.println(result.toString());
		result.addAll(solve(list, c+1, result));
		System.out.println(result.toString());
		return result;
	}

		
		
		

	
	

}