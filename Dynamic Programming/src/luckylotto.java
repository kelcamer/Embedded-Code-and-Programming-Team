import java.util.HashMap;
import java.util.Scanner;

public class luckylotto {
	public static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		//int loop = scanny.nextInt();
		//for(int x = 0; x < loop; x++){
			int n1 = scanny.nextInt();
			int n2 = scanny.nextInt();
			
			System.out.println(getAnswer(n1,n2,1,0,1,1,0));
		
		//}
	}
	public static int getAnswer(int goallength, int end, int previous, int length, int count, int index, int a){
		if(goallength == 0){
			return 0;
		}
		if(goallength == 1){
			return end - (index-1);
		}
		if(count == goallength){
			//System.out.println(previous + " " + index);
			if(map.get(index) == null){
				map.put(index, previous);
				a++;

			}
			else{
			if(map.get(index) != previous){
			map.put(index, previous);
			a++;
			}
			}
			//System.out.println("A " + a);
			return a;
		}
		if(index > end){
			return 0;
		}
	
		previous = index;
		int max = 0;

		for(int x = index*2; x <= end; x++){
			max += getAnswer(goallength, end, previous, length+1, count+1, x, a);
		}
		
		
		
		
		return max;
		
	}

}


/*
Starting 







*/
