import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class J1082 {

	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Point> fire = new LinkedList<Point>();
	static Queue<Point> man = new LinkedList<Point>();

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static boolean check = true;
	static boolean scape = false;
	static int m = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new char[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = sc.next().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'S') {
					man.add(new Point(i, j));
					visited[i][j] = true;
				}

				if (map[i][j] == '*') {
					fire.add(new Point(i, j));
					visited[i][j] = true;
				}
			}
		}

		BFS();

		if (scape == false) {
			System.out.println("impossible");
		}

	}

	private static void BFS() {
		while (check) {
			m = m + 1;
			int fsize = fire.size();

			for (int i = 0; i < fsize; i++) {
				Point p = fire.remove();
				for (int j = 0; j < 4; j++) {
					int x = dx[j] + p.x;
					int y = dy[j] + p.y;

					if (x >= 0 && y >= 0 && x < N && y < M && map[x][y] == '.' && visited[x][y] == false) {
						map[x][y] = map[p.x][p.y];
						visited[x][y] = true;
						fire.add(new Point(x, y));
					}
				}
				// m=m+1;
			}

			int msize = man.size();

			for (int i = 0; i < msize; i++) {
				Point p = man.remove();

				for (int j = 0; j < 4; j++) {
					int x = dx[j] + p.x;
					int y = dy[j] + p.y;

					if (x >= 0 && y >= 0 && x < N && y < M && (map[x][y] == '.' || map[x][y] == 'D')
							&& visited[x][y] == false) {
						if (map[x][y] == 'D') {
							System.out.println(m);
							check = false;
							scape = true;
							return;
						}

						map[x][y] = map[p.x][p.y];
						visited[x][y] = true;
						man.add(new Point(x, y));
					}
				}
				// m=m+1;
			}

			if (fire.size() == 0 && man.size() == 0) {
				check = false;
			}
		}
	}
}