
/*
* University of Central Florida
 * COP3330 - Fall 2015
 * Author: Kelsey Cameron
 */


package asteroidgame;

import java.util.ArrayList;
import java.util.Random;

import blobzx.BlobGUI;
import blobzx.SandBox;
import blobzx.SandBoxMode;
/*
 * 3) The no-argument constructor should: (a) instantiate a new SandBox,
 *  (b) set it to operate in "flow" mode, (c) set the frame rate value to
 *   15 frames per second, and (d) then run the sandbox's new "init()" method, 
 *   where the parameter that is passed to the init() method is "this".

(4) Add a "public void generate()" method that overrides the declaration
 in the BlobGUI interface. This method will perform the following actions:
  (a) instantiate your rocket and add it to the center of the sandbox, as 
  you did for Program 5; and (b) instantiate and add to the sandbox 10 
  asteroids with independent random x and y velocity components between -3 
  and +3 (with 0 values disallowed), and with randomly chosen +.1 or -.1
   rotation rates, as you did for Program 4.


 */
public class AsteroidGame implements BlobGUI{
	SandBox sand;
	Random random = new Random();
	static ArrayList<Asteroid> listofasteroids = new ArrayList<Asteroid>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AsteroidGame();
	}

	
	AsteroidGame(){
		sand = new SandBox();
		sand.setSandBoxMode(SandBoxMode.FLOW);
		sand.init(this);
		sand.setFrameRate(15);
	}


	@Override
	public void generate() {
		// TODO Auto-generated method stub
		Rocket rocket = new Rocket(300,300, sand);
		sand.addBlob(rocket);
		
		for(int x = 0; x < 10; x++){
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
			
			int which = random.nextInt(2);
			if(which == 0){
				rotRate = -0.1;
			}
			else{
				rotRate = 0.1;
			}
			Asteroid aster = new Asteroid(xv, yv, rotRate);
			
			sand.addBlob(aster);
			listofasteroids.add(aster);
			
			
			
	
			
			
			
	}
		
		
		
		
		
	}
	
}
