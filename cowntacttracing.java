import java.io.*;
import java.util.*;

public class cowntacttracing {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("tracing.in"));
		PrintWriter out = new PrintWriter(new FileWriter("tracing.out"));
		String[] line = br.readLine().split(" ");
		int numCows = Integer.parseInt(line[0]);
		int logEntries = Integer.parseInt(line[1]);
		String cows = br.readLine();
		ArrayList<Integer> corona = new ArrayList<Integer>();
		for(int a = 0; a < cows.length(); a++)
			if(cows.charAt(a) == '1')
				corona.add(a+1);
		TreeMap<Integer, ArrayList<Integer>> logs = new TreeMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < logEntries; i++){
			String[] vals = br.readLine().split(" ");
			logs.put(Integer.parseInt(vals[0]), new ArrayList<Integer>(Arrays.asList(Integer.parseInt(vals[1]), Integer.parseInt(vals[2]))));
		}
		ArrayList<Integer> possiblePatientZeroes = new ArrayList<Integer>();
		int minK = logEntries;
		int maxK = 0;
		for(int k = 0; k <= logEntries; k++){
			for(int j = 1; j <= numCows; j++){
				HashMap<Integer, Integer> infected = new HashMap<Integer, Integer>();
				infected.put(j, k);
				Set<Integer> keys = logs.keySet();
				ArrayList<Integer> hadCovid = new ArrayList<Integer>();
				hadCovid.add(j);
				for(int val : keys){
					ArrayList<Integer> handshake = logs.get(val);
					Set<Integer> infections = infected.keySet();
					if(infections.contains(handshake.get(0)) && k > 0){
						if(!hadCovid.contains(handshake.get(1))){
							infected.put(handshake.get(1), k);
							hadCovid.add(handshake.get(1));
						}
						else{
							int hooves = infected.get(handshake.get(1)-1);
							if(hooves > 1)
								infected.put(handshake.get(1), hooves-1);
							else
								infected.remove(handshake.get(1));
						}
						int infectionsLeft = infected.get(handshake.get(0));
						if(infectionsLeft > 1)
							infected.put(handshake.get(0), infectionsLeft-1);
						else if(infectionsLeft == 1)
							infected.remove(handshake.get(0));
					}
					else if(infections.contains(handshake.get(1)) && k > 0){
						if(!hadCovid.contains(handshake.get(0))){
							infected.put(handshake.get(0), k);
							hadCovid.add(handshake.get(0));
						}
						else{
							int hooves = infected.get(handshake.get(0)-1);
							if(hooves > 1)
								infected.put(handshake.get(0), hooves-1);
							else
								infected.remove(handshake.get(0));
						}
						int infectionsLeft = infected.get(handshake.get(1));
						if(infectionsLeft > 1)
							infected.put(handshake.get(1), infectionsLeft-1);
						else if(infectionsLeft == 1)
							infected.remove(handshake.get(1));
					}
				}
				Collections.sort(hadCovid);
				if(hadCovid.equals(corona)){
					if(!possiblePatientZeroes.contains(j))
						possiblePatientZeroes.add(j);
					minK = Math.min(minK, k);
					maxK = Math.max(maxK, k);
				}
			}
		}
		if(maxK == logEntries)
			out.println(possiblePatientZeroes.size() + " " + minK + " Infinity");
		else
			out.println(possiblePatientZeroes.size() + " " + minK + " " + maxK);
		out.close();
	}
}
