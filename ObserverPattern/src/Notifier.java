/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  ObserverPattern.java
 * Date: April 17, 2016
 */
import java.util.ArrayList;

public class Notifier implements Subject {
	// variables
	ArrayList<Observer> people;
	int viewcount;
	int vidcount;
	int subcount;

	// creates new arraylist inside of here, to store the people who will be notified
	Notifier() {
		people = new ArrayList<Observer>();
	}

	// adds object to list
	@Override
	public void register(Observer o) {
		people.add(o);

	}

	// removes object from list
	@Override
	public void unregister(Observer o) {
		int ind = people.indexOf(o);
		System.out.println("Account " + (ind) + " deleted.");
		people.remove(ind-1);
	}
	public void unregister(int ind){
		System.out.println("Account " + (ind) + " deleted.");
		people.remove(ind-1);
	}
	// removes the person with a matching num, stores deleted person, turns off sleep mode, adds back
	public void triggerSleepMode(int num) {
		Observer o = people.remove(num - 1);
		o.setSleepMode(true);
		people.add(o);
	}
	// calls update function on all people
	@Override
	public void notifyObserver() {
		for (Observer o : people) {
			o.update(subcount, vidcount, viewcount);
			
			
		}

	}
	// next three functions just set values
	public void setViewCount(int i) {
		this.viewcount = i;
	}

	public void setVidCount(int i) {
		this.vidcount = i;

	}

	public void setSubCount(int i) {

		this.subcount = i;
	}

}
