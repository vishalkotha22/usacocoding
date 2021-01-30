import java.io.*;
import java.util.*;

public class fenceplanfast {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("fenceplan.in"));
		out = new PrintWriter(new FileWriter("fenceplan.out"));//*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x;
		int y;
		Coords2[] nodes = new Coords2[n+1];
		for(int i = 1; i <= n; i++){
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			nodes[i] = new Coords2(x, y);
		}
		for(int j = 0; j < m; j++){
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			nodes[one].addNeighbor(two);
			nodes[two].addNeighbor(one);
		}
		int minPerimeter = Integer.MAX_VALUE;
		boolean[] visited = new boolean[n+1];
		for(int index = 1; index < visited.length; index++)
			if(!visited[index]){
				int tempx1 = Integer.MAX_VALUE;
				int tempx2 = Integer.MIN_VALUE;
				int tempy1 = Integer.MAX_VALUE;
				int tempy2 = Integer.MIN_VALUE;
				ArrayList<Integer> list = dfs(nodes, index, visited, tempx1, tempy1, tempx2, tempy2);
				minPerimeter = Math.min(minPerimeter, list.get(list.size()-1));
			}
		out.println(minPerimeter);
		out.close();
	}
	public static ArrayList<Integer> dfs(Coords2[] array, int pos, boolean[] visited, int x1, int y1, int x2, int y2){
		Coords2 node = array[pos];
		visited[pos] = true;
		ArrayList<Integer> locs = new ArrayList<Integer>();
		locs.add(pos);
		int[] coords = node.getCoords();
		x1 = Math.min(x1, coords[0]);
		x2 = Math.max(x2, coords[0]);
		y1 = Math.min(y1, coords[1]);
		y2 = Math.max(y2, coords[1]);
		for(int neighbor : node.getNeighbors())
			if(!visited[neighbor]){
				ArrayList<Integer> vals = dfs(array, neighbor, visited, x1, y1, x2, y2);
				for(int i = 0; i < vals.size()-1; i++){
					locs.add(vals.get(i));
					int[] xy = array[vals.get(i)].getCoords();
					x1 = Math.min(x1, xy[0]);
					x2 = Math.max(x2, xy[0]);
					y1 = Math.min(y1, xy[1]);
					y2 = Math.max(y2, xy[1]);
				}
			}
		int perim = 2 * ((x2 - x1) + (y2 - y1));
		locs.add(perim);
		return locs;
	}
}
class Coords2{
	int x;
	int y;
	ArrayList<Integer> neighbors;
	public Coords2(int x, int y){
		this.x = x;
		this.y = y;
		neighbors = new ArrayList<Integer>();
	}
	public int[] getCoords(){
		return new int[]{x, y};
	}
	public ArrayList<Integer> getNeighbors(){
		return neighbors;
	}
	public void addNeighbor(int n){
		neighbors.add(n);
	}
	public String toString(){
		return x + " " + y + " " + neighbors;
	}
}