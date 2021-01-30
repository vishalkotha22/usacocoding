/*
 ID: optic1e1
 LANG: JAVA
 TASK: transform
 */

import java.util.*;
import java.io.*;

public class transform {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new FileWriter("transform.out"));
		int dimension = Integer.parseInt(br.readLine());
		char[][] board = new char[dimension][dimension];
		for(int i = 0; i < dimension; i++){
			String[] chars = br.readLine().split("");
			for(int j = 0; j < dimension; j++)
				board[i][j] = chars[j].toCharArray()[0];
		}
		char[][] modifiedBoard = new char[dimension][dimension];
		for(int x = 0; x < dimension; x++){
			String[] chars = br.readLine().split("");
			for(int y = 0; y < dimension; y++)
				modifiedBoard[x][y] = chars[y].toCharArray()[0];
		}
		char[][] rotated = rotate90(board);
		char[][] doubleRotated = rotate90(rotated);
		char[][] tripleRotated = rotate90(doubleRotated);
		char[][] reflected = reflect(board);
		boolean solved = false;
		if(equal(rotated, modifiedBoard)){
			out.println("1");
			solved = true;
		};
		if(!solved && equal(doubleRotated, modifiedBoard)){
			out.println("2");
			solved = true;
		}
		if(!solved && equal(tripleRotated, modifiedBoard)){
			out.println("3");
			solved = true;
		}
		if(!solved && equal(reflect(board), modifiedBoard)){
			out.println("4");
			solved = true;
		}
		if(!solved && (equal(reflect(rotated), modifiedBoard) || equal(reflect(doubleRotated), modifiedBoard) || equal(reflect(tripleRotated), modifiedBoard))){
			out.println("5");
			solved = true;
		}
		if(!solved && equal(board, modifiedBoard)){
			out.println("6");
			solved = true;
		}
		if(!solved)
			out.println("7");
		out.close();
	}
	public static char[][] rotate90(char[][] array){
		char[][] rotated = new char[array.length][array.length];
		for(int i = 0; i < array.length; i++)
			for(int j = 0; j < array.length; j++)
				rotated[i][j] = array[array.length-j-1][i];
		return rotated;
	}
	public static char[][] reflect(char[][] array){
		char[][] reflected = new char[array.length][array.length];
		for(int i = 0; i < array.length; i++)
			for(int j = 0; j < array.length; j++)
				reflected[i][j] = array[i][array.length-1-j];
		return reflected;
	}
	public static boolean equal(char[][] a, char[][] b){
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < b.length; j++){
				if(a[i][j] != b[i][j])
					return false;
			}
		}
		return true;
	}
	public static void print(PrintWriter out, char[][] array){
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array[0].length; j++)
				out.print(array[i][j]);
			out.println();
		}
	}
}
