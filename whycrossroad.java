import java.io.*;
import java.util.*;

public class whycrossroad {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter out = new PrintWriter(new FileWriter("circlecross.out"));
		String circle = br.readLine();
		int crosses = 0;
		for(int i = 0; i < 52; i++){
			int secondOccurrence = circle.lastIndexOf(circle.charAt(i));
			if(secondOccurrence == -1)
				continue;
			for(int j = i+1; j < secondOccurrence; j++){
				int second = circle.lastIndexOf(circle.charAt(j));
				if(second > secondOccurrence)
					crosses++;
			}
		}
		out.println(crosses);
		out.close();
	}
}