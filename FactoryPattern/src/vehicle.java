/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  FactoryDesign
 * Date: March 24, 2016
 */
public abstract class vehicle {
	int engines;
	int wheels;
	int wheelspervehicle;
	int enginespervehicle;
	int total;
	public abstract void setEngines(int e);
	public abstract void setWheels(int w);
	public abstract void calcEng(int howmany);
	public abstract void calcWheels(int howmany);
	public abstract void setParameters();
	public int getTotalNumber(){
		return total;
	}
	public int getWheels() {
		return wheels;
	}

	public int getEngines() {
		return engines;
	}
}
