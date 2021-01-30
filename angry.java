import java.io.*;
import java.util.*;
 
//Code by Vishal Kotha

public class angry {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("angry.in"));
		out = new PrintWriter(new FileWriter("angry.out"));//*/ //set up i/o streams
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //read num of locations
		int k = Integer.parseInt(st.nextToken()); //read num of cows that can be used
		int[] locs = new int[n];
		for(int i = 0; i < n; i++)
			locs[i] = Integer.parseInt(br.readLine()); //set up array with locations of each cow
		Arrays.sort(locs); //sort array
		int low = 0;
		int high = 1000000000; //set up low and high for binary search
		while(low <= high){
			//System.out.println(low + " " + ans + " " + high);
			if(works((low + high) / 2,locs, k, n)){ //if k works, search lower half
				high = ((low + high) / 2) - 1;
			}
			else{ //else search higher half
				low = ((low + high) / 2) +1;
			}
			//System.out.println(low + " " + mid + " " + high);
		}
		//System.out.println("done");
		out.println(high + 1); //print answer
		out.close();
	}
	public static boolean works (int R, int [] haybales, int K, int N) {
        int upperLimit = haybales[0] + R*2; //if cow is shot R feet to the right R, it maximizes the destructive power (total distance of 2*R)
        int numCows = 1;
        for (int i = 1; i < N; i++) {
            if (haybales[i] > upperLimit) { //if cow is out of destructive power range, increase num of cows and adjust upperLimit
                numCows++;
                upperLimit = haybales[i] + R*2;
            }
        }
        return numCows <=K; //return whether the num of cows needed satisfies k
    }
}