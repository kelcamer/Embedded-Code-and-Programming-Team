package synchro;

import java.security.SecureRandom;

public class Consumer implements Runnable {
	SecureRandom gen = new SecureRandom();
	static int contime = 0;
	private final Buffer sharedLocation;
	String temp;
	public Consumer(Buffer sharedLocation) {
		// TODO Auto-generated constructor stub
		this.sharedLocation = sharedLocation;
		temp = "";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(!temp.contains("@")){
		try{
			waitNow();
			temp = sharedLocation.get();
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
		
		
		contime+=i%1000;
		
	}
}
