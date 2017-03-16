import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class cowroute {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		ArrayList<Integer> allcosts = new ArrayList<Integer>();

		for (int x = 0; x < loop; x++) {
			int from = scanny.nextInt();
			int to = scanny.nextInt();
			int number = scanny.nextInt();
			for(int y = 0; y < number; y++){
				int cost = scanny.nextInt();
				int numberofcities = scanny.nextInt();
				int[] cities = new int[numberofcities];
				for(int z = 0; z < numberofcities;z++){
					cities[z] = scanny.nextInt();
				}
				int state = 0;
				for(int z = 0; z < cities.length; z++){
					if(cities[z] == from){
						state =1;
					}
					if(state == 1 && cities[z] == to){
						allcosts.add(cost);
					}
					
				}
				
			}
			Collections.sort(allcosts);
			if(!allcosts.isEmpty()){
				System.out.println(allcosts.get(0));
			}
			else{
				System.out.println("-1");
			}
			allcosts.clear();
		}

	}

}
/*
1
1 2 3
3 3
3 2 1
4 4
2 1 4 2
8 5
4 1 7 8 2
*/