package 삼성1206;

import java.util.Scanner;

public class Solution {
	

	
	public static void main(String[] args) {
	
		int[] apart = new int[1000];
		int[] d = new int[1000];
		int N;
		int index;
		
		Scanner scan = new Scanner(System.in);
		
		for(int i=1; i<11; i++) {
			
			N = scan.nextInt();
			for(int j=0; j<N; j++) {
				apart[j] = scan.nextInt();
			}
			d[1] = 0;
			for(int j=2; j<N-2; j++) {
				index = Math.max(Math.max(apart[j-2], apart[j-1]),Math.max(apart[j+2], apart[j+1]));
				if(apart[j]>index) {
					d[j] = d[j-1] + apart[j] - index ; 
				}
				else d[j] = d[j-1];
			}
			
			System.out.print("#" + i+ " ");
			System.out.println(d[N-3]);
		}
		
	}
	
}

