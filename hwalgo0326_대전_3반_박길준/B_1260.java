import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B_1260 {

	static Queue<Integer> queue;
	static boolean[] visited;
	static int[][] matrix;
	static ArrayList<Integer> res1;
	static ArrayList<Integer> res2;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int V = sc.nextInt();

		matrix = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			matrix[start][end] = matrix[end][start] = 1;
		}

		// DFS
		res2 = new ArrayList<>();
		res2.add(V);
		visited[V] = true;
		dfs(V, 1, N);

		for (int i = 0; i < res2.size(); i++) {
			System.out.print(res2.get(i) + " ");
		}
		
		System.out.println();
		
		// BFS
		for (int i = 1; i < visited.length; i++) {
			visited[i] = false;
		}

		queue = new LinkedList<>();
		res1 = new ArrayList<>();
		bfs(V);

		for (int i = 0; i < res1.size(); i++) {
			System.out.print(res1.get(i) + " ");
		}
	}

	static void dfs(int start, int depth, int N) {
		if (res2.size() == N) {
			return;
		}
		for (int i = 1; i < matrix.length; i++) {
			if(matrix[start][i]==1&&!visited[i]) {
				res2.add(i);
				visited[i] = true;
				dfs(i, depth+1, N);
			}
		}
	}

	static void bfs(int start) {

		queue.add(start);
		res1.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (int i = 1; i < matrix.length; i++) {
				if (matrix[curr][i] == 1 && !visited[i]) {
					// 인접 정점
					queue.add(i);
					res1.add(i);
					visited[i] = true;
				}
			}
		}
	}
}
