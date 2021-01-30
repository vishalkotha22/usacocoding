import java.io.*;
import java.util.*;

public class mountains {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		Mountain[] mountains = new Mountain[n];
		StringTokenizer st;
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			mountains[i] = new Mountain(x, y);
		}
		Arrays.sort(mountains);
		out.close();
	}
	private static class Mountain implements Comparable<Mountain>{
		int x, y, leftX, rightX;
		private Mountain(int x, int y){
			this.x = x;
			this.y = y;
			leftX = x-y;
			rightX = x+y;
		}
		public int compareTo(Mountain m){
			return y - m.y;
		}
		private boolean peakCovered(Mountain m){
			if(y == m.y)
				return false;
			if(y < m.y){
				int difference = m.y - y;
				int rightIntersection = m.x + difference;
				int leftIntersection = m.x - difference;
				return rightIntersection >= x && x <= leftIntersection;
			}
			int difference = y - m.y;
			int rightIntersection = y + difference;
			int leftIntersection = y - differecen;
			return rightIntersection >= m.x && leftIntersection <= m.x;
		}
	}
}
