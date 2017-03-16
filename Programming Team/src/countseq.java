import java.util.Scanner;

public class countseq {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		for (int x = 0; x < loop; x++) {
			String main = scanny.next();
			String sub = scanny.next();

			int[][] lettercount = new int[main.length()][sub.length()];

			// 2D count, similar to LCSS

			for (int b = sub.length()-1; b>=0; b--) {
			for (int a = main.length()-1; a >=0; a--) {
					
						if(main.charAt(a) == sub.charAt(b)){
							lettercount[a][b]++;
							
						}
						else{
						if(b == sub.length()-1){
							lettercount[a][b] =  lettercount[a][b-1];
						}
						}
					}
		
				}
			
			for (int a = 0; a < main.length(); a++) {
				for (int b = 0; b < sub.length(); b++) {
					System.out.print(lettercount[a][b] + " ");
					
				}
				System.out.println();
			}
		}
		}

	}



// min of n's and i's
/*
 * int max = 1; for(int q = b; q>=0; q--){ if(lettercount[q]!=0) max *=
 * lettercount[q]; }
 * 
 */