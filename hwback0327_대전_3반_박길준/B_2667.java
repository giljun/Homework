import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B_2667 {

	static int[][] map;
	static int complex;
	static ArrayList<Integer> apartments;
	static boolean[][] visited;
	static int count;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		map = new int[N][N];

		String str = "";
		for (int i = 0; i < map.length; i++) {
			str = sc.next();
			for (int j = 0; j < map.length; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		complex = 0;
		count = 1;
		apartments = new ArrayList<>();
		visited = new boolean[N][N];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					complex++;
					visited[i][j] = true;
					dfs(i, j);
					apartments.add(count);
					count = 1;
				}
			}
		}

		Collections.sort(apartments);

		System.out.println(complex);
		for (int i = 0; i < apartments.size(); i++) {
			System.out.println(apartments.get(i));
		}

	}

	static void dfs(int i, int j) {
		for (int k = 0; k < 4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];
			if (x >= 0 && x < map.length && y >= 0 && y < map.length && map[x][y] == 1 && !visited[x][y]) {
				count++;
				visited[x][y] = true;
				dfs(x, y);
			}
		}
	}

}
