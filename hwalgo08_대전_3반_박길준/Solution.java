import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		int tc = 10;

		for (int i = 0; i < tc; i++) {
			int N = Integer.parseInt(sc.nextLine());

			String[][] s = new String[N][];

			for (int j = 0; j < N; j++) {
				s[j] = sc.nextLine().split(" ");
			}

			int result = 0;
			for (int j = 0; j < N; j++) {
				if (s[j].length == 4) {
					if (s[j][1].equals("+") || s[j][1].equals("-") || s[j][1].equals("*") || s[j][1].equals("/")) {
						result = 1;
					} else {
						result = 0;
						break;
					}
				} else {
					if (s[j][1].equals("+") || s[j][1].equals("-") || s[j][1].equals("*") || s[j][1].equals("/")) {
						result = 0;
						break;
					} else {
						result = 1;
					}
				}
			}
			
			System.out.println("#"+(i+1)+" "+result);
		}
	}

}
