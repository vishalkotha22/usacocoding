import java.io.*;
import java.util.*;

public class bcount {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		if(true){
			br = new BufferedReader(new FileReader("bcount.in"));
			out = new PrintWriter(new FileWriter("bcount.out"));
		}//*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int[] cows = new int[n];
		int[][] prefix = new int[n+1][3];
		int one = 0;
		int two = 0;
		int three = 0;
		prefix[0] = new int[]{0, 0, 0};
		for(int i = 1; i <= n; i++){
			cows[i-1] = Integer.parseInt(br.readLine());
			cows[i-1]--;
			if(i == 0)
				for(int j = 0; j < 3; j++)
					if(cows[i-1] == j)
						prefix[0][j] = 1;
					else
						prefix[0][j] = 0;
			else
				for(int j = 0; j < 3; j++)
					if(cows[i-1] == j)
						prefix[i][cows[i-1]] = prefix[i-1][cows[i-1]]+1;
					else
						prefix[i][j] = prefix[i-1][j];
		}
		for(int k = 0; k < q; k++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			start--;
			int end = Integer.parseInt(st.nextToken());
			for(int a = 0; a < 3; a++){
				if(a == 0){
					one = prefix[end][a] - prefix[start][a];
				}else if(a == 1){
					two = prefix[end][a] - prefix[start][a];
				}else{
					three = prefix[end][a] - prefix[start][a];
				}
			}
			out.println(one + " " + two + " " + three);
		}
		out.close();
	}
}
