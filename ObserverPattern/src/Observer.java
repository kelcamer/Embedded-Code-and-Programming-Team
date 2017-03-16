/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  ObserverPattern.java
 * Date: April 17, 2016
 */
// creates an interface to define the objects we will be working with

public interface Observer {
	public void update(int subCount, int vidCount, int viewCount);
	public void setSleepMode(boolean hi);
}
