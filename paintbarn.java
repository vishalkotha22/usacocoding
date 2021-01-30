import java.io.*;
import java.util.*;

public class paintbarn {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		/*if(true){
			br = new BufferedReader(new FileReader("paintbarn.in"));
			out = new PrintWriter(new FileWriter("paintbarn.out"));
		}//*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int asdf = Integer.parseInt(st.nextToken());
		int[][] barn = new int[1001][1001];
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			barn[x1][y1]++;
			barn[x1][y2]--;
			barn[x2][y1]--;
			barn[x2][y2]++;
		}
		int count = 0;
		for(int i = 0; i <= 1000; i++)
			for(int j = 0; j <= 1000; j++){
				if(i > 0)
					barn[i][j] += barn[i-1][j];
				if(j > 0)
					barn[i][j] += barn[i][j-1];
				if(i > 0 && j > 0)
					barn[i][j] += barn[i-1][j-1];
				if(barn[i][j] == asdf)
					count++;
			}
		out.println(count);
		out.close();
	}
}
