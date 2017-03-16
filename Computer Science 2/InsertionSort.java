
public class InsertionSort 
{
	public static void main(String[] args){
		int[] testint = {4, 1, 8, 7, 2, 5};
		test(testint);
		for(int x = 0; x < testint.length; x++){
			System.out.println(testint[x]);
		}
		
		
	}
	public static void test(int[] ar){
		for(int x = 1; x < ar.length; x++){
			int lastvalue = ar[x];
			int y = x-1;
			
			while(y >= 0 && ar[y] > lastvalue){
				int temp = ar[y];
				ar[y] = ar[y+1];
				ar[y+1] = temp;
				y--;
			}

			
		}
	}
	public void DoInsertionSort( int[] array )
	{
		for( int i=1; i<array.length; i++ )
		{
			int key = array[i];
			int j = i;
			while( j > 0 && array[j-1] > key )
			{
				array[j] = array[j-1];
				j--;
			}
			array[j] = key;
		}
		
	}
}
