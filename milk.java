/*
 ID: optic1e1 
 LANG: JAVA
 TASK: milk
 */

import java.util.*;
import java.io.*;

public class milk {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("milk.in"));
		PrintWriter out = new PrintWriter(new FileWriter("milk.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int milkNeeded = Integer.parseInt(st.nextToken());
		int farmersAvailable = Integer.parseInt(st.nextToken());
		HashMap<Integer, Integer> priceQuantity = new HashMap<Integer, Integer>();
		ArrayList<Integer> prices = new ArrayList<Integer>();
		for(int i = 0; i < farmersAvailable; i++){
			StringTokenizer token = new StringTokenizer(br.readLine());
			int price = Integer.parseInt(token.nextToken());
			int quantity = Integer.parseInt(token.nextToken());
			if(!priceQuantity.containsKey(price))
				priceQuantity.put(price, quantity);
			else
				priceQuantity.put(price, priceQuantity.get(price)+quantity);
			if(!prices.contains(price))
				prices.add(price);
		}
		Collections.sort(prices);
		int cost = 0;
		while(milkNeeded > 0){
			for(int j = 0; j < prices.size(); j++){
				int price = prices.get(j);
				int quantity = priceQuantity.get(price);
				if(quantity <= milkNeeded){
					cost += (price * quantity);
					milkNeeded -= quantity;
				}
				else{
					cost += (price * milkNeeded);
					milkNeeded = 0;
				}
			}
		}
		out.println(cost);
		out.close();
	}
}
