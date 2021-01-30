import java.io.*;
import java.util.*;

public class gymnastics {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("gymnastics.in"));
		PrintWriter out = new PrintWriter(new FileWriter("gymnastics.out"));
		HashMap<Integer, ArrayList<Integer>> performance = new HashMap<Integer, ArrayList<Integer>>();
		String[] line = br.readLine().split(" ");
		int numLines = Integer.parseInt(line[0]);
		int numCows = Integer.parseInt(line[1]);
		for(int i = 0; i < numLines; i++){
			HashMap<Integer, ArrayList<Integer>> thisTime = new HashMap<Integer, ArrayList<Integer>>();
			int[] cows = new int[numCows];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < numCows; j++)
				cows[j] = Integer.parseInt(st.nextToken());
			for(int a = 0; a < numCows; a++){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for(int b = a+1; b < numCows; b++)
					temp.add(cows[b]);
				thisTime.put(cows[a], temp);
				if(performance.containsKey(cows[a])){
					ArrayList<Integer> fetch = performance.get(cows[a]);
					ArrayList<Integer> copyBuffer = new ArrayList<Integer>();
					for(int val : fetch)
						if(temp.contains(val))
							copyBuffer.add(val);
					performance.put(cows[a], copyBuffer);
				}
				else
					performance.put(cows[a], temp);
			}
		}
		int count = 0;
		for(int iterate = 1; iterate <= numCows; iterate++)
			count += performance.get(iterate).size();
		out.println(count);
		out.close();
	}
}
