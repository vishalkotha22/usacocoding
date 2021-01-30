import java.io.*;
import java.util.*;

//Code by Vishal Kotha

public class oerimeter {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		br = new BufferedReader(new FileReader("perimeter.in"));
		out = new PrintWriter(new FileWriter("perimeter.out"));//*/ //set up i/o streams
		int n = Integer.parseInt(br.readLine()); //read n
		char[][] array = new char[n][n]; //create a 2d char array to store the grid
		boolean[][] visited = new boolean[n][n]; //create a 2d boolean array to store where we've visited
		for(int i = 0; i < n; i++){ //read in input to grid
			String line = br.readLine();
			for(int j = 0; j < n; j++)
				array[i][j] = line.charAt(j);
		}
		int[] maxes = new int[]{0, 0}; //create int array to store max area and perimeter
		for(int j = 0; j < n; j++){
			for(int k = 0; k < n; k++){
				int[] vals = floodfill(visited, array, j, k); //run floodfill on every location
				if(maxes[0] < vals[0]){ //if area is greater, set max to appropriate values
					maxes[0] = vals[0];
					maxes[1] = vals[1];
				}else if(maxes[0] == vals[0]){ //if area is equal, set perimeter to minimum
					maxes[1] = Math.min(maxes[1], vals[1]);
				}
			}
		}
		out.println(maxes[0] + " " + maxes[1]); //print answer
		out.close(); //close output stream
	}
	public static int[] floodfill(boolean[][] visited, char[][] array, int r, int c){
		if(r < 0 || c < 0 || r == array.length || c >= array[0].length) //if r or c are out of bounds, return 0, 0
			return new int[]{0, 0};
		if(array[r][c] == '.' || visited[r][c]) //if it isn't a # or it's been visited, return 0, 0
			return new int[]{0, 0};
		visited[r][c] = true; //set visited[r][c] to true because we are visiting it now
		boolean leftC = true; //initialize four booleans: leftC for checking left, rightC for checking right, upR for up, downR for down
		boolean rightC = true;
		boolean upR = true;
		boolean downR = true;
		int perimeter = 0; //initialize perimeter and area (starts from 1 because we know array[r][c] is a # otherwise it would've failed
		int area = 1;
		if(r == 0){ //if its the top row, don't check for a row above (doesn't exist) and automatically has a perimeter at the top
			perimeter++;
			upR = false;
		}
		if(r == array.length-1){ //if it's the bottom row, don't check row below (doesn't exist) and has a perimeter at the bottom
			perimeter++;
			downR = false;
		}
		if(c == 0){ //if it's the leftmost column, don't check column to the left (doesn't exist) and has a perimeter at the left
			perimeter++;
			leftC = false;
		}
		if(c == array[0].length-1){ //if it's the rightmost column, don't check column to right (doesn't exist) and has perimeter at right
			perimeter++;
			rightC = false;
		}
		if(leftC) //if char to the left is a ., increase perimeter by one
			if(array[r][c-1] == '.')
				perimeter++;
		if(rightC)
			if(array[r][c+1] == '.') //if char to the right is a ., increase perimeter by one
				perimeter++;
		if(upR)
			if(array[r-1][c] == '.') //if char above is a ., increase perimeter by one
				perimeter++;
		if(downR)
			if(array[r+1][c] == '.') //if char below is a ., increase perimeter by one
				perimeter++;
		int[] one = floodfill(visited, array, r, c+1); //call floodfill on right
		int[] two = floodfill(visited, array, r, c-1); //call floodfill on left
		int[] three = floodfill(visited, array, r-1, c); //call floodfill on above
		int[] four = floodfill(visited, array, r+1, c); //call floodfill on below
		perimeter += one[1] + two[1] + three[1] + four[1]; //add up values of recursive floodfills of neighboring chars
		area += one[0] + two[0] + three[0] + four[0]; 
		return new int[]{area, perimeter}; //return the values obtained
	}
}