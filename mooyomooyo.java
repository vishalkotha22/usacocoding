import java.io.*;
import java.util.*;

public class mooyomooyo {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
		PrintWriter out = new PrintWriter(new FileWriter("mooyomooyo.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		char[][] grid = new char[n][10];
		for(int i = 0; i < n; i++){
			String line = br.readLine();
			for(int j = 0; j < 10; j++)
				grid[i][j] = line.charAt(j);
		}
		findKRegions(grid, n, k);
		for(char[] array : grid){
			for(char c : array)
				out.print(c);
			out.println();
		}
		out.close();
	}
	private static class Pair{
		int x;
		int y;
		private Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
		public String toString(){
			return x + " " + y;
		}
	}
	public static void print(char[][] array){
		for(char[] arr : array){
			for(char c : arr)
				System.out.print(c);
			System.out.println();
		}
		System.out.println();
	}
	public static void findKRegions(char[][] grid, int n, int k){
		while(true){
			int maxSize = 0;
			for(int j = n-1; j >= 0; j--){
				for(int i = 0; i < 10; i++){
					if(grid[j][i] != '0'){
						ArrayList<Pair> locs = new ArrayList<Pair>();
						floodfill(new boolean[n][10], grid, j, i, grid[j][i], k, locs);
						maxSize = Math.max(maxSize, locs.size());
						if(locs.size() >= k)
							for(Pair array : locs)
								grid[array.x][array.y] = '0';
					}
				}
			}
			simulateGravity(grid);
			if(maxSize < k)
				break;
		}
	}
	public static void floodfill(boolean[][] visited, char[][] grid, int r, int c, char target, int k, ArrayList<Pair> locs){
		if(r < 0 || c < 0 || r >= grid.length || c > 9)
			return;
		if(grid[r][c] != target || visited[r][c] || target == '0')
			return;
		visited[r][c] = true;
		locs.add(new Pair(r, c));
		floodfill(visited, grid, r, c-1, target, k, locs);
		floodfill(visited, grid, r, c+1, target, k, locs);
		floodfill(visited, grid, r-1, c, target, k, locs);
		floodfill(visited, grid, r+1, c, target, k, locs);
	}
	public static void simulateGravity(char[][] grid){
		for(int i = 0; i < 10; i++){
			Stack<Character> nums = new Stack<Character>();
			for(int j = 0; j < grid.length; j++)
				if(grid[j][i] != '0')
					nums.push(grid[j][i]);
			for(int k = grid.length-1; k >= 0; k--){
				if(!nums.isEmpty())
					grid[k][i] = nums.pop();
				else
					grid[k][i] = '0';
			}
		}
	}
}
