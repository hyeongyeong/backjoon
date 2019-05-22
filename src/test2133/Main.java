package test2133;


import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		
		int[] d;
		int N;
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		
		d = new int[N+1];
		
		
		if(N%2 !=0) {
			System.out.println("0");
			return;
		} else if(N==2) {
			System.out.println("3");
			return;
		}
		
		d[0] = 1; d[2] = 3; d[3] = 0;
		
		for(int i = 4; i <= N; i = i+2) {
			d[i] = d[i-2] * 3;
			for(int j=4; j<=i; j = j+2) {
				d[i] += d[i-j]*2; 
			}
		}
		
		
		System.out.println(d[N]);
		
	}
}

