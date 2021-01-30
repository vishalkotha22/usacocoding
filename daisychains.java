import java.io.*;
import java.util.*;

public class daisychains {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> flowers = new ArrayList<Integer>();
		for(int i = 0; i < n; i++)
			flowers.add(Integer.parseInt(st.nextToken()));
		int numRanges = 0;
		for(int j = 1; j <= flowers.size(); j++){
			for(int k = 0; k < flowers.size()-j+1; k++){
				int sum = 0;
				ArrayList<Integer> subset = new ArrayList<Integer>();
				for(int a = k; a < k+j; a++){
					sum += flowers.get(a);
					subset.add(flowers.get(a));
				}
				double average = (double) sum / j;
				if(average == (int) average){
					if(subset.contains((int) average))
						numRanges++;
				}
			}
		}
		out.println(numRanges);
		out.close();
	}
}
