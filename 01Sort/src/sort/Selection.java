package sort;

import template.AbstractSort;

/* Selection Sort */
public class Selection extends AbstractSort {
    public static void sort(Comparable[] a) {
        int n = a.length, min;

        for (int i = 0; i < n - 1; i++) {
            // Find minimum value
            min = i;

            for (int j = i + 1; j < n; j++)
                if (less(a[j], a[min]))
                    min = j;
            // Exchange minimum value with the value in current position
            exch(a, i, min);

            // Make current position to next, and loop until (n - 1)
        }

        assert isSorted(a);
    }
}
