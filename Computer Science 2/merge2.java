import java.util.Arrays;

public class merge2 
{
	public static void main(String[] args){
		int[] array = {6, 3, 7, 1, 5, 2, 8};
		DoMergeSort(array);
		System.out.println();
		for(int x = 0; x<array.length; x++){
			System.out.print(array[x] + " ");
		}
		
		
	}
	public static void DoMergeSort( int[] a )
	{
		DoMergeSort( a, 0, a.length-1);
	}

	public static void DoMergeSort( int[] a, int left, int right )
	{
		// if there is more to be sorted, continue until size = 1
		if( left >= right ) return;
		// gets the center
		int center = left + ( right - left ) / 2;
		// sorts the left half
		DoMergeSort( a, left, center );
		
		// sorts the right half, the center is +1 because center itself is sorted already
		DoMergeSort( a, center + 1, right );
		
		// merges the two 
		Merge( a, left, center, right );
		
	}
	
	static void Merge( int[] a, int left, int center, int right )
	{
		int leftcount = left;
		int centercount = center+1;
		int a_ind = left;
		int[] temp = new int[a.length];
		for(int x = 0; x < a.length; x++){
			temp[x] = a[x];
		}
		while(leftcount <= center && centercount <= right){
			if(temp[leftcount] < temp[centercount]){
				a[a_ind] = temp[leftcount];
				a_ind++; leftcount++;
			}
			else{
				a[a_ind] = temp[centercount];
				centercount++; a_ind++;
			}
		}
		while(leftcount<=center){
			a[a_ind] = temp[leftcount];
			leftcount++;
			a_ind++;
		}
		while(centercount <= right){
			a[a_ind] = temp[centercount];
			centercount++;
			a_ind++;
		}
		
	}
	
	
}
