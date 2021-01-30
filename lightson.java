import java.io.*;
import java.util.*;

public class lightson {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("lightson.in"));
		out = new PrintWriter(new FileWriter("lightson.out")); //*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		boolean[][] illuminated = new boolean[n+1][n+1];
		int m = Integer.parseInt(st.nextToken());
		HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> toggles = new HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>>();
		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int sX = Integer.parseInt(st.nextToken());
			int sY = Integer.parseInt(st.nextToken());
			int tX = Integer.parseInt(st.nextToken());
			int tY = Integer.parseInt(st.nextToken());
			ArrayList<Integer> coords = new ArrayList<Integer>(Arrays.asList(sX, sY));
			if(toggles.containsKey(coords)){
				ArrayList<ArrayList<Integer>> fetch = toggles.get(coords);
				fetch.add(new ArrayList<Integer>(Arrays.asList(tX, tY)));
				toggles.put(coords, fetch);
			}else{
				ArrayList<ArrayList<Integer>> put = new ArrayList<ArrayList<Integer>>();
				put.add(new ArrayList<Integer>(Arrays.asList(tX, tY)));
				toggles.put(coords, put);
			}
		}
		illuminated[1][1] = true;
		floodfill(illuminated, new boolean[n+1][n+1], toggles, 1, 1);
		int count = 0;
		for(boolean[] array : illuminated)
			for(boolean b : array)
				if(b)
					count++;
		out.println(count);
		out.close();
	}
	public static void floodfill(boolean[][] array, boolean[][] visited, HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> toggles, int r, int c){
		if(r < 0 || c < 0 || r == array.length || c == array[0].length || visited[r][c])
			return;
		ArrayList<ArrayList<Integer>> switches = toggles.get(new ArrayList<Integer>(Arrays.asList(r, c)));
		visited[r][c] = true;
		if(enterable(array, r, c) && switches != null){
			array[r][c] = true;
			for(int i = 0; i < switches.size(); i++){
				ArrayList<Integer> coords = switches.get(i);
				int x = coords.get(0);
				int y = coords.get(1);
				if(enterable(array, x, y)){
					array[x][y] = true;
				}
				floodfill(array, visited, toggles, coords.get(0), coords.get(1));
			}
		}
	}
	public static boolean enterable(boolean[][] array, int r, int c){
		boolean up = r > 0;
		boolean down = r < array.length-1;
		boolean left = c > 0;
		boolean right = c < array[0].length-1;
		if(up && array[r-1][c])
			return true;
		if(down && array[r+1][c])
			return true;
		if(left && array[r][c-1])
			return true;
		return (right && array[r][c+1]) || array[r][c];
	}
}
