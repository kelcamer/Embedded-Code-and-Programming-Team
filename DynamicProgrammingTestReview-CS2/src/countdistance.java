
public class countdistance {

	public static void main(String[] args) {
		int n = 5;
		System.out.println(printCountRec(n));
		
		
	}
	/*
	 * n = 3
	 * n = 2	n = 1	n = 0	
	 * n = 1
	 * n = 0
	 * n = 1
	 * n = 0
	 * n = 0
	 * 
	 * 
	 * n = 1	1
	 * n = 2	2
	 * n = 3	4
	 * n = 4	7
	 * n = 5	13
	 * 
	 * 
	 */
private static int printCountRec(int dist){
	if(dist < 0){
		return 0;
	}
	if(dist == 0){
		return 1;
	}
	return printCountRec(dist-1) + printCountRec(dist-2)+ printCountRec(dist-3) ;
	
	
}
	

}
