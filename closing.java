import java.io.*;
import java.util.*;

//Code by Vishal Kotha

public class closing {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("closing.in"));
		out = new PrintWriter(new FileWriter("closing.out"));//*/ //set up i/o streams
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int n = Integer.parseInt(st.nextToken()); //read number of nodes
		int m = Integer.parseInt(st.nextToken()); //read number of paths
		Node[] nodes = new Node[n+1];
		for(int a = 0; a < nodes.length; a++) //initialize nodes in array
			nodes[a] = new Node(a); 
		for(int i = 0; i < m; i++){ //add the neighbors for each node in the array
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			nodes[one].addNeighbor(two);
			nodes[two].addNeighbor(one);
		}
		ArrayList<Integer> removed = new ArrayList<Integer>(); //this arraylist will store the stations (nodes) that have been closed
		if(connected(nodes, n, new boolean[n+1], removed)) //test if the stations are initially connnected
			out.println("YES"); //if true print yes
		else
			out.println("NO"); //else print no
		for(int j = 0; j < n-1; j++){ //read in n-1 closing (it's not necessary to read the nth closing since we don't print anything)
			int closing = Integer.parseInt(br.readLine()); //store station that's been closed
			removed.add(closing); //add the station that's been closed to removed
			//we need to remove all instances of closing from our adjacency list because it's no longer accessible
			for(int val : nodes[closing].getNeighbors()) //remove closing from all nodes that have closing as a neighbor
				nodes[val].removeNeighbor(closing);
			nodes[closing].clearNeighbors(); //remove all of closing's neighbors
			if(removed.size() < n-1){ //if the number of stations left is greater than or equal to two
				if(connected(nodes, n-removed.size(), new boolean[n+1], removed)){ //if it's connected print yes
					out.println("YES");
				}
				else{ //otherwise print no
					out.println("NO");
				}
			}
			else{ //if there's only one station, it's always going to be true
				out.println("YES");
			}
		}
		out.close();		
	}
	public static boolean connected(Node[] nodes, int n, boolean[] visited, ArrayList<Integer> closed){ //check if it's fconnected
		int startIndex = 0; //initialize a variable called startIndex
		if(n <= 1) //if there's one station (or less but this won't happen), return true
			return true;
		while(startIndex < nodes.length && nodes[startIndex].getNeighbors().size() == 0) //find first index that has neighbors
			startIndex++;
		if(startIndex == nodes.length) //if there are no paths and n >= 2 then return false
			return false;
		int count = dfs(nodes, startIndex, visited); //check the number of stations that are linked with startIndex
		return count >= n; //if count is less than n (the number of stations), then it's false, otherwise it's true
	}
	public static int dfs(Node[] nodes, int pos, boolean[] visited){
		Node node = nodes[pos]; //initalize node
		visited[pos] = true; //set visited of pos to true
		int count = 1; //initialize a variable called count at 1 since visited[pos] is true
		for(int neighbor : node.getNeighbors()) //for each neighbor in node's neighbors
			if(!visited[neighbor]) //if it hasn't been visited
				count += dfs(nodes, neighbor, visited); //call dfs to recursively count it's neighbors
		return count; //return count
	}
}
class Node{
	int num; //two fields; num stores the value of the node, neighbors stores the neighbors of the node
	ArrayList<Integer> neighbors;
	public Node(int n){ //constructor given n, sets num to n and neighbors to a new default constructor
		num = n;
		neighbors = new ArrayList<Integer>();
	}
	public void addNeighbor(int n){ //add n to neighbors
		neighbors.add(n);
	}
	public void removeNeighbor(int rem){ //remove rem from neighbors
		neighbors.remove(neighbors.indexOf(rem));
	}
	public void clearNeighbors(){ //delete all neighbors
		neighbors.clear();
	}
	public ArrayList<Integer> getNeighbors(){ //fetch neighbors
		return neighbors;
	}
	public int getVal(){ //fetch val
		return num;
	}
	public String toString(){ //convert Node into a string
		return num + " " + neighbors;
	}
}