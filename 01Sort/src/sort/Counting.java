package sort;

import template.AbstractSort;

/* Counting Sort (The type of linear sorting algorithms) */
public class Counting extends AbstractSort {
    public static int[] sort(int[] a, int k) {
        int n = a.length;
        int[] c = new int[k], b = new int[n];

        // Count the frequency of elements
        for (int i = 0; i < n; i++)
            c[a[i]]++;

        // Calculate the position that i-th element is saved
        for (int i = 1; i < k; i++)
            c[i] += c[i - 1];

        // Why the element saves from the last of the array?
        for (int i = n - 1; i >= 0; i--)
            // Create sorted array
            b[--c[a[i]]] = a[i];

        return b;
    }
}
