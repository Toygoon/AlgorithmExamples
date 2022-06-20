public class BinCoef {
    public final static int n = 5, k = 2;
    public static int divideSolveTimes = 0;
    public static int dynamicSolvetimes = 0;

    public static int divideSolve(int n, int k) {
        divideSolveTimes++;
        if (k == 0 || n == k)
            return 1;
        else
            return divideSolve(n - 1, k - 1) + divideSolve(n - 1, k);
    }

    public static int[][] dynamicSolve(int n, int k) {
        int[][] B = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                dynamicSolvetimes++;
                if (j == 0 || j == i)
                    B[i][j] = 1;
                else
                    B[i][j] = B[i - 1][j - 1] + B[i - 1][j];
            }
        }

        return B;
    }

    public static void main(String[] args) {


        System.out.println("Dynamic");
        int[][] res = dynamicSolve(n, k);
        System.out.printf("%-5s", "");
        for (int i = 0; i < res[0].length; i++)
            System.out.printf("%-5d", i);
        System.out.println();
        for (int i = 0; i < res[0].length; i++)
            System.out.print("------");
        System.out.println();

        for (int i = 0; i < res.length; i++) {
            System.out.printf("%-4d%-1c", i, '|');
            for (int j = 0; j < res[i].length; j++) {
                System.out.printf("%-5d", res[i][j]);
            }
            System.out.println();
        }

        System.out.println("Dynamic solve times : " + dynamicSolvetimes);

        divideSolve(n, k);
        System.out.println("Divide solve times : " + (divideSolveTimes - 1));
    }
}
