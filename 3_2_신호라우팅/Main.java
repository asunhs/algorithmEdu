import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	static int N, M;
	static ArrayList<Node>[] adjList = new ArrayList[10000];
	static PriorityQueue<Node> queue = new PriorityQueue<Node>(new Comp());
	static double[] discovered = new double[10000];
	static double Answer;
	
	static class Comp implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			int result = 0;
			
			if((double)o1.getValue() < (double)o2.getValue()) {
				result = -1;
			} else if((double)o1.getValue() > (double)o2.getValue()) {
				result = 1;
			}
			
			return result;
		}
		
	}
	
	static class Node {
		private int index;
		private double value;
		
		public Node(int index, double value) {
			this.index = index;
			this.value = value;
		}
		
		public int getIndex() {
			return index;
		}
		
		public void setIndex(int index) {
			this.index = index;
		}
		
		public double getValue() {
			return value;
		}
		
		public void setValue(double value) {
			this.value = value;
		}
	}
	
	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("sample_input.txt"));
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		int C = Integer.parseInt(br.readLine().trim());
		
		int from, to;
		double val;
		String[] tmp;
		
		for(int c = 1; c <= C; c++) {
			tmp = br.readLine().trim().split(" ");
			N = Integer.parseInt(tmp[0]);
			M = Integer.parseInt(tmp[1]);
			
			for(int i = 0; i < N; i++) {
				if(adjList[i] != null) {
					adjList[i].clear();
				}
				discovered[i] = Double.MAX_VALUE;
			}
			queue.clear();
			
			for(int i = 0; i < M; i++) {
				tmp = br.readLine().trim().split(" ");
				from = Integer.parseInt(tmp[0]);
				to = Integer.parseInt(tmp[1]);
				val = Double.parseDouble(tmp[2]);
				
				if(adjList[from] == null) {
					adjList[from] = new ArrayList<Node>();
				}
				adjList[from].add(new Node(to, val ));
				
				if(adjList[to] == null) {
					adjList[to] = new ArrayList<Node>();
				}
				adjList[to].add(new Node(from, val));
			}
		
			dijk();
			System.out.printf("%.10f\n", Answer);
		}
	}
	
	public static void dijk() {
		queue.add(new Node(0, 1d));
		
		Node first;
		
		while(queue.isEmpty() == false) {
			first = queue.poll();
			
			if(first.getIndex() == N - 1) {
				break;
			}
			
			if(adjList[first.getIndex()] != null) {
				for(Node adj : adjList[first.getIndex()]) {
					if((int) adj.getIndex() == first.getIndex()) {
						continue;
					}
					
					if(discovered[adj.getIndex()] > first.getValue() * adj.getValue()) {
						queue.add(new Node(adj.getIndex(), first.getValue() * adj.getValue()));
						discovered[adj.getIndex()] = first.getValue() * adj.getValue();
					}
				}
			}
		}
		
		Answer = discovered[N - 1];
	}
}