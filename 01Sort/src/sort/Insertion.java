package sort;

import template.AbstractSort;

/* Insertion Sort */
public class Insertion extends AbstractSort {
    public static final boolean isLinear = false;

    public static void sort(Comparable[] a) {
        int n = a.length;

        // Set current position as i for the very first
        for (int i = 1; i < n; i++)
            // Add the i-th element to the list which is sorted from 0 to i-1
            // Loop section until 'i' reaches 'n-1'
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);

        assert isSorted(a);
    }
}
