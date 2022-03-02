package sort;

import template.AbstractSort;

public class Selection extends AbstractSort {
    public static void sort(Comparable[] a) {
        int n = a.length, min;
        for (int i = 0; i < n - 1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
        assert isSorted(a);
    }
}
