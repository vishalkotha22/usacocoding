/*
 ID: optic1e1
 LANG: JAVA
 TASK: milk2
 */

import java.util.*;
import java.io.*;

public class milk2 {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter out = new PrintWriter(new FileWriter("milk2.out"));
		int farmers = Integer.parseInt(br.readLine());
		ArrayList<Integer> start = new ArrayList<Integer>();
		ArrayList<Integer> end = new ArrayList<Integer>();
		int minTime = Integer.MAX_VALUE;
		int maxTime = Integer.MIN_VALUE;
		for(int i = 0; i < farmers; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			start.add(Integer.parseInt(st.nextToken()));
			end.add(Integer.parseInt(st.nextToken()));
			minTime = Math.min(minTime, start.get(i));
			maxTime = Math.max(maxTime, end.get(i));
		}
		int maxClumpMilked = 0;
		int maxClumpNoMilk = 0;
		if(farmers > 0){
			boolean[] covered = new boolean[maxTime+1];
			for(int j = 0; j < start.size(); j++){
				for(int k = start.get(j); k < end.get(j); k++)
					covered[k] = true;
			}
			for(int a = minTime; a < maxTime; a++){
				int currClumpMilk = 0;
				int currClumpNoMilk = 0;
				while(covered[a] == covered[a+1]){
					if(covered[a] == true){
						currClumpMilk++;
					}
					else{
						currClumpNoMilk++;
					}
					a++;
					if(a == maxTime)
						break;
				}
				if(covered[a] == true){
					maxClumpMilked = Math.max(maxClumpMilked, currClumpMilk+1);
					currClumpMilk = 0;
				}
				else{
					maxClumpNoMilk = Math.max(maxClumpNoMilk, currClumpNoMilk+1);
					currClumpNoMilk = 0;
				}
			}
		}
		out.println(maxClumpMilked + " " + maxClumpNoMilk);
		out.close();
	}
}
