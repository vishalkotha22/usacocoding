import java.io.*;
import java.util.*;

public class berries {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("berries.in"));
		out = new PrintWriter(new FileWriter("berries.out"));//*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] berries = new int[n];
		st = new StringTokenizer(br.readLine());
		int max = 0;
		for(int i = 0; i < n; i++){
			berries[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, berries[i]);
		}
		int[] temp;
		int best = 0;
		for(int j = 1; j <= max; j++){
			temp = Arrays.copyOf(berries, berries.length);
			Arrays.sort(temp);
			int numBuckets = k;
			int filled = 0;
			for(int a = temp.length-1; a >= 0; a--){
				while(temp[a] - j > 0 && numBuckets > 0){
					filled++;
					numBuckets--;
					temp[a] -= j;
				}
				if(numBuckets == 0)
					a = -1;
			}
			best = Math.max(best, b)
		}
		out.close();
	}
}
