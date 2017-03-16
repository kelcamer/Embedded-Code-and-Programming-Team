/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  ObserverPattern.java
 * Date: April 17, 2016
 */
// creates an account owner
public class AccountOwner implements Observer{
	// variables
	int sCount;
	int vidCount;
	int viewCount;
	
	// toggles sleepMode
	boolean sleepMode;
	private static int obsIDTrack = 0;
	private int obsID = 0;
	// creates 1 grabber to update the subjects in it
	private Subject grabber;
	
	public AccountOwner(Subject s){
		this.grabber = s;
		// increment the id every time you add one
		this.obsID = ++obsIDTrack;
		// prints
		System.out.println("New Account: " + this.obsID);
		System.out.println();
		// sleep mode is false by default
		sleepMode = false;
	}
	
	@Override
	public void update(int subCount, int vidCount, int viewCount) {
		// sets all the data
		this.sCount = subCount;
		this.vidCount = vidCount;
		this.viewCount = viewCount;
		// if this account doesn't have sleep mode on, print
		if(!sleepMode){
			print();
		}
		
	}
	// prints according to desired format 
	// NOTE THIS WAS VERY VERY VAGUE IN THE HANDOUT.
	private void print() {
		System.out.println(this.obsID);
		System.out.println("Subscriber Count: "  + this.sCount + " \n"
				+ "Total View Count: " + this.viewCount + "\n"
				+ "Total Videos Uploaded: " + this.vidCount + "\n");
		
		System.out.println("All Users Updated and Notified Accordingly");
		System.out.println();
	}

	@Override
	// sets sleep mode
	public void setSleepMode(boolean hi) {
		this.sleepMode = hi;
	}

}
