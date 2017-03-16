
import java.io.*;
import java.util.*;
import java.awt.geom.*;

/**
 * Solution to Dueling Philosophers
 * 
 * @author vanb
 */
public class solution
{
    public Scanner sc;
    public PrintStream ps;

    public static final int MAXCOUNT = 1000000;

    /**
     * Driver.
     * @throws Exception
     */
    public void doit() throws Exception
    {
        sc = new Scanner( System.in ); //new File( "duelingphilosophers.sample" ) );
        ps = System.out; //new PrintStream( new FileOutputStream( "duelingphilosophers.out" ) );
             
        // needs[i] is a count of all of the essays that define a term used in essay i.
        // In other words, it's a count of all the essays that essay i needs to come before it.
        int termsdefined[] = new int[MAXCOUNT];
        
        // feeds[i] is a count of all the essays that use a term defined in essay i.
        // In other words, it's a list of all the essays that essay i 'feeds'
        LinkedList<Integer> termsused[] = new LinkedList[MAXCOUNT];
        
        // Initialize the feeds
        for( int i=0; i<MAXCOUNT; i++ )
        {
            termsused[i] = new LinkedList<Integer>();
        }
        
        // This will be a list of all of the essays with 0 needs.
        // That means that they could come next in then ordering.
        LinkedList<Integer> nextones = new LinkedList<Integer>();
                        
        for(;;)
        {
            // There are n essays
            int n = sc.nextInt();
            int m = sc.nextInt();
            if( n==0 ) break;
            
            // Initialize the main structures for this new data set
            for( int i=0; i<n; i++ )
            {
                termsdefined[i] = 0;
                termsused[i].clear();
            }
            
            // Read in the dependencies...
            // Essay 'a' defines a term used in essays 'b'.
            // So, 'a' feeds 'b', and 'b' needs 'a'.
            for(int i=0; i<m; i++)
            {
                int a = sc.nextInt()-1;
                int b = sc.nextInt()-1;                
          
                ++termsdefined[b];      
                System.out.println("TERMS DEFINED " + termsdefined[b]);
                termsused[a].add( b );           
            }
            
            for(int x = 0; x < n; x++){
            	System.out.println(termsdefined[x]);
            	System.out.println(termsused[x]);
            }
            
            // Capture the initial set of zeros
            nextones.clear();
            for( int i=0; i<n; i++ ) if( termsdefined[i]==0 ) 
            {
                nextones.add( i );
            }
            
            // answer=1 means there's exactly one solution.
            // We'll change that if we find otherwise.
            int answer = 1;
            
            // We should be able to find a spot for every essay.
            for( int i=0; i<n; i++ )
            {
                // If there are no essays with zero needs,
                if( nextones.size()==0 )
                {
                    // Then we've got a loop.
                    // answer=0 means a loop, and we can bail out.
                    answer = 0;
                    break;
                }
                else if( nextones.size() > 1 )
                {
                    // If there's more than one essay with no needs,
                    // then there's more than one solution. That's answer=2.
                    // But, we can't bail out, in case we find a loop later.
                    answer = 2;
                }
                
                // So, grab the next essay
                int essay = nextones.removeFirst();
                
                // Look at all of the essays that this one feeds.
                // They now have one fewer need.
                for( int j : termsused[essay] )
                {
                    --termsdefined[j];
                    if( termsdefined[j]==0 )
                    {
                        nextones.add( j );
                    }
                }
            }
          //  System.out.println(answer);
            ps.println( answer );
        }
    }
    
    /**
     * @param args
     */
    public static void main( String[] args ) throws Exception
    {
        new solution().doit();
    }
}
