import java.io.*;
import java.util.*;

public class div7 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("div7.in"));
		out = new PrintWriter(new FileWriter("div7.out"));//*/
		int n = Integer.parseInt(br.readLine());
		long[] prefix = new long[n+1];
		prefix[0] = 0;
		for(int i = 0; i < n; i++){
			int val = Integer.parseInt(br.readLine());
			prefix[i+1] = val + prefix[i];
		}
		int maxLen = 0;
		for(int j = n; j > 0; j--){
			for(int k = 0; k < n-j+1; k++){
				if((prefix[k+j] - prefix[k]) % 7 == 0){
					maxLen = j;
					break;
				}
			}
			if(maxLen > 0)
				break;
		}
		out.println(maxLen);
		out.close();
	}
}
