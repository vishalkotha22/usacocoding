import java.io.*;
import java.util.*;

public class angrygold {
	static boolean[] covered = new boolean[1000000000];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		/*br = new BufferedReader(new FileReader("angry.in"));
		out = new PrintWriter(new FileWriter("angry.out"));//*/
		int n = Integer.parseInt(br.readLine());
		int[] locs = new int[n];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++){
			locs[i] = Integer.parseInt(br.readLine());
			min = Math.min(min, locs[i]);
			max = Math.max(max, locs[i]);
			covered[i] = true;
		}
		Arrays.sort(locs);
		int low = 0;
		int high = 1000000000;
		int ans = 0;
		while(low < high-1){
			int mid = (low + high) / 2;
			System.out.println(low + " " + mid + " " + high);
			for(int j = min; j < max; j++){
				if(simulate(locs, j, mid)){
					high = mid;
					j = n;
				}else{
					low = mid+1;
				}
			}
		}
		out.close();
	}
	public static boolean simulate(int[] locs, int pos, int power){
		int lPow = power;
		int ticker = lPow;
		for(int i = pos-1; i >= 0; i--){
			if(!covered[i]){
				ticker--;
			}
		}
		System.out.println("passes left");
		lPow = power;
		for(int j = pos; j < locs.length-1; j++){
			System.out.println(j + " " + lPow + " " + pos + " " + power);
			if(locs[j+1] - locs[j] > lPow)
				return false;
			else
				lPow--;
		}
		System.out.println("passes right");
		return true;
	}
}
