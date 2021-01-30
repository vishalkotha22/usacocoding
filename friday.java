/*
ID: optic1e1
LANG: JAVA
TASK: friday
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

class friday {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new FileWriter("friday.out"));
		int years = Integer.parseInt(br.readLine())-1;
		int endYear = 1900+years;
		ArrayList<String> days = new ArrayList<String>();
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");
		days.add("Thursday");
		days.add("Friday");
		days.add("Saturday");
		days.add("Sunday");
		HashMap<String, Integer> counter = new HashMap<String, Integer>();
		counter.put("Saturday", 0);
		counter.put("Sunday", 0);
		counter.put("Monday", 0);
		counter.put("Tuesday", 0);
		counter.put("Wednesday", 0);
		counter.put("Thursday", 0);
		counter.put("Friday", 0);
		int index = -1;
		for(int i = 1900; i <= endYear; i++){
			for(int j = 1; j <= 12; j++){
				if(j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10 | j == 12){
					for(int k = 1; k <= 31; k++){
						index++;
						if(index == 7)
							index = 0;
						String today = days.get(index);
						if(k == 13){
							System.out.println(j + " " + k + " " + i + " " + today);
							counter.put(today, counter.get(today)+1);
						}
					}
				}
				else if(j == 4 || j == 6 || j == 9 || j == 11){
					for(int k = 1; k <= 30; k++){
						index++;
						if(index == 7)
							index = 0;
						String today = days.get(index);
						if(k == 13){
							System.out.println(j + " " + k + " " + i + " " + today);
							counter.put(today, counter.get(today)+1);
						}
					}
				}
				else if(j == 2){
					int daysz = 28;
					if(leapYear(i))
						daysz = 29;
					for(int k = 1; k <= daysz; k++){
						index++;
						if(index == 7)
							index = 0;
						String today = days.get(index);
						if(k == 13){
							System.out.println(j + " " + k + " " + i + " " + today);
							counter.put(today, counter.get(today)+1);
						}
					}
				}
			}
		}
		out.println(counter.get("Saturday") + " " + counter.get("Sunday") + " " + counter.get("Monday") + " " + counter.get("Tuesday") + " " + counter.get("Wednesday") + " " + counter.get("Thursday") + " " + counter.get("Friday"));
		out.close();
	}
	public static boolean leapYear(int n){
		if(n%4 != 0)
			return false;
		if(n%100 == 0 && n != 2000)
			return false;
		return true;
	}
}
