import java.io.*;
import java.util.*;

public class hps {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		if(true){
			br = new BufferedReader(new FileReader("hps.in"));
			out = new PrintWriter(new FileWriter("hps.out"));
		}//*/
		int n = Integer.parseInt(br.readLine());
		String[] moves = new String[n];
		int[][] prefix = new int[n+1][3];
		prefix[0] = new int[]{0, 0, 0};
		for(int i = 1; i <= n; i++){
			String m = br.readLine();
			if(m.equals("H")){
				prefix[i][0] = prefix[i-1][0] + 1;
				prefix[i][1] = prefix[i-1][1];
				prefix[i][2] = prefix[i-1][2];

			}
			else if(m.equals("P")){
				prefix[i][1] = prefix[i-1][1] + 1;
				prefix[i][0] = prefix[i-1][0];
				prefix[i][2] = prefix[i-1][2];
			}
			else{
				prefix[i][2] = prefix[i-1][2] + 1;
				prefix[i][1] = prefix[i-1][1];
				prefix[i][0] = prefix[i-1][0];
			}
		}
		int abs = 0;
		for(int j = 1; j <= n; j++){
			int first = prefix[j][0];
			int first1 = prefix[n][0];
			int first2 = first + prefix[n][1] - prefix[j][1];
			int first3 = first + prefix[n][2] - prefix[j][2];
			int second = prefix[j][1];
			int second1 = second + prefix[n][0] - prefix[j][0];
			int second2 = second;
			int second3 = second + prefix[n][2] - prefix[j][2];
			int third = prefix[j][2];
			int third1 = third + prefix[n][0] - prefix[j][0];
			int third2 = third + prefix[n][1] - prefix[j][1];
			int third3 = third;
			int max = Math.max(Math.max(first1, Math.max(first2, first3)), Math.max(Math.max(second1, Math.max(second2, second3)), Math.max(third1, Math.max(third2, third3))));
			abs = Math.max(abs, max);
		}
		out.println(abs);		
		out.close();
	}
}
