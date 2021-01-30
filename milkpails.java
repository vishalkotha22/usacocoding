import java.util.*;
import java.io.*;

public class milkpails{
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("pails.in"));
		PrintWriter out = new PrintWriter(new FileWriter("pails.out"));
		String[] line = br.readLine().split(" ");
		int smallBucket = Integer.parseInt(line[0]);
		int mediumBucket = Integer.parseInt(line[1]);
		int bigBucket = Integer.parseInt(line[2]);
		int tempBig = bigBucket;
		int maxMedium = bigBucket / mediumBucket;
		int minDifference = bigBucket;
		for(int i = 0; i <= maxMedium; i++){
			tempBig -= i * mediumBucket;
			tempBig -= (tempBig / smallBucket) * smallBucket;
			minDifference = Math.min(minDifference, tempBig);
			tempBig = bigBucket;
		}
		out.println(bigBucket - minDifference);
		out.close();
	}
}
