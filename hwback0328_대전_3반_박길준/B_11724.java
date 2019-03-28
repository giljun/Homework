import java.util.Scanner;

public class B_11724 {

	static int N, M;
	static int[][] graph;
	static boolean[] visited;
	static int cc;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		graph = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		cc = 0;

		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			graph[x][y] = graph[y][x] = 1;
		}
		
		for (int i = 1; i < graph.length; i++) {
			if (!visited[i]) {
				cc++;
				dfs(i);
			}
		}

		System.out.println(cc);
	}

	static void dfs(int i) {
		visited[i] = true;
		for (int j = 1; j < graph.length; j++) {
			if (graph[i][j] == 1 && !visited[j]) {
				dfs(j);
			}
		}
	}
}
