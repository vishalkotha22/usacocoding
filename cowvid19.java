import java.io.*;
import java.util.*;

public class cowvid19 {
	public static void main(String[] args) throws Exception{
		int choose = 1;
		BufferedReader br;
		PrintWriter out;
		if(choose == 0){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("socdist1.in"));
			out = new PrintWriter(new FileWriter("socdist1.out"));
		}
		int numCows = Integer.parseInt(br.readLine());
		StringBuilder cows = new StringBuilder(br.readLine());
		ArrayList<Integer> ones = new ArrayList<Integer>();
		for(int i = 0; i < numCows; i++)
			if(cows.charAt(i) == '1')
				ones.add(i);
		ArrayList<Integer> biggestGap = new ArrayList<Integer>();
		int maxGap = -1;
		for(int j = 1; j < ones.size(); j++){
			if(ones.get(j) - ones.get(j-1) > maxGap){
				maxGap = ones.get(j) - ones.get(j-1);
				biggestGap = new ArrayList<Integer>(Arrays.asList(ones.get(j-1), ones.get(j)));
			}
		}
		int index = (biggestGap.get(1) - biggestGap.get(0))/2 + biggestGap.get(0);
		cows.replace(index, index+1, "1");
		ones.add(index);
		Collections.sort(ones);
		ArrayList<Integer> round2 = new ArrayList<Integer>();
		int bigGap = -1;
		for(int k = 1; k < ones.size(); k++){
			if(ones.get(k) - ones.get(k-1) > bigGap){
				bigGap = ones.get(k) - ones.get(k-1);
				round2 = new ArrayList<Integer>(Arrays.asList(ones.get(k-1), ones.get(k)));
			}
		}
		int loc = (round2.get(1) - round2.get(0))/2 + round2.get(0);
		cows.replace(loc, loc+1, "1");
		int minGap = 1000000;
		ones.add(loc);
		Collections.sort(ones);
		for(int a = 1; a < ones.size(); a++)
			if(ones.get(a) - ones.get(a-1) < minGap)
				minGap = ones.get(a) - ones.get(a-1);
		out.println(minGap);
		out.close();
	}
}
