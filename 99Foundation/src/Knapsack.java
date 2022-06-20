import java.util.*;

public class Knapsack {
    static int[][] B;

    public static void knapsackDyProg(int W[], int V[], int M, int n) {
        B = new int[n + 1][M + 1];

        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= M; j++) {
                B[i][j] = 0;
            }

        int prev = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= M; j++) {
                B[i][j] = B[i - 1][j];
                prev = B[i][j];

                if ((j >= W[i - 1]) && (B[i][j] < B[i - 1][j - W[i - 1]] + V[i - 1])) {
                    B[i][j] = B[i - 1][j - W[i - 1]] + V[i - 1];

                    if (prev != B[i][j]) {
                        prev = B[i][j];
                    }
                }

            }
        }

        System.out.println("Max Value:\t" + B[n][M]);


        prev = -1;
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[i].length; j++) {
                if (B[i][j] != prev) {
                    System.out.printf("[%d, %d (%d)] ", i, j, B[i][j]);
                    prev = B[i][j];
                }
            }
        }

        System.out.println("\nSelected Packs: ");

        while (n != 0) {
            if (B[n][M] != B[n - 1][M]) {
                System.out.println("\tPackage " + n + " with W = " + W[n - 1] + " and Value = " + V[n - 1]);

                M = M - W[n - 1];
            }

            n--;
        }

    }

    public static void main(String[] args) {
        /*
         * Pack and Weight - Value
         */
        //int W[] = new int[]{3, 4, 5, 9, 4};
        int W[] = new int[]{5, 10, 20};

        //int V[] = new int[]{3, 4, 4, 10, 4};
        int V[] = new int[]{50, 60, 140};

        /*
         * Max Weight
         */
        //int M = 11;
        int M = 90;
        int n = V.length;

        /*
         * Run the algorithm
         */
        knapsackDyProg(W, V, M, n);

    }
}
