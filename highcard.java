import java.io.*;
import java.util.*;

//Code by Vishal Kotha

public class highcard {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("highcard.in"));
		out = new PrintWriter(new FileWriter("highcard.out"));//*/ //set up i/o streams
		int n = Integer.parseInt(br.readLine()); //read n from input
		boolean[] covered = new boolean[2*n + 1]; //create a boolean array of 2*n so we can deduce which cards belong to who
		int[] elsie = new int[n]; //an array to store elsies cards
		int[] bessie = new int[n]; //an array to stores bessies cards
		for(int i = 0; i < n; i++){ //add card to elsie's list, and update covered
			int card = Integer.parseInt(br.readLine());
			elsie[i] = card;
			covered[card] = true;
		}
		int index = 0;
		for(int j = 1; j < covered.length; j++) //based on covered, deduce which cards are bessie's, gets inserted in sorted order
			if(!covered[j]){
				bessie[index++] = j;
			}
		Arrays.sort(elsie); //sort elsie's cards
		int max = 0; //max is the number of points bessie gets
		int k = 0; //k is an iterator for elsie
		int find = 0; //find is an iterator for bessie
		//Greedy Algorithm: For each of Elsie's card, if Bessie has a greater card, choose the smallest card that's still greater
		while(find < n){ //while find is less than n
			if(bessie[find] > elsie[k]){ //if bessie gets the point
				max++; //update number of wins and both the cards since they're placed now
				k++;
				find++;
			}else if(bessie[find] < elsie[k]){ //otherwise move up bessie's index to get a greater card
				find++;
			}
		}
		out.println(max); //print the answer
		out.close(); //close the output
	}
}