import java.io.*;
import java.util.*;

public class race {
	public static void main(String[] args) throws Exception{
		int choose = 0;
		BufferedReader br;
		PrintWriter out;
		if(choose == 0){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("race.in"));
			out = new PrintWriter(new FileWriter("race.out"));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int raceLen = Integer.parseInt(st.nextToken());
		int number = Integer.parseInt(st.nextToken());
		for(int i = 0; i < number; i++){
			int max = Integer.parseInt(br.readLine()); 
			out.println(simulate(raceLen, max));
			out.println();
		}
		out.close();
	}
	public static int simulate(int raceLength, int maxSpeed){
		int steps = 0;
		int speed = 0;
		
	}
	public static int distanceNeeded(int speed, int max){
		return (speed*(speed+1)/2) - (max*(max+1)/2);
	}
}
