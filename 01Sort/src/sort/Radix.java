package sort;

/* Radix Sort */
public class Radix {
    public static void sort(int[] A) {
        int m = A[0], exp = 1, n = A.length;
        // Temporary array to save result of sorting
        int[] B = new int[n], C;

        for (int i = 1; i < n; i++)
            // Find maximum value in array, to find out how many times for
            if (A[i] > m)
                m = A[i];

        // exp is current exponential, start with first exponential
        while (m / exp > 0) {
            // Allocate a new bucket
            C = new int[10];

            // Calculate counts
            for (int i = 0; i < n; i++)
                C[(A[i] / exp) % 10]++;

            // Examine the positions
            for (int i = 1; i < 10; i++)
                C[i] += C[i - 1];

            // Save into the temporary array
            for (int i = n - 1; i >= 0; i--)
                B[--C[(A[i] / exp) % 10]] = A[i];

            // Save to real array
            for (int i = 0; i < n; i++)
                A[i] = B[i];

            // Increase the current exponential
            exp *= 10;
        }
    }
}
