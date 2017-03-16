package synchro;

public interface Buffer {
	
	public void put(String value) throws InterruptedException;
	public String get() throws InterruptedException;

}
