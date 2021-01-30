import java.io.*;
import java.util.*;

public class helpcross {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		/*br = new BufferedReader(new FileReader("helpcross.in"));
		out = new PrintWriter(new FileWriter("helpcross.out"));//*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] chickenTimes = new int[c];
		for(int i = 0; i < c; i++)
			chickenTimes[i] = Integer.parseInt(br.readLine());
		Time[] cowTimes = new Time[n];
		for(int j = 0; j < n; j++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			Time time = new Time(s, e);
			cowTimes[j] = time;
		}
		Arrays.sort(cowTimes);
		Arrays.sort(chickenTimes);
		int pointer = 0;
		int pairs = 0;
		boolean[] paired = new boolean[c];
		Arrays.fill(paired, false);
		for(int k = 0; k < c; k++){
			if(pointer < cowTimes.length && !paired[k] && cowTimes[pointer].getS() <= chickenTimes[k] && cowTimes[pointer].getE() >= chickenTimes[k]){
				pairs++;
				paired[k] = true;
				if(pointer+1 < cowTimes.length)
					pointer++;
			}else{
				pointer++;
			}
		}
		out.println(pairs);
		out.close();
	}
}
class Time implements Comparable<Time>{
	int s;
	int e;
	public Time(int s, int e){
		this.s = s;
		this.e = e;
	}
	public int getS(){
		return s;
	}
	public int getE(){
		return e;
	}
	public String toString(){
		return s + " " + e;
	}
	public int compareTo(Time t){
		return e - t.getE();
	}
}