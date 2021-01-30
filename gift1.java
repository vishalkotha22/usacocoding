/*
 ID: optic1e1
 LANG: JAVA
 TASK: gift1
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class gift1 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter out = new PrintWriter(new FileWriter("gift1.out"));
		int numOfFriends = Integer.parseInt(br.readLine());
		HashMap<String, Integer> amounts = new HashMap<String, Integer>();	
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < numOfFriends; i++)
			names.add(br.readLine());
		for(String n : names)
			amounts.put(n, 0);
		for(int j = 0; j < numOfFriends; j++){
			String name = br.readLine();
			StringTokenizer vals = new StringTokenizer(br.readLine());
			int money = Integer.parseInt(vals.nextToken());
			int people = Integer.parseInt(vals.nextToken());
			int totalAmountGiven = 0;
			if(people > 0)
				totalAmountGiven = (money/people)*people;
			amounts.put(name, amounts.get(name)-totalAmountGiven);
			if(people != 0){
				ArrayList<String> recipients = new ArrayList<String>();
				for(int k = 0; k < people; k++)
					recipients.add(br.readLine());
				for(String s : recipients)
					amounts.put(s, amounts.get(s)+(money/people));
			}
		}
		for(String n : names)
			out.println(n + " " + amounts.get(n));
		out.close();
	}
}
