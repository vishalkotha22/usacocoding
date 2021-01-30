import java.io.*;
import java.util.*;

public class moop {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		
		out.close();
	}
	private static class Particle implements Comparable<Particle>{
		int spin1, spin2;
		private Particle(int s1, int s2){
			spin1 = s1;
			spin2 = s2;
		}
		private int getSpin1(){
			return spin1;
		}
		private int getSpin2(){
			return spin2;
		}
		public int compareTo(Particle p){
			if(spin1 >= p.getSpin1() && spin2 >= p.getSpin2())
				return 1;
			if(p.getSpin1() >= spin1 && p.getSpin2() >= spin2)
				return -1;
			return 0;
		}
	}
}
