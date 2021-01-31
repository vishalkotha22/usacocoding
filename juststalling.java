import java.io.*;
import java.util.*;

public class juststalling {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		StringTokenizer one = new StringTokenizer(br.readLine());
		StringTokenizer two = new StringTokenizer(br.readLine());
		int[] cows = new int[n];
		int[] stalls = new int[n];
		for(int i = 0; i < n; i++){
			cows[i] = Integer.parseInt(one.nextToken());
			stalls[i] = Integer.parseInt(two.nextToken());
		}
		Arrays.sort(stalls);
		ArrayList<Integer> millyrock = new ArrayList<Integer>(n);
		for(int j = 0; j < cows.length; j++)
			millyrock.add(cows[j]);
		Collections.sort(millyrock);
		ArrayList<ArrayList<Integer>> yessirski = new ArrayList<ArrayList<Integer>>();
		generatePossibilities(yessirski, n, millyrock, new ArrayList<Integer>());
		long itsliluzivertmaybachmaybach = 0;
		for(ArrayList<Integer> combo : yessirski){
			boolean pass = true;
			for(int k = 0; k < n; k++)
				if(combo.get(k) > stalls[k]){
					pass = false;
					break;
				}
			if(pass)
				itsliluzivertmaybachmaybach++;
		}
		out.println(itsliluzivertmaybachmaybach);
		out.close();
	}
	public static void generatePossibilities(ArrayList<ArrayList<Integer>> adder, int n, ArrayList<Integer> vals, ArrayList<Integer> asdf){
		if(asdf.size() == n)
			adder.add(new ArrayList<Integer>(asdf));
		else{
			for(int i = 0; i < vals.size(); i++){
				ArrayList<Integer> temp = new ArrayList<Integer>(vals);
				temp.remove(i);
				ArrayList<Integer> downbad = new ArrayList<Integer>(asdf);
				downbad.add(vals.get(i));
				generatePossibilities(adder, n, temp, downbad);
			}
		}
	}
}
