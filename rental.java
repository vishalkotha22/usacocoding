import java.io.*;
import java.util.*;

public class rental {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("rental.in"));
		PrintWriter out = new PrintWriter(new FileWriter("rental.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[] cows = new int[n];
		for(int i = 0; i < n; i++)
			cows[i] = Integer.parseInt(br.readLine());
		Store[] stores = new Store[m];
		for(int j = 0; j < m; j++){
			st = new StringTokenizer(br.readLine());
			stores[j] = new Store(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int[] rentals = new int[r];
		for(int k = 0; k < r; k++)
			rentals[k] = Integer.parseInt(br.readLine());
		Arrays.sort(cows);
		Arrays.sort(stores);
		Arrays.sort(rentals);
		int[] cowPrefixSums = new int[n+1];
		cowPrefixSums[0] = 0;
		for(int b = 1; b < cowPrefixSums.length; b++)
			cowPrefixSums[b] = cowPrefixSums[b-1] + cows[cows.length-b];
		int[] rentalPrefixSums = new int[r+1];
		rentalPrefixSums[0] = 0;
		for(int a = 1; a < rentalPrefixSums.length; a++)
			rentalPrefixSums[a] = rentalPrefixSums[a-1] + rentals[rentals.length-a];
		long max = Long.MIN_VALUE;
		for(int use = n; use > 0; use--)
			max = Math.max(max, calcProfit(cowPrefixSums, stores, rentalPrefixSums, use));
		out.println(max);
		out.close();
	}
	public static long calcProfit(int[] cowPrefixSums, Store[] stores, int[] rentalPrefixSums, int use){
		int tempUse = use;
		int cowMilk = cowPrefixSums[use];
		long profit = 0;
		int index = stores.length-1;
		for(int i = stores.length-1; i >= 0 && cowMilk > stores[i].getQuantity(); i--, index--){
			profit += stores[i].getQuantity() * stores[i].getPrice();
			cowMilk -= stores[i].getQuantity();
		}
		if(index >= 0)
			profit += cowMilk * stores[index].getPrice();
		int numRentals = cowPrefixSums.length - 1 - use;
		profit += rentalPrefixSums[numRentals];
		return profit;
	}
}
class Store implements Comparable<Store>{
	int quantity;
	int price;
	public Store(int q, int s){
		quantity = q;
		price = s;
	}
	public int getQuantity(){
		return quantity;
	}
	public int getPrice(){
		return price;
	}
	public void setQuantity(int q){
		quantity = q;
	}
	public void setPrice(int p){
		price = p;
	}
	public int compareTo(Store s){
		return price - s.getPrice();
	}
	public String toString(){
		return quantity + " " + price;
	}
}
