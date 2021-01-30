import java.io.*;
import java.util.*;

//Code by Vishal Kotha

public class lemonade {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("lemonade.in"));
		out = new PrintWriter(new FileWriter("lemonade.out"));//*/
		int n = Integer.parseInt(br.readLine()); //set up i/o streams and read input
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int[] cows = new int[n];
		for(int i = 0; i < n; i++)
			cows[i] = Integer.parseInt(st.nextToken()); //stores data into array
		Arrays.sort(cows); //sort array
		ArrayList<Integer> line = new ArrayList<Integer>();
		int index = cows.length-1; //greedily solve by putting the most patient cows at the front of the line
		while(index >= 0 && cows[index] >= line.size()){
			line.add(cows[index]);
			index--;
		}
		out.println(line.size()); //print the answer
		out.close();
	}
}