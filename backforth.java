import java.util.*;
import java.io.*;

public class backforth {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("backforth.in"));
		PrintWriter out = new PrintWriter(new FileWriter("backforth.out"));
		ArrayList<Integer> firstBarn = new ArrayList<Integer>();
		ArrayList<Integer> secondBarn = new ArrayList<Integer>();
 		StringTokenizer s1 = new StringTokenizer(br.readLine());
		StringTokenizer s2 = new StringTokenizer(br.readLine());
		for(int i = 0; i < 10; i++)
			firstBarn.add(Integer.parseInt(s1.nextToken()));
		for(int j = 0; j < 10; j++)
			secondBarn.add(Integer.parseInt(s2.nextToken()));
		ArrayList<Integer> adder = new ArrayList<Integer>();
		search(adder, firstBarn, secondBarn, 1, 1000);
		out.println(adder.size());
		out.close();
	}
	public static void search(ArrayList<Integer> adder, ArrayList<Integer> barn1, ArrayList<Integer> barn2, int day, int value){
		if(day == 5){
			if(!adder.contains(value)){
				adder.add(value);
			}
		}
		else{
			if(day%2 == 1){
				ArrayList<Integer> temp1 = new ArrayList<Integer>(barn1);
				ArrayList<Integer> temp2 = new ArrayList<Integer>(barn2);
				int tempVal = value;
				for(int i = 0; i < temp1.size(); i++){
					int milk = temp1.get(i);
					temp1.remove(i);
					temp2.add(milk);
					search(adder, temp1, temp2, day+1, tempVal-milk);
					temp1 = new ArrayList<Integer>(barn1);
					temp2.remove(temp2.size()-1);
				}
			}
			else{
				ArrayList<Integer> temp1 = new ArrayList<Integer>(barn1);
				ArrayList<Integer> temp2 = new ArrayList<Integer>(barn2);
				int tempVal = value;
				for(int i = 0; i < temp2.size(); i++){
					int milk = temp2.get(i);
					temp2.remove(i);
					temp1.add(milk);
					search(adder, temp1, temp2, day+1, tempVal+milk);
					temp2 = new ArrayList<Integer>(barn2);
					temp1.remove(temp1.size()-1);
				}
			}
		}
	}
}
