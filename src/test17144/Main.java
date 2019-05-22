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
				if(map[y][x] > 0)
					cnt += map[y][x];
			}
		}
		
		return cnt;
	}
	
	public static void mapCopy(int src[][], int dest[][]) {
		for(int y=0; y<C; y++) {
            for(int x=0; x<R; x++) {
                dest[y][x] = src[y][x];
            }
        } 
	}
	
//	public static void printMap(int src[][] ) {
//		for(int y=0; y<C; y++) {
//            for(int x=0; x<R; x++) {
//               System.out.print(src[y][x] + " ");
//            }
//            System.out.println();
//        } 
//	}
	
	public static void expansion(int x, int y) {
		
		int tempX, tempY;
		
		if(map[y][x] > 0) {
			for(int i=0;i<4;i++) {
				tempX = x + dx[i];
				tempY = y + dy[i];
				
				if(-1<tempX && tempX<R && -1<tempY && tempY <C && map[tempY][tempX] != -1) {
					copiedMap[tempY][tempX] += map[y][x]/5;
					copiedMap[y][x] -= map[y][x]/5;
				}
				
			}
		}
	}
	
	public static void cycleClean(int index, int x, int y, boolean fig) { // 시계방향으로 동작.
		
		
		if(x == cleaner.get(index).left && y== cleaner.get(index).right && fig) {
			return;	
		} 
		if(map[y][x] == -1) {
			copiedMap[y][x+1] = 0;
			cycleClean(index,x+1,y, true);
		} else if(y== cleaner.get(index).right && x<R-1 ) { // 오른쪽으로 이동 
			copiedMap[y][x+1] = map[y][x];
			cycleClean(index,x+1,y, fig);
		} else if(x==R-1 && y < C-1) { // 아래로 이동 
			copiedMap[y+1][x] = map[y][x];
			cycleClean(index,x,y+1, fig);
		} else if(x>0 && y ==C-1) { // 왼쪽으로 이동 
			copiedMap[y][x-1]=map[y][x];
			cycleClean(index,x-1,y, fig);
		} else if(x==0 && y>cleaner.get(index).right+1) { // 위로 이
			copiedMap[y-1][x]=map[y][x];
			cycleClean(index,x,y-1, fig);
		}
	}
	
	public static void acycleClean(int index, int x, int y, boolean fig) { // 반시계방향으로 동작.
		
		
		if(x == cleaner.get(index).left && y== cleaner.get(index).right&& fig) {
			return;
		}
		
		if(map[y][x] == -1) {
			copiedMap[y][x+1] = 0;
			acycleClean(index,x+1,y, true);
		} else if ( x < R-1 && y == cleaner.get(index).right ) { // 오른쪽으로 이동 
			copiedMap[y][x+1] = map[y][x];
			acycleClean(index, x+1, y,fig);
		} else if (y > 0 && x == R-1) { // 위로 이동 
			copiedMap[y-1][x] = map[y][x];
			acycleClean(index,x,y-1, fig);
		} else if (y==0 && x>0) { // 왼쪽으로 이동 
			copiedMap[y][x-1] = map[y][x];
			acycleClean(index,x-1,y, fig);
		} else if( x==0 && y < cleaner.get(index).right-1){ // 아래로 이동 
			copiedMap[y+1][x] = map[y][x];
			acycleClean(index,x,y+1, fig);
		}
	}
	
	public static void main(String[] args) {
		
		int T;
		int answer = 0;
		Scanner scan = new Scanner(System.in);
		
		C = scan.nextInt();
		R = scan.nextInt();
		T = scan.nextInt();
		
		map = new int[C][R];
		copiedMap = new int[C][R];
		
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
			mapCopy(copiedMap, map);
//			printMap(map);
			acycleClean(0, cleaner.get(0).left,cleaner.get(0).right, false); // 반시계 회전 
			cycleClean(1, cleaner.get(1).left,cleaner.get(1).right, false); // 시계 회전 
			mapCopy(copiedMap, map);
			
//			System.out.println(T);
//			printMap(map);
			
			
		}
		
		answer = getTotalDust(map);
		System.out.println(answer);
		
	}
	
}
