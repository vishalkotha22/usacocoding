import java.io.*;
import java.util.*;

public class planting {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("planting.in"));
		PrintWriter out = new PrintWriter(new FileWriter("planting.out"));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Node[] tree = new Node[n+1];
		for(int i = 1; i <= n; i++)
			tree[i] = new Node();
		for(int j = 0; j < n-1; j++){
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			tree[p1].addChild(p2);
			tree[p2].addChild(p1);
		}
		int max = 0;
		for(int k = 1; k <= n; k++)
			max = Math.max(max, tree[k].children.size());
		out.println(max+1);
		out.close();
	}
	private static class Node{
		char type;
		ArrayList<Integer> children;
		private Node(){
			children = new ArrayList<Integer>();
			type = '0';
		}
		private void setType(char c){
			type = c;
		}
		private void addChild(int c){
			children.add(c);
		}
		private ArrayList<Integer> getChildren(){
			return children;
		}
	}
}
