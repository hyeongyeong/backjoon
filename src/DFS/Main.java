package DFS;

import java.util.*;

class Queue<T> {
	class Node<T> {
		private T data;
		private Node<T> next;
		
		public Node(T data) {
			this.data = data;
		}
	}
	private Node<T> first;
	private Node<T> last;

	public void enqueue(T item) {
		Node<T> t = new Node<T>(item);
		
		
		if(last!= null) {
			last.next = t;
		}
		last = t;
		
		if(first == null) {
			first = last;
		}
	}
	public T dequeue() {
		if(first == null) {
			throw new NoSuchElementException();
		}
		
		T data = first.data;
		first = first.next;
		if(first == null) {
			last = null;
		}
		return data;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
		
	
}

class Graph {
	class Node {
		int data;
		LinkedList<Node> adjacent;
		boolean marked;
		
		Node (int data) {
			this.data  = data;
			this.marked = false;
			adjacent = new LinkedList<Node>();
		}
	}
	
	Node[] nodes;
	
	Graph (int size) {
		nodes = new Node[size];
		for (int i = 0 ; i < size ; i++) {
			nodes[i] = new Node(i);
		}
	}
	
	void init(int size) {
		for (int i = 0 ; i < size ; i++) {
			nodes[i].marked = false;
		}
	}
	
	void addEdge(int i1, int i2) {
		Node n1 = nodes[i1];
		Node n2 = nodes[i2];
		if(!n1.adjacent.contains(n2)) {
			n1.adjacent.add(n2);
		}
		if (!n2.adjacent.contains(n1)) {
			n2.adjacent.add(n1);
		}
	}	
	
	void dfs() {
		dfs(0);
	}
	
	void dfs(int index) {
		Node root = nodes[index];
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		root.marked = true;
		while (!stack.isEmpty()) {
			Node r = stack.pop();
			for(Node n : r.adjacent) {
				if(n.marked == false) {
					n.marked = true;
					stack.push(n);
				}
			}
			visit(r);
		}
	}
	
	void bfs() {
		bfs(0);
	}
	
	void bfs(int index) {
		Node root = nodes[index];
		Queue<Node> queue = new Queue<Node>();
		
		queue.enqueue(root);
		root.marked = true;
		while(!queue.isEmpty()) {
			Node r = queue.dequeue();
			for(Node n : r.adjacent) {
				if (n.marked == false) {
					n.marked = true;
					queue.enqueue(n);
				}
			}
			visit(r);
		}
	}
	
	void dfsR(Node r) {
		
		if (r == null) return;
		r.marked = true;
		visit(r);
		for(Node n : r.adjacent) {
			if(n.marked == false) {
				dfsR(n);
			}
		}
	}
	
	void dfsR(int index) {
		Node r = nodes[index];
		dfsR(r);
	}
	
	void visit(Node n) {
		System.out.print(n.data + " ");
	}
	
	void dfsR() {
		dfsR(0);
	}
	
}

public class Main {
	
	public static void main(String[] args) {

		int graphSize, edgeSize, startEdge;
		int n1, n2;
		
		Scanner scan = new Scanner(System.in);
		graphSize = scan.nextInt();
		edgeSize = scan.nextInt();
		startEdge = scan.nextInt();
		
		Graph g = new Graph(graphSize+1);
		for(int i=0; i<edgeSize; i++) {
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			g.addEdge(n1, n2);
		}
		
		g.dfsR(startEdge);
		System.out.println("");
		g.init(graphSize+1);
		g.bfs(startEdge);
		
		scan.close();
		
	}
}
