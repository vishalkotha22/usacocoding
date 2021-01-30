import java.io.*;
import java.util.*;

public class rectangularpasture {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		long num = n+1;
		Cow[] cows = new Cow[n];
		StringTokenizer st;
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cows[i] = new Cow(x, y);
		}
		Arrays.sort(cows, new Comparator<Cow>(){
			@Override
			public int compare(Cow one, Cow two){
				return one.getCoords()[0] - two.getCoords()[0];
			}
		});
		for(int j = 0; j < n; j++)
			cows[j].setX(j+1);
		Arrays.sort(cows, new Comparator<Cow>(){
			@Override
			public int compare(Cow one, Cow two){
				return one.getCoords()[1] - two.getCoords()[1];
			}
		});
		int[][] locs = new int[n][n];
		for(int k = 0; k < n; k++){
			cows[k].setY(k+1);
			locs[cows[k].getCoords()[0]-1][cows[k].getCoords()[1]-1] = 1;
		}
		int[][] prefixSums = new int[n+1][n+1];
		for(int a = 1; a <= n; a++)
			for(int b = 1; b <= n; b++)
				prefixSums[a][b] = locs[a-1][b-1] + prefixSums[a-1][b] + prefixSums[a][b-1] - prefixSums[a-1][b-1];
		long possible = n+1;
		for(int one = 0; one < n; one++){
			for(int two = one+1; two < n; two++){
				int[] coords1 = cows[one].getCoords();
				int[] coords2 = cows[two].getCoords();
				int xMax = Math.max(coords1[0], coords2[0]);
				int xMin = Math.min(coords1[0], coords2[0]);
				int yMax = Math.max(coords1[1], coords2[1]);
				int yMin = Math.min(coords1[1], coords2[1]);
				int factor1 = prefixSums[xMax][yMin] - prefixSums[xMin-1][yMin] - prefixSums[xMax][0] + prefixSums[xMin-1][0];
				int factor2 = prefixSums[xMax][n] - prefixSums[xMin-1][n] - prefixSums[xMax][yMax-1] + prefixSums[xMin-1][yMax-1];
				possible += factor1 * factor2;
			}
		}
		out.println(possible);
		out.close();
	}
	private static class Cow{
		int x, y;
		private Cow(int x, int y){
			this.x = x;
			this.y = y;
		}
		private int[] getCoords(){
			return new int[]{x, y};
		}
		private void setX(int x){
			this.x = x;
		}
		private void setY(int y){
			this.y = y;
		}
		public String toString(){
			return x + " " + y;
		}
	}
}