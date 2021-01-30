import java.io.*;
import java.util.*;

public class triangles {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		//BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
		//PrintWriter out = new PrintWriter(new FileWriter("triangles.out"));
		int numCoordinates = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> coordinates = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < numCoordinates; i++){
			String[] line = br.readLine().split(" ");
			coordinates.add(new ArrayList<Integer>(Arrays.asList(Integer.parseInt(line[0]), Integer.parseInt(line[1]))));
		}
		double maxArea = 0;
		for(int a = 0; a < coordinates.size(); a++){
			for(int b = a+1; b < coordinates.size(); b++){
				for(int c = b+1; c < coordinates.size(); c++){
					ArrayList<Integer> one = coordinates.get(a);
					ArrayList<Integer> two = coordinates.get(b);
					ArrayList<Integer> three = coordinates.get(c);
					out.println(one + " " + two + " " + three);
					if(!(String.valueOf(one.get(0)).equals(String.valueOf(two.get(0))) || String.valueOf(two.get(0)).equals(String.valueOf(three.get(0))) || String.valueOf(one.get(0)).equals(String.valueOf(three.get(0)))))
						continue;
					if(!(String.valueOf(one.get(1)).equals(String.valueOf(two.get(1))) || String.valueOf(two.get(1)).equals(String.valueOf(three.get(1))) || String.valueOf(one.get(1)).equals(String.valueOf(three.get(1)))))
						continue;
					double len1 = calcDistance(one, two);
					double len2 = calcDistance(one, three);
					double len3 = calcDistance(two, three);
					double area = 0;
					if(len1%1 == 0 && len2%1 == 0)
						area = (double)len1 * len2;
					else if(len2%1 == 0 && len3%1 == 0)
						area = (double)len2 * len3;
					else if(len1%1 == 0 && len3%1 == 0)
						area = (double)len1 * len3;
					maxArea = Math.max(maxArea, area);
					out.println(one + " " + two + " " + three + " " + area);
				}
			}
		}
		out.println((int) maxArea);
		out.close();
	}
	public static double calcDistance(ArrayList<Integer> one, ArrayList<Integer> two){
		return Math.sqrt(Math.pow(one.get(0) - two.get(0), 2) + Math.pow(one.get(1) - two.get(1), 2));
	}
}
