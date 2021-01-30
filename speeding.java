import java.io.*;
import java.util.*;

public class speeding {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("speeding.in"));
		PrintWriter out = new PrintWriter(new FileWriter("speeding.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int roadSegments = Integer.parseInt(st.nextToken());
		int tractorSegments = Integer.parseInt(st.nextToken());
		ArrayList<Integer> road = new ArrayList<Integer>();
		ArrayList<Integer> tractor = new ArrayList<Integer>();
		for(int i = 0; i < roadSegments; i++){
			StringTokenizer rd = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(rd.nextToken());
			int speedLimit = Integer.parseInt(rd.nextToken());
			for(int j = 0; j < length; j++)
				road.add(speedLimit);
		}
		for(int k = 0; k < tractorSegments; k++){
			StringTokenizer trac = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(trac.nextToken());
			int speed = Integer.parseInt(trac.nextToken());
			for(int x = 0; x < length; x++)
				tractor.add(speed);
		}
		int maxDifference = Integer.MIN_VALUE;
		for(int y = 0; y < 100; y++)
			maxDifference = Math.max(maxDifference, tractor.get(y)-road.get(y));
		if(maxDifference < 0)
			out.println(0);
		else
			out.println(maxDifference);
		out.close();
	}
}
