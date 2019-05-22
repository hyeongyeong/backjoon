package test11724;

import java.util.*;


public class Main {
	
	static int[][] adjacent;
	static int[] visited;
	static int graphSize, edgeSize;
	
	static void dfs(int index) {
		visited[index] = 1;
		for(int i = 1 ; i<=graphSize; i++) {
			if(adjacent[index][i] == 1 && visited[i] !=1) {
				dfs(i);
			}
		}
	}
	
	public static void main(String[] args) {
		
		
		int n1, n2;
		int cnt=0;
		
		Scanner scan = new Scanner(System.in);
		graphSize = scan.nextInt();
		edgeSize = scan.nextInt();
		
		adjacent = new int[graphSize+1][graphSize+1];
		visited = new int[graphSize+1];
		
		for(int i=0; i< edgeSize ; i++) {
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			adjacent[n1][n2] = 1;
			adjacent[n2][n1] = 1;
		}
		for(int i=1; i<=graphSize; i++) {
			if(visited[i] != 1) {
				cnt++;
				dfs(i);
			}
		}
		System.out.println(cnt);
	}
	
}
