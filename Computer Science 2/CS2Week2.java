import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2Week2 
{
	
	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "Cameron,Kelsey,k3593775");
	}
	
	//	Problem #1
	// Directions: Return true if the array contains, somewhere,
	// three increasing consecutive numbers like ....4, 5, 6,... or
	// 23, 24, 25.

	//	FindThreeIncreasingNumbers({1, 4, 5, 6, 2}) → true
	//	FindThreeIncreasingNumbers({1, 2, 3}) → true
	//	FindThreeIncreasingNumbers({1, 2, 4}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		returns true if there are three increasing consecutive numbers
	 * 		returns false if there are not three increasing consecutive numbers
	 */
	static boolean FindThreeIncreasingNumbers(int[] NumberList) 
	{
		for(int x = 0; x < NumberList.length-2; x++){
			if(NumberList[x] < NumberList[x+1] && NumberList[x+1] < NumberList[x+2]
					&& (NumberList[x]+1) == NumberList[x+1] 
							&& (NumberList[x+1]+1) == NumberList[x+2]){
				return true;
			}
				
			
			
		}
		
		return false;
		
	}

	//	Problem #2
	//	For each multiple of 10 in the given array, change all the values 
	//	following it to be that multiple of 10, until encountering another 
	//	multiple of 10. So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.

	//	multiplesOfTen({2, 10, 3, 4, 20, 5}) → {2, 10, 10, 10, 20, 20}
	//	multiplesOfTen({10, 1, 20, 2}) → {10, 10, 20, 20}
	//	multiplesOfTen({10, 1, 9, 20}) → {10, 10, 10, 20}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return NumberList
	 * 		int[] list of the same numbers changed to multiples of
	 * 		ten as they are encountered.
	 */
	static int[] multiplesOfTen(int[] NumberList) 
	{
		int prev = -99;
		for(int x = 0; x < NumberList.length; x++){
			if(NumberList[x] % 10 == 0){
				prev = NumberList[x];
			}
			else if(prev!= -99){
				NumberList[x] = prev;
			}
			
			
		}
		
		
		return NumberList;
	}	

	//	Problem #3
	//	We'll say that an element in an array is "alone" if there are 
	//	values before and after it, and those values are different 
	//	from it. Return a version of the given array where every instance 
	//	of the given value which is alone is replaced by whichever 
	//	value to its left or right is larger.

	//	CheckForAloneNumbers({1, 2, 3}, 2) → {1, 3, 3}
	//	CheckForAloneNumbers({1, 2, 3, 2, 5, 2}, 2) → {1, 3, 3, 5, 5, 2}
	//	CheckForAloneNumbers({3, 4}, 3) → {3, 4}
	
	/**
	 * 
	 * @param NumberList, changingNumber
	 * 		int[] list containing some numbers.
	 * 		int value of the number that should change in the array.
	 * 
	 * @return NumberList
	 * 		int[] list of numbers where every occurrence of changingNumber
	 * 		has been replaced by the larger of its two neighbors.
	 */
	static int[] CheckForAloneNumbers(int[] NumberList, int changingNumber) 
	{
		for(int x = 1; x < NumberList.length-1; x++){
			if(NumberList[x] == changingNumber){
				if(NumberList[x-1] != NumberList[x] 
						&& NumberList[x] != NumberList[x+1]){
					NumberList[x] = Math.max(NumberList[x-1], NumberList[x+1]);
				}
				
			}

		}
	
		return NumberList;
	}	

	//	Problem #4
	//	Return a version of the given array where each zero value in 
	//	the array is replaced by the largest odd value to the right 
	//	of the zero in the array. If there is no odd value to the 
	//	right of the zero, leave the zero as a zero. 

	//	ReplaceZerosWithLargestOdd({0, 5, 0, 3}) → {5, 5, 3, 3}
	//	ReplaceZerosWithLargestOdd({0, 4, 0, 3}) → {3, 4, 3, 3}
	//	ReplaceZerosWithLargestOdd({0, 1, 0}) → {1, 1, 0}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return NumberList
	 * 		int[] list containing the numbers where the zeros have been
	 * 		replaced with the largest odd number to the right of them.
	 */
	public static int[] ReplaceZerosWithLargestOdd(int[] NumberList) 
	{
		int state = -1;
		int maxodd = -1;
		for(int x = 0; x < NumberList.length; x++){
			if(NumberList[x] == 0){
				if(x == NumberList.length-1){
					state = 0;
				}
				for(int y = x; y < NumberList.length; y++){
					if(NumberList[y] % 2 != 0){
						maxodd = Math.max(maxodd, NumberList[y]);
						state = 1;
					}
					
					
				}
				if(state == 1)
				NumberList[x] = maxodd;
			}
			
			
		}
		
		
		return NumberList;
	}
	
	//	Problem #5
	//	Given start and end numbers, return a new array containing 
	//	the sequence of integers from start up to but not including end, 
	//	so start=5 and end=10 yields {5, 6, 7, 8, 9}. The end number 
	//	will be greater or equal to the start number. 
	//	Note that a length-0 array is valid. 

	//	CreateIncreasingArray(5, 10) → {5, 6, 7, 8, 9}
	//	CreateIncreasingArray(11, 18) → {11, 12, 13, 14, 15, 16, 17}
	//	CreateIncreasingArray(1, 3) → {1, 2}	
	
	/**
	 * 
	 * @param start, end
	 * 		Two integers stating the start and end of the sequence.
	 * 
	 * @return NumberList
	 * 		int [] containg numbers ranging from start to end
	 * 		in order from least to greatest.
	 */
	static int[] CreateIncreasingArray(int start, int end) 
	{
		int[] list = new int[end-start];
		int c = 0;
		for(int x = start; x < end; x++){
			list[c] = x;
			c++;
		}
		
		
		return list;
	}
	
	//	Problem #6
	//	Given a non-empty array of ints, return a new array containing 
	//	the elements from the original array that come before the 
	//	first 4 in the original array. The original array will contain 
	//	at least one 4. Note that it is valid in java to create 
	//	an array of length 0.  

	//	CopyNumbersBeforeFour({1, 2, 4, 1}) → {1, 2}
	//	CopyNumbersBeforeFour({3, 1, 4}) → {3, 1}
	//	CopyNumbersBeforeFour({1, 4, 4}) → {1}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return AbridgedList
	 * 		int[] list containing all the numbers that appeared
	 * 		before the first 4 in the array.
	 */
	static int[] CopyNumbersBeforeFour(int[] NumberList) 
	{
		int size = 0;
		for(int x = 0; x < NumberList.length; x++){
			if(NumberList[x] == 4){
				break;
			}
			size = x+1;
		}
		
		int[] list = new int[size];
		for(int x = 0; x < NumberList.length; x++){
			if(NumberList[x] == 4){
				break;
			}
			list[x] = NumberList[x];
		}
	
		return list;
		
		
	}
	
	//	Problem #7
	//	Return an array that contains the exact same numbers as 
	//	the given array, but rearranged so that all the zeros 
	//	are grouped at the start of the array. The order of the 
	//	non-zero numbers does not matter. So {1, 0, 0, 1} becomes 
	//	{0 ,0, 1, 1}. You may modify and return the 
	//	given array or make a new array.  

	//	MoveZerosToFront({1, 0, 0, 1}) → {0, 0, 1, 1}
	//	MoveZerosToFront({0, 1, 1, 0, 1}) → {0, 0, 1, 1, 1}
	//	MoveZerosToFront({1, 0}) → {0, 1}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return RearrangedList
	 * 		int[] list containing all the numbers from original
	 * 		list with the zeros moved to the front.
	 */
	static int[] MoveZerosToFront(int[] NumberList) 
	{
		Arrays.sort(NumberList);
		return NumberList;
	}
	
	//	Problem #8
	//	Return an array that contains the exact same numbers as 
	//	the given array, but rearranged so that all the even numbers 
	//	come before all the odd numbers. Other than that, the 
	//	numbers can be in any order. You may modify and 
	//	return the given array, or make a new array.  

	//	EvenFrontOddBack({1, 0, 1, 0, 0, 1, 1}) → {0, 0, 0, 1, 1, 1, 1}
	//	EvenFrontOddBack({3, 3, 2}) → {2, 3, 3}
	//	EvenFrontOddBack({2, 2, 2}) → {2, 2, 2}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return RearrangedList
	 * 		int[] list containing all the numbers from original
	 * 		list with the even numbers in the front and the
	 * 		odd numbers in the back.
	 */
	static int[] EvenFrontOddBack(int[] NumberList) 
	{
		int[] newlist = new int[NumberList.length];
		int c = 0; 
		for(int x = 0; x< NumberList.length; x++){
			if(NumberList[x] % 2 == 0){
				newlist[c] = NumberList[x];
				c++;
			}
			
		}
		for(int x = 0; x< NumberList.length; x++){
			if(NumberList[x] % 2 != 0){
				newlist[c] = NumberList[x];
				c++;
			}
			
		}
		
		return newlist;
	}
	static void printList(int[] list){
		System.out.println();
		for(int x = 0; x < list.length; x++){
			System.out.print(list[x] + " ");
		}
	}
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
		
	}
	
}