import java.io.*;
import java.util.*;

public final class lifeguards {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new FileWriter("lifeguards.out"));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		ArrayList<Lifeguard> list = new ArrayList<Lifeguard>();
		HashMap<Integer, Integer> time = new HashMap<Integer, Integer>();
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			Lifeguard lg = new Lifeguard(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list.add(lg);
			for(int j = lg.getStart(); j <= lg.getEnd(); j++)
				if(time.containsKey(j))
					time.put(j, time.get(j)+1);
				else
					time.put(j, 1);
		}
		out.println(time.size()-n+1);
		out.close();
	}
}
class Lifeguard{
	int start;
	int end;
	public Lifeguard(){
		start = 0;
		end = 0;
	}
	public Lifeguard(int s, int e){
		start = s;
		end = e;
	}
	public int getStart(){
		return start;
	}
	public int getEnd(){
		return end;
	}
	public void setStart(int s){
		start = s;
	}
	public void setEnd(int e){
		end = e;
	}
	public int overlap(Lifeguard asdf){
		if(asdf.getStart() > end || start > asdf.getEnd())
			return 0;
		int overlap = Math.min(asdf.getEnd(), end) - Math.max(asdf.getStart(), start);
		return overlap;
	}
	public String toString(){
		return start + " " + end;
	}
}