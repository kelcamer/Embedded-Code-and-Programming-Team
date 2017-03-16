package synchro;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class SynchroTest {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("COP3330 (Fall 2015) Program 8: Synchronized Buffer");
		System.out.println("Author: Kelsey Cameron");
		System.out.println();
		System.out.println("Operation\t\t\t\tBuffer\tOccupied");
		System.out.println("---------\t\t\t\t------\t--------");
		ExecutorService es = Executors.newCachedThreadPool();
		Buffer sharedLocation = new SynchronizedBuffer();
		
		
		es.execute(new Consumer(sharedLocation));
		es.execute(new Producer(sharedLocation));
		
		es.shutdown();
		es.awaitTermination(1, TimeUnit.MINUTES);
		JOptionPane.showMessageDialog(null, "Input Sentence:\n" + Producer.total.substring(0, Producer.total.length()-3) , "Message", JOptionPane.DEFAULT_OPTION);
		
	}

}
