/*
 ID: optic1e1 
 LANG: JAVA
 TASK: barn1
 */

import java.util.*;
import java.io.*;

public class barn1 {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new FileWriter("barn1.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numBoards = Integer.parseInt(st.nextToken());
		int numStalls = Integer.parseInt(st.nextToken());
		int numCows = Integer.parseInt(st.nextToken());
		ArrayList<Boolean> occupied = new ArrayList<Boolean>();
		ArrayList<Integer> occupiedStalls = new ArrayList<Integer>();
		boolean[] open = new boolean[numStalls];
		for(int j = 0; j < numCows; j++)
			occupiedStalls.add(Integer.parseInt(br.readLine()));
		int index = 0;
		if(numBoards == 1)
			out.println(Collections.max(occupiedStalls) - Collections.min(occupiedStalls) + 1);
		else if(numBoards >= numCows)
			out.println(numCows);
		else{
			Collections.sort(occupiedStalls);
			for(int i = 0; i < numStalls; i++){
				if(index < occupiedStalls.size() && occupiedStalls.get(index)-1 == i){
					occupied.add(true);
					index++;
				}
				else
					occupied.add(false);
			}
			for(int x = 0; x < occupied.size(); x++){
				if(occupied.get(x) == false){
					occupied.set(x, true);
					open[x] = true;
				}
				else
					break;
			}
			for(int y = occupied.size()-1; y >= 0; y--){
				if(occupied.get(y) == false){
					occupied.set(y, true);
					open[y] = true;
				}
				else
					break;
			}
			numBoards--;
			while(numBoards-->0){
				ArrayList<Integer> indices = twoBoardLargeClump(occupied);
				for(int k = indices.get(0); k <= indices.get(1); k++){
					occupied.set(k, true);
					open[k] = true;
				}
			}
			int count = 0;
			for(boolean b : open){
				if(!b)
					count++;
			}
			out.println(count);
		}
		out.close();
	}
	public static ArrayList<Integer> twoBoardLargeClump(ArrayList<Boolean> array){
		int maxClump = -1;
		int start = -1;
		int end = -1;
		for(int a = 0; a < array.size(); a++){
			int currClumpNoMilk = 0;
			while(array.get(a) == false && array.get(a) == array.get(a+1)){
				currClumpNoMilk++;
				a++;
				if(a == array.size()-1){
					break;
				}
			}
			maxClump = Math.max(maxClump, currClumpNoMilk+1);
			if(maxClump == currClumpNoMilk+1){
				end = a;
				start = a-maxClump+1;
			}
			currClumpNoMilk = 0;
		}
		if(start == end)
			if(array.indexOf(false) != -1)
				return new ArrayList<Integer>(Arrays.asList(array.indexOf(false), array.indexOf(false)));
		return new ArrayList<Integer>(Arrays.asList(start, end));
	}
}