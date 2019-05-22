package test14891;


import java.util.*;
import java.io.*;

public class Main {
	
	// index = 톱니바퀴 번호. 
	
	static LinkedList<Integer>[] Gear = new LinkedList[5];

	public static void checkLeft(int index, int currentDirection) {
		if(index < 1 || index > 4) return;
		
		if(checkPole(index, index+1)) { // checkLeft(n-1, -d)
			checkLeft(index-1, -currentDirection);
			rotate(index, currentDirection);
		}
		
	}
	public static void checkRight(int index, int currentDirection) {
		if(index < 1 || index > 4) return;
		
		if(checkPole(index, index-1)) { // checkRight(n+1, -d)
			checkRight(index+1, -currentDirection);
			rotate(index, currentDirection);
		}
	}
	
	public static boolean checkPole(int currentIndex, int comparedWith) {
		// 2번, 6번 톱의 극을 비교하여 같으면 false 다르면 true를 반환한다. 
		int a,b;
		// 톱니바퀴 순서 : b a 
		a = Math.max(currentIndex, comparedWith);
		b = Math.min(currentIndex, comparedWith);
		if(a>4 || b<1) return false;
		
		if(Gear[b].get(2) == Gear[a].get(6)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void rotate(int index, int direction) {
		if(direction == 1) {
			Gear[index].addFirst(Gear[index].pollLast());
		} else {
			Gear[index].addLast(Gear[index].pollFirst());
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for(int i=1; i<5; i++) {
			Gear[i] = new LinkedList<Integer>();
			String input = sc.next();
			for(String s: input.split("")) {
				int n = Integer.parseInt(s);
				Gear[i].add(n);
			}
			//System.out.println(Gear[i]);
		}
		// 톱니바퀴 정보 저장. 
		
		int rotateNum = sc.nextInt();
		for(int k=0; k<rotateNum; k++) {
			int n = sc.nextInt();
			int d = sc.nextInt();
			
			checkLeft(n-1, -d);
			checkRight(n+1, -d);
			rotate(n, d);
		}
		
		int ans=0;
		for(int i=0; i<4;i++) {
			//System.out.println((int) Math.pow(2, i) * Gear[i+1].get(0));
			ans += (int) Math.pow(2, i) * Gear[i+1].get(0);
		}
		
		System.out.println(ans);
	}
}

