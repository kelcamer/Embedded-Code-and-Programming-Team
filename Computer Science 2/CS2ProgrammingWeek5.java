
///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek5 
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
	//	Given an array of ints, is it possible to choose a group 
	//	of some of the ints, such that the group sums to the given 
	//	target? This is a classic backtracking recursion problem. 
	//	Once you understand the recursive backtracking strategy in 
	//	this problem, you can use the same pattern for many problems to
	//	search a space of choices. Rather than looking at the whole array, 
	//	our convention is to consider the part of the array starting at 
	//	index start and continuing to the end of the array. The caller 
	//	can specify the whole array simply by passing start as 0. No loops 
	//	are needed -- the recursive calls progress down the array. 

	//	groupSumsTarget(0, {2, 4, 8}, 10) → true
	//	groupSumsTarget(0, {2, 4, 8}, 14) → true
	//	groupSumsTarget(0, {2, 4, 8}, 9) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget(int start, int[] nums, int target) 
	{	
		// if you reach zero, you must have gotten to the target
		if(target == 0){
			return true;
		}
		// if you get to nums.length and still haven't found it, stop and return false.
		if(start >= nums.length){
			return false;
		}
		// if the target subtracted too much, return false,
		if(target < 0){
			return false;
		}
		// both sum and not sum.
			boolean flag1 = groupSumsTarget(start+1, nums, target-nums[start]);
			boolean flag2 = groupSumsTarget(start+1, nums, target);
			// if choosing or not choosing was able to get you to the desired target, return true
			return flag1 || flag2;
		
	}

	//	Problem #2
	//	Given an array of ints, is it possible to choose a group of 
	//	some of the ints, beginning at the start index, such that 
	//	the group sums to the given target? However, with the additional 
	//	constraint that all 6's must be chosen. (No loops needed.)

	//	groupSumsTarget6(0, {5, 6, 2}, 8) → true
	//	groupSumsTarget6(0, {5, 6, 2}, 9) → false
	//	groupSumsTarget6(0, {5, 6, 2}, 7) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including all 6's in the group
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget6(int start, int[] nums, int target) 
	{	
		// if you reach zero, you subtracted the target amount.
		if(target == 0){
			return true;
		}
		// if you didn't find it, stop and return false.
		if(start >= nums.length){
			return false;
		}
		// if you subtracted too much, return false.
		if(target < 0){
			return false;
		}
		// if you encounter a 6 you must take it.
		if(nums[start] == 6){
			return groupSumsTarget6(start+1, nums, target-nums[start]);
		}
		// choose and don't choose every other number, and return true if either worked.
			boolean flag1 = groupSumsTarget6(start+1, nums, target-nums[start]);
			boolean flag2 = groupSumsTarget6(start+1, nums, target);
			return flag1 || flag2;
	}

	//	Problem #3
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with this 
	//	additional constraint: If a value in the array is chosen to be in 
	//	the group, the value immediately following it in the array 
	//	must not be chosen. (No loops needed.)

	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 12) → true
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 14) → false
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 7) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetNoAdj(int start, int[] nums, int target) 
	{
		// if you made it to target return 0
		if(target == 0){
			return true;
		}
		// if you went too far, return false
		if(start >= nums.length){
			return false;
		}
		// if you subtracted too much, return false.
		if(target < 0){
			return false;
		}
		// basically see if skipping and not skipping current number will work.
		// don't take current and take next
		if(groupSumsTargetNoAdj(start+2, nums, target-nums[start])){
			return true;
		}
		// don't take next
		return groupSumsTargetNoAdj(start+1, nums, target);
			
	}	

	//	Problem #4
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with these 
	//	additional constraints: all multiples of 5 in the array must be 
	//	included in the group. If the value immediately following a multiple 
	//	of 5 is 1, it must not be chosen. (No loops needed.) 

	//	groupSumsTarget5(0, {2, 5, 10, 4}, 19) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 17) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 12) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget5(int start, int[] nums, int target) 
	{
		// if target is 0 you made it, congrats, return true
		if(target == 0){
			return true;
		}
		// if you went too far, return false
		if(start >= nums.length){
			return false;
		}
		// if you subtracted too much, return false.
		if(target < 0){
			return false;
		}
		// if you get a 5 you must take it.
		if(nums[start] == 5){
			return groupSumsTarget5(start+1, nums, target-nums[start]);
		}
		// if you encounter a 1 and the one directly before it was a 5, you must not take it.
		if(nums[start] == 1 && start >0 && nums[start-1] == 5){
			return groupSumsTarget5(start+1, nums, target);
		}
		// get result of choosing both options
			boolean flag1 = groupSumsTarget5(start+1, nums, target-nums[start]);
			boolean flag2 = groupSumsTarget5(start+1, nums, target);
			return flag1 || flag2;
	}
	
	//	Problem #5
	//	Given an array of ints, is it possible to choose a group of some of 
	//	the ints, such that the group sums to the given target, with this 
	//	additional constraint: if there are numbers in the array that are adjacent 
	//	and the identical value, they must either all be chosen, or none of 
	//	them chosen. For example, with the array {1, 2, 2, 2, 5, 2}, either all 
	//	three 2's in the middle must be chosen or not, all as a group. (one loop 
	//	can be used to find the extent of the identical values). 

	//	groupSumsTargetClump(0, {2, 4, 8}, 10) → true
	//	groupSumsTargetClump(0, {1, 2, 4, 8, 1}, 14) → true
	//	groupSumsTargetClump(0, {2, 4, 4, 8}, 14) → false	
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */

	static boolean groupSumsTargetClump(int start, int[] nums, int target) 
	{
		// if you reached target return true
		if(target == 0){
			return true;
		}
		// if you go too far return false
		if(start >= nums.length){
			return false;
		}
		// creates a count variable and sets it to start+1 index.
		
		int count = 0;
		for(count = start+1; count < nums.length && nums[count] == nums[start]; count++){
			// counts up how many numbers you have of the same number.
		}
		// subtract the multiplication of number of numbers times their value
		if(groupSumsTargetClump(count, nums, target-((count-start) * nums[start]))){
			return true;
		}
		// else keep trying
		return groupSumsTargetClump(count, nums, target);
	}
	
	//	Problem #6
	//	Given an array of ints, is it possible to divide the ints into two 
	//	groups, so that the sums of the two groups are the same. Every int must 
	//	be in one group or the other. Write a recursive helper method that takes 
	//	whatever arguments you like, and make the initial call to your recursive 
	//	helper from splitArray(). (No loops needed.)    

	//	divideArray({2, 2}) → true
	//	divideArray({2, 3}) → false
	//	divideArray({5, 2, 3}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divideArray(int[] nums) 
	{
		return splitArray(0, 0, nums);
	}
	static boolean splitArray(int sum, int count, int[] nums){
		// if you made it, return true
		if(count == nums.length){
			if(sum == 0){
				return true;
			}
			else{
				return false;
			}
		}
		// if subtracting worked, return true
		if(splitArray(sum-nums[count], count+1, nums)){
			return true;
		}
		// else try adding.
		return splitArray(sum+nums[count], count+1, nums);
	}

	//	Problem #7
	//	Given an array of ints, is it possible to divide the ints into two groups, 
	//	so that the sum of one group is a multiple of 10, and the sum of the 
	//	other group is odd. Every int must be in one group or the other. Write 
	//	a recursive helper method that takes whatever arguments you like, and 
	//	make the initial call to your recursive helper from 
	//	splitOdd10(). (No loops needed.)  

	//	oddDivide10({5, 5, 5}) → true
	//	oddDivide10({5, 5, 6}) → false
	//	oddDivide10({5, 5, 6, 1}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met 
	 */
	static boolean oddDivide10(int[] nums) 
	{
		return splitOdd10(0,0,0,nums);
	}
	// creates two groups
	static boolean splitOdd10(int sum1, int sum2, int count, int[] nums){
		if(count == nums.length){
			// if one group is divisible by 10 and the other is even or vice versa return true.
			return (sum1 % 10 == 0 && sum2 %2 != 0) || (sum1 %10!= 0 && sum2%2==0);
	
		}
		// if adding to first group works, return true
		if(splitOdd10(sum1+nums[count], sum2, count+1, nums))
			return true;
		// else add to second group
		return splitOdd10(sum1, sum2 + nums[count], count+1, nums);
	}
	//	Problem #8
	//	Given an array of ints, is it possible to divide the ints into 
	//	two groups, so that the sum of the two groups is the same, with 
	//	these constraints: all the values that are multiple of 5 must 
	//	be in one group, and all the values that are a multiple of 3 
	//	(and not a multiple of 5) must be in the other. (No loops needed.)  

	//	divide53({1,1}) → true
	//	divide53({1, 1, 1}) → false
	//	divide53({2, 4, 2}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divide53(int[] nums) 
	{
		return same(0,0,0,nums);
	}
	static boolean same(int sum1, int sum2, int count, int[] nums){
		if(count == nums.length){
			if(sum1==sum2){
				// you got an equal sum, return true
				return true;
			}
			else{
				return false;
			}
		}
		// if you got a 3 you must add it to the first group
		if(nums[count] == 3){
			return same(sum1+nums[count], sum2, count+1, nums);
		}
		// if you got a 5 you must add it to last group
		if(nums[count] == 5){
			return same(sum1, sum2+nums[count], count+1, nums);
		}
		// else try both options.
		if(same(sum1+nums[count], sum2, count+1, nums)){
			return true;
		}
		
		
		return same(sum1, sum2+nums[count], count+1, nums);
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
