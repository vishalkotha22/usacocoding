import java.io.*;
import java.util.*;

public class cowntagion {
    static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
	public static void main(String[] args) throws Exception{
		Reader s = new Reader();
		PrintWriter out = new PrintWriter(System.out);
		int n = s.nextInt();
		DFSNode[] graph = new DFSNode[n+1];
		for(int a = 1; a < graph.length; a++)
			graph[a] = new DFSNode();
		for(int i = 0; i < n-1; i++){
			int par = s.nextInt();
			int ch = s.nextInt();
			graph[par].addChildren(ch);
		}
		int days = 0;
		for(int j = 1; j < graph.length; j++){
			if(!graph[j].beenVisited()){
				days += eval(graph[j].getChildren());
				graph[j].toggle();
			}
		}
		out.println(days + n-1);
		out.close();
	}
	public static int eval(ArrayList<Integer> children){
		int days = 0;
		int cows = 1;
		while(cows < children.size()+1){
			cows = cows * 2;
			days++;
		}
		return days;
	}
}
class DFSNode{
	ArrayList<Integer> children;
	int cows;
	boolean visited;
	public DFSNode(){
		children = new ArrayList<Integer>();
		cows = 0;
		visited = false;
	}
	public void addChildren(int c){
		children.add(c);
	}
	public ArrayList<Integer> getChildren(){
		return children;
	}
	public int getCows(){
		return cows;
	}
	public void setCows(int c){
		cows = c;
	}
	public String toString(){
		return cows + " " + children;
	}
	public void toggle(){
		visited = true;
	}
	public boolean beenVisited(){
		return visited;
	}
}
