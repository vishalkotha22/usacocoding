import java.io.*;
import java.util.*;

//Code by Vishal Kotha

public class socdist {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("socdist.in"));
		out = new PrintWriter(new FileWriter("socdist.out"));//*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Interval[] intervals = new Interval[m];
		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			long s = Long.parseLong(st.nextToken());
			long e = Long.parseLong(st.nextToken());
			intervals[i] = new Interval(s, e);
		}
		Arrays.sort(intervals);
		int low = 0;
		int high = 1000000000;
		while(low < high){
			int mid = (low + high + 1) / 2;
			if(works(intervals, mid, n))
				low = mid;
			else
				high = mid-1;
		}
		out.println(low);
		out.close();
	}
	public static boolean works(Interval[] grass, int d, int num){
        //Starts at the left-most position
        long pos = grass[0].getS(); int ix = 0;
        for(int i = 1; i < num; i++){
            //If the current patch won't work, then we need to find the next closest patch that does.
            if(pos+d > grass[ix].getE()){
                while(grass[ix].getS() < pos+d && grass[ix].getE() < pos+d){
                    ix++;
                    //No solution
                    if(ix == grass.length) return false;
                }
                pos = Math.max(grass[ix].getS(), pos+d);
            }
            else{
                pos += d;
            }
        }
        return true;
	}
}
class Interval implements Comparable<Interval>{
	long s;
	long e;
	public Interval(long s, long e){
		this.s = s;
		this.e = e;
	}
	public long getS(){
		return s;
	}
	public long getE(){
		return e;
	}
	public boolean contained(long v){
		return (v >= s && v <= e);
	}
	public int compareTo(Interval i){
		return (int) (s - i.getS());
	}
}
