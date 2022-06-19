package com.toygoon.sort.algs;

import com.toygoon.utils.Print;

public class LSD {
    public static void sort(String[] a, int W) {
        int N = a.length, R = 256;
        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R];

            for (int i = 0; i < N; i++)
                count[a[i].charAt(d)]++;

            for (int r = 1; r < R; r++)
                count[r] += count[r - 1];

            for (int i = N - 1; i >= 0; i--)
                aux[--count[a[i].charAt(d)]] = a[i];

            for (int i = 0; i < N; i++)
                a[i] = aux[i];

            Print.printArray(a, "LSD " + (W - d));
        }
    }
}
