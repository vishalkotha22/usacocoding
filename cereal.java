import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class cereal {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		PriorityQueue<Cow>[] cereals = new PriorityQueue[n+1];
		int[][] cows = new int[n][2];
		for(int a = 0; a < m+1; a++)
			cereals[a] = new PriorityQueue<Cow>();
		for(int b = 0; b < n; b++){
			st = new StringTokenizer(br.readLine());
			int f1 = Integer.parseInt(st.nextToken());
			int f2 = Integer.parseInt(st.nextToken());
			cows[b][0] = f1;
			cows[b][1] = f2;
			cereals[f1].add(new Cow(b, f1, f2));
			cereals[f2].add(new Cow(b, f1, f2));
		}
		int amt = 0;
		ArrayList<Integer> cowsUsed = new ArrayList<Integer>();
		for(int c = 1; c < cereals.length; c++){
			if(cereals[c].size() > 0 && !cowsUsed.contains(o))
				amt++;
		}
		int t = n-1;
		do{
			out.println(amt);
			ArrayList<Integer> cowsUsed = new ArrayList<Integer>();
			
		}while(t-->0);
		out.close();
	}
	private static class Cow{
		int arrival;
		int choice1;
		int choice2;
		boolean used;
		private Cow(int a, int c1, int c2){
			arrival = a;
			choice1 = c1;
			choice2 = c2;
			used = false;
		}
		private void toggle(){
			used = true;
		}
		public int compareTo(Cow c){
			return -Integer.compare(arrival, c.arrival);
		}
	}
}
