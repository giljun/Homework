import java.util.Scanner;

public class Solution {
	// 18보다 작은 소수
	static int[] prime_number = { 2, 3, 5, 7, 11, 13, 17 };
	static double result;

	// factorial(0~18)까지의 값을 저장해놓은 배열
	static long[] fac = new long[19];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		factorial(18);
		for (int tc = 1; tc <= T; tc++) {
			result = 0.0;

			// a장인과 b장인의 확률
			double A = sc.nextDouble() / 100.0;
			double B = sc.nextDouble() / 100.0;

			// 소수에 대한 확률 계산.
			for (int i = 0; i < prime_number.length; i++) {
				calc_probability(prime_number[i], A, B);
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	// factorial의 값을 미리 저장해놓는 메서드
	static long factorial(int n) {
		if (n == 1) {
			fac[0] = 1; // 0! = 1 증명
			fac[1] = 1;
			return 1;
		}
		return fac[n] = n * factorial(n - 1);
	}

	static long combination(int n, int r) {
		long temp_res = fac[n] / (fac[n - r] * fac[r]);

		return temp_res;
	}

	static void calc_probability(int prime_number, double a, double b) {
		if (a == 0 && b == 0) {
			return;
		}
		// a장인의 확률
		double res_a = combination(18, prime_number) * Math.pow(a, prime_number) * Math.pow(1 - a, 18 - prime_number);

		// b장인의 확률
		double res_b = combination(18, prime_number) * Math.pow(b, prime_number) * Math.pow(1 - b, 18 - prime_number);

		// 독립된 사건 a, b에 대해 교집합은 두 집합의 곱과 같다.
		result += res_a + res_b - (res_a * res_b);
	}
}
