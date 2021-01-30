import java.io.*;
import java.util.*;

public class breeding {
	public static void main(String[] args) throws Exception{
		int choose = 123;
		BufferedReader br;
		PrintWriter out;
		if(choose == 1){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("breedflip.in"));
			out = new PrintWriter(new FileWriter("breedflip.out"));
		}
		int len = Integer.parseInt(br.readLine());
		String a = br.readLine();
		String b = br.readLine();
		ArrayList<Integer> different = new ArrayList<Integer>();
		for(int i = 0; i < len; i++)
			if(b.charAt(i) != a.charAt(i))
				different.add(i);
		int numFlips = 0;
		for(int j = 0; j < different.size()-1; j++){
			if(different.get(j+1) - different.get(j) > 1)
				numFlips++;
		}
		numFlips++;
		out.println(numFlips);
		out.close();
	}
}
