/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  cipher.java
 * Date: March 17, 2016
 */
public class cipher {
	// defines table
	static double[] table = { 8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0,
			6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1 };

	// main method
	public static void main(String[] args) {
		System.out.println("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!");
		System.out.println(crack("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!"));
	}

	// converts number to alphabet
	public static int let2nat(char c) {

		return c - 'a';

	}
	// converts char to ascii
	public static char nat2let(int c) {

		return (char) ((int) c + (int) 'a');
	}
// shifts right if positive left if negative
	public static char shift(int num, char c) {
		// makes sure you shift through alphabet
		num%=26;
		// if you're past a or z return character
		if(c-'a'<0 || c-'a' > 26){
			return c;
		}
		// gets asci value
		int temp = let2nat(c);
		// adds num and 26 to it to prevent negative numbers
		temp = temp + num + 26;
		// keeps in range
		temp%=26;
		// returns the asci character of temp
		return nat2let(temp);
	}

	public static String encode(int num, String str) {
		// creates new string builder
		StringBuilder build = new StringBuilder();
		// loops through and shifts each char
		for(int x = 0; x < str.length(); x++){
			build.append(shift(num, str.charAt(x)));
		}
		
		// returns final string
		return build.toString();
	}

	public static String decode(int num, String str) {
		// creates stringbuilder
		StringBuilder build = new StringBuilder();
		// shifts each char left
		for (int x = 0; x < str.length(); x++) {
			build.append(shift(-num, str.charAt(x)));
		}
		// returns string
		return build.toString();
	}

	// counts lowercase letters
	public static int lowers(String str) {
		
		int count = 0;
		// loop through string
		for (int x = 0; x < str.length(); x++) {
			// if you're between a and z increment count
			if (str.charAt(x) - 'a' >=0 && str.charAt(x) -'a' < 26) {
				count++;
			}

		}
		return count;
	}

	public static int count(String str, char c) {
// loop through string
		int count = 0;
		for (int x = 0; x < str.length(); x++) {
			// if you find the char you want increment
			if (str.charAt(x) == c) {
				count++;
			}

		}
		return count;
	}

	public static double percent(int a, int b) {
		// gets percent val of two number
		double d = ((double) a / (double) b) * 100;

		return d;
	}

	static double[] freqs(String str) {
		// creates new list
		double[] list = new double[26];
		// gets num of lowercase letters
		int low = lowers(str);
		// for each letter get the percent relative to the lowercase frequency and how many lowercase exist
		for (int x = 0; x < 26; x++) {
			
				list[x] = percent(count(str, nat2let(x)), low);

		}
		return list;
	}


	static double[] rotate(int n, double[] list) {
		// makes sure n is in range
		n %= 26;
		// creates new list
		double[] newlist = new double[list.length];
		// loops through list and shifts by n value and adds 26 to eliminate negative, then % 26 to stay in bounds
		for (int x = 0; x < list.length; x++) {
			newlist[x] = list[(x + n + list.length) % list.length];
		}
		return newlist;
	}

	static double chisqr(double[] os) {
		double total = 0.0;
		// applys chisqr given algorithm to get array
		for (int x = 0; x < 26; x++) {
			total += Math.pow(os[x] - table[x], 2) / table[x];
		}

		return total;
	}
	// gets position in array you dont really need this.
	static int position(double a, double[] list) {
		for (int x = 0; x < list.length; x++) {
			if (Double.compare(list[x], a) == 0) {
				return x;
			}
		}
		return 0;
	}

	static String crack(String str) {
		// gets frequency
		double[] frequency = freqs(str);
		double[] chirot = new double[26];
		// sets smallest to max val
		double smallest = Double.MAX_VALUE;
		// creates smallest index
		int sm_ind = 0;
		// for each letter
		for (int x = 0; x < 26; x++) {
			
			// get the chisqr value
			chirot[x] = chisqr(frequency);
			// rotate once
			frequency = rotate(1, frequency);
			// if your square value is the smallest value, try that index to decode
			if (chirot[x] < smallest) {
				smallest = chirot[x];
				sm_ind = x;
			}
		}
		// return decoded string
		return decode(sm_ind, str);
	}

}
