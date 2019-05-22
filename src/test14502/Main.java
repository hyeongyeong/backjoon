package test14502;

import java.util.*;

public class Main {
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,1,-1};
	static int N, M;
	static Vector<Pair<Integer,Integer>> emptySpace = new Vector<Pair<Integer,Integer>>();
	static Vector<Pair<Integer,Integer>> virus = new Vector<Pair<Integer,Integer>>();

	
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
	
//    vec.add(Pair.of(x,y)); // my preference
//    vec.add(pairOf(x,y)); // use with import static x.y.Pair.pairOf
	
	public static int[][] expansion(int[][] board,int x, int y) { // target = board[y][x]
		
		
		for(int i = 0 ; i <4; i++) {
			int tempX = x + dx[i];
			int tempY = y + dy[i];
			
			if(-1 < tempX && tempX< M && -1<tempY && tempY<N) {
			
				if(board[tempY][tempX] == 0) {
					board[tempY][tempX] = 2;
					expansion(board, tempX, tempY);
				}	
			}
		
		}
		return board;
	}
	
	
	public static int getSafeZone(int[][] board) { // 안전지역 
		int cnt = 0;
		
		for(int y = 0; y < N ; y ++) {
			for (int x = 0; x <M ; x ++ ) {
				if(board[y][x]==0)
					cnt++;
			}
		}
		
		return cnt;
	}
	
	public static void mapCopy(int src[][], int dest[][]) {
		for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                dest[i][j] = src[i][j];
            }
        }
	}
	
	public static int build(int[][] board) { // 벽 설치 
		int max = 0;
		int[][] exBoard = new int[N][M];
		// empty에 저장되어있는 위치 3개를 받아서 expansion , safeZone 계산하여 max값 return 하기.
		
		for(int a = 0; a<emptySpace.size()-2; a++) {
			
			board[emptySpace.get(a).right][emptySpace.get(a).left] = 1; // 벽 
			
			for(int b = a+1; b<emptySpace.size()-1; b++) {
				
				board[emptySpace.get(b).right][emptySpace.get(b).left] = 1; // 벽 
				
				for(int c = b+1; c<emptySpace.size(); c++) {
					
					board[emptySpace.get(c).right][emptySpace.get(c).left] = 1; // 벽 
					
					mapCopy(board, exBoard);
					for(int v = 0; v<virus.size(); v++) {
						exBoard = expansion(exBoard,virus.get(v).left,virus.get(v).right);
					}
					int temp = getSafeZone(exBoard);
					max = max > temp? max : temp;
					
					board[emptySpace.get(c).right][emptySpace.get(c).left] = 0; // 벽 제거 
				}
				board[emptySpace.get(b).right][emptySpace.get(b).left] = 0; // 벽 제거 
			}
			board[emptySpace.get(a).right][emptySpace.get(a).left] = 0; // 벽 제거 
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		
		
		int board[][];
		int answer = 0;
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		
		board = new int[N][M];
		for(int y = 0 ; y<N ; y ++) {
			for(int x = 0 ; x<M ; x++) {
				board[y][x] = scan.nextInt();
				if(board[y][x] == 0) {
					emptySpace.add(Pair.of(x,y));
				} else if(board[y][x]==2) {
					virus.add(Pair.of(x,y));
				}
			}
		} 

		// 빈칸을 받아 emptySpace에 저장, board에 데이터 저장.
		answer = build(board);
		
		System.out.println(answer);
		
	}
	
}
