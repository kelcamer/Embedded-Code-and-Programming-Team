import java.util.Arrays;
import java.util.HashSet;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek7
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
		return("Cameron,Kelsey,k3593775");
	}
	
	//	Problem #1
	//	We want to make a row of bricks that is goal inches long. We have a number of 
	//	small bricks (1 inch each) and big bricks (5 inches each). Return true if it 
	//	is possible to make the goal by choosing from the given bricks. This is a 
	//	little harder than it looks and can be done without any loops.

	//	makeRowOfGoalBricks(3, 1, 8) → true
	//	makeRowOfGoalBricks(3, 1, 9) → false
	//	makeRowOfGoalBricks(3, 2, 10) → true
	
	/**
	 * 
	 * @param small, big, goal
	 * 		int containing the number of 1inch bricks available
	 * 		int containing the number of 5inch bricks available
	 * 		int containing the number of inches for the goal
	 * 
	 * @return 
	 * 		returns true if the goal can be reached with the available bricks
	 * 		returns false if the goal cannot be reached with the available bricks
	 */
	static boolean makeRowOfGoalBricks(int small, int big, int goal) 
	{
		// if you reach 0, then you've made the brick
		if(goal == 0){
			return true;
		}
		// if you have more smalls, test if using a small will get you to your goal, and subtract 1 from goal
		if(small > 0){
		if(makeRowOfGoalBricks(small-1, big, goal-1)){
			return true;
		}
		}
		// if you have more bigs, test if using a big will get you to your goal, and subtract 5 from goal
		if(big > 0){
		return makeRowOfGoalBricks(small, big-1, goal - 5);
		}
		// if that didn't work, return false.
		return false;
	}

	//	Problem #2
	//	Given 3 int values, a b c, return their sum. However, if one of the values 
	//	is the same as another of the values, it does not count towards the sum.

	//	sumExcludingDuplicates(1, 2, 3) → 6
	//	sumExcludingDuplicates(3, 2, 3) → 2
	//	sumExcludingDuplicates(3, 3, 3) → 0
	
	/** 
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where duplicates are not included
	 */
	static int sumExcludingDuplicates(int a, int b, int c) 
	{
		// goes through all possible cases of adding and making sure numbers are not equal
		// in this case, only a and c are added because a and b are equal
		if(a == b && a!=c){
			return a + c;
		}
		// if all are equal
		if(a == b && a == c){
			return a;
		}
		// if only a = c
		if(a !=b && a == c){
			return a+b;
		}
		// if b = c
		if(b==c && a!=c){
			return a+c;
		}
		// else return the sum of everything
		return a + b + c;
	}	

	//	Problem #3
	//	Given 3 int values, a b c, return their sum. However, if one of the values is 
	//	13 then it does not count towards the sum and values to its right do not 
	//	count. So for example, if b is 13, then both b and c do not count. 

	//	sumExcludingUnluckyNums(1, 2, 3) → 6
	//	sumExcludingUnluckyNums(1, 2, 13) → 3
	//	sumExcludingUnluckyNums(1, 13, 3) → 1
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where values to the right of 13, inclusive, are not included
	 */
	static int sumExcludingUnluckyNums(int a, int b, int c) 
	{
		// if first value is 13, ignore rest
		if(a == 13){
			return 0;
		}
		// if b is, only add a
		if(b == 13){
			return a;
		}
		// if c is return a+b
		if(c == 13){
			return a+b;
		}
		// else return everything
		return a+b+c;
	}	

	//	Problem #4
	//	Given 3 int values, a b c, return their sum. However, if any of the values is a 
	//	teen -- in the range 13..19 inclusive -- then that value counts as 0, except 15 
	//	and 16 do not count as teens. Write a separate helper "public int fixTeen(int n) 
	//	{"that takes in an int value and returns that value fixed for the teen rule. In 
	//	this way, you avoid repeating the teen code 3 times (i.e. "decomposition").

	//	sumExcludingTeens(1, 2, 3) → 6
	//	sumExcludingTeens(2, 13, 1) → 3
	//	sumExcludingTeens(2, 1, 14) → 3
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where teens are not included
	 */
	static int sumExcludingTeens(int a, int b, int c) 
	{
		// adds all of them together ignoring specific teens
		return fixTeen(a) + fixTeen(b) + fixTeen(c);
	}
	public static int fixTeen(int n){
		// if you're between 13 and 19 but not 15 or 16, don't count num and return 0
		if(n >= 13 && n<=19 && n!=15 && n!=16){
			return 0;
		}
		// else return number
		return n;
	}
	
	
	//	Problem #5
	//	For this problem, we'll round an int value up to the next multiple of 10 if its rightmost 
	//	digit is 5 or more, so 15 rounds up to 20. Alternately, round down to the previous multiple 
	//	of 10 if its rightmost digit is less than 5, so 12 rounds down to 10. Given 3 ints, 
	//	a b c, return the sum of their rounded values. To avoid code repetition, write a separate 
	//	helper "public int round10(int num) {" and call it 3 times. Write the helper entirely below 
	//	and at the same indent level as roundSum().

	//	roundedSum(16, 17, 18) → 60
	//	roundedSum(12, 13, 14) → 30
	//	roundedSum(6, 4, 4) → 10
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where each value is rounded to the nearest tens place
	 */
	static int roundedSum(int a, int b, int c) 
	{
		// sets three variables
		int a1=0,b1=0,c1=0;
		// if a is negative, round and make negative
		if(a < 0){
			a1 = round10(a) * -1;
		}
		else{
			// else round
			a1 = round10(a);
		}
		// rounds the rest of the numbers
		if(b < 0){
			b1 = round10(b) * -1;
		}
		else{
			b1 = round10(b);
		}
		if(c < 0){
			c1 = round10(c) * -1;
		}
		else{
			c1 = round10(c);
		}
		// gets the sum of the three
		return a1 + b1 + c1;
	}
	
	static int round10(int num)
	{
		// converts num to a string
		String str = num + "";
		// creates string builder
		StringBuilder append = new StringBuilder();
		// if you have more than 1 char
		if(str.length() > 1){
			
			// add the string except last char
			append.append(str.subSequence(0, str.length()-1));
			// parse last int
			int num2 = Integer.parseInt(str.charAt(str.length()-1) +"");
			// if last num is >= 5, add a zero and add 10
			if(num2 >=5){
				return 10 + Integer.parseInt(append.toString() + "0");
			}
			// else just add a zero
			else{
				return Integer.parseInt(append.toString() + "0");
			}
		}
		// basically does the same thing, just returns either 10 or 0
		if(str.length() == 1){
			int num2 = Integer.parseInt(str.charAt(str.length()-1) +"");
			if(num2 >=5){
				return 10;
			}
			else{
				return 0;
			}
			
		}
		return num;
	}
	
	//	Problem #6
	//	Given three ints, a b c, return true if one of b or c is "close" (differing from 
	//	a by at most 1), while the other is "far", differing from both other values by 2 
	//	or more. Note: Math.abs(num) computes the absolute value of a number. 

	//	isCloseAndFar(1, 2, 10) → true
	//	isCloseAndFar(1, 2, 3) → false
	//	isCloseAndFar(4, 1, 3) → true
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints with original integers to compute relativity
	 * 
	 * @return 
	 * 		returns true if one of b or c is close to a and if the other is far from both other values
	 */
	static boolean isCloseAndFar(int a, int b, int c) 
	{
		// get difference between b and a or c and a, make sure that they're within 1, and make sure other two are far
		if((Math.abs(b-a) <= 1 && Math.abs(c-a) >= 2) || (Math.abs(c-a) <= 1 && Math.abs(b-a) >= 2)){
			if(Math.abs(c-b) >= 2)
				return true;
		}
		return false;
	}
	
	//	Problem #7
	//	Given 2 int values greater than 0, return whichever value is nearest to 21 without 
	//	going over. Return 0 if they both go over. 

	//	blackjack(19, 21) → 21
	//	blackjack(21, 19) → 21
	//	blackjack(19, 22) → 19
	
	/**
	 * 
	 * @param a, b
	 * 		ints representing the values of two cards in a game of black jack
	 * 
	 * @return 
	 * 		returns the value of the int that is closest to 21 without going over
	 */
	static int blackjack(int a, int b) 
	{
		// if b doesn't work choose a
		if(b > 21 && a <= 21){
			return a;
		}
		// if a doesn't work choose b
		if(a > 21 && b <= 21){
			return b;
		}
		//  gets max if less than 21
		if(a <=21 && b <=21){
			return Math.max(a, b);
		}
		return 0;
	}
	
	//	Problem #8
	//	Given three ints, a b c, one of them is small, one is medium and one is large. 
	//	Return true if the three values are evenly spaced, so the difference between 
	//	small and medium is the same as the difference between medium and large. 

	//	spacedEvenly(2, 4, 6) → true
	//	spacedEvenly(4, 6, 2) → true
	//	spacedEvenly(4, 6, 3) → false
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing original integers to compute with
	 * 
	 * @return 
	 * 		returns true if the input values are evenly spaced
	 * 		returns false if the input values are not evenly spaced
	 */
	static boolean spacedEvenly(int a, int b, int c) 
	{
		// makes an array
		int[] num = {a,b,c};
		// sorts array
		Arrays.sort(num);
		// if difference is same return true
		if(Math.abs(num[0] - num[1]) == Math.abs(num[1] - num[2])){
			return true;
		}
		return false;
	}
	
	//	Problem #9
	//	We want to make a package of goal kilos of chocolate. We have small bars 
	//	(1 kilo each) and big bars (5 kilos each). Return the number of small bars 
	//	to use, assuming we always use big bars before small bars. Return -1 
	//	if it can't be done.

	//	makeKilosOfChocolate(4, 1, 9) → 4
	//	makeKilosOfChocolate(4, 1, 10) → -1
	//	makeKilosOfChocolate(4, 1, 7) → 2
	
	/**
	 * 
	 * @param small, big, goal
	 * 		int containing the number of 1kilo bars available
	 * 		int containing the number of 5kilo bars available
	 * 		int containing the number of kilos for the goal
	 * 
	 * @return 
	 * 		returns the value of the number of small bars needed to meet the goal
	 */
	static int makeKilosOfChocolate(int small, int big, int goal) 
	{
		// subtracts 5 times the big bars
		goal -= big*5;
		// if you don't have enough small bars, return -1
		if(goal > small){
			return -1;
		}
		// if you have more than enough, return the # you use
		if(goal < small){
			return Math.abs(goal - small);
		}
		// else use all
		return small;

		
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