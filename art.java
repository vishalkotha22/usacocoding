import java.io.*;
import java.util.*;

public class art {
	public static void main(String[] args) throws Exception{
		int switch = 0;
		BufferedReader br;
		PrintWriter out;
		if(switch == 0){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new FileWriter(System.out));
		}
		else{
			br = new BufferedReader(new FileReader("art.in"));
			out = new PrintWriter(new FileWriter("art.out"));
		}
		int n = Integer.parseInt(br.readLine());
		char[][] art = new char[n][n];
		ArrayList<Integer> types = new ArrayList<Integer>();
		HashMap<Integer, ArrayList<int[]>> positions = new HashMap<Integer, ArrayList<int[]>>();
		for(int i = 0; i < n; i++){
			String line = br.readLine();
			for(int j = 0; j < n; j++){
				art[i][j] = line.charAt(j);
				if(!types.contains(Integer.parseInt(art[i][j]+""))){
					types.add(Integer.parseInt(art[i][j]+""));
					ArrayList<int[]> temp = new ArrayList<int[]>();
					int[] put = new int[2];
					put[0] = i;
					put[1] = j;
					temp.add(put);
					positions.put(types, new ArrayList<Integer>(temp));
				}
				else{
					ArrayList<int[]> fetch = positions.get(Integer.parseInt(art[i][j]+""));
					int[] temp = new int[2];
					temp[0] = i;
					temp[1] = j;
					fetch.add(temp);
					positions.put(Integer.parseInt(art[i][j]+""), new ArrayList<Integer>(fetch));
				}
			}
		}
		
		out.close();
	}
	public boolean isARectangle(ArrayList<int[]> coordinates){
		
	}
}
