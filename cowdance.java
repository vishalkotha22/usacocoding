import java.io.*;
import java.util.*;

//Code by Vishal Kotha

public class cowdance {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		/*br = new BufferedReader(new FileReader("cowdance.in"));
		out = new PrintWriter(new FileWriter("cowdance.out"));//*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int maxTime = Integer.parseInt(st.nextToken());
		int[] times = new int[n];
		for(int i = 0; i < n; i++)
			times[i] = Integer.parseInt(br.readLine()); //read in data
		Arrays.sort(times); //sort array
		boolean[] covered = new boolean[n]; //track which indices have already been "used:
		Arrays.fill(covered, true);
	    int low = 0;
	    int high = times.length;
	    for(int i = 0; i < times.length; i++)
	    	System.out.println(works(times, i, maxTime));
		out.close();
	}
}
