package rocketsim;

import blobzx.BlobGUI;
import blobzx.SandBox;
import blobzx.SandBoxMode;

public class RocketTest implements BlobGUI {

	public SandBox sand = new SandBox();
	public static void main(String[] args) {
		new RocketTest();
		
	}

	public RocketTest(){
		sand.setSandBoxMode(SandBoxMode.FLOW);
		sand.setFrameRate(15);
		sand.init(this);
	}

	@Override
	public void generate() {
		// TODO Auto-generated method stub
		// instantiate rocket and put in center
		// add rocket to sandbox
		Rocket rock = new Rocket(300, 300, sand);
		sand.addBlob(rock);
	}
}
