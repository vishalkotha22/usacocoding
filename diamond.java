//Code by Vishal Kotha

import java.io.*;
import java.util.*;

public class diamond {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //read input
		PrintWriter out = new PrintWriter(System.out);
		/*br = new BufferedReader(new FileReader("diamond.in"));
		out = new PrintWriter(new FileWriter("diamond.out"));//*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int maxDiff = Integer.parseInt(st.nextToken());
		int[] diamonds = new int[n];
		for(int i = 0; i < n; i++) //store input into an array
			diamonds[i] = Integer.parseInt(br.readLine());
		Arrays.sort(diamonds); //sort the array
		int index = 0;
		int max = 1;
		int pointer = 0;
		for(int j = 0; j < diamonds.length; j++, pointer = j){ //use the two pointers method to find the largest num of diamonds
			while(pointer+1 < diamonds.length && diamonds[pointer+1] - diamonds[j] <= maxDiff)
				pointer++;
			if(pointer-j+1 > max){
				max = pointer-j+1;
				index = j;
			}
		}
		int[] temp = new int[n-max]; //create a new array that removes the diamonds counted in the previous loop
		int ind = 0;
		for(int k = 0; k < index; k++)
			temp[ind++] = diamonds[k];
		for(int a = index+max; a < diamonds.length; a++)
			temp[ind++] = diamonds[a];
		int len = 1;
		pointer = 0;
		for(int j = 0; j < temp.length; j++, pointer = j){ //repeat the same two pointers method to find the next largest sum of diamodns
			while(pointer+1 < temp.length && temp[pointer+1] - temp[j] <= maxDiff)
				pointer++;
			len = Math.max(len, pointer-j+1);
		}
		if(max == n) //print the answer
			out.println(max);
		else if(n < 2)
			out.println(n);
		else
			out.println(max + len);
		out.close();
	}	
}
