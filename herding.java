import java.io.*;
import java.util.*;

public class herding {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		//BufferedReader br = new BufferedReader(new FileReader("herding.in"));
		//PrintWriter out = new PrintWriter(new FileWriter("herding.out"));
		int n = Integer.parseInt(br.readLine());
		int[] cows = new int[n];
		for(int i = 0; i < n; i++)
			cows[i] = Integer.parseInt(br.readLine());
		Arrays.sort(cows);
		out.close();
	}
}
