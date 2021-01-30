import java.io.*;
import java.util.*;

public class gates {
	public static void main(String[] args) throws Exception{
		boolean[][] grid = new boolean[2001][2001];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		/*br = new BufferedReader(new FileReader("gates.in"));
		out = new PrintWriter(new FileWriter("gates.out"));//*/
		int n = Integer.parseInt(br.readLine());
		String directions = br.readLine();
		grid[1000][1000] = true;
		int x = 1000;
		int y = 1000;
		for(char c : directions.toCharArray()){
			if(c == 'N'){
				y++;
				grid[x][y] = true;
			}else if(c == 'S'){
				y--;
				grid[x][y] = true;
			}else if(c == 'W'){
				x--;
				grid[x][y] = true;
			}else{
				x++;
				grid[x][y] = true;
			}
		}
		
		out.close();
	}
}
