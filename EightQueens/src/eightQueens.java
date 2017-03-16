/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  eightQueens.java
 * Date: March 1, 2016
 */
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class eightQueens extends Applet implements MouseListener, MouseMotionListener, Runnable, ActionListener{

	// creates new button
	Button button = new Button("Solve");
	// determines if you solved it yet
	boolean m_bSolving = false;
	
	// creates new image
	Image shape;
	// required by java
	private static final long serialVersionUID = 1L;

	// media tracker finds the image
	MediaTracker tracker = new MediaTracker(this);
	// dimensions of board
	static final int NUMROWS = 8;
	static final int NUMCOLS = 8;
	static final int SQUAREWIDTH = 50;
	static final int SQUAREHEIGHT = 50;
	static final int BOARDLEFT = 50;
	static final int BOARDTOP = 50;
	// initialize unsolved boolean and board and string
	int m_nBoard[][] = new int[8][8];
	boolean m_bClash = false;
	String str = "Eight Queens Program";

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
      
        try 
        {
        	// get the image and store it into shape
        	shape = getImage(getCodeBase(),"queen.png");
        	tracker.addImage(shape, 1);
        	tracker.waitForAll();        	
		} 
        catch (Exception e1) 
        {
		}
        
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
				
				if( m_nBoard[nRow][nCol] != 0 )
				{
					canvas.drawImage( shape,
						BOARDLEFT + nCol * SQUAREWIDTH + 8, BOARDTOP + nRow * SQUAREHEIGHT + 6, null );
				}
			}
		}
	}
	// provided function given to us

	void CheckColumns( Graphics canvas )
	{
		// Check all columns
		for(  int nCol=0; nCol<NUMCOLS; nCol++ )
		{
			int nColCount = 0;
			for( int nRow=0; nRow<NUMROWS; nRow++ )
			{
				if( m_nBoard[nRow][nCol] != 0 )
				{
					nColCount++;
				}
			}
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + ( SQUAREHEIGHT / 2 ),	
					BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + SQUAREHEIGHT * 7 + ( SQUAREHEIGHT / 2 ) );
				
				m_bClash = true;
			}
		}
	}
	// provided function given to us

	void CheckRows( Graphics canvas )
	{
		for(  int nRow=0; nRow<NUMROWS; nRow++ )
		{
			int nRowCount = 0;
			for( int nCol=0; nCol<NUMCOLS; nCol++ )
			{
				if( m_nBoard[nRow][nCol] != 0 )
				{
					nRowCount++;
				}
			}
			if( nRowCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
					BOARDLEFT + 7 * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
				
				m_bClash = true;
			}
		}
	}
	// provided function given to us

	void CheckDiagonal1( Graphics canvas )
	{
		// Check diagonal 1
	
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
		{
			int nCol = 0;
			
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
			
			while( nThisCol < NUMCOLS &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol++;
				nThisRow++;
			}
			
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
				
				m_bClash = true;
			}
		}

		for( int nCol=1; nCol<NUMCOLS; nCol++)
		{
			int nRow = 0;
		
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
			
			while( nThisCol < NUMCOLS &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol++;
				nThisRow++;
			}
			
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
				
				m_bClash = true;
			}
			
		}
		
	}
	// provided function given to us

	void CheckDiagonal2( Graphics canvas )
	{
		// Check diagonal 2
		
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
		{
			int nCol = NUMCOLS - 1;
			
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
			
			while( nThisCol >= 0 &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol--;
				nThisRow++;
			}
			
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
				
				m_bClash = true;
			}
		}

		for( int nCol=NUMCOLS-1; nCol>=0; nCol--)
		{
			int nRow = 0;
		
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
			
			while( nThisCol >= 0 &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol--;
				nThisRow++;
			}
			
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
				
				m_bClash = true;
			}
			
		}
	}
	// provided function given to us

	public void paint (Graphics canvas)

	{

		m_bClash = false;
		
		DrawSquares( canvas );
		
		canvas.setColor(Color.RED);
		
		CheckColumns( canvas );
		CheckRows( canvas );
		CheckDiagonal1( canvas );
		CheckDiagonal2( canvas );
		
		canvas.setColor(Color.BLUE);
		canvas.drawString(str, BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 8 + 20);
	}





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
		// get the x of the click and subtract how far left board is, then divide by width of board
		int nCol = ( ms.getX() - BOARDLEFT ) / SQUAREWIDTH;
		// do same for height
		int nRow = ( ms.getY() - BOARDTOP ) / SQUAREHEIGHT;
		// if inbounds
		if( nCol >= 0 &&
			nRow >= 0 &&
			nCol < NUMCOLS &&
			nRow < NUMROWS )
		{
			// if a 0 is there replace with a 1
			m_nBoard[nRow][nCol] ^= 1;
			
			// shows the queen on board
			repaint();
		}
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
	void clear()
	{
		// loops through each row and col and clears board.
		for( int nRow=0; nRow<NUMROWS; nRow++ )
		{
			for( int nCol=0; nCol<NUMCOLS; nCol++ )
			{
				m_nBoard[nRow][nCol] = 0;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// enables button
		button.setEnabled(false);
		
		// clears board
		clear();
		
		// no clash

		m_bClash = false;
		
		// repaints
		repaint();
		// problem hasn't been solved
		m_bSolving = true;
	}

	private boolean solveIt(int col) {
		// if you're outside columns, you must have solved board
		if(col >= NUMCOLS){
			return true;
		}
		// loop through each row
		for(int row = 0; row < NUMROWS; row++){
			
			// change string
			str = "Placing Piece";
			// place queen
			m_nBoard[row][col] = 1;
			repaint();
			
			// sleep
			try 
			{
				Thread.sleep(200);
			} 
			catch (InterruptedException e){
			}
			
			// if there is a clash, change string, and unplace queen
			if(m_bClash){
				str = "Conflict, removing Piece";
			m_nBoard[row][col] = 0;
			repaint();
			// sleep
				try 
				{
					Thread.sleep(200);
				} 
				catch (InterruptedException e){} 
			}
			else{
				// if solve it doesn't work, change string and unplace queen
				if(!solveIt(col+1)){
					str = "Backtracking, removing piece";
					m_nBoard[row][col] = 0;
					repaint();
				}
				else{
					// else place it, no conflicts
					return true;
				}
				
				
				
			}
			
		}
		// sleep
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e){} 
		
		
		return false;
	}
	@Override
	public void run() {
		// run program until board is solved.
		while(true)
		{
			// if you haven't solved it yet, solve it
			if( m_bSolving )
			{
				solveIt(0);
			}
			// finished with solving it
			m_bSolving = false;
			// enable button again
			button.setEnabled(true);
			
			// sleep
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
