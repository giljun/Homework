import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	static int N;
	static ArrayList<Temperature> temper;
	static int result;
	static int[] check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		// 초기 냉장고의 수
		result = 1;
		temper = new ArrayList<>();
		check = new int[N];
		
		for (int i = 0; i < N; i++) {
			temper.add(new Temperature(sc.nextInt(), sc.nextInt()));
		}

		Collections.sort(temper, new Comparator<Temperature>() {
			@Override
			public int compare(Temperature o1, Temperature o2) {
				if (o1.max > o2.max) {
					return 1;
				} else if (o1.max < o2.max) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		int temp = temper.get(0).max;
		for (int i = 0; i < temper.size(); i++) {
			if( temp < temper.get(i).min) {
				temp = temper.get(i).max;
				result++;
			}
		}
		
		System.out.println(result);
	}

	static class Temperature {
		int min;
		int max;

		Temperature(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}
}
