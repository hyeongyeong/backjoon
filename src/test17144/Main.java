package test17144;

import java.util.*;

public class Main {
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,1,-1};
	
	static int map[][];
	static int copiedMap[][];
	
	static int R;
	static int C;
	
	static Vector<Pair<Integer,Integer>> cleaner = new Vector<Pair<Integer,Integer>>();
	
	static class Pair<L,R> {
	      final L left;
	      final R right;

	      public Pair(L left, R right) {
	        this.left = left;
	        this.right = right;
	      }

	      static <L,R> Pair<L,R> of(L left, R right){
	          return new Pair<L,R>(left, right);
	      }
	}
	
	public static int getTotalDust(int[][] map) { // 미세먼지 총량 
		int cnt = 0;
		
		for(int y = 0; y < C ; y ++) {
			for (int x = 0; x <R ; x ++ ) {
				if(map[y][x] > -1)
					cnt += map[y][x];
			}
		}
		
		return cnt;
	}
	
	public static void mapCopy(int src[][], int dest[][]) {
		for(int i=0; i<C; i++) {
            for(int j=0; j<R; j++) {
                dest[i][j] = src[i][j];
            }
        }
	}
	
	public static void expansion(int x, int y) {
		
		int tempX, tempY;
		
		if(map[y][x] > -1) {
			for(int i=0;i<4;i++) {
				tempX = x + dx[i];
				tempY = y + dy[i];
				
				if(-1<tempX && tempX<C && -1<tempY && tempY <R) {
					copiedMap[tempY][tempX] += map[y][x]/5;
					copiedMap[y][x] -= map[y][x]/5;
					expansion(tempX,tempY);
				}
				
			}
		}
	}
	
	public static void clean(int direction, int x, int y) { // 공기청정기 2개, 반시계, 시계방향으로 동작.
		
	}
	
	public static void main(String[] args) {
		
		int T;
		int answer = 0;
		Scanner scan = new Scanner(System.in);
		
		R = scan.nextInt();
		C = scan.nextInt();
		T = scan.nextInt();
		
		map = new int[C][R];
		for(int y = 0 ; y<C ; y ++) {
			for(int x = 0 ; x<R ; x++) {
				map[y][x] = scan.nextInt();
				if(map[y][x] == -1) {
					cleaner.add(Pair.of(x,y));
				} 
			}
		} 
		
		mapCopy(map, copiedMap);
		
		while(T-- > 0) {
			
			for(int y=0; y<C; y++) {
				for(int x=0; x<R; x++) {
					expansion(x,y);
				}
			}
			
			clean(-1, cleaner.get(0).left,cleaner.get(0).right); // 반시계 회전 
			clean(1, cleaner.get(1).left,cleaner.get(1).right); // 시계 회전 
			mapCopy(copiedMap, map);
			
		}
		
		answer = getTotalDust(map);
		System.out.println(answer);
		
	}
	
}
