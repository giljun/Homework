import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class J_2634 {

	static int M, N, L;
	static int[] mx;
	static Animal[] animals;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		mx = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			mx[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(mx);

		animals = new Animal[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			animals[i] = new Animal(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(animals, new Comparator<Animal>() {

			@Override
			public int compare(Animal o1, Animal o2) {
				return o1.x - o2.x;
			}
		});

		int huntable = 0;
		int result = 0;
		int index = 0;
		for (int i = 0; i < N; i++) {
			while (index < M - 1 && animals[i].x > mx[index]) {
				index++;
			}
			if (index == 0) {
				huntable = Math.abs(animals[i].x - mx[index]) + animals[i].y;
				if (huntable <= L) {
					result++;
				}
			} else {
				huntable = Math.abs(animals[i].x - mx[index]) + animals[i].y;
				if (huntable <= L) {
					result++;
				} else {
					huntable = Math.abs(animals[i].x - mx[index - 1]) + animals[i].y;
					if (huntable <= L) {
						result++;
					}
				}
			}
		}

		System.out.println(result);
	}

	static class Animal {
		int x;
		int y;

		Animal(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
