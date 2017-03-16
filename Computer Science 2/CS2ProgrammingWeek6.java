import java.util.StringTokenizer;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek6
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
	//	Given a string, count the number of words ending in 'y' 
	//	or 'z' -- so the 'y' in "heavy" and the 'z' in "fez" count, 
	//	but not the 'y' in "yellow" (not case sensitive). We'll say 
	//	that a y or z is at the end of a word if there is not an 
	//	alphabetic letter immediately following it. (Note: 
	//	Character.isLetter(char) tests if a char is an alphabetic letter.) 

	//	wordEndYZ("fez day") → 2
	//	wordEndYZ("day fez") → 2
	//	wordEndYZ("day fyyyz") → 2
	
	/**
	 * 
	 * @param str
	 * 		str containing the original string
	 * 
	 * @return int
	 * 		int containing the # of words that end in y or z
	 */
	static int wordEndYZ(String str) 
	{
		// creates a count
		int count = 0; 
		// uses string tokenizer to get each word
		StringTokenizer tok = new StringTokenizer(str, " \t");
		// for all words
		while(tok.hasMoreElements()){
			// get next word
			String test = tok.nextToken();
			//if word ends in y or z, increment count
			if(test.charAt(test.length()-1) == 'y' || test.charAt(test.length()-1) == 'z'){
				count++;
			}
		}
		// return count
		return count;
	}

	//	Problem #2
	//	Given two strings, base and remove, return a version of the base 
	//	string where all instances of the remove string have been removed 
	//	(not case sensitive). You may assume that the remove string is length 
	//	1 or more. Remove only non-overlapping instances, so with "xxx" 
	//	removing "xx" leaves "x".

	//	removeFromBase("Hello there", "llo") → "He there"
	//	removeFromBase("Hello there", "e") → "Hllo thr"
	//	removeFromBase("Hello there", "x") → "Hello there"
	
	/**
	 * 
	 * @param base, remove
	 * 		base contains original string of characters
	 * 		remove contains original string that is to be removed from base
	 * 
	 * @return
	 * 		String containing the base with all instances of remove taken out
	 */
	static String removeFromBase(String base, String remove) 
	{
		// gets length of word to remove
		int num = remove.length();
		// if length is only one, remove only one instance of the letter
		if(num == 1){
			return base.replaceFirst(remove, "");
		}
		// else length is at least two.
		String next = "";
		// analyze each char and the one next to it.
		for(int x = 0; x < base.length()-1; x++){
			// if you find the first two letters of the substring you wanna remove
			if(base.charAt(x) == remove.charAt(0) && base.charAt(x+1) == remove.charAt(1)){
				// then take the first part of the base, and combine it with the substring after the removed index
				next = base.substring(0, x) + base.substring(x+remove.length());
			}
		}
		// returns string
		return next;
	}	

	//	Problem #3
	//	Given a string, return true if the number of appearances of 
	//	"is" anywhere in the string is equal to the number of appearances 
	//	of "not" anywhere in the string (case sensitive). 

	//	equalAppearance("This is not") → false
	//	equalAppearance("This is notnot") → true
	//	equalAppearance("noisxxnotyynotxisi") → true
	
	/**
	 * 
	 * @param str
	 * 		str contains the original string of characters
	 * 
	 * @return
	 * 		returns true if "is" appears as many times as "not"
	 * 		returns false if "is" does not appear as many times as "not"
	 */
	static boolean equalAppearance(String str) 
	{
		// creates two counts
		int c1 = 0, c2 = 0;
		// loops through array and analyzes each pair of chars
		for(int x = 0; x < str.length()-1; x++){
			// if you find an is, increment c1
			if(str.charAt(x) == 'i' && str.charAt(x+1) == 's'){
				c1++;
			}
		}
		// loops through array and analyzes each triple of characters
		for(int x = 0; x < str.length()-2; x++){
			// if you find a not, increment c2
			if(str.charAt(x) == 'n' && str.charAt(x+1) == 'o' && str.charAt(x+2) == 't'){
				c2++;
			}
		}
		// if they are equal return true else return false
		return (c1==c2);
		
	}	

	//	Problem #4
	//	We'll say that a lowercase 'g' in a string is "happy" if there 
	//	is another 'g' immediately to its left or right. Return true if 
	//	all the g's in the given string are happy. 

	//	gisHappy("xxggxx") → true
	//	gisHappy("xxgxx") → false
	//	gisHappy("xxggyygxx") → false
	
	/**
	 * 
	 * @param str
	 * 		String containing original string of characters
	 * 
	 * @return
	 * 		returns true if 'g' appears with another 'g' to it's right or left
	 * 		returns false if this is not the case
	 */
	static boolean gisHappy(String str) 
	{
		// creates a temp flag
		boolean flag = true;
		// if your length is equal to one return true, because it must be happy.
		if(str.length() <=1){
			// if your only char is a g, return false
			if(str.charAt(0) == 'g'){
				return false;
			}
			// else return true
			return true;
		}
		if(str.length() ==2){
			// if the first g is not next to the second g, return false, else return true
			if(str.charAt(0) == 'g' && str.charAt(1) != 'g'){
				return false;
			}
			return true;
		}
		// loop through list, looking at letter on left and right
		for(int x = 1; x < str.length()-1; x++){
			if(str.charAt(x) == 'g'){
				// if the one left is not a g and the one right is not a g, and you're at a g, word is unhappy
				if(str.charAt(x-1) != 'g' && str.charAt(x+1) != 'g'){
					flag = false;
				}
			}
		}
		
		return flag;
	}
	
	//	Problem #5
	//	We'll say that a "triple" in a string is a char appearing three times in a row. 
	//	Return the number of triples in the given string. The triples may overlap. 

	//	numberOfTriples("abcXXXabc") → 1
	//	numberOfTriples("xxxabyyyycd") → 3
	//	numberOfTriples("a") → 0	
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return
	 * 		Integer containing the # of "triples" in str
	 */
	static int numberOfTriples(String str) 
	{
		// if you have less than 3, return 0 triples
		if(str.length() <=2){
			return 0;
		}
		// make a count
		int count = 0;
		// loop through list starting from left and right
		for(int x = 1; x < str.length()-1; x++){
			// if you see a triple, increment count
			if(str.charAt(x) == str.charAt(x-1) && str.charAt(x) == str.charAt(x+1)){
				count++;
			}
		}
		// return count
		return count;
	}
	
	//	Problem #6
	//	Given a string, return the sum of the digits 0-9 that appear in the 
	//	string, ignoring all other characters. Return 0 if there are no digits 
	//	in the string. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.) 

	//	addUpDigits("aa1bc2d3") → 6
	//	addUpDigits("aa11b33") → 8
	//	addUpDigits("Chocolate") → 0
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # sum of all digits that appear in str
	 */
	static int addUpDigits(String str) 
	{
		// creates sum variable
		int sum = 0;
		// loop through string
		for(int x = 0; x < str.length(); x++){
			// if you find a digit
			if(Character.isDigit(str.charAt(x))){
				// get character, subtract 48 from the ascii value, and add to sum
				char c = str.charAt(x);
				int hello = c - 48;
				sum+=hello;
			}
		}
		return sum;
		
	}
	
	//	Problem #7
	//	Given a string, return the longest substring that appears at 
	//	both the beginning and end of the string without overlapping. 
	//	For example, beginningAndEndOfString("abXab") is "ab". 

	//	beginningAndEndOfString("abXYab") → "ab"
	//	beginningAndEndOfString("xx") → "x"
	//	beginningAndEndOfString("xxx") → "x"
	
	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning and ending substrings that are the same
	 */
	// finish this one
	static String beginningAndEndOfString(String string) 
	{
		// creates null string
		String hey = "";
		// loops through string
		for(int x = 0; x < string.length(); x++){
			// move divider from 0 to end of string, and the last one that it contains is the substring
			if(string.substring(0, x).contains(string.substring(x, string.length()))){
				hey = string.substring(x, string.length());
			}
			
		}
		return hey;
		}

	
	//	Problem #8
	//	Given a string, look for a mirror image (backwards) string at both 
	//	the beginning and end of the given string. In other words, zero or more 
	//	characters at the very beginning of the given string, and at the very 
	//	end of the string in reverse order (possibly overlapping). For example, 
	//	the string "abXYZba" has the mirror end "ab". 

	//	beginningMirrorEnd("abXYZba") → "ab"
	//	beginningMirrorEnd("abca") → "a"
	//	beginningMirrorEnd("aba") → "aba"
	
	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning of the string that is mirrored at the end
	 */
	static String beginningMirrorEnd(String string) 
	{
		// if you have one, then it mirrors itself
		if(string.length() == 1){
			return string;
		}
		// creates string builder
		StringBuilder hey = new StringBuilder();
		// gets middle point
		int halflength = string.length()/2;
		// start y at end point and x at beginnging
		int y = string.length()-1;
		// loop through from front to middle and end to middle
		for(int x = 0; x < halflength && y>=halflength; x++){
			// if the first and last chars are equal, add to stringbuilder
			if(string.charAt(x) == string.charAt(y)){
				hey.append(string.charAt(x));
			}
			// subtracts y to look at next char
			y--;
			
		}
		// returns mirrored string
		return hey.toString();
		}
	
	//	Problem #9
	//	Given a string, return the length of the largest "block" in the string. 
	//	A block is a run of adjacent chars that are the same. 

	//	largestBlock("hoopla") → 2
	//	largestBlock("abbCCCddBBBxx") → 3
	//	largestBlock("") → 0
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # of chars in the largest "block" in str
	 */
	static int largestBlock(String str) 
	{
		// if you have only one letter, you have 1 as largest block
		if(str.length()==1){
			return 1;
		}
		// creates max variable
		int max = 0;
		
		// loops through all except last char
		for(int x = 0; x < str.length()-1; x++){
			// starts count at 1 because you have at least 1 letter
			int count = 1;
			// analyzes each pair of letters and while they're the same, increment count
			while(str.charAt(x) == str.charAt(x+1) && x < str.length()-1){
				count++;
				x++;
				if(x+1 >= str.length()){
					// break if you exceed the string
					break;
				}
			
			}
			// gets max of max and count, and stores it in max
			max = Math.max(max, count);
			
		}
		return max;
	}
	
	//	Problem #10
	//	Given a string, return the sum of the numbers appearing in the string, 
	//	ignoring all other characters. A number is a series of 1 or more digit 
	//	chars in a row. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)

	//	addUpNumbers("abc123xyz") → 123
	//	addUpNumbers("aa11b33") → 44
	//	addUpNumbers("7 11") → 18
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the sum of all the numbers that appear in str
	 */
	static int addUpNumbers(String str) 
	{
		// creates a sum
		int sum = 0;
		for(int x = 0; x < str.length(); x++){
			// loops through string and makes a new string builder
			StringBuilder tok = new StringBuilder();
			// get nextchar
			char next = str.charAt(x);
		
			// if next is a digit
			while(Character.isDigit(next)){
				// append it to the string
				tok.append(next);
				// look at next digit
				x++;
				if(x == str.length()){
					break;
				}
				// gets next
				next = str.charAt(x);
			}
			// if you have a number, parse the token string and add to sum
			if(tok.toString().length()>0){
				sum+=Integer.parseInt(tok.toString());
			}
			
		}
		// return sum
		return sum;
		
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