import java.io.*;
import java.util.*;

//Code by Vishal Kotha

public class cardgame {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("cardgame.in"));
		out = new PrintWriter(new FileWriter("cardgame.out"));//*/ //set up i/o streams
		int n = Integer.parseInt(br.readLine()); //read n from input
		boolean[] covered = new boolean[2*n + 1]; //create a boolean array of 2*n so we can deduce which cards belong to who
		int[] elsie = new int[n]; //an array to store elsies cards
		int[] bessie = new int[n]; //an array to stores bessies cards
		int[] lowHalf = new int[n/2]; //subarrays used to split elsie's cards into the two subset of cards in the rounds where the 
		int[] highHalf = new int[n/2]; //point is given to the max and min respectively
		for(int i = 0; i < n; i++){ //add card to elsie's list, update covered, and fill lowHalf and highHalf
			int card = Integer.parseInt(br.readLine());
			elsie[i] = card;
			covered[card] = true;
			if(i < n/2)
				lowHalf[i] = elsie[i];
			else
				highHalf[i-n/2] = elsie[i];
		}
		Arrays.sort(lowHalf); //sort highhalf and lowhalf
		Arrays.sort(highHalf);
		int index = 0;
		for(int j = 1; j < covered.length; j++) //based on covered, deduce which cards are bessie's, gets inserted in sorted order
			if(!covered[j]){
				bessie[index++] = j;
			}
		int points = 0;
		int bessieIndex = n/2;
		int elsieIndex = 0;
		while(elsieIndex < n/2 && bessieIndex < n){ //try to pair each card with the least difference but greater card from Bessie 
			if(lowHalf[elsieIndex] < bessie[bessieIndex]){
				elsieIndex++;
				bessieIndex++;
				points++;
			}else{
				bessieIndex++;
			}
		}
		bessieIndex = n/2-1;
		elsieIndex = n/2-1;
		while(bessieIndex >= 0 && elsieIndex >= 0){ //try to pair each card with the least difference but smaller card from Bessie
			if(highHalf[elsieIndex] > bessie[bessieIndex]){
				bessieIndex--;
				elsieIndex--;
				points++;
			}else{
				bessieIndex--;
			}
		}
		out.println(points); //print the answer 
		out.close(); //close the output stream
	}
}