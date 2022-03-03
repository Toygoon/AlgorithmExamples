package sort;

import template.AbstractSort;

/* Shell Sort */
public class Shell extends AbstractSort {
    public static void sort(Comparable[] a) {
        int n = a.length, h = 1;

        /* Create h sequences; 1, 4, 13, 40, 121, 364, 1093... */
        while (h < n / 3)
            h = 3 * h + 1;

        /* h-sort the array */
        while (h >= 1) {
            for (int i = h; i < n; i++)
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    // Perform the insertion sort which is far away for h distances
                    exch(a, j, j - h);

            // Decrease the size of h, until h(r-1) reaches h(1)
            h /= 3;
        }
    }
}
