import java.io.*;
import java.util.*;

//By: Vishal Kotha

public class homework {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //set up input and output
		PrintWriter out = new PrintWriter(System.out);
		if(true){
			br = new BufferedReader(new FileReader("homework.in"));
			out = new PrintWriter(new FileWriter("homework.out"));
		}//*/
		int n = Integer.parseInt(br.readLine()); //read number of questions from input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] answers = new int[n];
		int[] prefix = new int[n+1];
		int[] min = new int[n+1];
		min[n] = Integer.MAX_VALUE;
		prefix[0] = 0;
		for(int i = 0; i < n; i++){
			answers[i] = Integer.parseInt(st.nextToken()); //store scores of questions
			prefix[i+1] = prefix[i] + answers[i]; //create prefix sums of the scores of the questions
		}
		for(int j = n-1; j >= 0; j--)
			min[j] = Math.min(min[j+1], answers[j]); //create array where value at j is min from j to end of the scores array
		double max = Double.MIN_VALUE; //store value of maximum score 
		ArrayList<Integer> papers = new ArrayList<Integer>(); //store what numbers of paper Bessie can eat for a maximum score
		for(int k = 0; k < n-2; k++){
			int sum = prefix[n] - prefix[k+1]; //find sum of scores if Bessie eats k papers
			int dropped = sum - min[k+1]; //drop lowest score
			double avg = (double) dropped / (n - k - 2); //calculate average 
			if(max < avg){ //if average is greater than max, set max to average and clear papers and add k to papers
				papers.clear();
				max = avg;
				papers.add(k+1);
			}
			else if(max == avg) //else if average is equal to max add k to the list of papers
				papers.add(k+1);
		}
		for(int val : papers) //print out all the number of papers Bessie can eat in order to achieve an optimal score
			out.println(val);
		out.close(); //flush and close answers
	}
}