/*
 ID: optic1e1
 LANG: JAVA
 TASK: crypt1
 */

import java.io.*;
import java.util.*;

public class crypt1 {
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new FileWriter("crypt1.out"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		int numPossible = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> possibleValues = new ArrayList<Integer>();
		for(int i = 0; i < numPossible; i++)
			possibleValues.add(Integer.parseInt(st.nextToken()));
		Collections.sort(possibleValues);
		ArrayList<Integer> topNumber = new ArrayList<Integer>();
		generator(2, topNumber, possibleValues);
		//out.println(topNumber);
		ArrayList<Integer> bottomNumber = new ArrayList<Integer>();
		generator(1, bottomNumber, possibleValues);
		//out.println(bottomNumber);
		int solutions = 0;
		for(int top : topNumber){
			for(int bottom : bottomNumber){
				int topSum = Integer.parseInt(String.valueOf(bottom).charAt(0)+"") * top;
				int bottomSum = Integer.parseInt(String.valueOf(bottom).charAt(1)+"") * top;
				boolean pass = true;
				if(pass){
					for(char c : String.valueOf(topSum).toCharArray())
						if(!possibleValues.contains(Integer.parseInt(c+""))){
							pass = false;
							break;
						}
					if(String.valueOf(topSum).length() > 3)
						pass = false;
				}
				int sum = top * bottom;
				if(pass){
					for(char c : String.valueOf(bottomSum).toCharArray())
						if(!possibleValues.contains(Integer.parseInt(c+""))){
							pass = false;
							break;
						}
					if(String.valueOf(bottomSum).length() > 3)
						pass = false;
					if(String.valueOf(sum).length() > 4)
						pass = false;
				}
				if(pass){
					for(char c : String.valueOf(sum).toCharArray())
						if(!possibleValues.contains(Integer.parseInt(c+""))){
							pass = false;
							break;
						}
				}
				if(pass){
					//out.println(top + " " + bottom + " " + sum + " " + topSum + " " + bottomSum);
					solutions++;
				}
			}
		}
		out.println(solutions);
		out.close();
	}
	public static ArrayList<Integer> generator(int stop, ArrayList<Integer> totaler, ArrayList<Integer> values){
		ArrayList<Integer> possibilities = new ArrayList<Integer>();
		for(int val : values)
			for(int num : threepeat(stop, totaler, val, values))
				possibilities.add(num);
		return possibilities;
	}
	public static ArrayList<Integer> threepeat(int stop, ArrayList<Integer> home, int n, ArrayList<Integer> possibilities){
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if(String.valueOf(n).length() == stop){
			for(int i = 0; i < possibilities.size(); i++)
				ret.add(Integer.parseInt(new StringBuilder(String.valueOf(n)).append(possibilities.get(i)).toString()));
			for(int r : ret)
				home.add(r);
			return ret;
		}
		else{
			for(int i = 0; i < possibilities.size(); i++){
				ret.add(Integer.parseInt(new StringBuilder(String.valueOf(n)).append(possibilities.get(i)).toString()));
				threepeat(stop, home, ret.get(i), possibilities);
			}
			return new ArrayList<Integer>();
		}
	}
}
