import java.io.*;
import java.util.*;

public class ballcatch {
	public static void main(String[] args) throws Exception{
		int choose = 0;
		BufferedReader br;
		PrintWriter out;
		if(choose == 0){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("hoofball.in"));
			out = new PrintWriter(new FileWriter("hoofball.out"));
		}
		int numCows = Integer.parseInt(br.readLine());
		ArrayList<Integer> cows = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < numCows; i++)
			cows.add(Integer.parseInt(st.nextToken()));
		Collections.sort(cows);
		int numCycles = 0;
		int numSources = 0;
		String[] direction = new String[numCows];
		for(int i = 1; i < cows.size()-1; i++){
			if(cows.get(i) - cows.get(i-1) <= cows.get(i+1) - cows.get(i)){
				direction[i] = "LEFT";
			}
			else{
				direction[i] = "RIGHT";
			}
		}
		direction[0] = "RIGHT";
		direction[numCows-1] = "LEFT";
		out.println(cows);
		for(int j = 0; j < direction.length; j++){
			if(j == 0){
				if(direction[1].equals("RIGHT"))
					numSources++;
				else if(direction[1].equals("LEFT"))
					numCycles++;
			}
			else if(j == direction.length-1){
				if(direction[j-1].equals("LEFT"))
					numSources++;
				else if(direction[j-1].equals("RIGHT"))
					numCycles++;
			}
			else{
				if((direction[j-1].equals("RIGHT") && direction[j].equals("LEFT")) || (direction[j].equals("RIGHT") && direction[j+1].equals("LEFT")))
					numCycles++;
				else if((direction[j].equals("LEFT") && direction[j-1].equals("LEFT") && direction[j+1].equals("RIGHT")) || (direction[j].equals("RIGHT") && direction[j+1].equals("RIGHT") && direction[j-1].equals("LEFT")))
					numSources++;
				out.println(direction[j-1] + " " + direction[j] + " " + direction[j+1]);
			}
		}
		out.println((numCycles/2) + numSources);
		out.close();
	}
}
