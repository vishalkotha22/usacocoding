import java.io.*;
import java.util.*;

public class balancing {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("lineup.in"));
		PrintWriter out = new PrintWriter(new FileWriter("lineup.out"));
		int numConstraints = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<String>> constraints = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < numConstraints; i++){
			StringTokenizer line = new StringTokenizer(br.readLine());
			String one = line.nextToken();
			line.nextToken();
			line.nextToken();
			line.nextToken();
			line.nextToken();
			String two = line.nextToken();
			constraints.add(new ArrayList<String>(Arrays.asList(one, two)));
		}
		ArrayList<String> names = new ArrayList<String>(Arrays.asList("Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"));
		ArrayList<ArrayList<String>> possibilities = new ArrayList<ArrayList<String>>();
		permutations(possibilities, constraints, new ArrayList<String>(), names);
		Collections.sort(possibilities, new Comparator<ArrayList<String>>(){
			public int compare(ArrayList<String> one, ArrayList<String> two){
				for(int a = 0; a < one.size(); a++)
					if(one.get(a).compareTo(two.get(a)) != 0)
						return one.get(a).compareTo(two.get(a));
				return 0;
			}
		});
		ArrayList<String> answer = possibilities.get(0);
		for(String name : answer)
			out.println(name);
		out.close();
	}
	public static void permutations(ArrayList<ArrayList<String>> adder, ArrayList<ArrayList<String>> constraints, ArrayList<String> build, ArrayList<String> options){
		if(build.size() == 7){
			build.add(options.get(0));
			if(passes(constraints, build))
				adder.add(build);
		}
		else{
			for(String name : options){
				ArrayList<String> temp = new ArrayList<String>(build);
				temp.add(name);
				ArrayList<String> names = new ArrayList<String>(options);
				names.remove(names.indexOf(name));
				permutations(adder, constraints, temp, names);
			}
		}
	}
	public static boolean passes(ArrayList<ArrayList<String>> constraints, ArrayList<String> list){
		for(ArrayList<String> constraint : constraints){
			if(Math.abs(list.indexOf(constraint.get(0)) - list.indexOf(constraint.get(1))) > 1)
				return false;
		}
		return true;
	}
}
