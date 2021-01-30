import java.io.*;
import java.util.*;

public class wordprocessor {
	public static void main(String[] args) throws Exception{
		int choose = 123;
		BufferedReader br;
		PrintWriter out;
		if(choose == 1){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("word.in"));
			out = new PrintWriter(new FileWriter("word.out"));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		String[] words = new String[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			words[i] = st.nextToken();
		int currLen = 0;
		for(int j = 0; j < words.length; j++){
			if(words[j].length() + currLen <= k){
				if(j == words.length-1 || words[j+1].length() + words[j].length() + currLen > k){
					out.println(words[j]);
					currLen = 0;
				}
				else{
					out.print(words[j] + " ");
					currLen += words[j].length();
				}
			}
			else{
				out.println(words[j]);
				currLen += words[j].length();
			}
		}
		out.close();
	}
}
