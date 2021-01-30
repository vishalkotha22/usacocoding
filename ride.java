/*
	ID: optic1e1
	LANG: JAVA
	TASK: ride
 */

import java.io.*;
import java.util.*;

class ride {
	public static void main(String[] args) throws Exception{
		Scanner s = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new FileReader("ride.in"));
		PrintWriter out = new PrintWriter(new FileWriter("ride.out"));
		char[] comet = br.readLine().toCharArray();
		char[] group = br.readLine().toCharArray();
		long cometProduct = 1;
		long groupProduct = 1;
		for(char c : comet)
			cometProduct *= convLetter(c);
		for(char c : group)
			groupProduct *= convLetter(c);
		if(cometProduct%47 == groupProduct%47)
			out.println("GO");
		else
			out.println("STAY");
		out.close();
	}
	public static int convLetter(char c){
		return c - 'A' + 1;
	}
}
