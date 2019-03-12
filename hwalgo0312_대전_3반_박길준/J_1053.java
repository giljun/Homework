import java.util.Scanner;

public class J_1053 {

	static final int End = -1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			long N = sc.nextLong();
			if (N == End) {
				break;
			} else {
				long[][] base = { { 1, 0 }, { 0, 1 } };
				long[][] a = { { 1, 1 }, { 1, 0 } };

				while (N > 0) {
					if (N % 2 == 1) {
						base = matrix_multiple(base, a);
					}
					a = matrix_multiple(a, a);
					N /= 2;
				}

				System.out.println(base[0][1]);
			}
		}
	}

	static long[][] matrix_multiple(long[][] b, long[][] a) {
		long[][] res = new long[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					res[i][j] += b[i][k] * a[k][j];
				}
				res[i][j] %= 1000L;
			}
		}
		return res;
	}

}
