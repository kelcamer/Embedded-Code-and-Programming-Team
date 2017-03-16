

/*
* University of Central Florida
 * COP3330 - Fall 2015
 * Author: Kelsey Cameron
 */


package asteroidgame;

import java.awt.Point;
import java.awt.event.KeyEvent;

import blobzx.BlobAction;
import blobzx.BlobProximity;
import blobzx.BlobUtils;
import blobzx.PolyBlob;
import blobzx.SandBox;

public class Rocket extends PolyBlob implements BlobProximity, BlobAction {
	private double angle = 0.0;
	private final double delta = 0.15;
	private final double speed = 5.0;
	final int x_rocket[] = {10, -10, -5, -10};
	final int y_rocket[] = {0,-7,0,7};
	//int x_rocket[] = {5,0,8,10,8,0};
	//int y_rocket[] = {0,10,5,0,-5,-10};
	private int[] motion_x = new int[4];
	private int[] motion_y = new int[4];
	SandBox s;
	public Rocket(int x, int y, SandBox sand){
		super(0,0,0);
	
		setLoc(x, y);
		setPolygon(x_rocket, y_rocket);
		s= sand;
		
	}
	
	public void keyAction(KeyEvent key) {
		// TODO Auto-generated method stub
		int code = key.getKeyCode();
		switch(code){
		case 32:
			launch(s);
			BlobUtils.playSound();
			break;
		case 37:
			// left
			angle-=delta;
			if(angle < 0){
				angle+= 2*Math.PI;
			}
			turn();
			break;
			
		case 38:
			// up
			int xloc = getLoc().x;
			int yloc = getLoc().y;
			
			xloc = xloc + (int) Math.round(speed*Math.cos(angle));
			yloc = yloc + (int) Math.round(speed*Math.sin(angle));
			setLoc(xloc, yloc);
			break;
			
		case 39:
			// right
			angle+=delta;
			if(angle > 2* Math.PI){
				angle-= 2*Math.PI;
			}
			turn();
			
			break;
		}
		
		
		
	}
	private void launch(SandBox sb) {
		// TODO Auto-generated method stub
		int boundingRad = getSize() / 2;
		int launchPoint = boundingRad + 5;
		int xloc = getLoc().x;
		int yloc = getLoc().y;
		
		Point p = BlobUtils.translatePoint(xloc, yloc, launchPoint, angle);
		
		Missle myMis = new Missle(p.x, p.y, angle);
		sb.addBlob(myMis);
		
		
		/*for(int x = 0; x < 10; x++){
			if(AsteroidGame.listofasteroids.get(x).close(myMis)){
				//System.out.println("HIII");
				sb.removeBlob(myMis);
				sb.removeBlob(AsteroidGame.listofasteroids.get(x));
				SandBox.addScore();
			}
			
			
		}*/
		

	
	}

	public void turn(){
		 //System.out.println("Turning is being called");
		for(int z = 0; z < motion_x.length; z++){
			Point p = BlobUtils.rotatePoint(x_rocket[z], y_rocket[z], angle);
			
			motion_x[z] = p.x;
			motion_y[z] = p.y;
			//System.out.println("x " + motion_x[z] + " y " + motion_y[z]);
		}
		
		setPolygon(motion_x, motion_y);

	}

}
