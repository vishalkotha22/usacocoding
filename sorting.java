import java.io.*;
import java.util.*;

public class sorting {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
		PrintWriter out = new PrintWriter(new FileWriter("sleepy.out"));
		int numCows = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> cows = new ArrayList<Integer>();
		ArrayList<Integer> outOfOrder = new ArrayList<Integer>();
		for(int i = 0; i < numCows; i++){
			int value = Integer.parseInt(st.nextToken());
			cows.add(value);
			if(value != i+1)
				outOfOrder.add(i);
		}
		int time = 0;
		while(outOfOrder.size() > 0){
			//out.println(cows + " " + outOfOrder);
			int val = outOfOrder.get(0);
			int cow = cows.get(val);
			time += val + 1;
			cows.add(cow, cow);
			if(cows.indexOf(cow) == cow)
				cows.remove(cows.lastIndexOf(cow));
			else
				cows.remove(cows.indexOf(cow));
			//out.println(cows);
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int i = 0; i < cows.size(); i++)
				if(cows.get(i) != i+1)
					temp.add(i);
			//out.println(temp);
			outOfOrder = new ArrayList<Integer>(temp);
		}
		if(time == numCows)
			out.println(time-1);
		else
			out.println(time);
		out.close();
	}
}
