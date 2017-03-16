
public class quickSort2 {

	public static void main(String[] args) {
		int[] array = {3, 4, 2, 5, 7, 8, 1};
		quickSort(array);
		for(int x = 0; x < array.length; x++){
			System.out.print(array[x] + " ");
		}
	}
	public static void quickSort(int[] array){
		quickSort(array, 0, array.length-1);
		
		
	}
	public static void quickSort(int[] array, int left, int right){
		
		if(left < right){
		int pivot = partition(array, left, right);
		// Because pivot is already sorted!!!
		quickSort(array, left, pivot-1);
		quickSort(array, pivot+1, right);
		}
		
		
	}
	
	public static int partition(int[] array, int left, int right){
		int leftcount = left;
		int rightcount = right;
		int pivot = array[left];
		while(leftcount <= rightcount){
			
			while(array[leftcount] < pivot){
				leftcount++;
			}
			while(array[rightcount] > pivot){
				rightcount--;
			}
			if(leftcount<=rightcount){
				swap(array, leftcount, rightcount);
				leftcount++;
				rightcount--;
			}
		};
		return leftcount;
		
		
	}
	public static void swap(int[] array, int in1, int in2){
		int temp = array[in1];
		array[in1] = array[in2];
		array[in2] = temp;
		
	}
}
