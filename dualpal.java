/*
 ID: optic1e1 
 LANG: JAVA
 TASK: dualpal
 */

import java.io.*;
import java.util.*;

public class dualpal {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new FileWriter("dualpal.out"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int printNum = Integer.parseInt(st.nextToken());
		int minVal = Integer.parseInt(st.nextToken());
		while(printNum > 0){
			minVal++;
			int palinCount = 0;
			for(int i = 2; i <= 10; i++){
				String val = convBase10(minVal, i);
				if(isPalindrome(val))
					palinCount++;
				if(palinCount == 2){
					out.println(minVal);
					printNum--;
					i = 10;
				}
			}
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
			conv.append(remainder);
			n = quotient;
		}
		return conv.reverse().toString();
	}
}
