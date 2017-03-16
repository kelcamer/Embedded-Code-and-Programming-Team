/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  Sudoku.java
 * Date: March 3, 2016
 */
// imports
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku extends Applet implements MouseListener, MouseMotionListener, Runnable, ActionListener{

	// creates new button
	Button button = new Button("Solve");
	// determines if you solved it yet
	boolean m_bSolving = false;
	
	// media tracker finds the image
	MediaTracker tracker = new MediaTracker(this);
	// dimensions of board
	 final int NUMROWS = 9;
	 final int NUMCOLS = 9;
	 final int SQUAREWIDTH = 50;
	 final int SQUAREHEIGHT = 50;
	 final int BOARDLEFT = 50;
	 final int BOARDTOP = 50;
	 
	 // dimensions of board
	final  int N = 9;
	// initialize unsolved boolean and board and string
	 int Grid[][] = new int[9][9];
	 
	 // determines whether a conflict exists
	 boolean m_bClash = false;
	 
	 // the string at the top
	String str = "Sudoku Program";

	// creates a new thread, without this run will not execute
	Thread myThread;
	
	public void init()
	{
		// sets the size
		setSize(1020,700);
		// listens for mouse clicks
		addMouseListener(this);
		addMouseMotionListener(this);
		// adds the button to the board
        add(button);
        // listens for clicks to the button to activate run
        button.addActionListener(this);
      
     
        // starts this new thread
         myThread = new Thread(this);
        myThread.start();
	}
	// provided function given to us
	void DrawSquares( Graphics canvas )
	{
		
		canvas.setColor(Color.BLACK);
		for( int nRow=0; nRow<NUMROWS; nRow++ )
		{
			for( int nCol=0; nCol<NUMCOLS; nCol++ )
			{
				canvas.drawRect( BOARDLEFT + nCol * SQUAREWIDTH,
					BOARDTOP + nRow * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT );
				
				canvas.setFont(new Font("SnapITC", Font.PLAIN, 30));
				// here is where I can put in initial board
				canvas.drawString(Grid[nCol][nRow] + "", BOARDLEFT + nCol * SQUAREWIDTH + 15,
					BOARDTOP + nRow * SQUAREHEIGHT + 30);
			}
		}
	}
	

	public void paint (Graphics canvas)

	{
		// sets clash equal to false
		m_bClash = false;
		// draws squares (and this updates grid, essentially)
		DrawSquares( canvas );
		// updates the string
		canvas.drawString(str, BOARDLEFT, 30);
	}




	// required implemented methods that do absolutely nothing because we were not instructed to do so.
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent ms) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	// initializes the grid to 0
	 void clear()
	{
		// loops through each row and col and clears board.
		for( int nRow=0; nRow<NUMROWS; nRow++ )
		{
			for( int nCol=0; nCol<NUMCOLS; nCol++ )
			{
				Grid[nRow][nCol] = 0;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// enables button
		button.setEnabled(false);
		
		// no clash

		m_bClash = false;
		
		// repaints
		repaint();
		// problem hasn't been solved
		m_bSolving = true;
	}

	 void LoadData() throws FileNotFoundException
	{
		// Open the file for reading. Will almost always be input.txt
		Scanner objScanner = new Scanner(new File("input.txt"));
		clear();
		// Iterate through the rows.
		for( int nRow=0; nRow<N; nRow++ )
		{
			// Iterate through the columns.
			for( int nColumn=0; nColumn<N; nColumn++ )
			{
			// MUST BE COL ROW OR IT REVERSES THE GRID
				Grid[nColumn][nRow] = objScanner.nextInt();
			}
		}
		// Closing is good practice and avoids an eclipse warning.
		objScanner.close();
	}
	
	/* Searches the grid to find an entry that is still unassigned. If
	   found, the reference parameters row, col will be set the location
	   that is unassigned, and true is returned. If no unassigned entries
	   remain, false is returned. Function given to us*/
	int FindUnassignedLocation( int grid[][] )
	{
		  for( int row=0; row<N; row++ )
		  {
		    for( int col=0; col<N; col++ )
		    {
		      if( grid[row][col] == 0 )
		      {
		        return( col | ( row << 8 ) );
		      }
		    }
		  }

		return( -1 );
	}
	
	/* Returns a boolean which indicates whether any assigned entry
	   in the specified row matches the given number. Function given to us */
	 boolean UsedInRow(int grid[][], int row, int num)
	{
		for( int col=0; col<9; col++ )
		{
			if( grid[row][col] == num ){
				m_bClash = true;
				return true;
			}
		}
		
		return false;
	}
	 
	/* Returns a boolean which indicates whether any assigned entry
	   in the specified column matches the given number. Function given to us. */
	 boolean UsedInCol(int grid[][], int col, int num)
	{
		for( int row=0; row<9; row++ )
		{
			if( grid[row][col] == num ){
				m_bClash = true;
				return true;
			}
		}

		return false;
	}
	 
	/* Returns a boolean which indicates whether any assigned entry
	   within the specified 3x3 box matches the given number. Function given to us */
	 boolean UsedInBox( int grid[][], int boxStartRow, int boxStartCol, int num )
	{
		for( int row=boxStartRow; row<boxStartRow+3; row++ )
		{
			for( int col=boxStartCol; col<boxStartCol+3; col++ )
			{
				if( grid[row][col] == num ){
					m_bClash = true;
					return true;
				}
			}
		}
		
		return false;
	}
	 
	/* Returns a boolean which indicates whether it will be legal to assign
	   num to the given row,col location. Function given to us */
	 boolean IsPromising( int grid[][], int row, int col, int num )
	{
		if( !UsedInRow( grid, row, num ) &&
			!UsedInCol( grid, col, num ) &&
			!UsedInBox( grid, row-(row%3), col-(col%3), num ) )
		{
			
			return( true );
		}
		
		return false;
	}
	
	/* Takes a partially filled-in grid and attempts to assign values to
	  all unassigned locations in such a way to meet the requirements
	  for Sudoku solution (non-duplication across rows, columns, and boxes) Function given to us*/
	 boolean SolveSudoku(int grid[][])
	{
		 // sleeps
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e){} 
		
		// gets next location
		int result = FindUnassignedLocation(grid);
		
		// if location doesn't exist, you finished the board, yay!
		if( result == -1 ) return true;
		
		// gets the row and col
		int row = result >> 8;
		int col = result & 0xff;
		
		// loops through each number and tries it
		for( int num=1; num<=9; num++ )
		{
			// if you have an open space
			if( IsPromising( grid, row, col, num ) )
			{
				// set the num
				grid[row][col] = num;
				
				try 
				{
					// sets string and repaint
					str = "Placing Number";
					repaint();
					
					
				} 
				catch (Exception e){} 
				// if you solved it, return true
				if( SolveSudoku( grid ) )
				{
					return true;
				}
				else{
					// else backtrack and update strings
					try 
					{
						str = "Backtracking, Removing";
						Thread.sleep(200);
						repaint();
					} 
					catch (Exception e){} 
				}
				// unplace number because it didn't work
				grid[row][col] = 0;
				try 
				{
					// update strings and repaint
					str = "Conflict, Removing";
					Thread.sleep(200);
					repaint();
				} 
				catch (Exception e){} 
			}
		}

		return false; // this triggers backtracking
	}
	
	/* A utility function to print grid You don't need this in this assignment. */
	 void printGrid( int grid[][] )
	{
	    for (int row = 0; row < N; row++)
	    {
	       for (int col = 0; col < N; col++)
	             System.out.print(""+grid[row][col]+" ");
	        System.out.println("");;
	    }
	}	
	

	@Override
	public void run() {
		// run program until board is solved.
		try {
			LoadData();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true)
		{
			// if you haven't solved it yet, solve it
			if( m_bSolving )
			{
				SolveSudoku(Grid);
			}
			// finished with solving it
			m_bSolving = false;
			// enable button again
			button.setEnabled(true);
			
			// sleep
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
