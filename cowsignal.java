import java.io.*;
import java.util.*;

public class cowsignal {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("cowsignal.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m, n, k;
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		char[][] signal = new char[m][n];
		char[][] enlarged = new char[k*m][k*n];
		for(int i = 0; i < m; i++){
			String line = br.readLine();
			for(int j = 0; j < n; j++){
				signal[i][j] = line.charAt(j);
			}
		}
		for(int a = 0; a < k*m; a++){
			for(int b = 0; b < k*n; b++){
				enlarged[a][b] = signal[a/k][b/k];
			}
		}
		PrintWriter out = new PrintWriter(new FileWriter("cowsignal.out"));
		for(int c = 0; c < enlarged.length; c++){
			for(int d = 0; d < enlarged[0].length; d++){
				out.print(enlarged[c][d]);
			}
			out.println();
		}
		out.close();
	}
}