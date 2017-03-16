// Nadeem Mohsin
// 2/4/2010
// Code for UCF Programming Team Lecture: Four Algorithms

// Covers gcd, combinations, permutations and subsets.
// Added technique of using bit masks is developed.

import java.util.*;

public class combinations
{
    public static void main(String[] args) throws Exception { new combinations();}

    int[] values;
    int N;
    public combinations() throws Exception
    {
        values = new int[] {1, 2, 3, 4};
        N = values.length;

      //  System.out.println("Subsets:");
        sub = new int[N];
     //   subsets3(0, 0);

        System.out.println("\nCombinations: ");
        K = 3;
        comb = new int[N];
        combine(0, 0);

        /*System.out.println("\nPermutations: ");
        perm = new int[N];
        used = new boolean[N];
        permute3(0);

        System.out.println("\nIterative permutations: ");
        // Generate all permutations iteratively.
        do
        {
            for(int k = 0; k < N; k++)
                System.out.printf("%d ", values[k]);
            System.out.println();
        } while(next_permutation(values));


        System.out.println("\nEuclid's Algorithm");
        int a = 54, b = 252;
        System.out.printf("gcd(%d, %d) = %d\n", a, b, gcd(a, b));

        System.out.println("\nExtended Euclidean Algorithm");
        int[] ret = euclid(a, b);
        System.out.printf("For a = %d, b = %d, we have %d*%d + %d*%d = %d\n", a, b, a, ret[0], b, ret[1], ret[2]);
*/
    }


