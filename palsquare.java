/*
 ID: optic1e1 
 LANG: JAVA
 TASK: palsquare
 */

import java.util.*;
import java.io.*;

public class palsquare {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new FileWriter("palsquare.out"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		int base = Integer.parseInt(br.readLine());
		for(int i = 1; i <= 300; i++){
			if(isPalindrome(convBase10(i*i, base)))
				out.println(convBase10(i, base) + " " + convBase10(i*i, base));
		}
		out.close();
	}
	public static boolean isPalindrome(String s){
		StringBuilder rev = new StringBuilder(s);
		rev.reverse();
		if(rev.toString().equals(s))
			return true;
		return false;
	}
	public static String convBase10(int n, int base){
		StringBuilder conv = new StringBuilder();
		while(n > 0){
			int quotient = n / base;
			int remainder = n % base;
			conv.append(retChar(remainder));
			n = quotient;
		}
		return conv.reverse().toString();
	}
	public static char retChar(int n){
		if(n <= 9)
			return String.valueOf(n).toCharArray()[0];
		else if(n == 10)
			return 'A';
		else if(n == 11)
			return 'B';
		else if(n == 12)
			return 'C';
		else if(n == 13)
			return 'D';
		else if(n == 14)
			return 'E';
		else if(n == 15)
			return 'F';
		else if(n == 16)
			return 'G';
		else if(n == 17)
			return 'H';
		else if(n == 18)
			return 'I';
		return 'J';
	}
}

