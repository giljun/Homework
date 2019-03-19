import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.swing.text.AbstractDocument.LeafElement;

public class Main {

	static int[] words = { 0, 15, 19, 28, 38, 41, 53, 58 };
	static int[] result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 문자열의 갯수

		String str = br.readLine();

		int[] letter_num = new int[N];

		for (int i = 0; i < N; i++) {
			String temp = str.substring(6 * i, (6 * i) + 6);
			letter_num[i] = StringToDec(temp);
		}

		result = new int[N];
		Arrays.fill(result, -1);

		for (int i = 0; i < letter_num.length; i++) {
			for (int j = 0; j < words.length; j++) {
				int tmp_res = letter_num[i] ^ words[j];
				int cnt = 0;
				for (int k = 0; k < 6; k++) {
					if ((tmp_res & (1 << k)) != 0) {
						cnt++;
					}
				}
				if (cnt <= 1) {
					result[i] = j;
				}
			}
		}

		String ans = "";

		for (int i = 0; i < result.length; i++) {
			if (result[i] == -1) {
				ans = "";
				ans += (i + 1);
				break;
			} else {
				switch (result[i]) {
				case 0:
					ans += "A";
					break;
				case 1:
					ans += "B";
					break;
				case 2:
					ans += "C";
					break;
				case 3:
					ans += "D";
					break;
				case 4:
					ans += "E";
					break;
				case 5:
					ans += "F";
					break;
				case 6:
					ans += "G";
					break;
				case 7:
					ans += "H";
					break;
				}
			}
		}
		
		System.out.println(ans);
	}

	static int StringToDec(String str) {
		int res = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(5 - i) == '1') {
				res += (1 << i);
			}
		}
		return res;
	}
}
