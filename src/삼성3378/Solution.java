package 삼성3378;

import java.util.*;

public class Solution {

	
	public static void main(String[] args) {
	
		
		Scanner scan = new Scanner(System.in);
		int testCase = scan.nextInt();
		for(int T=1; T<=testCase; T++) {
			int N = scan.nextInt();
			int M = scan.nextInt();
			int K = scan.nextInt();
			String result = "Possible";
			boolean check = true;
			LinkedList<Integer> guest = new LinkedList<Integer>();
			for(int i = 0; i<N; i++) {
				guest.add(scan.nextInt());
			}
			Collections.sort(guest);
			int time = 0;
			int bread = 0;
			
			while(!guest.isEmpty() && result != "Impossible") {
				while(guest.getFirst() < time + M ) {
					if(bread == 0) {
						result = "Impossible";
						break;
					}
					
					bread --;
					guest.pollFirst();
					if(guest.isEmpty()) {
						result = "Possible";
						break;
					}
				}
				time += M;
				bread += K;
			}
			System.out.println("#" + T + " " + result);
		}
		
	}
	
}

