import java.util.Scanner;

public class Solution_1266 {
	// 18보다 작은 소수
	static int[] prime_number = { 2, 3, 5, 7, 11, 13, 17 };
//	static int[] prime_number = { 0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18 };
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
			int A = sc.nextInt();
			int B = sc.nextInt();

			double res_a = 0.0;
			double res_b = 0.0;
			
			// 소수에 대한 확률 계산.
			for (int i = 0; i < prime_number.length; i++) {
				res_a += calc_probability(prime_number[i], A);
				res_b += calc_probability(prime_number[i], B);
			}

			result = res_a + res_b - res_a*res_b;
			
			System.out.printf("#%d %.6f \n", tc, result);
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

	static double calc_probability(int prime_number, int a) {
		if (a == 0 ) {
			return 0;
		}
		
		double t_a = a;
		double p = t_a/100;
		
		// a장인의 확률
		double res = combination(18, prime_number) * Math.pow(p, prime_number) * Math.pow(1.0 - p, 18 - prime_number);

		// 독립된 사건 a, b에 대해 교집합은 두 집합의 곱과 같다.
		return res;
	}
}
