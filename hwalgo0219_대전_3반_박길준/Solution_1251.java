import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251 {
	static int[] parent;
	static int N; // 정점의 수
	static double E; // 환경 부담 세율
	static PriorityQueue<Edge> pq;
	static ArrayList<Edge> mst;
	static double result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// 1. 섬의 수
			N = Integer.parseInt(br.readLine());

			parent = new int[N];
			pq = new PriorityQueue<>(new EdgeComparator());
			mst = new ArrayList<>();
			result = 0;

			Point[] p = new Point[N];
			// 2. 간선에 대한 정보 받아오기
			// 2.1. x좌표 받아오기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				p[i] = new Point(i, Integer.parseInt(st.nextToken()), 0);
			}
			// 2.2. y좌표 받아오기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				p[i].y = Integer.parseInt(st.nextToken());
			}

			// 3. 환경 부담 세율 받아오기
			E = Double.parseDouble(br.readLine());

			// 4. 우선순위 큐에 저장하기.
			double v = 0.0f;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					v = E * (Math.pow(p[i].x - p[j].x, 2) + Math.pow(p[i].y - p[j].y, 2));
					pq.add(new Edge(p[i], p[j], v));
				}
			}

			kruskal();

			for (int i = 0; i < mst.size(); i++) {
				result += mst.get(i).value;
			}

			System.out.printf("#%d %.0f\n", tc, result);
		}
	}

	static void kruskal() {
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		int nE = pq.size();
		for (int i = 0; i < nE; i++) {
			Edge edge = pq.poll();

			if (find(edge.start.num) == find(edge.end.num)) {
				continue;
			}

			union(edge.start.num, edge.end.num);

			mst.add(edge);
		}
	}

	static int find(int n) {
		if (parent[n] == n) {
			return n;
		}
		parent[n] = find(parent[n]);
		return parent[n];
	}

	static void union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);

		if (p1 != p2) {
			parent[p1] = p2;
		}
	}

	static class EdgeComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.value > o2.value ? 1 : -1;
		}
	}

	static class Edge {
		Point start;
		Point end;
		double value;

		Edge(Point s, Point e, double v) {
			start = s;
			end = e;
			value = v;
		}
	}

	static class Point {
		int num;
		int x;
		int y;

		Point(int n, int x, int y) {
			this.num = n;
			this.x = x;
			this.y = y;
		}
	}
}
