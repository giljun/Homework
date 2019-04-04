import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class J1733 {

	static int[][] map;
	static int res;
	static int x, y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		map = new int[19][19];

		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		res = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 1) {
					res = solve(i, j, 1);
				} else if (map[i][j] == 2) {
					res = solve(i, j, 2);
				}
				if (res != 0) {
					break;
				}
			}
			if (res != 0) {
				break;
			}
		}

		if (res != 0) {
			System.out.println(res);
			System.out.println((x + 1) + " " + (y + 1));
		} else {
			System.out.println(res);
		}
	}

	//
	static int solve(int i, int j, int stone) {
		// 가로
		int cnt = 1;

		for (int k = 0; k <= 6; k++) {
			if (k == 0) {
				if (j - 1 >= 0 && map[i][j - 1] == stone) {
					break;
				}
			} else if (k >= 1 && k < 6) {
				if (j + k < map.length && map[i][j + k] == stone) {
					cnt++;
				} else {
					break;
				}
			} else {
				if (j + k < map.length && map[i][j + k] == stone) {
					cnt++;
				}
			}
		}

		if (cnt == 5) {
			x = i;
			y = j;
			return stone;
		} else {
			cnt = 1;
		}

		// 우하 대각선
		for (int k = 0; k <= 6; k++) {
			if (k == 0) {
				if (i - 1 >= 0 && j - 1 >= 0 && map[i - 1][j - 1] == stone) {
					break;
				}
			} else if (k >= 1 && k < 6) {
				if (i + k < map.length && j + k < map.length && map[i + k][j + k] == stone) {
					cnt++;
				} else {
					break;
				}
			} else {
				if (i + k < map.length && j + k < map.length && map[i + k][j + k] == stone) {
					cnt++;
				}
			}
		}

		if (cnt == 5) {
			x = i;
			y = j;
			return stone;
		} else {
			cnt = 1;
		}

		// 세로
		for (int k = 0; k <= 6; k++) {
			if (k == 0) {
				if (i - 1 >= 0 && map[i - 1][j] == stone) {
					break;
				}
			} else if (k >= 1 && k < 6) {
				if (i + k < map.length && map[i + k][j] == stone) {
					cnt++;
				} else {
					break;
				}
			} else {
				if (i + k < map.length && map[i + k][j] == stone) {
					cnt++;
				}
			}
		}

		if (cnt == 5) {
			x = i;
			y = j;
			return stone;
		} else {
			cnt = 1;
		}

		// 좌하 대각선
		for (int k = 0; k <= 6; k++) {
			if (k == 0) {
				if (i - 1 >= 0 && j + 1 < map.length && map[i - 1][j + 1] == stone) {
					break;
				}
			} else if (k >= 1 && k < 6) {
				if (i + k < map.length && j - k >= 0 && map[i + k][j - k] == stone) {
					cnt++;
				} else {
					break;
				}
			} else {
				if (i + k < map.length && j - k < map.length && map[i + k][j - k] == stone) {
					cnt++;
				}
			}
		}

		if (cnt == 5) {
			x = i + 4;
			y = j - 4;
			return stone;
		}

		return 0;
	}

}
