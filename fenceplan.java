import java.io.*;
import java.util.*;

public class fenceplan {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("fenceplan.in"));
		out = new PrintWriter(new FileWriter("fenceplan.out"));//*/ //set up i/o streams
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //read in number of cows
		int m = Integer.parseInt(st.nextToken()); //read in number of paths
		int x;
		int y;
		Coords[] nodes = new Coords[n+1];
		for(int i = 1; i <= n; i++){
			st = new StringTokenizer(br.readLine()); //create the array of nodes
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			nodes[i] = new Coords(x, y);
		}
		for(int j = 0; j < m; j++){
			st = new StringTokenizer(br.readLine()); //add the neighbors for each node
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			nodes[one].addNeighbor(two);
			nodes[two].addNeighbor(one);
		}
		int minPerimeter = Integer.MAX_VALUE; //run dfs for all indices that haven't been covered by previous dfs (new moo networks)
		boolean[] visited = new boolean[n+1];
		for(int index = 1; index < visited.length; index++)
			if(!visited[index])
				minPerimeter = Math.min(minPerimeter, findPerimeter(dfs(nodes, index, visited), nodes)); //find minimum perimeter
		out.println(minPerimeter); //print answer
		out.close();
	}
	public static ArrayList<Integer> dfs(Coords[] array, int pos, boolean[] visited){ //dfs
		Coords node = array[pos];
		visited[pos] = true;
		ArrayList<Integer> locs = new ArrayList<Integer>();
		locs.add(pos);
		for(int neighbor : node.getNeighbors())
			if(!visited[neighbor])
				for(int val : dfs(array, neighbor, visited))
					locs.add(val); //all locations that are linked are added to the arraylist locs, which is then returned
		return locs;
	}
	public static int findPerimeter(ArrayList<Integer> locs, Coords[] array){ 
		int minX = Integer.MAX_VALUE; //the perimeter is the difference between the max x and the min x plus the max y and the min y
		int maxX = Integer.MIN_VALUE; //times two
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;
		for(int loc : locs){
			int[] coords = array[loc].getCoords();
			minX = Math.min(minX, coords[0]);
			maxX = Math.max(maxX, coords[0]);
			minY = Math.min(minY, coords[1]);
			maxY = Math.max(maxY, coords[1]);
		}
		return 2 * ((maxY - minY) + (maxX - minX)); //calculate those four values, and return the perimeter
	}
}
class Coords{ //create a node class that stores the x y coordinates as well as the neighbors
	int x;
	int y;
	ArrayList<Integer> neighbors;
	public Coords(int x, int y){
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