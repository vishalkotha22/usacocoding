import java.io.*;
import java.util.*;

public class evenmoreoddphotos {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int evenNumbers = 0;
		int oddNumbers = 0;
		for(int i = 0; i < n; i++){
			int num = Integer.parseInt(st.nextToken());
			if(num%2 == 0)
				evenNumbers++;
			else
				oddNumbers++;
		}
		int maxGroups = Math.min(evenNumbers, oddNumbers) * 2;
		oddNumbers -= maxGroups/2;
		evenNumbers -= maxGroups/2;
		if(evenNumbers > 0)
			maxGroups++;
		else{
			int add = oddNumbers / 3;
			maxGroups += add*2;
			oddNumbers -= add*3;
			if(oddNumbers == 1)
				maxGroups--;
			else if(oddNumbers == 2)
				maxGroups++;
		}
		out.println(maxGroups);
		out.close();
	}
}
