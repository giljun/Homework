import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B_2583 {

	static int N, M, K;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int result;
	static int area;
	static ArrayList<Integer> res;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		M = sc.nextInt(); // Y
		N = sc.nextInt(); // X
		K = sc.nextInt(); // 사각형의 개수

		map = new int[M][N];
		visited = new boolean[M][N];

		for (int i = 0; i < K; i++) {
			Point p1 = new Point(sc.nextInt(), sc.nextInt());
			Point p2 = new Point(sc.nextInt(), sc.nextInt());

			for (int j = p1.y; j < p2.y; j++) {
				for (int k = p1.x; k < p2.x; k++) {
					map[j][k] = 1;
				}
			}
		}

		result = 0;
		area = 1;
		res = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					result++;
					visited[i][j] = true;
					dfs(i, j);
					res.add(area);
					area = 1;
				}
			}
		}
		
		Collections.sort(res);
		
		System.out.println(result);
		for (int i = 0; i < res.size(); i++) {
			System.out.print(res.get(i)+" ");
		}
	}

	static void dfs(int i, int j) {
		for (int k = 0; k < 4; k++) {
			if (i + dy[k] >= 0 && i + dy[k] < M && j + dx[k] >= 0 && j + dx[k] < N) {
				if (map[i + dy[k]][j + dx[k]] == 0 && !visited[i + dy[k]][j + dx[k]]) {
					area++;
					visited[i + dy[k]][j + dx[k]] = true;
					dfs(i + dy[k], j + dx[k]);
				}
			}
		}
	}

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
