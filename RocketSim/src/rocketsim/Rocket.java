package rocketsim;

import java.awt.Point;
import java.awt.event.KeyEvent;

import blobzx.BlobAction;
import blobzx.BlobUtils;
import blobzx.PolyBlob;
import blobzx.SandBox;

public class Rocket extends PolyBlob implements BlobAction {
	private double angle = 0.0;
	private final double delta = 0.15;
	private final double speed = 5.0;
	final int x_rocket[] = {10, -10, -5, -10};
	final int y_rocket[] = {0,-7,0,7};
	//int x_rocket[] = {5,0,8,10,8,0};
	//int y_rocket[] = {0,10,5,0,-5,-10};
	private int[] motion_x = new int[4];
	private int[] motion_y = new int[4];
	
	public Rocket(int x, int y, SandBox sand){
		super(0,0,0);
	
		setLoc(x, y);
		setPolygon(x_rocket, y_rocket);
	//	motion_x = x_rocket;
	//	motion_y = y_rocket;
		
		
	}
	
	@Override
	public void keyAction(KeyEvent key) {
		// TODO Auto-generated method stub
		int code = key.getKeyCode();
		switch(code){
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
