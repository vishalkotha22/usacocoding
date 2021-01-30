import java.io.*;
import java.util.*;

public class revegetate {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));
		PrintWriter out = new PrintWriter(new FileWriter("revegetate.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		StringBuilder binary = new StringBuilder();
		Node[] graph = new Node[n+1];
		for(int i = 1; i <= n; i++)
			graph[i] = new Node();
		for(int j = 0; j < m; j++){
			st = new StringTokenizer(br.readLine());
			char condition = st.nextToken().charAt(0);
			int fav1 = Integer.parseInt(st.nextToken());
			int fav2 = Integer.parseInt(st.nextToken());
			graph[fav1].setCondition(condition+"", fav2);
			graph[fav2].setCondition(condition+"", fav1);
		}
		boolean[] visited = new boolean[n+1];
		for(int k = 1; k <= n; k++)
			if(!visited[k]){
				binary.append("0");
				graph[k].setVal(1);
				dfs(graph, visited, k, 1, 'S');
			}
		out.println("1"+binary);
		out.close();
	}
	public static void dfs(Node[] graph, boolean[] visited, int pos, int val, char condition){
		if(visited[pos])
			return;
		visited[pos] = true;
		if(graph[pos].children.size() == 0)
			return;
		if(condition == 'S')
			graph[pos].setVal(val);
		else if(condition == 'D'){
			graph[pos].setVal(3 - val);
			val = 3 - val;
		}
		for(int i = 0; i < graph[pos].children.size(); i++)
			dfs(graph, visited, graph[pos].children.get(i), val, graph[pos].condition.get(i).charAt(0));
	}
	private static class Node{
		int val;
		ArrayList<String> condition;
		ArrayList<Integer> children;
		private Node(){
			condition = new ArrayList<String>();
			children = new ArrayList<Integer>();
			val = -1;
		}
		private void setCondition(String s, int v){
			condition.add(s);
			children.add(v);
		}
		private void setVal(int v){
			val = v;
		}
		public String toString(){
			return val + " " + condition + " " + children;
		}
	}
}
