package 삼성1210;
// 진행중.
import java.util.*;
import java.io.*;

public class Solution {
	
	static int dy[] = {0, 0, 1};
	static int dx[] = {1, -1, 0};
	static int[][] ladder = new int [100][100];
	
	public static boolean findPath(int y, int x) {
		int nx, ny;
		boolean direction = false;
		
		ladder[y][x] = 0;
		
		for(int i=0; i<3; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			if(nx<0 || nx>99 || ny<0 || ny>99)
				continue;
			
			
			if(ladder[ny][nx] == 1) {
				direction = true;
				findPath(ny, nx);
			} else if(ladder[ny][nx] == 2) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
	
		int ans=0;
		int T=0;
		
		Scanner scan = new Scanner(System.in);
		
		for(int i=1; i<11; i++) {
			
			T = scan.nextInt();
			
			for(int j=0; j<100; j++) {
				for(int k=0; k<100;k++) {
					ladder[j][k]=scan.nextInt();
				}
			}
			for(int j=0; j<100; j++) {
				if(ladder[0][j] == 1) {
					ans = j;
					if(findPath(0,j)) {
						System.out.print("#" + i+ " ");
						System.out.println(ans);
					};
				}
			}
		}
		
	}
	
}

