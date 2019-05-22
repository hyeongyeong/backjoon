package 삼성1208;

import java.util.Scanner;

public class Solution {
	
	static int[] box = new int[100];
	
	static int Max (int a, int b) {
		int max;
		max = Math.max(box[a], box[b]);
		if(max == box[a]) return a;
		else return b;

	}
	
	static int Min (int a, int b) {
		int min;
		min = Math.min(box[a],box[b]);
		if(min == box[a]) return a;
		else return b;
	}
	
	public static void main(String[] args) {
	
		int max;
		int min;
		int N;
		int ans = 0;
		Scanner scan = new Scanner(System.in);
		
		for(int i=1; i<11; i++) {
			max = 0; 
			min = 0;
			N = scan.nextInt(); // 덤프 가능 횟수
			
			for(int j=0; j<100; j++) {
				box[j] = scan.nextInt();
			} // 값받기 
			
			
			for(int j=0; j<N; j++) { // 덤핑하기 
				max = 0; 
				min = 0;
				for(int k=1; k<100; k ++ ) {
					max = Max(max, k);
					min = Min(min, k);
				}
				
				box[max]--;
				box[min]++;
				
				if( j == N-1) {
					max = 0;
					min = 0;
					for(int k=1; k<100; k ++ ) {
						max = Max(max, k);
						min = Min(min, k);
					}
					ans = box[max] - box[min];
				}
			}
			System.out.print("#" + i+ " ");
			System.out.println(ans);
		}
		
	}
	
}

