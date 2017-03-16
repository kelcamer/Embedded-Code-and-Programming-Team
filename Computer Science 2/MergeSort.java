
public class MergeSort 
{
	
	public void DoMergeSort( int[] a )
	{
		DoMergeSort( a, 0, a.length-1);
	}

	public void DoMergeSort( int[] a, int left, int right )
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
	
	void Merge( int[] a, int left, int center, int right )
	{
		// creates a temporary array
		int[] tmp = new int[a.length];
		
		//loops through temp and copies entire array  in there.
		for( int i=0; i<tmp.length; i++ ){
			tmp[i] = a[i];
		}

		// ai is just the left value
		int ai = left; 
		
		// bi is the point where you stop
		int bi = center + 1;
		// ci is also the left value.
		int ci = left;
		
		
		while( ai <= center && bi <= right )
		{
			// if the temp at the left index is less than the temp in the center
			if( tmp[ai] < tmp[bi] ){
				// store temp at left index into array at left+1.
				a[ci++] = tmp[ai++];
			}
			else {
				a[ci++] = tmp[bi++];
			}
		}
		
		while( ai <= center )
		{
			a[ci++] = tmp[ai++];
		}
		
		while( bi <= right )
		{
			a[ci++] = tmp[bi++];
		}
		
	}
	
}
