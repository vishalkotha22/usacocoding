import java.util.*;
import java.io.*;

public class measurement {
	public static TreeMap<Useless, Integer> amt;
	public static void main(String[] args) throws Exception{
		InputReader input = new InputReader("measurement.in");
		PrintWriter out = new PrintWriter(System.out);
		out = new PrintWriter(new FileWriter("measurement.out"));//*/
		int n = input.nextInt();
		int g = input.nextInt();
		TreeMap<Integer, String> days = new TreeMap<Integer, String>();
		for(int i = 0; i < n; i++){
			days.put(input.nextInt(), input.nextInt() + " " + input.nextInt());
		}
		TreeMap<Integer, Integer> amt = new TreeMap<Integer, Integer>();
		int numRuns = 0;
		ArrayList<Integer> previousIDs = new ArrayList<Integer>();
		int numAlterations = 0;
		StringTokenizer st;
		for(int day : days.keySet()){
			st = new StringTokenizer(days.get(day));
			int id = Integer.parseInt(st.nextToken());
			String c = st.nextToken();
			int quantity;
			if(c.contains("+"))
				quantity = Integer.parseInt(c.substring(1));
			else
				quantity = Integer.parseInt(c);
			if(amt.containsKey(id))
				amt.put(id, amt.get(id) + quantity);
			else
				amt.put(id, g + quantity);
			numRuns++;
			if(numRuns == 1){
				previousIDs.add(amt.lastKey());
				numAlterations++;
			}else{
				ArrayList<Integer> paintings = findMaxIDs(amt);
				if(!paintings.equals(previousIDs)){
					previousIDs = paintings;
					numAlterations++;
				}
			}
		}
		out.println(numAlterations);
		out.close();
	}
	public static class Useless implements Comparable<Useless>{
		int x;
		public Useless(int x){
			this.x = x;
		}
		public int getValue(){
			return x;
		}
		
	}
	public static ArrayList<Integer> findMaxIDs(TreeMap<Integer, Integer> amt){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		int max = Integer.MIN_VALUE;
		for(int key : amt.keySet()){
			if(amt.get(key) == max)
				ids.add(key);
			if(amt.get(key) > max){
				max = amt.get(key);
				ids.clear();
				ids.add(key);
			}
		}
		return ids;
	}
    private static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader() {
            try {
                reader = new BufferedReader(new InputStreamReader(System.in), 32768);
            } catch (Exception e) {
                throw new NullPointerException("Could not create input stream");
            }
        }

        public InputReader(String fileName) {
            try {
                reader = new BufferedReader(new FileReader(new File(fileName)), 32768);
            } catch (Exception ex) {
                throw new NullPointerException("Input file does not exist! Put it in the project folder.");
            }
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public boolean hasNextInt() throws IOException { return reader.ready(); }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public char nextChar() {
            return next().charAt(0);
        }

        /**
         * When you call next(), that entire line will be skipped.
         * No flushing buffers.
         * Doesn't work when you want to scan the remaining line.
         *
         * @return entire line
         */

        public String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
                tokenizer = null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return str;
        }
    }
}
class Day{
	int id;
	String change;
	public Day(int d, int id, String c){
		this.id = id;
		change = c;
	}
	public int getID(){
		return id;
	}
	public int getChange(){
		if(change.contains("+"))
			return Integer.parseInt(change.substring(1));
		return Integer.parseInt(change);
	}
}