import java.util.*;
import java.io.*;

public class citiesandstates {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
		PrintWriter out = new PrintWriter(new FileWriter("citystate.out"));
		int numCities = Integer.parseInt(br.readLine());
		HashMap<String, ArrayList<String>> stateBased = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> cityBased = new HashMap<String, ArrayList<String>>();
		ArrayList<ArrayList<String>> cityStates = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < numCities; i++){
			String[] line = br.readLine().split(" ");
			String city = line[0];
			String state = line[1];
			cityStates.add(new ArrayList<String>(Arrays.asList(city, state)));
			if(stateBased.containsKey(state)){
				ArrayList<String> fetch = stateBased.get(state);
				fetch.add(city);
				stateBased.put(state, fetch);
			}
			else
				stateBased.put(state, new ArrayList<String>(Arrays.asList(city)));
			if(cityBased.containsKey(city)){
				ArrayList<String> fetch = cityBased.get(city);
				fetch.add(state);
				cityBased.put(city, fetch);
			}
			else
				cityBased.put(city, new ArrayList<String>(Arrays.asList(state)));
		}
		int num = 0;
		for(ArrayList<String> cityState : cityStates){
			String city = cityState.get(0);
			String state = cityState.get(1);
			if(!state.equals(city.substring(0, 2)) && stateBased.containsKey(city.substring(0, 2)))
				for(String yeye : stateBased.get(city.substring(0, 2)))
					if(yeye.substring(0, 2).equals(state) && !yeye.equals(city))
						num++;
		}
		out.println(num/2);
		out.close();
	}
}