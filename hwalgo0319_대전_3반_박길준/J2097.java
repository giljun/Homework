import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int[][] line;
	static int[][] path_line;
	static ArrayList<Integer> route;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int dest = sc.nextInt();

		line = new int[N][N];
		path_line = new int[N+1][N+1];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				line[i][j] = sc.nextInt();
			}
		}

		route = new ArrayList<>();

		Floyd();

		System.out.println(line[0][dest - 1]);
		
		// 숙소가 있는 위치
		System.out.print(1+" ");
		
		path(1, dest);
		
		// 목적지가 있는 위치
		System.out.println(dest);
	}

	static void Floyd() {
		for (int course = 0; course < line.length; course++) {
			for (int start = 0; start < line.length; start++) {
				for (int dest = 0; dest < line.length; dest++) {
					if (line[start][dest] > line[start][course] + line[course][dest]) {
						line[start][dest] = line[start][course] + line[course][dest];
						path_line[start+1][dest+1] = course+1;
					}
				}
			}
		}
	}
	static void path(int start, int dest) {
		if(path_line[start][dest]==0) {
			return ;
		}
		
		path(start, path_line[start][dest]);
		System.out.print(path_line[start][dest]+" ");
		path(path_line[start][dest], dest);
	}
}
