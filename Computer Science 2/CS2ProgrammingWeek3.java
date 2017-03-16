import java.util.ArrayList;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek3 
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
		// returns a string
		return( "Cameron,Kelsey,k3593775");
	}
	
	//	Problem #1
	//	Given a string and a non-empty substring sub, compute recursively if at 
	//	least n copies of sub appear in the string somewhere, possibly with 
	//	overlapping. N will be non-negative.

	//	subCopies("catcowcat", "cat", 2) → true
	//	subCopies("catcowcat", "cow", 2) → false
	//	subCopies("catcowcat", "cow", 1) → true
	
	/**
	 * 
	 * @param str, sub, n
	 * 		String str contains the original string to be tested against
	 * 		String sub contains the string that is used to test against str
	 * 		int n specifies how many copies of sub to check for
	 * 
	 * @return
	 * 		returns true if there are n copies of sub in str
	 * 		returns false if there are not n copies of sub in str
	 */
	static boolean subCopies(String str, String sub, int n) 
	{
		// checks if it contains a string
		if(str.contains(sub)){
			// removes first instance of string
			str = str.replaceFirst(sub, "");
			n--;
			
		}
		else{
			// if you removed the entire string
			if((n)==0){
				return true;
			}
			return false;
		}
		// else keep checking
		return subCopies(str, sub, n);

		
	}

	//	Problem #2
	//	Given a non-negative int n, return the sum of its digits recursively 
	//	(no loops). Note that mod (%) by 10 yields the rightmost 
	//	digit (126 % 10 is 6), while divide (/) by 10 removes the 
	//	rightmost digit (126 / 10 is 12).

	//	sumDigitsInNumber(126) → 9
	//	sumDigitsInNumber(49) → 13
	//	sumDigitsInNumber(12) → 3
	
	/**
	 * 
	 * @param n
	 * 		int n contains integer to deal with.
	 * 
	 * @return integer
	 * 		integer that is the sum of each digit in int n.
	 */
	static int sumDigitsInNumber(int n) 
	{
		int number = 0;
		// if number is evenly divisible by 10
		if(n%10 == 0){
			return number;
		}
		// add first digit
		number+=n%10;
		// divide by 10
		n/=10;
		// add remaining digits
		number+= sumDigitsInNumber(n);
		
		return number;
	}	

	//	Problem #3
	//	Given base and n that are both 1 or more, compute recursively (no loops) 
	//	the value of base to the n power, so powerN(3, 2) is 9 (3 squared).

	//	exponential(3, 1) → 3
	//	exponential(3, 2) → 9
	//	exponential(3, 3) → 27
	
	/**
	 * 
	 * @param base, n
	 * 		int base containing the base of the term
	 * 		int n containing the exponent of the term
	 * 
	 * @return integer
	 * 		integer that corresponds with equating base to the n power
	 */
	static int exponential(int base, int n) 
	{
		// anything ^ 0 = 1
		if(n == 0){
			return 1;
		}
		// anything ^ 1 = itself
		if(n == 1){
			return base;
		}
		// if it is even, then square it
		if(n % 2 == 0){
			base = base*base;
		}
		// if odd, square the base and continue
		if(n%2 != 0){
			base = exponential(base*base, n-1);
		}
		return base;
		
	}	

	//	Problem #4
	//	Given a string, compute recursively (no loops) a new string 
	//	where all the lowercase 'x' chars have been changed to 'y' chars. 

	//	changeXtoY("codex") → "codey"
	//	changeXtoY("xxhixx") → "yyhiyy"
	//	changeXtoY("xhixhix") → "yhiyhiy"
	
	/**
	 * 
	 * @param str
	 * 		String containing original string of chars to deal with
	 * 
	 * @return String
	 * 		String of characters where the lowercase x's have been changed to y's
	 */
	public static String changeXtoY(String str) 
	{
		// if str contains x
		if(str.contains("x")){
			// replaces x to y
			str = str.replace("x", "y");
			return str;
		}
		// if it doesn't contain x, keep trying
			str = changeXtoY(str);
			return str;
	}
	
	//	Problem #5
	//	Given an array of ints, compute recursively if the array 
	//	contains a 6. We'll use the convention of considering only 
	//	the part of the array that begins at the given index. 
	//	In this way, a recursive call can pass index+1 to move down 
	//	the array. The initial call will pass in index as 0. 

	//	find6({1, 6, 4}, 0) → true
	//	find6({1, 4}, 0) → false
	//	find6({6}, 0) → true	
	
	/**
	 * 
	 * @param nums, index
	 * 		int[] list containing the original array of numbers
	 * 		int containing the position to start in nums
	 * 
	 * @return boolean
	 * 		returns true if a 6 is found in the array
	 * 		returns false if no 6 is found in the array
	 */
	static boolean find6(int[] nums, int index) 
	{
		// flag will represent if 6 was found
		boolean flag = false;
		// if you're at the end of the array, return false
		if(index == nums.length){
			return flag;
		}
		// if you find a 6 return true
		if(nums[index] == 6){
			return true;
		}
		else{
			// keep searching and if you find a true at all, set flag to true
			flag |= find6(nums, index+1);
		}
		
		
		return flag;
	}
	
	//	Problem #6
	//	Given a string, compute recursively a new string where all 
	//	the adjacent chars are now separated by a "*".   

	//	insertAsterisk("hello") → "h*e*l*l*o"
	//	insertAsterisk("abc") → "a*b*c"
	//	insertAsterisk("ab") → "a*b"
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return AbridgedList
	 * 		String with an asterisk between each char
	 */
	static String insertAsterisk(String str) 
	{
		String firstchar ="";
		
		// if you have a string
		if(str.length() > 0){
			// get character and add an asterisk to it
		 firstchar = str.charAt(0) + "";
		 firstchar+= "*";
		}
		// if you have at least one remaining character and the firstcharacter exists
			if(str.length() > 1 && firstchar != ""){
				// append first character plus insert asterisk to rest of string
			str = firstchar + insertAsterisk(str.substring(1, str.length()));
			return str;
			}
		return str;
	}
	
	//	Problem #7
	//	We'll say that a "pair" in a string is two instances of 
	//	a char separated by a char. So "AxA" the A's make a pair. 
	//	Pair's can overlap, so "AxAxA" contains 3 pairs -- 2 for 
	//	A and 1 for x. Recursively compute the number of 
	//	pairs in the given string.  

	//	numberPairs("axa") → 1
	//	numberPairs("axax") → 2
	//	numberPairs("axbx") → 1
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars provided
	 * 
	 * @return 
	 * 		int with the number of pairs as defined above
	 */
	static int numberPairs(String str) 
	{
		int pairs = 0;
		
		// if your string exists
		if(str.length() > 0){
			// remember str in temp
			String temp = str;
			// get first character
			String firstchar = str.charAt(0) + "";
			// cuts off first character
			str = str.substring(1);
			// if you have the first character in the remaining string
			if(temp.substring(1).contains(firstchar)){
				// increment by 1 and keep going
				pairs = numberPairs(str) +1;
			}
			else{
				// recurse
				pairs = numberPairs(str);
			}
		
			
		}
		
		
		
		return pairs;
	}
	
	//	Problem #8
	//	Given a string, return recursively a "cleaned" string where 
	//	adjacent chars that are the same have been reduced 
	//	to a single char. So "yyzzza" yields "yza".  

	//	reduceChars("yyzzza") → "yza"
	//	reduceChars("abbbcdd") → "abcd"
	//	reduceChars("Hello") → "Helo"
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return 
	 * 		String with all repeated, adjacent chars are removed
	 */
	static String reduceChars(String str) 
	{
		// if you can't compare two letters anymore, stop
		if (str.length() < 2){
			return str;
		}
		// if first two characters are equal, cut one off
		  if (str.charAt(0) == str.charAt(1)){
		    return reduceChars(str.substring(1));
		  }
		  else{
			  // else return first character plus the characters of the rest
		    return str.charAt(0) + reduceChars(str.substring(1));
		  }
	}
	
	//	Problem #9
	//	Given a string, return true if it is a nesting of zero or more 
	//	pairs of parenthesis, like "(())" or "((()))". Suggestion: 
	//	check the first and last chars, and then recur on what's inside them.  

	//	nestedParens("(())") → true
	//	nestedParens("((()))") → true
	//	nestedParens("(((x))") → false
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return 
	 * 		returns true if there are zero or more pairs of parenthesis
	 * 		returns false if there are not zero or more pairs of parenthesis
	 */
	static boolean nestedParens(String str) 
	{
		// determines if you have an even amount of pairs.
		boolean flag = true;
		
		// if you have at least length 2 string
		if(str.length() > 1){
			// if you start and end with parenthesis
			if(str.charAt(0) == '(' && str.charAt(str.length()-1) == ')'){
				// then remove the remaining parenthesis and determine if you removed all
				flag &= nestedParens(str.substring(1, str.length()-1));
			}
			else{
				// false means you encountered one without the other
				return false;
			}
			
		}
		
		
		return flag;
	}
	
	//	Problem #10

	//	Given a string and a non-empty substring sub, compute 
	//	recursively the largest substring which starts and 
	//	ends with sub and return its length.  

	//	subStrSub("catcowcat", "cat") → 9
	//	subStrSub("catcowcat", "cow") → 3
	//	subStrSub("cccatcowcatxx", "cat") → 9
	
	/**
	 * 
	 * @param str, sub
	 * 		String containing the original chars to be tested against
	 * 		String containing the original chars to test with
	 * 
	 * @return 
	 * 		integer containing the largest number of chars in string 
	 * 		that start and end with sub
	 */
	static int subStrSub(String str, String sub) 
	{
		// gets firstindex
		int first = str.indexOf(sub);
		
		if(first < 0 && str.contains(sub)){
			// if str contains sub, return sub.length
			return sub.length();
		}
		// if first is less than zero, then return sub.length
		else if(first < 0){
			return sub.length();
		}
		// gets last index of sub
		int last = str.lastIndexOf(sub);
		
		// returns the last index minus the first plus the remaining length recursively
		return last - first + subStrSub(str.substring(str.length()), sub);
		
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