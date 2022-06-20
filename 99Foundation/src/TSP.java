import java.io.*;
import java.util.*;

public class TSP {
    private static int N;
    private static int visitedAll;
    private static int W[][];

    public static class DList {
        public int start;
        public List<Integer> vertices;
        public int w;

        public DList(int start) {
            this.start = start;
            this.vertices = new ArrayList<>();
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
        calcD();
    }

    public static HashSet<DList> calcD() {
        HashSet<DList> result = new HashSet<>();

        
        for (int i = 1; i <= N; i++) {
            // ���°� 1�������� �Ѱ���
            DList d = new DList(i);

            for (int j = 1; j <= N - 1; j++) {
                // j : �¥��?
                int count = 0;
                for (int k = 1; k < N; k++) {
                    if (k == i)
                        continue;
                    System.out.println("i : " + i + ", j : " + j + ", k + " + k);
                }

                /*
                // 1�� ���� ���
                DList d = new DList(i);
                for (int k = 0; k <= N; k++) {
                    // �������� ���
                    if (k == 0) {
                        d.vertices.add(k);
                        d.w = W[i][0];
                        continue;
                    }

                    for (int l = 2; l <= k; l++) {

                    }
                }

                 */
            }
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
