import java.io.*;
import java.util.*;

public class photoshoot {
	public static void main(String[] args) throws Exception{
		int choose = 123;
		BufferedReader br;
		PrintWriter out;
		if(choose == 1){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("photo.in"));
			out = new PrintWriter(new FileWriter("photo.out"));
		}
		int n = Integer.parseInt(br.readLine());
		int[] sums = new int[n-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n-1; i++)
			sums[i] = Integer.parseInt(st.nextToken());
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int j = 0; j < n; j++)
			values.add(j+1);
		ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
		permutate(permutations, new ArrayList<Integer>(), values, n);
		ArrayList<ArrayList<Integer>> potential = new ArrayList<ArrayList<Integer>>();
		for(ArrayList<Integer> list : permutations){
			boolean pass = true;
			for(int k = 0; k < sums.length; k++)
				if((list.get(k) + list.get(k+1)) != sums[k] || list.indexOf(list.get(k)) != list.lastIndexOf(list.get(k)))
					pass = false;
			if(pass)
				potential.add(new ArrayList<Integer>(list));
		}
		ArrayList<Integer> minimum = potential.get(0);
		for(ArrayList<Integer> list : potential){
			for(int a = 0; a < list.size(); a++){
				if(list.get(a) < minimum.get(a)){
					minimum = list;
					a = list.size();
				}
				else if(list.get(a) > minimum.get(a)){
					a = list.size();
				}
			}
		}
		for(int c = 0; c < minimum.size(); c++)
			if(c < minimum.size()-1)
				out.print(minimum.get(c) + " " );
			else
				out.println(minimum.get(c));
		out.close();
	}
	public static void permutate(ArrayList<ArrayList<Integer>> adder, ArrayList<Integer> build, ArrayList<Integer> vals, int n){
		if(build.size() == n){
			adder.add(new ArrayList<Integer>(build));
		}else{
			for(int val : vals){
				ArrayList<Integer> temp = new ArrayList<Integer>(build);
				temp.add(val);
				ArrayList<Integer> values = new ArrayList<Integer>(vals);
				values.remove(values.indexOf(val));
				permutate(adder, temp, values, n);
			}
		}
	}
}
