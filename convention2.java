import java.io.*;
import java.util.*;

public class convention2 {
	public static void main(String[] args) throws Exception{
		FastReader input = new FastReader();
		PrintWriter out = new PrintWriter(new FileWriter("convention2.out"));
		int n = input.nextInt();
		StringTokenizer st;
		PriorityQueue<Arrival> queue = new PriorityQueue<Arrival>();
		int[] waiting = new int[n];
		for(int i = 1; i <= n; i++){
			int a = input.nextInt();
			int e = input.nextInt();
			queue.add(new Arrival(i, a, e));
		}
		int maxTime = Integer.MIN_VALUE;
		while(!queue.isEmpty()){
			Arrival temp = queue.poll();
			int finish = temp.getArrival() + temp.getEatingTime();
			while(!queue.isEmpty() && queue.peek().getArrival() < finish){
				Arrival word = queue.poll();
				int diff = finish - word.getArrival();
				word.setArrival(finish);
				waiting[word.getSeniority()-1] += diff;
				maxTime = Math.max(maxTime, waiting[word.getSeniority()-1]);
				queue.add(word);
			}
		}
		out.println(maxTime);
		out.close();
	}
	static class FastReader
    { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader() throws Exception
        { 
            br = new BufferedReader(new
                     FileReader("convention2.in")); 
        } 
  
        String next() 
        { 
            while (st == null || !st.hasMoreElements()) 
            { 
                try
                { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException  e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
  
        int nextInt() 
        { 
            return Integer.parseInt(next()); 
        } 
  
        long nextLong() 
        { 
            return Long.parseLong(next()); 
        } 
  
        double nextDouble() 
        { 
            return Double.parseDouble(next()); 
        } 
  
        String nextLine() 
        { 
            String str = ""; 
            try
            { 
                str = br.readLine(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    } 
}
class Arrival implements Comparable<Arrival>{
	int seniority;
	int arrival;
	int eating;
	public Arrival(int s, int a, int e){
		seniority = s;
		arrival = a;
		eating = e;
	}
	public int getSeniority(){
		return seniority;
	}
	public int getArrival(){
		return arrival;
	}
	public int getEatingTime(){
		return eating;
	}
	public void setArrival(int a){
		arrival = a;
	}
	public int compareTo(Arrival a){
		if(arrival != a.getArrival())
			return arrival - a.getArrival();
		return seniority - a.getSeniority();
	}
	public String toString(){
		return seniority + " " + arrival + " " + eating;
	}
}