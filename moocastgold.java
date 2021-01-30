import java.io.*;
import java.util.*;

public class moocastgold {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("moocast.in"));
		out = new PrintWriter(new FileWriter("moocast.out")); //*/
		int n = Integer.parseInt(br.readLine());
		WalkieTalkie[] nodes = new WalkieTalkie[n+1];
		StringTokenizer st;
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			nodes[i+1] = new WalkieTalkie(x, y);
		}
		int low = 0;
		int high = 1250000000;
		while(low < high-1){
			int mid = (low + high) / 2;
			if(dfs(nodes, 1, new boolean[n+1], mid))
				high = mid;
			else
				low = mid+1;
		}
		if(dfs(nodes, 1, new boolean[n+1], low)) out.println(low);
		else if(dfs(nodes, 1, new boolean[n+1], high)) out.println(high);
		else if(dfs(nodes, 1, new boolean[n+1], low-1)) out.println(low-1);
		else if(dfs(nodes, 1, new boolean[n+1], high+1)) out.println(high+1);
		else throw new IllegalArgumentException();
		out.close();
	}
	public static boolean dfs(WalkieTalkie[] nodes, int pos, boolean[] visited, int money){
		visited[pos] = true;
		ArrayList<Integer> neighbors = findNeighbors(nodes, pos, money);
		if(neighbors.size() == 0)
			return false;
		for(int val : neighbors)
			if(!visited[val])
				if(!dfs(nodes, val, visited, money))
					return false;
		return true;
	}
	public static ArrayList<Integer> findNeighbors(WalkieTalkie[] nodes, int pos, int money){
		WalkieTalkie source = nodes[pos];
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int[] coords = source.getCoords();
		for(int i = 1; i < nodes.length; i++){
			int[] xy = nodes[i].getCoords();
			double distanceSquared = Math.pow(xy[0]-coords[0], 2)+Math.pow(xy[1]-coords[1], 2);
			if(distanceSquared <= money && i != pos)
				ret.add(i);
		}
		return ret;
	}
}
class WalkieTalkie{
	int x, y;
	public WalkieTalkie(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int[] getCoords(){
		return new int[]{x, y};
	}
	public String toString(){
		return x + " " + y;
	}
}