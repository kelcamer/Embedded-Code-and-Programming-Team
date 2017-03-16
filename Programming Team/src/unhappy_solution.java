import java.io.*;
import java.util.*;

/**
 * Solution to Unhappy Numbers
 * 
 * @author vanb
 */
public class unhappy_solution
{
    public Scanner sc;
    public PrintStream ps;
    
    /** Memoization for the happiness for 0-1999 */
    private int memo[] = new int[2000];
    
    /** Unhappiness for 0-1999 */
    private boolean unhappy[] = new boolean[2000];
    
    /** Memoization for the digit squared counts for 9, 99, 999, 9999, etc. */
    private HashMap<Long,long[]> nines = new HashMap<Long,long[]>();
    
    /**
     * Sum of digits squared.
     * 
     * @param x Any non-negative long
     * @return The sum of the squares of x's digits
     */
    private int digitsumsq( long x )
    {
        int sum = 0;
        while( x>0 )
        {
            int digit = (int)(x%10);
            sum += digit*digit;
            x /= 10;
        }
        
        return sum;
    }
    
    /**
     * The Happiness number of a number, or -2 if it is Unhappy.
     * 
     * @param x Any non-negative long
     * @return The Happiness number of x, or -2 if x is Unhappy.
     */
    private int happiness( int x )
    {
        // In our memoization array, -1 means that it hasn't been calculated yet.
        if( memo[x] == -1 )
        {
            // -2 means it loops - it's unhappy.
            // We'll set it now, in case we loop back to it.
            memo[x] = -2;
            
            // Get the sum of digit squares
            int sum = digitsumsq(x);
            
            // If it's one, we're happy!
            if( sum==1 )
            {
                memo[x] = 0;
            }
            else 
            {
                // Otherwise, find the happiness of the digit sum.
                int sumhappy = happiness( sum );
                
                // If it's -2, the number is unhappy 
                // (so, we'll leave memo[x] as -2)
                if( sumhappy>=0 )
                {
                    // Otherwise, we're one step further away,
                    // so our happiness is 1 + the digit sum's happiness.
                    memo[x] = 1 + sumhappy;
                }
            }
        }
        
        return memo[x];
    }
    
    /**
     * Produce an array of sum counts for all numbers from 0 to x, inclusive.
     * So, sums[19] is a count of all the numbers k, from 0 to x, 
     * for which digitsumsq(k) is 19.
     *  
     * @param x Any non-negative long
     * @return An array of sum counts for 0-x
     */
    private long[] sumcounts( long x )
    {
        // This is a complicated procedure, so these comments
        // will frame it with a specific example. Suppose that
        // x is 6724.
        long sums[] = new long[2000];
        Arrays.fill( sums, 0L );
        
        // x=0 is a simple base case
        if( x==0 )
        {
            sums[0] = 1;
        }
        else
        {
            // Figure out the highest digit, and its power of 10.
            // For our example (6724), digit=6, power=1000.
            long xx = x;
            int digit = 0;
            long power = 1;
            // skip is kinda weird, but it's necessary to avoid overflow.
            // If we didn't use skip, power would come out 10 times what it should be.
            // We can't just divide by 10 when we're done, because for very large
            // values of x, power would overflow in the loop.
            boolean skip = true;
            while( xx>0 )
            {
                if( xx<10 ) digit = (int)xx;
                xx /= 10;
                if( skip ) skip = false;
                else power *= 10;
            }
                      
            
            // OK, we're going to break down the problem by that first digit.
            // In our example (6724), that means calculating the digit sums
            // for 0-999, then 1000-1999, then 2000-2999, 3000-3999, 4000-4999,
            // 5000-5999, and finally, 6000-6724.
            //
            // We'll start by finding the digit sums for 999 (which is power-1),
            // and adding the first digits (0-5).
            //
            // The all-9's are going to get reused a lot, so we can memoize them.
            // Here, we look for the sums for power-1, create them if they're not there.
            long subsums[] = nines.get( power-1 );
            if( subsums==null )
            {    
                subsums = sumcounts( power-1 );
                nines.put( power-1, subsums );
            }
            
            // Now, go through the first digits (0-5 in our example) 
            for( int d=0; d<digit; d++ )
            {
                for( int i=0; i<sums.length-d*d; i++ )
                {
                    sums[i+d*d] += subsums[i];
                }
            }
            
            // Lastly, do the last digit. That's 6000-6724
            // in our example. So, we've got to get the
            // digit sums for 742, which is 6742(x) mod 1000(power)
            subsums = sumcounts( x % power );
            
            // And, add in that upper digit.
            for( int i=0; i<sums.length-digit*digit; i++ )
            {
                sums[i+digit*digit] += subsums[i];
            }
            
        }
        
        return sums;
    }
    
    /**
     * Count the number of Unhappy numbers from 0 to n, inclusive.
     * 
     * @param n Any non-negative long
     * @return A count of the Unhappy numbers in the range from 0 to n
     */
    private long unhappycount( long n )
    {
        // n is Unhappy iff digitsumsq(n) is unhappy.
        // So, find the counts of all the digit square sums, 
        // and if a sum is unhappy, tally the count.
        long counts[] = sumcounts( n );
        long total = 0L;
        for( int i=0; i<unhappy.length; i++ )
        {
            if( unhappy[i] )
            {
                total += counts[i];
            }
        }
        
        return total;
    }
    
    /**
     * Do it!
     * @throws Exception
     */
    private void doit() throws Exception
    {
        sc = new Scanner( System.in ); //new File( "unhappy.in" ) );
        ps = System.out; //new PrintStream( new FileOutputStream( "unhappy.out" ) ); 
        
        // No matter how big the input, its sum of digit squares is less than 2000.
        // (I believe that 1522 is the max, for 8999999999999999999L)
        // So, here, we'll figure out which of the numbers from 0 to 1999 are Unhappy.
        Arrays.fill( memo, -1 );
        for( int i=0; i<2000; i++ )
        {
            unhappy[i] = (happiness(i)==-2);
        }
            
        for(;;)
        {
            long lower = sc.nextLong();
            long upper = sc.nextLong();
            if( upper==0L && lower==0L ) break;
            
            ps.println( unhappycount(upper) - unhappycount(lower-1) );
        }
    }
    
    /**
     * @param args
     */
    public static void main( String[] args ) throws Exception
    {
        new unhappy_solution().doit();
    }

}
