/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  FactoryDesign
 * Date: March 24, 2016
 */
public class airplane extends vehicle{
	airplane(int num){
		setParameters();
		calcEng(num);
		calcWheels(num);
		total = num;
	}

	
	@Override
	public void setEngines(int e) {
		
		engines = e;
	}

	@Override
	public void setWheels(int w) {
		wheels = w;
	}

	@Override
	public void calcWheels(int howmany) {
		wheels = wheelspervehicle * howmany;
	}

	@Override
	public void calcEng(int howmany) {
		engines = enginespervehicle * howmany;
	}

	@Override
	public void setParameters() {
		wheelspervehicle = 12;
		enginespervehicle = 8;		
	}

	
	
}
