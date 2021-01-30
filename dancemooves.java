import java.io.*;
import java.util.*;

public class dancemooves {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] original = new int[n+1];
		Cow[] cows = new Cow[n+1];
		Set<Integer>[] positionsTravelled = new Set[n+1];
		for(int a = 1; a <= n; a++){
			cows[a] = new Cow(a);
			original[a] = a;
			Set<Integer> place = new HashSet<>();
			place.add(a);
			positionsTravelled[a] = new HashSet<Integer>(place);
		}
		for(int j = 0; j < k; j++){
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			int temp = cows[one].value;
			cows[one].setValue(cows[two].value);
			cows[two].setValue(temp);
			Set<Integer> onesPositions = positionsTravelled[cows[two].value];
			Set<Integer> twosPositions = positionsTravelled[cows[one].value];
			onesPositions.add(two);
			twosPositions.add(one);
			positionsTravelled[cows[two].value] = onesPositions;
			positionsTravelled[cows[one].value] = twosPositions;
		}
		HashMap<Integer, Integer> startEnd = new HashMap<Integer, Integer>();
		for(int a = 1; a < cows.length; a++){
			startEnd.put(cows[a].value, a);
		}
		boolean[] visited = new boolean[n+1];
		int[] answers = new int[n+1];
		for(int b = 1; b < n+1; b++){
			if(visited[b])
				continue;
			int start = b;
			Set<Integer> uniqueLocations = new HashSet<>();
			ArrayList<Integer> cycle = new ArrayList<Integer>();
			do{
				start = startEnd.get(start);
				visited[start] = true;
				uniqueLocations.addAll(positionsTravelled[start]);
				cycle.add(start);
			}while(start != b);
			for(int s : cycle)
				answers[s] = uniqueLocations.size();
		}
		StringBuilder out = new StringBuilder();
		for(int a = 1; a < answers.length; a++)
			out.append(answers[a]).append('\n');
		System.out.print(out);
	}
	private static class Cow{
		int value;
		ArrayList<Integer> positions;
		private Cow(int a){
			value = a;
			positions = new ArrayList<Integer>();
		}
		private void setValue(int v){
			value = v;
		}
		public String toString(){
			return value + " " + positions;
		}
	}
}
