import java.util.Random;

public class randomLong {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int i = rand.nextInt();
		if(i < 0){
			i*=-1;
		}
		int i2 = rand.nextInt(i+1)+1;
		
		
		long l = i2/i;
		System.out.println(i);
		System.out.println(i2);
		System.out.println(l);
		
		
	}

}
