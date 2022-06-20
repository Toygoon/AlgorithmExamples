import java.io.*;
import java.util.*;

public class TSP {
    private static int N;
    private static int visitedAll;
    private static int W[][];
    static List<DList> list = new ArrayList<>();

    static void powerSet(int[] arr, int idx, boolean[] sel) {
        if (idx == arr.length) {
            DList d = new DList(idx);
            for (int i = 0; i < sel.length; i++) {
                if (sel[i]) {
                    d.vertices.add(arr[i]);
                }

            }
            if (d.vertices.size() < 1 || d.vertices.contains(0))
                return;

            list.add(d);
            return;
        }

        sel[idx] = true;
        powerSet(arr, idx + 1, sel);
        sel[idx] = false;
        powerSet(arr, idx + 1, sel);
    }

    public static class DList implements Comparable<DList> {
        public int start;
        public List<Integer> vertices;
        public int w;

        public DList(int start) {
            this.start = start;
            this.vertices = new ArrayList<>();
        }

        @Override
        public int compareTo(DList o) {
            if (this.vertices.size() > o.vertices.size())
                return 1;
            else if (this.vertices.size() < o.vertices.size())
                return -1;

            return this.vertices.get(0).compareTo(o.vertices.get(0));
        }
    }

    // dp[i][status] : ������� ���õ��� �湮�� ����(status)���� ���� ���� i�� �湮�� ����� �ּ� �Ÿ�
    private static int dp[][];

    private static final int START = 0;
    private static final int INF = 999;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        visitedAll = (1 << N) - 1;
        W = new int[N][N];
        dp = new int[N][1 << N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) W[i][j] = stoi(st.nextToken());
        }
        System.out.print(TSP(START, 1));
        System.out.println();
        //calcD();

        int[] arr = {0, 1, 2, 3};
        powerSet(arr, 0, new boolean[arr.length]);
        Collections.sort(list);

        for(DList d : list) {
            System.out.print(d.start + " : ");
            for(int i : d.vertices) {
                System.out.print(i + " ");
            }
            System.out.println("/");
        }
    }

    public static HashSet<DList> calcD() {
        HashSet<DList> result = new HashSet<>();
        /*
        for (int i = 1; i < N; i++) {
            DList d = new DList(i);
            d.vertices.add(0);
            d.w = W[i][0];
            result.add(d);
        }
         */

        for (int i = 0; i < N; i++) {
            DList d = new DList(i);
            for (int j = 0; j < N; j++) {
                d.vertices.add(j);
            }
            result.add(d);
        }

        for (DList d : result) {
            for (int i : d.vertices) {
                System.out.printf("%-2d", i);
            }
            System.out.println();
        }
        return null;
    }

    private static int TSP(int now, int visited) {
        // ��� ��带 �湮 �ߴٸ�
        if (visited == visitedAll) {
            if (W[now][START] != 0) return W[now][START]; // �ٽ� �������� ���ƿ� �� ����
            else return INF; // �ٽ� �������� ���ư��� ����
        }

        // ���� ���¿��� ���� ��带 �湮�� ���� �ִ� �� �Ǵ�
        int ret = dp[now][visited];
        if (ret > 0) return ret;
        else ret = INF;

        for (int i = 0; i < N; i++) {
            // ���� i�� �湮�� ���� ����, ���� ��ġ���� i�� �� �� �ִٸ� �̵�
            if ((visited & (1 << i)) == 0 && W[now][i] > 0) {
                ret = Math.min(ret, TSP(i, visited | (1 << i)) + W[now][i]);
            }
        }


        return dp[now][visited] = ret;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
