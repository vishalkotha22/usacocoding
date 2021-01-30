import java.io.*;
import java.util.*;

public class stuckinarut {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		ArrayList<Cow> northCows = new ArrayList<Cow>();
		ArrayList<Cow> eastCows = new ArrayList<Cow>();
		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			char d = st.nextToken().charAt(0);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(d == 'N')
				northCows.add(new Cow(d, x, y, i));
			else
				eastCows.add(new Cow(d, x, y, i));
		}
		Collections.sort(northCows, new Comparator<Cow>(){
			@Override
			public int compare(Cow one, Cow two){
				return one.getY() - two.getY();
			}
		});
		Collections.sort(eastCows, new Comparator<Cow>(){
			@Override
			public int compare(Cow one, Cow two){
				return one.getX() - two.getX();
			}
		});
		int pointer1 = 0;
		int pointer2 = 0;
		while(pointer1 < northCows.size() && pointer2 < eastCows.size()){
			simulate(pointer1, pointer2, northCows, eastCows);
		}
		for(Cow cow : northCows)
			out.println(cow);
		for(Cow cow : eastCows)
			out.println(cow);
	}
	private static void simulate(int pointer1, int pointer2, ArrayList<Cow> northCows, ArrayList<Cow> eastCows){
		Cow one = northCows.get(pointer1);
		Cow two = eastCows.get(pointer2);
		int[] intersections = new int[]{one.getX(), two.getY()};
		int oneDistance = intersections[1] - one.getY();
		int twoDistance = intersections[0] - two.getX();
		if(oneDistance < twoDistance){
			for(int a = pointer2; intersections[0] - eastCows.get(a).getX() > oneDistance; pointer2++)
				one.increaseStopped(1 + eastCows.get(a).getStopped());
			for(int i = pointer1; i < eastCows.size(); i++)
				eastCows.get(i).setX(eastCows.get(i).getX() + oneDistance);
			for(int j = pointer2; j < northCows.size(); j++)
				northCows.get(j).setY(northCows.get(j).getY() + oneDistance);
		}else if(oneDistance > twoDistance){
			for(int a = pointer1; intersections[1] - northCows.get(a).getY() > twoDistance; pointer1++)
				two.increaseStopped(1 + northCows.get(a).getStopped());
			for(int i = pointer1; i < eastCows.size(); i++)
				eastCows.get(i).setX(eastCows.get(i).getX() + twoDistance);
			for(int j = pointer2; j < northCows.size(); j++)
				northCows.get(j).setY(northCows.get(j).getY() + twoDistance);
		}else{
			for(int i = pointer1; i < eastCows.size(); i++)
				eastCows.get(i).setX(eastCows.get(i).getX() + oneDistance);
			for(int j = pointer2; j < northCows.size(); j++)
				northCows.get(j).setY(northCows.get(j).getY() + twoDistance);
			simulate(pointer1, pointer2, northCows, eastCows);
			simulate(pointer1, pointer2, northCows, eastCows);
		}
	}
	private static class Cow{
		char d;
		int x;
		int y;
		int marker;
		int stopped;
		private Cow(char d, int x, int y, int m){
			this.d = d;
			this.x = x;
			this.y = y;
			stopped = 0;
		}
		private int getX(){
			return x;
		}
		private int getY(){
			return y;
		}
		private void setX(int x){
			this.x = x;
		}
		private void setY(int y){
			this.y = y;
		}
		private void increaseStopped(int n){
			stopped += n;
		}
		private int getStopped(){
			return stopped;
		}
		public String toString(){
			return x + " " + y + " " + d + " " + stopped;
		}
	}
}
