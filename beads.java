/*
 ID: optic1e1
 LANG: JAVA
 TASK: beads
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class beads {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new FileWriter("beads.out"));
		Scanner s = new Scanner(System.in);
		int len = Integer.parseInt(br.readLine());
		String bead = br.readLine();
		int splitIndex = 0;
		int maxCalc = 0;
		int breakPoint = 0;
		for(int i = 0; i < len; i++){
			int totalCalc = 0;
			int left = i-1;
			int right = i;
			if(right == 0)
				left = len-1;
			char rightCharset = bead.charAt(i);
			boolean wPass = false;
			if(bead.charAt(i) == 'w')
				wPass = true;
			while(!(bead.charAt(right) != 'w' && bead.charAt(right) != rightCharset) || wPass){
				if(wPass && bead.charAt(right) != 'w'){
					wPass = false;
					rightCharset = bead.charAt(right);
				}
				if(i == 23)
					System.out.println("RIGHT: " + right);
				totalCalc++;
				right++;
				if(right == len)
					right = 0;
				if(totalCalc >= len)
					break;
			}
			int val = i-1;
			if(val == -1)
				val = len-1;
			char leftCharset = bead.charAt(val);
			boolean leftWPass = false;
			if(bead.charAt(val) == 'w'){
				leftWPass = false;
				leftCharset = bead.charAt(left);
			}
			while(!(bead.charAt(left) != 'w' && bead.charAt(left) != bead.charAt(val)) || leftWPass){
				if(leftWPass && bead.charAt(left) != 'w')
				if(i == 23)
					System.out.println("LEFT: " + left);
				totalCalc++;
				left--;
				if(left < 0)
					left = len-1;
				if(totalCalc >= len)
					break;
			}
			if(totalCalc > len)
				totalCalc = len;
			maxCalc = Math.max(maxCalc, totalCalc);
			if(maxCalc == totalCalc)
				breakPoint = i;
			System.out.println(i + " " + totalCalc);
			if(maxCalc > len)
				maxCalc = len;
		}
		out.println(maxCalc);
		out.close();
	}
}
