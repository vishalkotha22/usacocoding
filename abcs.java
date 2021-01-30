import java.io.*;
import java.util.*;

public class abcs {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> nums = new ArrayList<Integer>();
		while(st.hasMoreTokens()){
			nums.add(Integer.parseInt(st.nextToken()));
		}
		int a = Collections.min(nums);
		nums.remove(nums.indexOf(a));
		int b = Collections.min(nums);
		nums.remove(nums.indexOf(b));
		nums.remove(nums.indexOf(a+b));
		int c = Collections.min(nums);
		out.println(a + " " + b + " " + c);
		out.close();
	}
}
