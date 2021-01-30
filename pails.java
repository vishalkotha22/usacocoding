import java.io.*;
import java.util.*;

public class pails {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		/*br = new BufferedReader(new FileReader("pails.in"));
		out = new PrintWriter(new fileWriter("pails.out"));//*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		out.println(findMin(x, y, 0, 0, k, m));
		out.close();
	}
	public static int findMin(int p1, int p2, int x, int y, int k, int m){
		if(k == 0){
			int min = Math.min( (int)(Math.abs(p1-m)), (int) Math.abs(p2 - m));
			if(p1-x <= y)
				min = Math.min(min, (int) Math.abs(m - (p1 + y-(p1-x))));
			if(p2 - y <= x)
				min = Math.min(min, (int) Math.abs(m - (x-(p2-y) + p2)));
			min = Math.min(min, (int) Math.abs(m - (x+y)));
			return min;
		}
		int ret = Math.min((int)Math.abs(p1 - m), findMin(p1, p2, p1, y, k, m));
		ret = Math.min(ret, (int) Math.min(Math.abs(p2-m), findMin(p1, p2, x, p2, k, m)));
		if(p1-x <= y)
			ret = Math.min(ret, findMin(p1, p2, p1, y-(p1-x), k-1, m));
		else
			ret = Math.min(ret, findMin(p1, p2, x+y, 0, k-1, m));
		if(p2 - y <= x)
			ret = Math.min(ret, findMin(p1, p2, x-(p2-y), p2, k-1, m));
		else
			ret = Math.min(ret, findMin(p1, p2, 0, x+y, k-1, m));
		return ret;
	}
}