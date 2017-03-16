/*
 * University of Central Florida
 * COP3330 - Fall 2015
 * Author: Kelsey Cameron
 */
package asteroidgame;
import java.awt.Point;
import java.util.Random;

import blobzx.BlobUtils;
import blobzx.PolyBlob;

public class Asteroid extends PolyBlob {
	// get asteroids to move in both directions TODO
	public static final double oneradian = 0.0174532925;
	int xvelocity, yvelocity;
	double rotrate;
	private static Random random = new Random();

	public Asteroid(int xvel, int yvel, double rotationrate) {
		super(xvel, yvel, rotationrate);
		this.xvelocity = xvel;
		this.yvelocity = yvel;
		this.rotrate = rotationrate;
		
		setLoc(-100, -100);
		setDelta(xvel, yvel);
		
		int sides = random.nextInt(4) + 5;
		
		// creates a list of corners
		int[] vertices = new int[sides];
		// holds all point values
		int[] x = new int[sides];
		int[] y = new int[sides];
		// holds all angles
		double[] angles = new double[sides];
		// He divided up the area into however many sides there are to create different regions
		/*
		 * 	//////////////////////////////
		 *  ////				//		//
		 *  //	//			//			//
		 *  //	  //	//				//
		 *  //		//					//
		 *  //	 // 	//				//
		 *  /////			//			//
		 *  //////////////////////////////
		 */
		double region = 2 * Math.PI / sides;
		
		// for each side 
		for (int a = 0; a < sides; a++) {
			// your first vertice is a random number between 5 and 15.
			vertices[a] = random.nextInt(10) + 5;
			// the angle is the total region of where you're at plus a random area between 0 and 1 in that region.
			angles[a] = (a * region) + (Math.random() * region);
			// creates a point and rotates it that angle
			Point point = BlobUtils.rotatePoint(vertices[a], angles[a]);
			// storage
			x[a] = point.x;
			y[a] = point.y;
		}
		// creates the shape
		setPolygon(x, y);

		/*
		 * This approach wouldn't work because polygons would cross over. double
		 * totalangles = 0.0; for(int a = 0; a < num_of_sides; a++){
		 * 
		 * double randAng = random.nextInt() % (90*oneradian) + oneradian; int
		 * randRadius = random.nextInt() % 15 + 5; if(a < num_of_sides-1){
		 * totalangles+=randAng; Point p = BlobUtils.rotatePoint(randRadius,
		 * randAng); x[a] = p.x; y[a] = p.y; } else{ double lastAng =
		 * (360*oneradian) - totalangles; Point p =
		 * BlobUtils.rotatePoint(randRadius, lastAng); x[a] = p.x; y[a] = p.y; }
		 * 
		 * } setPolygon(x, y);
		 * 
		 */

	}
}
