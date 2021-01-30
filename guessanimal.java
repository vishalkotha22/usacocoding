import java.io.*;
import java.util.*;

public class guessanimal {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("guess.in"));
		PrintWriter out = new PrintWriter(new FileWriter("guess.out"));
		int numAnimals = Integer.parseInt(br.readLine());
		HashMap<String, ArrayList<String>> characteristics = new HashMap<String, ArrayList<String>>();
		for(int i = 0; i < numAnimals; i++){
			StringTokenizer line = new StringTokenizer(br.readLine());
			String animal = line.nextToken();
			int numCharacteristics = Integer.parseInt(line.nextToken());
			ArrayList<String> chars = new ArrayList<String>();
			for(int j = 0; j < numCharacteristics; j++)
				chars.add(line.nextToken());
			characteristics.put(animal, chars);
		}
		Set<String> set1 = characteristics.keySet();
		Set<String> set2 = characteristics.keySet();
		int max = 0;
		for(String s1 : set1){
			ArrayList<String> char1 = characteristics.get(s1);
			for(String s2 : set2){
				if(!s1.equals(s2)){
					int curr = 0;
					ArrayList<String> char2 = characteristics.get(s2);
					for(String ch : char1)
						if(char2.contains(ch))
							curr++;
					max = Math.max(max, curr);
				}
			}
		}
		out.println(max+1);
		out.close();
	}
}
