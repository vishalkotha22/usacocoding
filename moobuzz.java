import java.io.*;
import java.util.*;

public class moobuzz {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
		PrintWriter out = new PrintWriter(new FileWriter("moobuzz.out"));
		int n = Integer.parseInt(br.readLine());
		int ticker = n / 8;
		int remainder = n%8;
		int[] addend = {0, 1, 2, 4, 7, 8, 11, 13, 14};
		out.println(ticker * 15 + addend[remainder]);
		out.close();
	}
}
