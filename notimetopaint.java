import java.io.*;
import java.util.*;

public class notimetopaint {
	static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		String fence = br.readLine();
		int[] prefixSums = strokesOfPaint(fence);
		int[] suffixSums = strokesOfPaint2(fence);
		/*for(int prefix : prefixSums)
			out.println(prefix);
		out.println();
		for(int suffix : suffixSums)
			out.println(suffix);
		out.println();*/
		for(int j = 0; j < q; j++){
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			int total = (prefixSums[one-1] + suffixSums[two]);
			out.println(total);
		}
		out.close();
	}
	public static int[] strokesOfPaint(String s){
		int[] prefixSums = new int[s.length()+1];
		prefixSums[0] = 0;
		int[] last = new int[26];
		Arrays.fill(last, -1);
		for(int i = 0; i < s.length(); i++){
			int letter = s.charAt(i) - 'A';
			prefixSums[i+1] = prefixSums[i];
			boolean increment = last[letter] == -1;
			for(int j = 0; j < letter; j++){
				if(last[j] > last[letter]){
					increment = true;
					break;
				}
			}
			if(increment)
				prefixSums[i+1]++;
			last[letter] = i;
		}
		return prefixSums;
	}
	public static int[] strokesOfPaint2(String s){
		int[] last = new int[26];
		int[] suffixSums = new int[s.length()+1];
		suffixSums[s.length()] = 0;
		Arrays.fill(last, s.length()+1);
		for(int i = s.length()-1; i >= 0; i--){
			suffixSums[i] = suffixSums[i+1];
			int letter = s.charAt(i) - 'A';
			boolean increment = last[letter] == s.length()+1;
			for(int j = 0; j < letter; j++){
				if(last[j] < last[letter]){
					increment = true;
					break;
				}
			}
			if(increment)
				suffixSums[i]++;
			last[letter] = i;
		}
		return suffixSums;
	}
}
