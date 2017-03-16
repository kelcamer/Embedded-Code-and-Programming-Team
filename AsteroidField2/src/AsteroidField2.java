/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Kelsey Cameron
 */
import java.util.Random;

import blobzx.Blob;
import blobzx.BlobGUI;
import blobzx.SandBox;
import blobzx.SandBoxMode;

public class AsteroidField2 implements BlobGUI{
	Random random = new Random();
	public static void main(String[] args){
		//System.out.println("Hello world");
		new AsteroidField2();
	}
	@Override
	public void generate() {
		// TODO Auto-generated method stub
		SandBox sand = new SandBox();
		sand.setSandBoxMode(SandBoxMode.FLOW);
		sand.init(this);
		sand.setFrameRate(15);
		/*
		 *      (i) it must create 20 asteroids using the velocity components and rotational values described here;

     (ii) it must randomly choose x and y velocity components for each asteroid, where the x and y components are chosen independently of each other and where each of these values is an integer that may range from -3 to +3, but where zero values are disallowed, all as discussed in lecture;

     (iii) it must randomly choose a rotation value of either -.1 or +.1 for each asteroid, with equal probability. Values in between -.1 and +.1 are not permitted; and

     (iv) it must add each asteroid to the sandbox.
		 */
		for(int x = 0; x < 20; x++){
			int xv = random.nextInt(6);
			while(xv-3 == 0){
				xv = random.nextInt(6);
			}
			int yv = random.nextInt(6);
			while(yv-3 == 0){
				yv = random.nextInt(6);
			}
			
			xv-=3;
			yv-=3;
			
			double rotRate = 0.0;
			
			int which = random.nextInt(1);
			if(which == 0){
				rotRate = -0.1;
			}
			else{
				rotRate = 0.1;
			}
			
			sand.addBlob(new Asteroid2(xv, yv, rotRate));
			
		}
	}

}
