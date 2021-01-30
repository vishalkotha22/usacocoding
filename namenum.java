/*
 ID: optic1e1
 LANG: JAVA
 TASK: namenum 
 */

import java.util.*;
import java.io.*;

public class namenum {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		//BufferedReader fileRead = new BufferedReader(new FileReader("C:\\Sashank\\workspace\\CodeForces\\src\\dict.txt"));
		BufferedReader br = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter out = new PrintWriter(new FileWriter("namenum.out"));
		BufferedReader fileRead = new BufferedReader(new FileReader("dict.txt"));
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < 4617; i++)
			names.add(fileRead.readLine());
		fileRead.close();
		char[] serial = br.readLine().toCharArray();
		if(serial.length < 10){
			char[][] combinatorics = new char[serial.length][3];
			for(int j = 0; j < serial.length; j++){
				char[] possible = possibleLetters(Integer.parseInt(serial[j]+""));
				for(int k = 0; k < 3; k++)
					combinatorics[j][k] = possible[k];
			}
			ArrayList<String> possibleNames = new ArrayList<String>();
			if(serial.length == 1){
				char[] possible = possibleLetters(Integer.parseInt(serial[0]+""));
				for(char c : possible)
					possibleNames.add(c+"");
			}
			else if(serial.length == 2)
				possibleNames = twoLevels(serial);
			else if(serial.length == 3)
				possibleNames = threeLevels(serial);
			else if(serial.length == 4)
				possibleNames = fourLevels(serial);
			else if(serial.length == 5)
				possibleNames = fiveLevels(serial);
			else if(serial.length == 6)
				possibleNames = sixLevels(serial);
			else if(serial.length == 7)
				possibleNames = sevenLevels(serial);
			else if(serial.length == 8)
				possibleNames = eightLevels(serial);
			else
				possibleNames = nineLevels(serial);
			ArrayList<String> namesContained = new ArrayList<String>();
			for(String s : possibleNames)
				if(names.contains(s))
					namesContained.add(s);
			Collections.sort(namesContained);
			for(String s : namesContained)
				out.println(s);
			if(namesContained.size() == 0)
				out.println("NONE");
		}
		else{
			ArrayList<String> validNames = new ArrayList<String>();
			for(String s : names)
				if(s.length() == serial.length)
					validNames.add(s);
			ArrayList<String> serials = new ArrayList<String>();
			StringBuilder serialString = new StringBuilder();
			for(char c : serial)
				serialString.append(c);
			for(String s : validNames){
				char[] array = s.toCharArray();
				StringBuilder serialBuild = new StringBuilder();
				for(int i = 0; i < array.length; i++)
					serialBuild.append(reverseLetters(array[i]));
				serials.add(serialBuild.toString());
			}
			ArrayList<String> answers = new ArrayList<String>();
			if(serials.contains(serialString.toString())){
				int index = serials.indexOf(serialString.toString());
				String name = validNames.get(index);
				answers.add(name);
			}	
			Collections.sort(answers);
			for(String s : answers)
				out.println(s);
			if(answers.size() == 0)
				out.println("NONE");
		}
		out.close();
	}
	public static int reverseLetters(char c){
		if(c == 'A' || c == 'B' || c == 'C')
			return 2;
		else if(c == 'D' || c == 'E' || c == 'F')
			return 3;
		else if(c == 'G' || c == 'H' || c == 'I')
			return 4;
		else if(c == 'J' || c == 'K' || c == 'L')
			return 5;
		else if(c == 'M' || c == 'N' || c == 'O')
			return 6;
		else if(c == 'P' || c == 'R' || c == 'S')
			return 7;
		else if(c == 'T' || c == 'U' || c == 'V')
			return 8;
		return 9;
	}
	public static char[] possibleLetters(int n){
		char[] ret = new char[3];
		if(n == 2){
			ret[0] = 'A';
			ret[1] = 'B';
			ret[2] = 'C';
		}
		else if(n == 3){
			ret[0] = 'D';
			ret[1] = 'E';
			ret[2] = 'F';
		}
		else if(n == 4){
			ret[0] = 'G';
			ret[1] = 'H';
			ret[2] = 'I';
		}
		else if(n == 5){
			ret[0] = 'J';
			ret[1] = 'K';
			ret[2] = 'L';
		}
		else if(n == 6){
			ret[0] = 'M';
			ret[1] = 'N';
			ret[2] = 'O';
		}
		else if(n == 7){
			ret[0] = 'P';
			ret[1] = 'R';
			ret[2] = 'S';
		}
		else if(n == 8){
			ret[0] = 'T';
			ret[1] = 'U';
			ret[2] = 'V';
		}
		else{
			ret[0] = 'W';
			ret[1] = 'X';
			ret[2] = 'Y';
		}
		return ret;
	}
	public static ArrayList<String> twoLevels(char[] two){
		char[] vals = possibleLetters(Integer.parseInt(two[1]+""));
		char[] supreme = possibleLetters(Integer.parseInt(two[0]+""));
		ArrayList<String> combinations = new ArrayList<String>();
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++)
				combinations.add(String.valueOf(supreme[i]) + vals[j]);
		}
		return combinations;
	}
	public static ArrayList<String> threeLevels(char[] three){
		char[] sub = new char[2];
		sub[0] = three[1];
		sub[1] = three[2];
		ArrayList<String> combos = twoLevels(sub);
		ArrayList<String> combinations = new ArrayList<String>();
		char[] vals = possibleLetters(Integer.parseInt(three[0]+""));
		for(int i = 0; i < 3; i++){
			char c = vals[i];
			for(int j = 0; j < 9; j++)
				combinations.add(c + combos.get(j));
		}
		return combinations;
	}
	public static ArrayList<String> fourLevels(char[] four){
		char[] sub = new char[four.length-1];
		for(int i = 1; i < four.length; i++)
			sub[i-1] = four[i];
		ArrayList<String> subCombos = threeLevels(sub);
		ArrayList<String> combinations = new ArrayList<String>();
		char[] vals = possibleLetters(Integer.parseInt(four[0]+""));
		for(int i = 0; i < 3; i++){
			char c = vals[i];
			for(int j = 0; j < subCombos.size(); j++)
				combinations.add(String.valueOf(c) + subCombos.get(j));
		}
		return combinations;
	}
	public static ArrayList<String> fiveLevels(char[] four){
		char[] sub = new char[four.length-1];
		for(int i = 1; i < four.length; i++)
			sub[i-1] = four[i];
		ArrayList<String> subCombos = fourLevels(sub);
		ArrayList<String> combinations = new ArrayList<String>();
		char[] vals = possibleLetters(Integer.parseInt(four[0]+""));
		for(int i = 0; i < 3; i++){
			char c = vals[i];
			for(int j = 0; j < subCombos.size(); j++)
				combinations.add(c + subCombos.get(j));
		}
		return combinations;
	}
	public static ArrayList<String> sixLevels(char[] four){
		char[] sub = new char[four.length-1];
		for(int i = 1; i < four.length; i++)
			sub[i-1] = four[i];
		ArrayList<String> subCombos = fiveLevels(sub);
		ArrayList<String> combinations = new ArrayList<String>();
		char[] vals = possibleLetters(Integer.parseInt(four[0]+""));
		for(int i = 0; i < 3; i++){
			char c = vals[i];
			for(int j = 0; j < subCombos.size(); j++)
				combinations.add(c + subCombos.get(j));
		}
		return combinations;
	}
	public static ArrayList<String> sevenLevels(char[] four){
		char[] sub = new char[four.length-1];
		for(int i = 1; i < four.length; i++)
			sub[i-1] = four[i];
		ArrayList<String> subCombos = sixLevels(sub);
		ArrayList<String> combinations = new ArrayList<String>();
		char[] vals = possibleLetters(Integer.parseInt(four[0]+""));
		for(int i = 0; i < 3; i++){
			char c = vals[i];
			for(int j = 0; j < subCombos.size(); j++)
				combinations.add(c + subCombos.get(j));
		}
		return combinations;
	}
	public static ArrayList<String> eightLevels(char[] four){
		char[] sub = new char[four.length-1];
		for(int i = 1; i < four.length; i++)
			sub[i-1] = four[i];
		ArrayList<String> subCombos = sevenLevels(sub);
		ArrayList<String> combinations = new ArrayList<String>();
		char[] vals = possibleLetters(Integer.parseInt(four[0]+""));
		for(int i = 0; i < 3; i++){
			char c = vals[i];
			for(int j = 0; j < subCombos.size(); j++)
				combinations.add(c + subCombos.get(j));
		}
		return combinations;
	}
	public static ArrayList<String> nineLevels(char[] four){
		char[] sub = new char[four.length-1];
		for(int i = 1; i < four.length; i++)
			sub[i-1] = four[i];
		ArrayList<String> subCombos = eightLevels(sub);
		ArrayList<String> combinations = new ArrayList<String>();
		char[] vals = possibleLetters(Integer.parseInt(four[0]+""));
		for(int i = 0; i < 3; i++){
			char c = vals[i];
			for(int j = 0; j < subCombos.size(); j++)
				combinations.add(c + subCombos.get(j));
		}
		return combinations;
	}
	public static ArrayList<String> tenLevels(char[] four){
		char[] sub = new char[four.length-1];
		for(int i = 1; i < four.length; i++)
			sub[i-1] = four[i];
		ArrayList<String> subCombos = nineLevels(sub);
		ArrayList<String> combinations = new ArrayList<String>();
		char[] vals = possibleLetters(Integer.parseInt(four[0]+""));
		for(int i = 0; i < 3; i++){
			char c = vals[i];
			for(int j = 0; j < subCombos.size(); j++)
				combinations.add(c + subCombos.get(j));
		}
		return combinations;
	}
	public static ArrayList<String> elevenLevels(char[] four){
		char[] sub = new char[four.length-1];
		for(int i = 1; i < four.length; i++)
			sub[i-1] = four[i];
		ArrayList<String> subCombos = tenLevels(sub);
		ArrayList<String> combinations = new ArrayList<String>();
		char[] vals = possibleLetters(Integer.parseInt(four[0]+""));
		for(int i = 0; i < 3; i++){
			char c = vals[i];
			for(int j = 0; j < subCombos.size(); j++)
				combinations.add(c + subCombos.get(j));
		}
		return combinations;
	}
	public static ArrayList<String> twelveLevels(char[] four){
		char[] sub = new char[four.length-1];
		for(int i = 1; i < four.length; i++)
			sub[i-1] = four[i];
		ArrayList<String> subCombos = elevenLevels(sub);
		ArrayList<String> combinations = new ArrayList<String>();
		char[] vals = possibleLetters(Integer.parseInt(four[0]+""));
		for(int i = 0; i < 3; i++){
			char c = vals[i];
			for(int j = 0; j < subCombos.size(); j++)
				combinations.add(c + subCombos.get(j));
		}
		return combinations;
	}
}
