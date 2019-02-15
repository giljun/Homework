import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_2819 {
	static int[][] board; // 4x4 격자판
	static int result_count;
	static boolean[] number;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		ArrayList<String> result = new ArrayList<>();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			board = new int[4][4];
			result_count = 0;
			number = new boolean[10000000];

			for (int i = 0; i < board.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < board.length; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					dfs(i, j, "");
				}
			}
			
			System.out.println("#"+tc+" "+result_count);
		}
	}

	static void dfs(int x, int y, String num) {
		num += board[x][y];

		if (num.length() == 7) {
			if (number[Integer.parseInt(num)] == false) {
				number[Integer.parseInt(num)] = true;
				result_count++;
			}
			return;
		}

		// 좌, 상, 우, 하
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, -1, 0, 1 };
		
		for (int i = 0; i < 4; i++) {
			int next_x = x+dx[i];
			int next_y = y+dy[i];
			
			if( next_x >= 0 && next_x < 4 && next_y >= 0 && next_y < 4) {
				dfs( next_x, next_y, num);
			}
		}
	}
}