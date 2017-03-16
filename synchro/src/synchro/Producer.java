package synchro;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Producer extends javax.swing.JFrame implements Runnable{
	private static final SecureRandom gen = new SecureRandom();
	private final Buffer sharedLocation;
	static int prodtime = 0;
	static String total  = "";
	Scanner scanny = null;
	public Producer(Buffer sharedLocation){
		this.sharedLocation = sharedLocation;
		
		try {
			scanny = getScanner();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void run(){
		while(!total.contains("@") && scanny.hasNext()){
			try{
				waitNow();
				String temp = scanny.next();
				total = total + " " + temp;
				sharedLocation.put(temp);
			}
			catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			
		}
		
		
		
	}
	
	
	
	private void waitNow() throws InterruptedException{
		int i = gen.nextInt();
		if(i < 0){
			i*=-1;
		}
		Thread.sleep(i%1000);
		
		
		prodtime+=i%1000;
		
		
		
		
		
	}
	
	private Scanner getScanner() throws FileNotFoundException {//GEN-FIRST:event_loadButtonActionPerformed
	   	// Here I want to open up a file chooser.
	   
	   File selectedFile = null;
	   JFileChooser fileChooser = new JFileChooser();
	   fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	   int result = fileChooser.showOpenDialog(this);
	   if (result == JFileChooser.APPROVE_OPTION) {
	        selectedFile = fileChooser.getSelectedFile();
	    //   System.out.println("Selected file: " + selectedFile.getAbsolutePath());
	   }

	   if(selectedFile == null){
		   System.out.println("File not selected");
		   return null;
	   }
	   Scanner scanny = new Scanner(new File(selectedFile.getAbsolutePath()));
	   // store sentence data
	    return scanny;
	    
   }//GEN-LAST:event_loadButtonActionPerformed
	

}
