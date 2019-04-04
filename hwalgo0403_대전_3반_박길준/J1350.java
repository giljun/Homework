import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class J1350 {
 
    static int N, E;
    static int[] parent;
    static ArrayList<Edge> eArr;
    static ArrayList<Integer> mst;
    static int result;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
 
        st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
 
        parent = new int[N + 1];
         
        eArr = new ArrayList<>();
         
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            eArr.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
 
        Collections.sort(eArr, new EdgeComparator());
         
        mst = new ArrayList<>();
 
        kruscal();
 
        result = 0;
        for (int i = 0; i < mst.size(); i++) {
            result += mst.get(i);
        }
 
        System.out.println(result);
    }
 
    static void kruscal() {
        // ��� ���� �� ó���� �ڱ� �ڽ��� ����Ų��.
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
 
        int nE = eArr.size();
        for (int i = 0; i < nE; i++) {
            // �켱������ ����� ����ġ�� ���� ���� ū ������ ������.
            Edge edge = eArr.get(i);
 
            // ������ ������ ���� ���� ���� �� ��尡 ����Ŭ���� �ƴ����� �˻��Ѵ�. �θ� ������ ����Ŭ�� �����ȴ�.
            if (find(edge.start) == find(edge.end)) {
                continue;
            }
 
            union(edge.start, edge.end);
 
            mst.add(edge.value);
        }
    }
 
    static void union(int start, int end) {
        int p1 = find(start);
        int p2 = find(end);
 
        if (p1 != p2) {
            parent[p1] = p2;
        }
    }
 
    static int find(int num) {
        if (parent[num] == num) {
            return num;
        }
        parent[num] = find(parent[num]);
        return parent[num];
    }
 
    static class Edge {
        int start;
        int end;
        int value;
 
        Edge(int s, int e, int v) {
            start = s;
            end = e;
            value = v;
        }
    }
 
    static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.value < o2.value ? 1 : -1;
        }
 
    }
}
 
