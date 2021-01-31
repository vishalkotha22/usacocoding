import java.io.*;
import java.util.*;

public class udderedbutnotherd {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String alphabet = br.readLine();
		String farmerHeard = br.readLine();
		Queue<Character> queue = new LinkedList<Character>();
		for(int i = 0; i < farmerHeard.length(); i++)
			queue.add(farmerHeard.charAt(i));
		int count = 0;
		while(!queue.isEmpty()){
			for(int j = 0; j < alphabet.length(); j++)
				if(!queue.isEmpty() && queue.peek() == alphabet.charAt(j))
					queue.remove();
			count++;
		}
		out.println(count);
		out.close();
	}
}
