/*
 ID: optic1e1 
 LANG: JAVA
 TASK: wormhole
 */

import java.io.*;
import java.util.*;

public class wormhole {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		//BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));
		//PrintWriter out = new PrintWriter(new FileWriter("wormhole.out"));
		int numWormholes = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> wormholes = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> xCoords = new ArrayList<Integer>();
		ArrayList<Integer> yCoords = new ArrayList<Integer>();
		HashMap<ArrayList<Integer>, Integer> reverseCoords = new HashMap<ArrayList<Integer>, Integer>();
		//initialization of stuff and input
		for(int i = 0; i < numWormholes; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			ArrayList<Integer> wormhole = new ArrayList<Integer>();
			//wormhole is an arraylist of the coordinates, which is added to an arraylist of arraylists called wormholes
			wormhole.add(Integer.parseInt(st.nextToken()));
			wormhole.add(Integer.parseInt(st.nextToken()));
			//add x coordinate to xCoords and add y coordinate to yCoords
			xCoords.add(wormhole.get(0));
			yCoords.add(wormhole.get(1));
			//put the arraylist of the coordinates of the wormhole as the key which returns a number which is the wormhole label
			reverseCoords.put(wormhole, i);
			wormholes.add(wormhole);
		}
		HashMap<Integer, ArrayList<Integer>> wormholeCoords = new HashMap<Integer, ArrayList<Integer>>();
		int index = 0;
		ArrayList<Integer> possibilities = new ArrayList<Integer>();
		//creates an arraylist containing the numbers (0 ... numWormholes1-1)
		for(int j = 0; j < numWormholes; j++)
			possibilities.add(j);
		//assigns a number to each wormhole, for example: 1, [0, 0]
		for(ArrayList<Integer> array : wormholes)
			wormholeCoords.put(index++, array);
		//allCombos contains every single sequence of size wormholes
		ArrayList<ArrayList<Integer>> allCombos = getCombos(numWormholes, possibilities);
		//a hashmap where given an arraylist key it returns a hashmap of the arraylist's pairings
		HashMap<ArrayList<Integer>, HashMap<Integer, Integer>> pairer = new HashMap<ArrayList<Integer>, HashMap<Integer, Integer>>();
		for(ArrayList<Integer> sequence : allCombos){
			HashMap<Integer, Integer> pairs = new HashMap<Integer, Integer>();
			for(int i = 0; i < sequence.size(); i+=2){
				pairs.put(sequence.get(i), sequence.get(i+1));
				pairs.put(sequence.get(i+1), sequence.get(i));
			}
			pairer.put(sequence, pairs);
		}
		ArrayList<ArrayList<Integer>> diffSequences = new ArrayList<ArrayList<Integer>>();
		//filters allCombos by only adding sequences to diffSequences if it has a different group of pairings then all other
		//sequences already contained in diffSequences
		for(ArrayList<Integer> seq : allCombos){
			boolean pass = true;
			for(ArrayList<Integer> check : diffSequences)
				if(pairer.get(check).equals(pairer.get(seq)))
					pass = false;
			if(pass)
				diffSequences.add(seq);
		}
		int count = 0;
		System.out.println(diffSequences);
		//for each sequence in diffSequences add one if it's a cycle or zero if it's not a cycle
		for(ArrayList<Integer> sequence : diffSequences)
			count += numOfCycles(sequence, wormholeCoords, xCoords, yCoords, reverseCoords);
		//out.println(wormholeCoords);
		//print count of number of sequences with infinite loops
		out.println(count);
		out.close();
	}
	public static int numOfCycles(ArrayList<Integer> sequence, HashMap<Integer, ArrayList<Integer>> wormholeCoords, ArrayList<Integer> xCoords, ArrayList<Integer> yCoords, HashMap<ArrayList<Integer>, Integer> reverseCoords){
		HashMap<Integer, Integer> pairs = new HashMap<Integer, Integer>();
		//generates the pairings based on the sequence (each index is paired with the adjacent index and so on starting with zero)
		for(int i = 0; i < sequence.size(); i+=2){
			pairs.put(sequence.get(i), sequence.get(i+1));
			pairs.put(sequence.get(i+1), sequence.get(i));
		}
		//tests each position in the sequence to see if it can cause a cycle
		for(int i = 0; i < sequence.size(); i++){
			System.out.println("Starting Check for Sequence: " + sequence);
			if(isCycle(sequence, wormholeCoords, i, xCoords, yCoords, new ArrayList<Integer>(), pairs, reverseCoords)){
				System.out.println("sequence contains cycle: " + sequence + " at point " + i);
				return 1;
			}
		}
		System.out.println("sequence does not contain a cycle: " + sequence);
		return 0;
	}
	public static boolean isCycle(ArrayList<Integer> sequence, HashMap<Integer, ArrayList<Integer>> wormholeCoords, int startPos, ArrayList<Integer> xCoords, ArrayList<Integer> yCoords, ArrayList<Integer> visitedHoles, HashMap<Integer, Integer> pairs, HashMap<ArrayList<Integer>, Integer> reverseCoords){
		//finds startHole coordinates of sequence value at startPos
		ArrayList<Integer> startHoleCoords = wormholeCoords.get(sequence.get(startPos));
		ArrayList<Integer> sameYCoords = new ArrayList<Integer>();
		ArrayList<Integer> correspondingXCoords = new ArrayList<Integer>();
		//creates two temp arraylists of xCoords and yCoords
		ArrayList<Integer> tempYCoords = new ArrayList<Integer>(yCoords);
		ArrayList<Integer> tempXCoords = new ArrayList<Integer>(xCoords);
		int index = reverseCoords.get(startHoleCoords);
		tempYCoords.remove(index);
		tempXCoords.remove(index);
		//removes one value from each, because xCoords and yCoords also contains the value of the hole we are testing
		System.out.println("temp y: " + tempYCoords);
		for(int i = 0; i < tempYCoords.size(); i++){
			//if a different hole has the same y value as the original hole
			if(String.valueOf(tempYCoords.get(i)).equals(String.valueOf(startHoleCoords.get(1)))){
				//add the y coordinate and the x coordinate to two separate lists
				sameYCoords.add(tempYCoords.get(i));
				correspondingXCoords.add(tempXCoords.get(i));
			}
		}
		//debugging code
		System.out.println("sequence: " + sequence);
		System.out.println("start hole : " + sequence.get(startPos));
		System.out.println("hole coords: " + startHoleCoords);
		System.out.println("Corresponding X: " + correspondingXCoords);
		System.out.println("Same Y: " + sameYCoords);
		System.out.println("Coords on Same Y Level: " + !(sameYCoords.size() == 0));
		if(correspondingXCoords.size() > 0)
			System.out.println("Holes to the Right: " + !(startHoleCoords.get(0) > Collections.max(correspondingXCoords)));
		System.out.println("Visited: " + visitedHoles.contains(startPos));
		System.out.println("Visited Holes: " + visitedHoles);
		//if no other holes have the same y coordinate, return false
		if(sameYCoords.size() == 0)
			return false;
		//if there are no other holes to the right, return false
		if(startHoleCoords.get(0) > Collections.max(correspondingXCoords))
			return false;
		//if visited holes contains startHole then return true
		if(visitedHoles.contains(sequence.get(startPos)))
			return true;
		//add current hole to the visited holes
		visitedHoles.add(sequence.get(startPos));
		System.out.println("start: " + startPos + ", sequence val: " + sequence.get(startPos));
		System.out.println("AFTER: " + visitedHoles);
		int xCoordNextHoleHit = Integer.MAX_VALUE;
		//find the x coordinate of the closest hole thats to the right and on the same y level
		for(int num : correspondingXCoords)
			if(num > startHoleCoords.get(0) && num-startHoleCoords.get(0) < xCoordNextHoleHit-startHoleCoords.get(0))
				xCoordNextHoleHit = num;
		ArrayList<Integer> nextHoleHitCoords = new ArrayList<Integer>();
		//creates arraylist of coordinates of the next hole hit
		nextHoleHitCoords.add(xCoordNextHoleHit);
		nextHoleHitCoords.add(sameYCoords.get(0));
		//more debugging
		System.out.println("next hole hit: " + nextHoleHitCoords);
		System.out.println("reverse: " + reverseCoords);
		System.out.println("reverse coords: " + reverseCoords.get(nextHoleHitCoords));
		System.out.println("pairs: " + pairs);
		//gets hole number based on hole coordinates
		int currHole = reverseCoords.get(nextHoleHitCoords);
		//finds paired hole based on the hole number we found above
		int pairedHole = pairs.get(reverseCoords.get(nextHoleHitCoords));
		System.out.println("paired hole: " + pairedHole);
		//if the hole has already been visited before, return true
		if(visitedHoles.contains(currHole)){
			//some debugging
			System.out.println("Value Contained: " + currHole);
			System.out.println("sequence: " + sequence + ", startPos: " + startPos + ", visitedHoles: " + visitedHoles);
			return true;
		}
		//otherwise, add it to the list of visited holes
		visitedHoles.add(currHole);
		System.out.println("Visited Holes: " + visitedHoles);
		System.out.println("UNCLEAR; CHECKING NEXT HOLE");
		//if neither true nor false is returned, run isCycle again but this time start from the paired hole as bessie is teleported
		//there because it's paired to the hole she hit after going right from the start hole
		return isCycle(sequence, wormholeCoords, sequence.indexOf(pairedHole), xCoords, yCoords, visitedHoles, pairs, reverseCoords);
	}
	public static ArrayList<ArrayList<Integer>> getCombos(int desiredLength, ArrayList<Integer> vals){
		//generates all possible subsequences of length desiredLength without reuse of digits
		ArrayList<ArrayList<Integer>> combos = new ArrayList<ArrayList<Integer>>();
		combifier(desiredLength, combos, vals, new ArrayList<Integer>());
		return combos;
	}
	public static void combifier(int desiredLength, ArrayList<ArrayList<Integer>> adder, ArrayList<Integer> vals, ArrayList<Integer> build){
		//for each value of vals, create a combo with each of vals added to the build, then remove the value added, and
		//keep running that tidbit of code to generate all sequences until desiredLength is met
		for(int val : vals){
			ArrayList<Integer> combo = new ArrayList<Integer>(build);
			combo.add(val);
			if(combo.size() == desiredLength)
				adder.add(combo);
			else{
				ArrayList<Integer> adjustedVals = new ArrayList<Integer>(vals);
				adjustedVals.remove(adjustedVals.indexOf(val));
				combifier(desiredLength, adder, adjustedVals, combo);
			}
		}
	}
}
