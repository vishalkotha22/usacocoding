import java.io.*;
import java.util.*;

public class practiceusaco {
	public static void main(String[] args) throws Exception{
		int choose = 1;
		BufferedReader br;
		PrintWriter out;
		if(choose == 1){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("race.in"));
			out = new PrintWriter(new FileWriter("race.out"));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		for(int i = 0; i < n; i++){
			int s = Integer.parseInt(br.readLine());
			out.println(simulate(k, s));
		}
		out.close();
	}
	public static int simulate(int raceLen, int maxSpeed){
		int speed = 0;
		int steps = 0;
		while(raceLen > 0){
			raceLen -= speed;
			if(decelerate(speed+1, maxSpeed) < raceLen){
				speed++;
				steps++;
			}
			else if(decelerate(speed+1, maxSpeed) == raceLen){
				steps += speed+1 - maxSpeed;
				return steps;
			}
			else if(decelerate(speed, maxSpeed) < raceLen && decelerate(speed+1, maxSpeed) > raceLen){
				steps++;
			}
			else if(decelerate(speed, maxSpeed) > raceLen){
				speed--;
				steps++;
			}
			else if(decelerate(speed, maxSpeed) == raceLen){
				steps += speed - maxSpeed;
				return steps;
			}
		}
		return steps;
	}
	public static int decelerate(int speed, int maxSpeed){
		return (speed*(speed+1))/2 - (maxSpeed*(maxSpeed+1))/2;
	}
}
