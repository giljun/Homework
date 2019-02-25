import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution_1257 {

	static int K;
	static String str;
	static TreeSet<String> str_set;
	static String result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());

			// 1. 문자열을 입력받는다.
			str = br.readLine();

			int len = str.length();

			str_set = new TreeSet<>();
			for (int i = 0; i < len; i++) {
				for (int j = i; j < len; j++) {
					str_set.add(str.substring(i, j + 1));
				}
			}

			if (str_set.size() >= K) {
				for (int i = 1; i <= K; i++) {
					if (K == i) {
						result = str_set.pollFirst();
					} else {
						str_set.pollFirst();
					}
				}
				System.out.println("#" + tc + " " + result);
			} else {
				System.out.println("#" + tc + " " + "none");
			}
		}
	}
}