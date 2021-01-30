import java.io.*;
import java.util.*;

public class maxcross {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
		PrintWriter out = new PrintWriter(new FileWriter("maxcross.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[] lights = new int[n];
		int[] prefix = new int[n+1];
		prefix[0] = 0;
		for(int i = 0; i < b; i++)
			lights[Integer.parseInt(br.readLine())-1] = 1;
		for(int a = 1; a <= n; a++)
			prefix[a] = prefix[a-1] + lights[a-1];
		int minNeeded = Integer.MAX_VALUE;
		for(int j = 1; j <= (n-k); j++)
			minNeeded = Math.min(minNeeded, prefix[j+k]-prefix[j]);
		out.println(minNeeded);
		out.close();
	}
}
