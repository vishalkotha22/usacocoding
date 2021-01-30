/*
 ID: optic1e1
 LANG: JAVA
 TASK: combo 
 */

import java.io.*;
import java.util.*;

public class combo {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new FileWriter("combo.out"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		int totalNums = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] farmerCombo = new int[3];
		for(int i = 0; i < 3; i++)
			farmerCombo[i] = Integer.parseInt(st.nextToken());
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		int[] masterCombo = new int[3];
		for(int j = 0; j < 3; j++)
			masterCombo[j] = Integer.parseInt(st1.nextToken());
		ArrayList<ArrayList<Integer>> allCombos = findCombos(farmerCombo, masterCombo, totalNums);
		ArrayList<ArrayList<Integer>> distinctCombos = new ArrayList<ArrayList<Integer>>();
		for(ArrayList<Integer> combo : allCombos)
			if(!distinctCombos.contains(combo))
				distinctCombos.add(combo);
		out.println(distinctCombos.size());
		out.close();
	}
	public static ArrayList<ArrayList<Integer>> findCombos(int[] farmerCombo, int[] masterCombo, int max){
		ArrayList<ArrayList<Integer>> combos = new ArrayList<ArrayList<Integer>>();
		merge(combos, new ArrayList<Integer>(), findVals(farmerCombo, new ArrayList<Integer>(), max), farmerCombo, max);
		merge(combos, new ArrayList<Integer>(), findVals(masterCombo, new ArrayList<Integer>(), max), masterCombo, max);
		return combos;
	}
	public static void merge(ArrayList<ArrayList<Integer>> combos, ArrayList<Integer> build, ArrayList<Integer> vals, int[] key, int max){
		for(int val : vals){
			ArrayList<Integer> combo = new ArrayList<Integer>(build);
			combo.add(val);
			if(combo.size() == 3)
				combos.add(combo);
			else
				merge(combos, combo, findVals(key, combo, max), key, max);
		}
	}
	public static ArrayList<Integer> findVals(int[] combo, ArrayList<Integer> build, int max){
		ArrayList<Integer> vals = new ArrayList<Integer>();
		if(combo[build.size()]-2 < 1){
			if(combo[build.size()]-2 == -1){
				vals.add(max-1);
				vals.add(max);
				vals.add(1);
				vals.add(2);
				vals.add(3);
			}
			else{
				vals.add(max);
				vals.add(1);
				vals.add(2);
				vals.add(3);
				vals.add(4);
			}
		}
		else if(combo[build.size()]+2 > max){
			if(combo[build.size()]+2 == max+2){
				vals.add(max-2);
				vals.add(max-1);
				vals.add(max);
				vals.add(1);
				vals.add(2);
			}
			else{
				vals.add(max-3);
				vals.add(max-2);
				vals.add(max-1);
				vals.add(max);
				vals.add(1);
			}
		}
		else{
			vals.add(combo[build.size()]-2);
			vals.add(combo[build.size()]-1);
			vals.add(combo[build.size()]);
			vals.add(combo[build.size()]+1);
			vals.add(combo[build.size()]+2);
		}
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(int val : vals)
			if(!(val < 1 || val > max))
				ret.add(val);
		return ret;
	}
}
