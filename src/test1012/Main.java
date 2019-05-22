package test1012;


import java.util.*;

public class Main {
	
	static int[][] land;
	static int T, M, N, K;
	static int dy[] = {1, -1, 0, 0};
	static int dx[] = {0, 0, 1, -1};
	
	public static void dfs(int y, int x) {
		for(int i=0; i<4; i++) {
			int tempX = x + dx[i];
			int tempY = y + dy[i];
			
			land[y][x] = 0;
			
			if(tempY > -1 && tempY < N && tempX > -1 && tempX < M) {
				
				if(land[tempY][tempX] == 1)
					dfs(tempY, tempX);
			}
		}
	}
	
	public static void main(String[] args) {
		
		int ans = 0;
		
		Scanner scan = new Scanner(System.in);
		
		T = scan.nextInt();
		while (T-- > 0) {
			
			ans = 0;
			
			M = scan.nextInt();
			N = scan.nextInt();
			K = scan.nextInt();
			
			land = new int[N][M];
			for(int i=0; i<K; i++) {
				int m, n;
				m = scan.nextInt();
				n = scan.nextInt();
				
				land[n][m] = 1;
			
			}
			
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(land[j][k] == 1) {
						ans ++;
						dfs(j,k);
					}
				}
			}
			
			System.out.println(ans);
		}
	}
}

