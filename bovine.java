import java.io.*;
import java.util.*;

public class bovine {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new FileWriter("cownomics.out"));
		String[] line = br.readLine().split(" ");
		int numCows = Integer.parseInt(line[0]);
		int seqLen = Integer.parseInt(line[1]);
		char[][] spottedCows = new char[numCows][seqLen];
		char[][] nonSpottedCows = new char[numCows][seqLen];
		int possible = 0;
		for(int a = 0; a < numCows; a++){
			String lin = br.readLine();
			for(int b = 0; b < seqLen; b++)
				spottedCows[a][b] = lin.charAt(b);
		}
		for(int c = 0; c < numCows; c++){
			String lin = br.readLine();
			for(int d = 0; d < seqLen; d++)
				nonSpottedCows[c][d] = lin.charAt(d);
		}
		for(int i = 0; i < seqLen; i++){
			ArrayList<Character> temp = new ArrayList<Character>();
			for(int j = 0; j < numCows; j++)
				temp.add(spottedCows[j][i]);
			ArrayList<Character> temp2 = new ArrayList<Character>();
			for(int k = 0; k < numCows; k++)
				temp2.add(nonSpottedCows[k][i]);
			if(eval(temp, temp2))
				possible++;
		}
		out.println(possible);
		out.close();
	}
	public static boolean eval(ArrayList<Character> one, ArrayList<Character> two){
		for(char c : one)
			if(two.contains(c))
				return false;
		return true;
	}
}
