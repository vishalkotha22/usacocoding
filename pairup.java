import java.util.*;
import java.io.*;

//Code by Vishal Kotha

public class pairup {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("pairup.in"));
		out = new PrintWriter(new FileWriter("pairup.out"));//*/ //set up input and output streams
		int n = Integer.parseInt(br.readLine()); //read number of pairs
		Pair[] array = new Pair[n]; //create an array of pairs
		StringTokenizer st;
		for(int i = 0; i < n; i++){ //read the input into an array of pairs
			st = new StringTokenizer(br.readLine());
			int freq = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			array[i] = new Pair(freq, val);
		}
		Arrays.sort(array); //sort the array
		int lPointer = array.length-1; //instantiate the left pointer, the right pointer, and a max int
		int fPointer = 0;
		int max = 0;
		//we can take advantage of the fact that pairing the smallest cow available with the largest cow available is optimal
		while(fPointer < lPointer-1){ //while the left pointer is not next to the right pointer (not all the cows are paired)
			int one = array[fPointer].getFreq(); //set one and two to the frequencies of the Pair at the pointers
			int two = array[lPointer].getFreq();
			int timeForMilking = array[fPointer].getVal() + array[lPointer].getVal(); //the time it will take to milk is the sum of the two
			max = Math.max(timeForMilking, max); //set max to the max of max and the time it will take to milk the cows at the pointers
			if(one < two){ //if the cow at the left pointer has a lower frequency than the cow at the right pointer
				array[lPointer].setFreq(two-one); //account for using "one" number of lPointer cows to pair up with all the fPointer cows
				fPointer++; //move the left pointer since we have paired all the fPointer cows
			}
			else if(one == two){ //if the frequencies are equal, pair all the fPointers and lPointers cows together and move both pointers
				fPointer++;
				lPointer--;
			}
			else if(one > two){ //if the cow at the rightPointer has a lower frequency than the cow at the left pointer
				array[fPointer].setFreq(one-two); //account for the pairing of all the lPointer cows with "two" number of fPointer cows
				lPointer--; //move the right pointer
			}
		}
		out.println(max); //print the max and close the stream
		out.close();
	}
}
class Pair implements Comparable<Pair>{ //define a pair class
	int freq; //two fields for the frequency or number of times val occurs and the actual value
	int val;
	public Pair(int f, int v){ //create a constructor for Pair
		freq = f;
		val = v;
	}
	public int getFreq(){ //create a getter for freq
		return freq;
	}
	public int getVal(){ //create a getter for val
		return val;
	}
	public void setFreq(int f){ //create a setter for freq
		freq = f;
	}
	public int compareTo(Pair p){ //define the abstract method inherited from Comparable so we can use Arrays.sort
		return val - p.getVal();
	}
}