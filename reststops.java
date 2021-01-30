import java.io.*;
import java.util.*;

public class reststops {
	public static void main(String[] args) throws Exception{
		int choose = 0;
		BufferedReader br;
		PrintWriter out;
		if(choose == 0){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("reststops.in"));
			out = new PrintWriter(new FileWriter("reststops.out"));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int numStops = Integer.parseInt(st.nextToken());
		int farmerSpeed = Integer.parseInt(st.nextToken());
		int bessieSpeed = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> stops = new ArrayList<ArrayList<Integer>>();
		int maxLoc = -1;
		int maxVal = -1;
		for(int i = 0; i < numStops; i++){
			StringTokenizer str = new StringTokenizer(br.readLine());
			int loc = Integer.parseInt(str.nextToken());
			int val = Integer.parseInt(str.nextToken());
			stops.add(new ArrayList<Integer>(Arrays.asList(loc, val)));
			if(val > maxVal){
				maxVal = val;
				maxLoc = loc;
			}
		}
		int difference = farmerSpeed - bessieSpeed;
		
		out.close();
	}
}