    // Euclid's algorithm for gcd.
    int gcd(int a, int b)
    {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Fast lcm using the identity lcm(a, b) * gcd(a, b) = a*b.
    // Note that division is done first to prevent overflow.
    int lcm(int a, int b)
    {
        return a * (b / gcd(a, b));
    }

    // Extended Euclidean algorithm.
    // Solves the equation ax + by = gcd(a, b), which can be used to solve the
    // family ax + by = c, if c is divisible by gcd(a, b).
    // Also useful for finding the modular inverse of a modulo b.
    // Returns {x, y, gcd(a, b)}.
    int[] euclid(int a, int b)
    {
        if(b == 0)
            return new int[] {1, 0, a};
        
        int q = a/b, r = a % b;
        int[] temp = euclid(b, r);

        int x = temp[1], y = temp[0] - q*x, g = temp[2];
        return new int[] {x, y, g};
    }


    // Recursive subsets.
    // Invoke this function as subsets(0, 0).
    int[] sub;
    void subsets(int pos, int len)
    {
        if(pos == N)
        {
            // Do something problem-specific with the subset. Here I'll just
            // print it.
            for(int k = 0; k < len; k++)
                System.out.printf("%d ", sub[k]);
            System.out.println();
            return;
        }
        // Try the current element in the subset.
        sub[len] = values[pos];
        subsets(pos+1, len+1);

        // Skip the current element.
        subsets(pos+1, len);
        
    }

    // Iterative subsets.
    void subsets2()
    {
        // mask will iterate through all 2^N subsets.
        for(int mask = 0; mask < (1 << N); mask++)
        {
            // Do something problem-specific with the subset. Here I'll just
            // print it.
            for(int k = 0; k < N; k++)
                if(on(mask, k))
                    System.out.printf("%d ", values[k]);
            System.out.println();
        }
    }


    // In the recursive method, instead of storing the subset in an array, we
    // can just put it in a bitmask.
    // Invoke this function as subsets3(0, 0).
    void subsets3(int pos, int mask)
    {
        if(pos == N)
        {
            // Do something problem-specific with the subset. Here I'll just
            // print it.
            for(int k = 0; k < N; k++)
                if(on(mask, k))
                    System.out.printf("%d ", values[k]);
            System.out.println();
            return;
        }

        // Try putting the current element in the subset.
        subsets3(pos+1, set(mask, pos));
        // Skip this element.
        subsets3(pos+1, mask);
    }

    // Recursive combinations algorithm. This is like a generalization of
    // recursive subsets, since the set of combinations of K elements out of N 
    // is identical to the set of all subsets of N elements of size K.
    // I'll assume that comb is an array of length N, and K is global.
    // Invoke this function as combine(0, 0).
    int[] comb;
    int K;
    void combine(int pos, int size)
    {
        // If there are not enough remaining elements to fill up our
        // combination, there's no point recursing further.
        if(N-pos < K-size)
            return;

        if(size == K)
        {
            // Do something problem-specific with the combination. Here I'll
            // just print it.
            for(int k = 0; k < size; k++)
                System.out.printf("%d ", comb[k]);
            System.out.println();
            return;
        }

        // Use the current element in the combination.
        System.out.println("Size: " + size);
        comb[size] = values[pos];
        combine(pos+1, size+1);

        // Skip the current element.
        combine(pos+1, size);
    }

    // Instead of storing the combination in an array, you can just use a
    // bitmask.
    // Invoke this function as combine2(0, 0).
    void combine2(int pos, int size, int mask)
    {
        // If there are not enough remaining elements to fill up our
        // combination, there's no point recursing further.
        if(N-pos < K-size)
            return;

        if(size == K)
        {
            // Do something problem-specific with the combination. Here I'll
            // just print it.
            for(int k = 0; k < N; k++)
                if(on(mask, k))
                    System.out.printf("%d ", values[k]);
            System.out.println();
            return;
        }

        // Use the current element in the combination.
        combine2(pos+1, size+1, set(mask, pos));

        // Skip the current element.
        combine2(pos+1, size, mask);
    }

    // If you feel like simplifying even further, then you can throw away the
    // 'size' argument altogether, because the number of bits that are on in the
    // mask will always be equal to it.
    // Invoke this function as combine3(0, 0).
    void combine3(int pos, int mask)
    {
        int size = Integer.bitCount(mask);
        // If there are not enough remaining elements to fill up our
        // combination, there's no point recursing further.
        if(N-pos < K-size)
            return;

        if(size == K)
        {
            // Do something problem-specific with the combination. Here I'll
            // just print it.
            for(int k = 0; k < N; k++)
                if(on(mask, k))
                    System.out.printf("%d ", values[k]);
            System.out.println();
            return;
        }

        // Use the current element in the combination.
        combine3(pos+1, set(mask, pos));

        // Skip the current element.
        combine3(pos+1, mask);
    }


    // Recursive permutation algorithm. Here I assume that perm and used are
    // arrays of length N, and used is initialized to all false.
    // Invoke this function as permute(0).
    int[] perm;
    boolean[] used;
    void permute(int ind)
    {
        if(ind == N)
        {
            // Do something problem-specific with the permutation. Here I'll just
            // print it.
            for(int k = 0; k < N; k++)
                System.out.printf("%d ", perm[k]);
            System.out.println();
            return;
        }

        // Try all unused elements at this position in the permutation.
        for(int i = 0; i < N; i++)
            if(!used[i])
            {
                perm[ind] = values[i];
                used[i] = true;
                permute(ind+1);
                used[i] = false;
            }
    }

    // It's a bit more compact to use a bitmask instead of a boolean array for
    // keeping track of used elements.
    // Invoke this function as permute2(0, 0).
    void permute2(int ind, int used)
    {
        if(ind == N)
        {
            // Do something problem-specific with the permutation. Here I'll just
            // print it.
            for(int k = 0; k < N; k++)
                System.out.printf("%d ", perm[k]);
            System.out.println();
            return;
        }

        // Try all unused elements at this position in the permutation.
        for(int i = 0; i < N; i++)
            if(!on(used, i))
            {
                perm[ind] = values[i];
                permute2(ind+1, set(used, i));
            }
    }

    // If you feel like simplifying even further, then you can throw away the
    // 'ind' argument altogether, because the number of bits that are on in the
    // mask will always be equal to it.
    // Invoke this function as permute3(0).
    void permute3(int used)
    {
        // Check if used is all 1s.
        if(used+1 == (1 << N))
        {
            // Do something problem-specific with the permutation. Here I'll just
            // print it.
            for(int k = 0; k < N; k++)
                System.out.printf("%d ", perm[k]);
            System.out.println();
            return;
        }

        // Try all unused elements at this point in the permutation.
        int ind = Integer.bitCount(used);
        for(int i = 0; i < N; i++)
            if(!on(used, i))
            {
                perm[ind] = values[i];
                permute3(set(used, i));
            }
    }

    // Iterative permutation algorithm. It takes an array representing a
    // permutation, and rearranges it to give the lexicographically next
    // permutation. It returns true for everything but the lexicographically
    // last permutation (descending order).
    // Note that it also handles repeated values elegantly (no duplicate
    // permutations).
    // To generate all permutations, it is generally invoked in a do-while
    // loop. See the main() function for an example.
    boolean next_permutation(int[] nums)
    {
        int n = nums.length;

        int i = n-2;
        while(i >= 0 && nums[i] >= nums[i+1])
            i--;
        if(i == -1) return false;

        int x = nums[i];

        int j = n-1;
        while(j > i && nums[j] <= x)
            j--;

        nums[i] = nums[j];
        nums[j] = x;

        Arrays.sort(nums, i+1, n);
        return true;
    }

    // Check if the bit at index pos is on in mask.
    boolean on(int mask, int pos)
    {
        return (mask & (1 << pos)) > 0;
    }

    // Set the bit at index pos in mask.
    int set(int mask, int pos)
    {
        return mask | (1 << pos);
    }
}