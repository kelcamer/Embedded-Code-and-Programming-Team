/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  Floodfill
 * Date: March 1, 2016
 */
import java.applet.Applet;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Floodfill extends Applet implements MouseListener
{
	// stuff that Leinecker added to get the applet to work and produce the image onscreen
	Color m_objSelectedColor = Color.blue;
	int m_nSelectedColor = 0xff0000ff;
	BufferedImage m_objShape;
	MediaTracker tracker = new MediaTracker(this);
	
	int m_nTestShapeX = 100;
	int m_nTestShapeY = 100;
	
	static Color[] m_Colors =
	{
		Color.blue, Color.red, Color.green, Color.yellow,
		Color.gray, Color.magenta, Color.orange, Color.cyan
	};
	
	int m_nUpperLeftX = 10;
	int m_nUpperLeftY = 10;
	int m_nColorWidth = 50;
	int m_nColorHeight = 50;
	int m_nLowerRightX;
	int m_nLowerRightY;
	
    CheckboxGroup lngGrp = new CheckboxGroup();
    Checkbox full = new Checkbox("Full Recursion", lngGrp, true);
    Checkbox partial = new Checkbox("Partial Recursion", lngGrp, true);
    
	public void init()
	{
		addMouseListener(this);
        setSize(1020,700);

        add(partial);
        add(full);
        
        try 
        {
			m_objShape = ImageIO.read(Floodfill.class.getResourceAsStream("Untitled.png"));
			tracker.addImage(m_objShape, 100);
			tracker.waitForAll();
		} 
        catch (Exception e1) 
        {
		}
		
	}

	void DrawColors( Graphics canvas )
	{
		for( int i=0; i<m_Colors.length; i++ )
		{
			canvas.setColor( m_Colors[i] );
			canvas.fillRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, m_nColorWidth, m_nColorHeight );
			canvas.setColor( Color.black );
			canvas.drawRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, m_nColorWidth, m_nColorHeight );
			
			m_nLowerRightX = m_nUpperLeftX + m_nColorWidth;
			m_nLowerRightY = ( i + 1 ) * m_nColorHeight;
		}
		
	}
	
	void DrawTestShape( Graphics canvas )
	{
		canvas.drawImage(m_objShape, m_nTestShapeX, m_nTestShapeY, null);
	}
	
	void SetPixel( int x, int y, Graphics canvas )
	{
		canvas.drawLine(x, y, x, y);
	}
	
	void SetPixel( int x, int y, int nColor )
	{
		m_objShape.setRGB(x, y, nColor);
	}
	
	public int GetPixel( int x, int y )
	{
		return( m_objShape.getRGB(x, y) );
	}
	
	public void paint( Graphics canvas )
	{
		DrawColors( canvas );
		DrawTestShape( canvas );
	}
	
	void DoRecursiveFill( int x, int y )
	{
		x -= m_nTestShapeX;
		y -= m_nTestShapeY;
		m_nStartColor = GetPixel(x,y) | 0xff000000;
		Graphics canvas = getGraphics();
		canvas.setColor( m_objSelectedColor );
		
		int w = m_objShape.getWidth();
		int h = m_objShape.getHeight();

		if( m_nStartColor == m_nSelectedColor)
		{
			return;
		}
		RecursiveFill( x, y, w, h, canvas);
	}
	// my method
	void RecursiveFill( int x, int y, int w, int h, Graphics canvas )
	{
		// if you go out of bounds return
		if(x >= w || y >= h || x <= 0 || y <= 0)
		{
			return;
		}
		// if you find a different color than the original color, return
		if(m_nStartColor != GetPixel(x,y))
		{
			return;
		}
		
		// we can simply write on test "fill one pixel with this color"
		// stores the board in memory the purpose is that java doesn't have a getPixel
		SetPixel(x, y, m_nSelectedColor);
		// the part stored in memory ^
		// the part that you see v
		SetPixel(x+m_nTestShapeX,y+m_nTestShapeY,canvas);
		
		// recurse for all four directions.
		RecursiveFill(x+1, y, w, h, canvas);
		RecursiveFill(x-1, y, w, h, canvas);
		RecursiveFill(x, y+1, w, h, canvas);
		RecursiveFill(x, y-1, w,h,canvas);
	}
	
	int m_nStartX, m_nStartY, m_nStartColor;
	void DoFloodFill( int x, int y )
	{
		x -= m_nTestShapeX;
		y -= m_nTestShapeY;
		m_nStartColor = GetPixel(x,y) | 0xff000000;
		Graphics canvas = getGraphics();
		canvas.setColor( m_objSelectedColor );
		
		int w = m_objShape.getWidth();
		int h = m_objShape.getHeight();

		if( m_nStartColor == m_nSelectedColor)
		{
			return;
		}
		
		FloodFill( x, y, w, h, canvas);
	}
	// my method
	void FloodFill( int x, int y, int w, int h, Graphics canvas )
	{
		// if out of bounds, return
		if(x < 0 || y < 0 || x>=w || y >= h)	return;
		// if you find a color that's different than the start color, return
		if(m_nStartColor!=GetPixel(x, y))	return;
		/*
		 * Iteratively go left while you're in bounds and not at the start color
		 * Iteratively go right while you're in bounds and not at start color
		 * recurse for up and recurse for down*/
		// subtract 1 so that you can go left
		int tempLeft = x-1;
		// set tempright at x
		int tempRight = x;
		// while in bounds and at a different color
		while(tempLeft >= 0 && tempLeft < w && m_nStartColor == GetPixel(tempLeft, y)){
			
			// color one pixel
			SetPixel(tempLeft, y, m_nSelectedColor);
			SetPixel(tempLeft+m_nTestShapeX,y+m_nTestShapeY,canvas);
			// the part stored in memory ^
			// the part that you see v
			// subtracts 1 from tempLeft to go left
			tempLeft--;
		}
		// while in bounds and at new color
		while(tempRight >= 0 && tempRight < w && m_nStartColor==GetPixel(tempRight, y)){
		
			// color one pixel
			SetPixel(tempRight, y, m_nSelectedColor);
			SetPixel(tempRight+m_nTestShapeX,y+m_nTestShapeY,canvas);
			// the part stored in memory ^
			// the part that you see v
			// add 1 to go right
			tempRight++;
		}
		// while you haven't filled in between left and right
		while(tempLeft < tempRight){
			// recurse
			FloodFill(tempLeft, y+1, w, h, canvas);
			FloodFill(tempLeft, y-1, w, h, canvas);
			// increment left because you filled that column already.
			tempLeft++;
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent ms) 
	{
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
	}

	@Override
	public void mousePressed(MouseEvent ms) 
	{
		if( ms.getX() >= m_nUpperLeftX &&
				ms.getY() >= m_nUpperLeftY &&
				ms.getX() < m_nLowerRightX &&
				ms.getY() < m_nLowerRightY )
			{
				int nColorIndex = ( ms.getY() - m_nUpperLeftY ) / m_nColorHeight;
				if( nColorIndex >= 0 && nColorIndex <= 7 )
				{
					m_objSelectedColor = m_Colors[nColorIndex];
					m_nSelectedColor = m_Colors[nColorIndex].getRGB();
				}
			}
			
			else if( ms.getX() >= m_nTestShapeX &&
				ms.getY()>=m_nTestShapeY &&
				ms.getX() < m_nTestShapeX + m_objShape.getWidth() &&
				ms.getY() < m_nTestShapeY + m_objShape.getHeight())
			{
				if( full.getState() )
				{
					DoRecursiveFill( ms.getX(), ms.getY());
				}
				else
				{
					DoFloodFill( ms.getX(), ms.getY());
				}
			}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
	}
	
}
