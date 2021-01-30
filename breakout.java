import java.io.*;
import java.util.*;

public class breakout {
	public static void main(String[] args) throws Exception{
		int choose = 1;
		BufferedReader br;
		PrintWriter out;
		if(choose == 0){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}
		else{
			br = new BufferedReader(new FileReader("taming.in"));
			out = new PrintWriter(new FileWriter("taming.out"));
		}
		int numDays = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] days = new int[numDays];
		for(int i = 0; i < numDays; i++)
			days[i] = Integer.parseInt(st.nextToken());
		if(days[0] > 0){
			out.println(-1);
		}
		else{
		int val = -1;
		for(int j = days.length-1; j >= 0; j--){
			if(days[j] != -1){
				val = days[j]-1;
				continue;
			}
			if(val >= 0){
				days[j] = val;
				val--;
			}
		}
		days[0] = 0;
		int numBreakouts = 0;
		int numUnknowns = 0;
		for(int value : days){
			if(value == 0)
				numBreakouts++;
			else if(value == -1)
				numUnknowns++;
		}
		out.println(numBreakouts + " " + (numBreakouts + numUnknowns));
		}
		out.close();
	}
}
