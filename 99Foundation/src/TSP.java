import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TSP {
    static int n, statusFullBit, INF = 999;
    static int[][] w;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        statusFullBit = (1 << n) - 1;
        w = new int[n][n];
        dp = new int[n][statusFullBit];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(tsp(0, 1)); // 0������ ���� Ž�� ���� (check: 0001)

    }

    static int tsp(int x, int check) {

        // ��� ���� �湮 �Ϸ�
        if (check == statusFullBit) {
            if (w[x][0] == 0) return INF; // ��� ������ INF�� Ž�� ��ȿȭ (Math.min)
            else return w[x][0]; // ��ΰ� �����ϸ� w[x][0]
        }

        // �̹� �湮�� ����
        if (dp[x][check] != -1) return dp[x][check];

        // �ش� ���ÿ� �⼮ ǥ��
        dp[x][check] = INF;

        // �湮���� ���� ���� Ž��
        for (int i = 0; i < n; i++) {
            // next : i ���� �湮
            int next = check | (1 << i);

            // ��ΰ� ���ų� i ���ø� �̹� �湮���� ��� continue
            if (w[x][i] == 0 || (check & (1 << i)) != 0)
                continue;

            dp[x][check] = Math.min(dp[x][check], tsp(i, next) + w[x][i]);
        }

        return dp[x][check];
    }
}
