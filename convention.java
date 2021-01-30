import java.io.*;
import java.util.*;

public class convention {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("convention.in"));
		out = new PrintWriter(new FileWriter("convention.out"));//*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] times = new int[n];
		for(int i = 0; i < n; i++)
			times[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(times);
		int low = 0;
		int high = 1000000000;
		while(low != high){
			int mid = (low + high) / 2;
			if(works(times, m, c, mid))
				high = mid;
			else
				low = mid+1;
		}
		out.println(low);
		out.close();
	}
	public static boolean works(int[] times, int numBuses, int busCapacity, int waitingTime){
		int start = 0;
		int index = 0;
		int[] busStorage = new int[numBuses];
		Arrays.fill(busStorage, 1);
		for(int i = 0; i < numBuses; i++){
			while(index+1 < times.length && times[index+1] - times[start] <= waitingTime && index - start + 2 <= busCapacity){
				index++;
				busStorage[i]++;
			}
			start = index+1;
			index = start;
		}
		int numCowsBoarded = 0;
		for(int val : busStorage)
			numCowsBoarded += val;
		return numCowsBoarded >= times.length;
	}
}
