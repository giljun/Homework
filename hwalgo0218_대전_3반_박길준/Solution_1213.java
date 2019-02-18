import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int i = 0; i < 10; i++) {
			int n_case = Integer.parseInt(br.readLine());

			String s1 = br.readLine();

			st = new StringTokenizer(br.readLine());
			int st_count = st.countTokens();
			String s2 = "";
			for (int j = 0; j < st_count; j++) {
				s2 = st.nextToken();
			}

			String s3 = "";

			int count = 0;
			for (int j = 0; j < s2.length(); j++) {
				if (s2.charAt(j) == s1.charAt(0)) {
					if (j + s1.length() <= s2.length()) {
						s3 = s2.substring(j, j + s1.length());
						if (s3.equals(s1)) {
							count++;
						}
					}
				}
			}
			System.out.println("#" + n_case + " " + count);
		}
	}
}
