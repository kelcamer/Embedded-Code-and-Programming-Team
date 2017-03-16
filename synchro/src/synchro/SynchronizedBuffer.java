package synchro;

public class SynchronizedBuffer implements Buffer {
	private String buffer = "NIL";
	private boolean occupied = false;
	int prodcount = 1;
	int concount = 1;
	
	
	
	@Override
	public synchronized void put(String value) throws InterruptedException {
		while(occupied){
			System.out.println("Producer wait # " + prodcount + "\t\t\t" + buffer + "\t" + occupied);
			prodcount++;
			wait();
		}
		buffer = value;
		occupied = true;
		System.out.println();
		System.out.println("Producer writes: " + buffer);
		notifyAll();
		if(value.contains("@")){
			System.out.println();

			System.out.println("Producer summary: " + Producer.total);
			System.out.println("Total wait time: " + Producer.prodtime + " milliseconds");
		
		}
	}

	@Override
	public synchronized String get() throws InterruptedException {
		
		// TODO Auto-generated method stub
		while(!occupied){
			System.out.println("Consumer wait # " + concount + "\t\t\t" + buffer + "\t" + occupied);
			concount++;
		
			wait();
			// Here is the problem
			
		}
		occupied = false;
		System.out.println();
		System.out.println("Consumer reads: " + buffer);
		notifyAll();
		
		if(buffer.contains("@")){
			System.out.println();
			System.out.println("Consumer summary: " + Producer.total);
			System.out.println("Total wait time: " + Consumer.contime + " milliseconds");
		
			
		}
		
		return buffer;	
		}

}
