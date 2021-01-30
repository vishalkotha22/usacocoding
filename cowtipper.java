import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class cowtipper {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("cowtip.in"));
		PrintWriter out = new PrintWriter(new FileWriter("cowtip.out"));
		int n = Integer.parseInt(br.readLine());
		int[][] cows = new int[n][n];
		for(int i = 0; i < n; i++){
			String line = br.readLine();
			for(int j = 0; j < n; j++)
				cows[i][j] = Integer.parseInt(line.charAt(j)+"");
		}
		int flips = 0;
		for(int i = cows.length-1; i >= 0; i--){
			for(int j = cows.length-1; j >= 0; j--){
				if(cows[i][j] == 1){
					flips++;
					cows = flip(cows, j, i);
				}
			}
		}
		out.println(flips);
		out.close();
	}
	public static int[][] flip(int[][] array, int rightBound, int bottomBound){
		for(int i = 0; i <= rightBound; i++){
			for(int j = 0; j <= bottomBound; j++){
				if(array[i][j] == 0){
					array[i][j] = 1;
				}
				else if(array[i][j] == 1){
					array[i][j] = 0;
				}
			}
		}
		return array;
	}
}
