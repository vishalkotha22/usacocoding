import java.io.*;
import java.util.*;

//Code by Vishal Kotha

public class moocast {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("moocast.in"));
		out = new PrintWriter(new FileWriter("moocast.out"));//*/ //set up i/o streams
		int n = Integer.parseInt(br.readLine()); //read number of cows
		Node2[] nodes = new Node2[n]; //create a node array of size n
		StringTokenizer st;
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine()); //read x coordinate, y coordinate, and power
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			nodes[i] = new Node2(x, y, p); //set nodes[i] equal to a new node of the values we just read
			for(int j = i-1; j >= 0; j--){ //check nodes[i] with all previous nodes
				int[] coords = nodes[j].getCoords(); //fetch coords of nodes[j]
				double distance = Math.sqrt(Math.pow(x-coords[0], 2)+Math.pow(y-coords[1], 2)); //calculate distance between cows
				if(distance <= p){ //if distance is <= p, cows[i] can transmit to cows[j]
					nodes[i].addNeighbor(j); //therefore add j to the neighbors of nodes[i]
				}
				if(distance <= nodes[j].getPower()){ //if distance <= nodes[j].getPower(), cows[j] can transmit to cows[i]
					nodes[j].addNeighbor(i); //therefore add i to the neighbors of nodes[j]
				}
			}
		}
		int max = Integer.MIN_VALUE; //initialize an integer called max
		for(int i = 0; i < nodes.length; i++)
			max = Math.max(max, dfs(nodes, i, new boolean[n])); //max gets set equal to the maximum number of cows that can be reached
		out.println(max); //print max
		out.close(); 
	}
	public static int dfs(Node2[] array, int pos, boolean[] visited){
		Node2 node = array[pos]; //set node to the node at pos in array
		visited[pos] = true; //set visited[pos] to true so we know that it's been visited
		int count = 1; //initialize count that starts from 1 because we must factor array[pos]
		for(int val : node.getNeighbors()) //for each of the neighbors in node
			if(!visited[val]) //if it hasn't been visited before
				count += dfs(array, val, visited); //add the dfs of the neighbor to count
		return count; //return count
	}
}
class Node2{ //simple node class
	int x; //fields of the x coordinate, y coordinate, power, and neighbors
	int y;
	int power;
	ArrayList<Integer> neighbors;
	public Node2(int x, int y, int power){ //read in x coordinate, y coordinate, and power. set neighbors a blank arraylist for now
		this.x = x;
		this.y = y;
		this.power = power;
		neighbors = new ArrayList<Integer>();
	}
	public void addNeighbor(int n){ //add n to neighbors
		neighbors.add(n);
	}
	public ArrayList<Integer> getNeighbors(){ //return neighbors
		return neighbors;
	}
	public int[] getCoords(){ //return coordinates
		return new int[]{x, y};
	}
	public int getPower(){ //return power
		return power;
	}
	public String toString(){ //convert node to a string format
		return x + " " + y + " " + power + " " + neighbors;
	}
}