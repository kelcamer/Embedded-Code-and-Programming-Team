/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  FactoryDesign
 * Date: March 24, 2016
 */
public class boat extends vehicle{
	boat(int num){
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
	public void calcEng(int howmany) {
		engines = enginespervehicle * howmany;
	}

	@Override
	public void calcWheels(int howmany) {
		wheels = 0;
	}

	@Override
	public void setParameters() {
		wheelspervehicle = 0;
		enginespervehicle = 4;
	}
	
}
