/*
 ID: optic1e1 
 LANG: JAVA
 TASK: skidesign
 */

import java.io.*;
import java.util.*;

public class skidesign {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter out = new PrintWriter(new FileWriter("skidesign.out"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		int numHills = Integer.parseInt(br.readLine());
		ArrayList<Integer> hills = new ArrayList<Integer>();
		for(int i = 0; i < numHills; i++)
			hills.add(Integer.parseInt(br.readLine()));
		int leastCost = 0;
		while(Collections.max(hills)-Collections.min(hills) > 17){
		int max = Collections.max(hills);
		int min = Collections.min(hills);
		int difference = (max-min)-17;
		int optimum1 = difference/2;
		int optimum2 = difference - optimum1;
		leastCost += optimum1*optimum1 + optimum2*optimum2;	
		hills.remove(hills.indexOf(min));
		hills.remove(hills.indexOf(max));
		}
		out.println(leastCost);
		out.close();
	}
}
