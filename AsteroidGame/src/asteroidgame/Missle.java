

/*
* University of Central Florida
 * COP3330 - Fall 2015
 * Author: Kelsey Cameron
 */

package asteroidgame;

import java.util.ArrayList;

import blobzx.Blob;
import blobzx.BlobProximity;

public class Missle extends Blob implements BlobProximity{

	public Missle(int locx, int locy, double angle) {
		super(locx, locy);
		// 5 for size and 5 for speed
		int speed = 5;
		int dx = (int)Math.round(speed*Math.cos(angle));
		int dy = (int)Math.round(speed*Math.sin(angle));
		
		setDelta(dx, dy);
		ArrayList<Integer> locations = new ArrayList<Integer>();
				// TODO Auto-generated constructor stub
	}

}
