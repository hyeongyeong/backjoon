package test1932;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		int answer=0;
		Scanner scan = new Scanner(System.in);
		
		int size = scan.nextInt();
		int[][] triangle = new int[size+1][size+1];
		int[][] buffer = new int[size+1][size+1];
		
		for(int i=0; i<size; i++) {
			for(int j=1;j<=i+1; j++) {
				triangle[i][j] = scan.nextInt();
			}
		}
		
		// ok
		
		buffer[0][1] = triangle[0][1];

		for(int i=1; i<size; i++) {
			for(int j=1;j<=i+1; j++) {
				buffer[i][j] = Math.max(triangle[i][j] + buffer[i-1][j-1] , triangle[i][j] + buffer[i-1][j] );
			}
		}
	
		
		int maxValue = 0;
		
		for(int i = 1 ; i<= size; i++ ) {
			maxValue = Math.max(maxValue, buffer[size-1][i]);
		}
		
		System.out.println(maxValue);
		
	}
}
