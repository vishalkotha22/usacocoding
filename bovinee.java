import java.io.*;
import java.util.*;

public class bovinee{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.in);
		//BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
		//PrintWriter out = new PrintWriter(new FileWriter("cownomics.out"));
		String[] line = br.readLine().split(" ");
		int numCows = Integer.parseInt(line[0]);
		int numGenes = Integer.parseInt(line[1]);
		char[][] spotted = new char[numCows][numGenes];
		char[][] notSpotted = new char[numCows][numGenes];
		for(int i = 0; i < numCows; i++){
			char[] array = br.readLine().toCharArray();
			for(int j = 0; j < array.length; j++)
				spotted[i][j] = array[j];
		}
		for(int a = 0; a < numCows; a++){
			char[] array = br.readLine().toCharArray();
			for(int b = 0; b < array.length; b++)
				notSpotted[a][b] = array[b];
		}
		int numPossible = 0;
		
		out.close();
	}

}
