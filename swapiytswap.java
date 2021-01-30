import java.io.*;
import java.util.*;

public class swapiytswap {
	public static void main(String[] args) throws Exception{
		int choose = 123;
		BufferedReader br;
		PrintWriter out;
		if(choose == 1){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("swap.in"));
			out = new PrintWriter(new FileWriter("swap.out"));
		}
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st1.nextToken());
		int num = Integer.parseInt(st1.nextToken());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		int a1 = Integer.parseInt(st2.nextToken());
		int a2 = Integer.parseInt(st2.nextToken());
		StringTokenizer st3 = new StringTokenizer(br.readLine());
		int b1 = Integer.parseInt(st3.nextToken());
		int b2 = Integer.parseInt(st3.nextToken());
		int[] cows = new int[n];
		for(int i = 0; i < n; i++)
			cows[i] = i+1;
		for(int j = 0; j < num; j++){
			cows = reverse(cows, a1, a2);
			cows = reverse(cows, b1, b2);
		}
		for(int val : cows)
			out.println(val);
		out.close();
	}
	public static int[] reverse(int[] array, int start, int end){
		int[] reverse = new int[end-start+1];
		int index = 0;
		for(int i = end; i >= start; i--){
			reverse[index] = array[i-1];
			index++;
		}
		int iterate = 0;
		for(int j = start; j <= end; j++){
			array[j-1] = reverse[iterate];
			iterate++;
		}
		return array;
	}
}
