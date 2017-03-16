/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author:  Kelsey Cameron
 */
package histogram;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HistogramPanel extends JPanel {
   
   private List<String> sents;
   private int snum;
   private int[] histo = new int[26];
   // code I added:
  // public Histogram histo = new Histogram();
   // end code I added.
   public String readFile( File file ) {
      sents = new ArrayList();
      snum = -1;
      clearDisplay( this.getGraphics() );
      StringBuilder sb = new StringBuilder();
      try {
         Scanner scanner = new Scanner( new FileInputStream(file));
         while( scanner.hasNextLine() ) {
            sents.add( scanner.nextLine() );
         }
         
         for( int i = 0; i < sents.size(); i++ ) {
            sb.append(i + " : " + sents.get( i ) + "\n\n");
         }
         scanner.close();
      } catch (FileNotFoundException ex) {
         Logger.getLogger(HistogramPanel.class.getName()).log(Level.SEVERE, null, ex);
      }
      return sb.toString().trim();
   }
   
   @Override
   public void paintComponent( Graphics gc ) {
      super.paintComponent( gc );
      if( sents != null && snum >= 0 && snum < sents.size() ) { 
         showHisto( snum, true );
      }
   }
   
   public void showHisto() {
	    //System.out.println(sents);

      this.setBackground( Color.white );
      showHisto( snum, false );
      System.out.println("Snum: " + snum);
   }
   
   public void showHisto( int n, boolean b) {       
      if( sents != null && n >= 0 && n < sents.size() ) {
    	 // System.out.println("Sents size " + sents.size());
         snum = n;
         Graphics gc = this.getGraphics();
         clearDisplay( gc );
         histo = histo(sents.get(n));
         drawLines( gc );
         drawHisto( gc );
      }
      else if( b && sents != null ) {
    	 // System.out.println("HI");
         JOptionPane.showMessageDialog(this, "Sentence index out of range");
      }
   }
   
   private void clearDisplay( Graphics gc ) {      
      gc.setColor( Color.WHITE );
      gc.fillRect(0,0,this.getWidth(),this.getHeight());
   }
   
   private void drawLines( Graphics gc ) { 

	  gc.setColor(Color.BLACK);
	  int wid = this.getWidth();
	  int hei = this.getHeight();
	  int wid8 = wid * 8 / 10;
	  int wid2 = wid * 2 / 10;
	  int wid1 = wid / 10;
	  int hei1 = hei / 10;
	  int hei2 = hei * 2 / 10;
	  int hei8 = hei*8/10;
	  int hei26 = hei8 / 26;
	  int wid26 = wid8 / 26;
	  gc.draw3DRect(0, 0, wid, hei, false);
	  gc.setColor(Color.RED);
	  //gc.drawLine(x1, y1, x2, y2);
	  gc.drawLine(wid1, hei8, wid1, hei1);
	  gc.drawLine(wid8, hei8, wid1, hei8);
	  
   }
   // pass int array to this class named histo
   private void drawHisto( Graphics gc) {
/*
   for(int i = 0; i < histo.length; i++){
	   int x = 50 + 10*i;
	   int y = 40 - 260 -histo[i];
	   gc.drawRect(x, y, 10, histo[i]);
	   
   (1,1)	
   				
   					
   						
   						(8,8)
   
   
   }
   */	
	 //  int max = findMax(histo);
	   scale(histo);
	   
	  // for(int x = 0; x < histo.length; x++){
	//   System.out.println(histo[x]);
	 //  }
	   
	   	int wid = this.getWidth();
		  int hei = this.getHeight();
		  int wid8 = wid * 8 / 10;
		  int wid2 = wid * 2 / 10;
		  int wid1 = wid / 10;
		  int hei1 = hei / 10;
		  int hei2 = hei * 2 / 10;
		  int hei8 = hei*8/10;
		//  int hei26 = hei8 / 26;
		  int wid26 = wid8 / 26;
	   
	    
	   gc.setColor(Color.BLUE);
		  for(int x = 0; x < histo.length; x++){
			  gc.drawRect(wid1, hei8 - histo[x], wid26, histo[x]);
			  wid1+=wid26;
		  }
		

	   
	   
   }
   private void scale(int[] inp){
	   int max = findMax(inp);
	   for(int i = 0; i < histo.length; i++){
		   double scaled = (this.getHeight()*0.7) * (((double)inp[i]) / max);
		   histo[i] = (int)Math.floor(scaled);
		   
	   }
	   
	   
   
   }
   int findMax(int[] inp){
	   int max = inp[0];
	   for(int i = 0; i < inp.length; i++){
		   if(inp[i] > max){
			   max = inp[i];
		   }
	   }
	   return max;
	   
   }
// my code
   public static int[] histo(String s){
	   s = s.toLowerCase();
	   int[] letter = new int[26];
	   for(int i = 0; i < s.length(); i++){
		   char ch = s.charAt(i);
		   if(Character.isLetter(ch)){
			   int num = ch - 'a';
			   letter[num]++;
		   }
	   }
	   return letter;
   }
   // end my code

}