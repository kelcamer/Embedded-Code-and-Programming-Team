import java.util.Arrays;

public class googleinterview2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	//[4, 1, 2, 3, 6] -> 3


	public static int longestConSeq(int[] array){
	int[] seq = new int[array.length];
	Arrays.fill(seq, 1);
	for(int x = 0; x < array.length; x++){
	for(int y = x+1; y < array.length; y++){
			if(array[x]+1 == array[y]){
			seq[x]++;
			}
	}
	}
	return getMax(seq);
	}
	public static int getMax(int[] seq){
	int largest = 0;
	for(int z = 0; z < seq.length; z++){
	if(seq[z] > largest){
	largest = seq[z];
	}	

	}
	return largest;
	}






	/*[4, 1, 2, 3, 6] -> 3

	4,1,2,3,6,7,8,9

	1,4,5,6,10,11,12,13
	1,2,3,5,6,7,8,9,10,11
	0,1,3,4,5,6
	0,1,2,3,4,10

	[1, 2]

*/
	public static int compareFaster(int[] array){
	int count = 1;
	int prev = 0;
	for(int x = 0; x <= array.length-1; x++){
		if(array[x] + 1 == array[x+1]){
			count++;
	}	
		else{
	if(count > prev){
		prev = count;
	count = 1;
			

		}
		}
	if(x == array.length-1){
	if(count > prev){
		prev = count;
	count = 1;
			

		}
	}



	}
	return prev;
	}

/*	// O(n)




	5 -> 1
	  \
	   -> 2 -> 3 -> 4

	Longest path of consecutive integers: 3




	Math.max(go(1), go(0));



*/

}
