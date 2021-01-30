import java.io.*;
import java.util.*;

public class diamondss {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new FileWriter("diamond.out"));
		String[] line = br.readLine().split(" ");
		int numDiamonds = Integer.parseInt(line[0]);
		int maxDifference = Integer.parseInt(line[1]);
		int[] diamonds = new int[numDiamonds];
		for(int i = 0; i < numDiamonds; i++)
			diamonds[i] = Integer.parseInt(br.readLine());
		Arrays.sort(diamonds);
		int max = 1;
		for(int j = 0; j < diamonds.length; j++){
			int pointer = j;
			while(pointer+1 < diamonds.length && diamonds[pointer+1] - diamonds[j] <= maxDifference)
				pointer++;
			max = Math.max(max, pointer-j+1);
		}
		out.println(max);
		out.close();
	}
}
