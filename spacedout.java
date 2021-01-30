import java.io.*;
import java.util.*;

public class spacedout {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		int[][] beautyValues = new int[n][n];
		StringTokenizer st;
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++)
				beautyValues[i][j] = Integer.parseInt(st.nextToken());
		}
		out.println(maxBeauty(beautyValues));
		out.close();
	}
	public static int maxBeauty(int[][] beautyValues){
		int maxRowSum = 0;
		for(int i = 0; i < beautyValues.length; i++){
			int startCowRowSum = 0;
			int startBlankRowSum = 0;
			for(int one = 0; one < beautyValues[0].length; one+=2)
				startCowRowSum += beautyValues[i][one];
			for(int two = 1; two < beautyValues[0].length; two+=2)
				startBlankRowSum += beautyValues[i][two];
			maxRowSum += Math.max(startCowRowSum, startBlankRowSum);
		}
		int maxColSum = 0;
		for(int i = 0; i < beautyValues[0].length; i++){
			int startCowColSum = 0;
			int startBlankColSum = 0;
			for(int one = 0; one < beautyValues.length; one+=2)
				startCowColSum += beautyValues[one][i];
			for(int two = 1; two < beautyValues.length; two+=2)
				startBlankColSum += beautyValues[two][i];
			maxColSum += Math.max(startCowColSum, startBlankColSum);
		}
		return Math.max(maxColSum, maxRowSum);
	}
}
