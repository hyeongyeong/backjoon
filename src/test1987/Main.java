package test1987;

import java.util.*;

public class Main {
	
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	// 상하 좌우 이동 경우의 수 (-1,0) (0,-1) (0,1) (1,0)
	static int R, C;
	static int cnt = 1;
	static int maxValue = 1;
	static char[][] board;
	
	
	public static int Max(int a, int b) {return a>b ? a:b;}
	// 좌표평면 (x,y) , array [y][x]
	// 좌표평면 행/열, array는 열/행으로 구성되어 있음.
	// x -> C, y -> R
	// 사실 이 문제에선 필요 없지만 알아두기. 
	public static void dfsBactracking(boolean[] visited, int y, int x) {
		int index = (int)(board[y][x] - 'A'); 
		
		visited[index] = true;
		for (int i=0; i<4; i++) {
			int tempX = x + dx[i];
			int tempY = y + dy[i];
			
			if(-1< tempX && tempX < C && -1 < tempY && tempY<R) {
				int possible = (int)(board[tempY][tempX] - 'A');
				if(!visited[possible]) {
					maxValue = Max(++cnt, maxValue);
					dfsBactracking(visited, tempY, tempX);
				}
			}
		}
		
		cnt--;
		visited[index] = false;
		
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		boolean[] visitedAlphabet = new boolean[26];
		
		R = scan.nextInt();
		C = scan.nextInt();
		
		board = new char[R][C];
		
		for(int i=0; i<R; i++) {
			board[i] = scan.next().toCharArray();

		}
		
		dfsBactracking(visitedAlphabet,0,0);
		System.out.println(maxValue);
		
	}
}
