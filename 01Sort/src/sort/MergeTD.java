package sort;

import template.AbstractSort;

/* Top Down Merge Sort */
public class MergeTD extends AbstractSort {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Assume that a[lo ... mid] and a[mid + 1 ... hi ] is already sorted

        // Array copy process; Why don't this use System.arraycopy?
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        // Compare the array with aux[], and save to the array a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            // The case; left elements are done
            if (i > mid)
                a[k] = aux[j++];
            // The case; right elements are done
            else if (j > hi)
                a[k] = aux[i++];
            // Compare whether aux[j] is less than aux[i]
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            // If not, increase i++ and put aux[i];
            else
                a[k] = aux[i++];
        }

    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo)
            return;

        int mid = lo + (hi - lo) / 2;

        // The case; left
        sort(a, aux, lo, mid);
        // The case; right
        sort(a, aux, mid + 1, hi);
        // The case; merging
        merge(a, aux, lo, mid, hi);
    }
}
