import java.io.*;
import java.util.*;

public class censor {
	public static void main(String[] args) throws Exception{
		int choose = 1;
		BufferedReader br;
		PrintWriter out;
		if(choose == 0){
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		}else{
			br = new BufferedReader(new FileReader("censor.in"));
			out = new PrintWriter(new FileWriter("censor.out"));
		}
		String str = br.readLine();
		String delete = br.readLine();
		StringBuilder build = new StringBuilder();
		for(int i = 0; i < str.length(); i++){
			build.append(str.charAt(i));
			if(build.toString().contains(delete))
				build.delete(build.length()-delete.length(), build.length());
		}
		out.println(build.toString());
		out.close();
	}
}
