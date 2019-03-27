import java.util.Scanner;

public class B_1012 {

	static int[][] map;
	static int N, M, K;
	static int worm;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();

			map = new int[N][M];

			for (int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				map[y][x] = 1;
			}

			worm = 0;
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						worm++;
						visited[i][j] = true;
						dfs(i, j);
					}
				}
			}
			System.out.println(worm);
		}
	}

	static void dfs(int i, int j) {
		for (int k = 0; k < 4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];
			if (x >= 0 && x < N && y >= 0 && y < M && map[x][y] == 1 && !visited[x][y]) {
				visited[x][y] = true;
				dfs(x, y);
			}
		}
	}
}
