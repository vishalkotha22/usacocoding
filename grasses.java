import java.io.*;
import java.util.*;

public class grasses {
	public static void main(String[] args) throws Exception{
		int choose = 1;
		BufferedReader br;
		PrintWriter out;
		if(choose == 1){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("planting.in"));
			out = new PrintWriter(new FileWriter("planting.out"));
		}
		int numFields = Integer.parseInt(br.readLine());
		HashMap<Integer, ArrayList<Integer>> links = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < numFields-1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			if(links.containsKey(one)){
				ArrayList<Integer> fetch = links.get(one);
				fetch.add(two);
				links.put(one, fetch);
			}
			else{
				links.put(one, new ArrayList<Integer>(Arrays.asList(two)));
			}
			if(links.containsKey(two)){
				ArrayList<Integer> fetch = links.get(two);
				fetch.add(one);
				links.put(two, fetch);
			}
			else{
				links.put(two, new ArrayList<Integer>(Arrays.asList(one)));
			}
		}
		int[] types = new int[numFields];
		for(int a = 0; a < types.length; a++)
			types[a] = 0;
		for(int j = 0; j < numFields; j++){
			for(int k = 0; k < numFields; k++)
				if(links.get(j).get(k) == 1)
					types[k] = types[j]+1;
		}
		ArrayList<Integer> distinctTypes = new ArrayList<Integer>();
		for(int type : types)
			if(!distinctTypes.contains(type))
				distinctTypes.add(type);
		out.println(distinctTypes.size());
		out.close();
	}
}
