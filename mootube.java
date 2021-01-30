import java.io.*;
import java.util.*;

//Code by Vishal Kotha

public class mootube {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("mootube.in"));
		out = new PrintWriter(new FileWriter("mootube.out"));//*/ //set up i/o streams
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //read number of cows
		int q = Integer.parseInt(st.nextToken()); //read number of questions
		Video[] array = new Video[n+1]; //create a new array of Videos and initialize them 
		for(int i = 0; i <= n; i++)
			array[i] = new Video();
		for(int j = 0; j < n-1; j++){ //read the pairs and appropriately add them to the two neighbors of the right Video object
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			int relevance = Integer.parseInt(st.nextToken());
			array[one].addNeighbors(two, relevance);
			array[two].addNeighbors(one, relevance);
		}
		for(int k = 0; k < q; k++){ //for each question, print the answer
			st = new StringTokenizer(br.readLine());
			int relevance = Integer.parseInt(st.nextToken());
			int startingPos = Integer.parseInt(st.nextToken());
			out.println(dfs(array, startingPos, new boolean[n+1], relevance)-1); //we do -1 because the dfs includes the starting node
		}
		out.close(); //flush the output and close the stream
	}
	public static int dfs(Video[] nodes, int pos, boolean[] visited, int target){
		Video node = nodes[pos]; //recursively visit the nodes of each video if it hasn't been visited and has a relevance >= k, and
		visited[pos] = true; //store the number of nodes we visit and return that
		int count = 1;
		ArrayList<Integer> neighbors = node.getNeighbors();
		ArrayList<Integer> relevance = node.getRelevance();
		for(int i = 0; i < neighbors.size(); i++)
			if(!visited[neighbors.get(i)] && relevance.get(i) >= target)
				count += dfs(nodes, neighbors.get(i), visited, target);
		return count;
	}
}
class Video{ //a node class
	ArrayList<Integer> neighbors; //stores all neighbors
	ArrayList<Integer> relevance; //stores the relevance of the neighbor at the corresponding index in neighbors
	public Video(){ //default constructor
		neighbors = new ArrayList<Integer>();
		relevance = new ArrayList<Integer>();
	}
	public void addNeighbors(int n, int r){ //method to add values
		neighbors.add(n);
		relevance.add(r);
	}
	public ArrayList<Integer> getNeighbors(){ //getter for neighbors
		return neighbors;
	}
	public ArrayList<Integer> getRelevance(){ //getter for relevance
		return relevance;
	}
	public String toString(){ //toString so we can print Video for debugging purposes
		return neighbors + " " + relevance;
	}
}