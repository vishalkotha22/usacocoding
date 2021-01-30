import java.io.*;
import java.util.*;

public class rut {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		int[][] grid = new int[101][101];
		StringTokenizer st;
		ArrayList<Cow> cows = new ArrayList<Cow>();
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			char d = st.nextToken().charAt(0);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cows.add(new Cow(x, y, d));
		}
		ArrayList<String> answers = new ArrayList<String>();
		for(int k = 0; k < n; k++)
			answers.add("*");
		while(answers.contains("*")){
			ArrayList<ArrayList<Integer>> curr = new ArrayList<ArrayList<Integer>>();
			for(int j = 0; j < cows.size(); j++){
				Cow cow = cows.get(j);
				curr.add(new ArrayList<Integer>(Arrays.asList(cow.getX(), cow.getY())));
				String what = cow.eat(grid, curr);
				if(what.equals("INFINITY"))
					answers.set(j, "Infinity");
				else if(what.equals("FALSE"))
					answers.set(j, cow.eaten()+"");
			}
		}
		for(String answer : answers)
			out.println(answer);
		out.close();
	}
}
class Cow{
	int x;
	int y;
	char d;
	int grass = 0;
	public Cow(int x, int y, char d){
		this.x = x;
		this.y = y;
		this.d = d;
	}
	public String eat(int[][] grid, ArrayList<ArrayList<Integer>> iter){
		if(x == grid.length || y == grid.length){
			return "INFINITY";
		}
		iter.remove(iter.indexOf(new ArrayList<Integer>(Arrays.asList(x, y))));
		if(grid[x][y] == 0 || iter.contains(new ArrayList<Integer>(Arrays.asList(x, y)))){
			iter.add(new ArrayList<Integer>(Arrays.asList(x, y)));
			grid[x][y] = 1;
			if(d == 'N'){
				y++;
			}
			else{
				x++;
			}
			grass++;
			return "TRUE";
		}
		iter.add(new ArrayList<Integer>(Arrays.asList(x, y)));
		return "FALSE";
	}
	public int eaten(){
		return grass;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public char getD(){
		return d;
	}
}