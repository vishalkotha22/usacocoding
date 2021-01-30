import java.util.*;
import java.io.*;

public class badmilk {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("badmilk.in"));
		PrintWriter out = new PrintWriter(new FileWriter("badmilk.out"));
		StringTokenizer str = new StringTokenizer(br.readLine());
		int numFriends = Integer.parseInt(str.nextToken());
		int numTypes = Integer.parseInt(str.nextToken());
		int numLogs = Integer.parseInt(str.nextToken());
		int numSick = Integer.parseInt(str.nextToken());
		HashMap<Integer, ArrayList<int[]>> mapper = new HashMap<Integer, ArrayList<int[]>>();
		ArrayList<ArrayList<Integer>> storage = new ArrayList<ArrayList<Integer>>();
		storage.add(new ArrayList<Integer>(Arrays.asList(numFriends, numTypes, numLogs, numSick)));
		for(int i = 0; i < numLogs; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int person = Integer.parseInt(st.nextToken());
			int type = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			storage.add(new ArrayList<Integer>(Arrays.asList(person, type, time)));
			if(mapper.containsKey(time)){
				ArrayList<int[]> fetch = mapper.get(time);
				int[] temp = new int[2];
				temp[0] = person;
				temp[1] = type;
				fetch.add(temp);
				mapper.put(time, fetch);
			}
			else{
				ArrayList<int[]> fill = new ArrayList<int[]>();
				int[] temp = new int[2];
				temp[0] = person;
				temp[1] = type;
				fill.add(temp);
				mapper.put(time, fill);
			}
		}
		HashMap<Integer, ArrayList<Integer>> badMilks = new HashMap<Integer, ArrayList<Integer>>();
		for(int j = 0; j < numSick; j++){
			StringTokenizer s = new StringTokenizer(br.readLine());
			int person = Integer.parseInt(s.nextToken());
			int time = Integer.parseInt(s.nextToken());
			for(int k = 1; k < time; k++){
				if(mapper.containsKey(k)){
					ArrayList<int[]> fetch = mapper.get(k);
					for(int[] array : fetch){
						if(array[0] == person){
							if(badMilks.containsKey(person)){
								ArrayList<Integer> possible = badMilks.get(person);
								if(!possible.contains(array[1]))
									possible.add(array[1]);
								badMilks.put(person, possible);
							}
							else{
								ArrayList<Integer> temp = new ArrayList<Integer>();
								temp.add(array[1]);
								badMilks.put(person, temp);
							}
						}
					}
				}
			}
		}
		Set<Integer> keySet = badMilks.keySet();
		ArrayList<Integer> possible = new ArrayList<Integer>();
		for(int key : keySet){
			ArrayList<Integer> temp = badMilks.get(key);
			if(possible.size() == 0)
				for(int val : temp)
					possible.add(val);
			else{
				for(int val : possible)
					if(!temp.contains(val))
						possible.remove(possible.indexOf(val));
			}
		}
		HashMap<Integer, ArrayList<Integer>> whoWhat = new HashMap<Integer, ArrayList<Integer>>();
		for(int a = 1; a < storage.size(); a++){
			ArrayList<Integer> fetch = storage.get(a);
			int person = fetch.get(0);
			int type = fetch.get(1);
			if(!whoWhat.containsKey(type))
				whoWhat.put(type, new ArrayList<Integer>(Arrays.asList(person)));
			else{
				ArrayList<Integer> asdf = whoWhat.get(type);
				if(!asdf.contains(person))
					asdf.add(person);
				whoWhat.put(type, asdf);
			}
		}
		int maxDoses = 0;
		for(int val : possible)
			maxDoses = Math.max(maxDoses, whoWhat.get(val).size());
		out.println(maxDoses);
		out.close();
	}
}
