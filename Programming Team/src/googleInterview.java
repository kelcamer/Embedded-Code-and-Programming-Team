

public class googleInterview {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(searchStrings("12345A", "12345"));
	}
	public static char searchStrings(String str1, String str2){

		int max = Math.max(str1.length(), str2.length());

		str1 = str1 + "  ";
		str2 = str2 + "  ";

		for(int x = 0; x <= max-1; x++){

		// skips every one of the same characters in each string
		    if(str1.charAt(x) != str2.charAt(x)){
		    if((str1.charAt(x) == str2.charAt(x+1))){
		    	return str2.charAt(x);
		    }
		 if(str1.charAt(x+1) == str2.charAt(x)){
			 return str1.charAt(x);
		
		}
		}
		}


		return ' ';
		}
		}


/*
 * 
 * 
 * 
 * 
 * 
 * Please use this Google doc to code during your interview. To free your hands for coding, we recommend that you use a headset or a phone with speaker option.
Kels1ey
Kelsey

12345A
12345


str1 = “12345A”;
str2 = “12345”;

str1.length() = 6;
str2.length() = 5;
6 > 5

6-1 = 5.  
max - 1 = 5.

x= 0.       |  charA = ‘1’ charB = ‘1’
x= 1.       |  charA = ‘2’ charB = ‘2’
x= 2.       |  charA = ‘3’ charB = ‘3’ 
x= 3.       | charA = ‘4’ charB = ‘4’
x= 4.       | charA = ‘5’ charB = ‘5’ 
x= 5.       | charA = ‘A’ 



What the computer sees if you skip the last index ^


public static char searchStrings(String str1, String str2){

int max = Math.max(str1.length(), str2.length());

str1 = str1+ “ “;
str2 = str2 + “ “;

for(int x = 0; x <= max-1; x++){

// skips every one of the same characters in each string
    if(str1.charAt(x) != str2.charAt(x)){
    if((str1.charAt(x) == str2.charAt(x+1)){
return str2.charAt(x);
}
 if(str1.charAt(x+1) == str2.charAt(x)){
return str1.charAt(x);

}
}


return ‘ ‘;
}




}



Implement contains() function
O(n^2)



12345A
51423







 * 
 * 
 * 
 * 
 * 
 * 
 * */
