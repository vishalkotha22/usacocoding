import java.io.*;
import java.util.*;

public class where {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		/*br = new BufferedReader(new FileReader("where.in"));
		out = new PrintWriter(new FileWriter("where.out"));//*/
		int n = Integer.parseInt(br.readLine());
		char[][] image = new char[n][n];
		for(int i = 0; i < n; i++){
			String line = br.readLine();
			for(int j = 0; j < n; j++)
				image[i][j] = line.charAt(j);
		}
		for(int k = 0; k < n; k++){
			for(int a = 0; a < n; a++){
				for(int b = k; b < n; b++){
					for(int c = a; c < n; c++){
						
					}
				}
			}
		}
		out.close();
	}
	public static boolean isPCL(char[][] grid, boolean[][] array, int topX, int topY, int bottomX, int bottomY){
		for(int i = topX; i < bottomX; i++){
			for(int j = topY; j < bottomY; j++){
				
			}
		}
	}
	public static void floodfill(boolean[][] visited, int x, int y, int x1, int y1, int x2, int y2){
		
	}
}
