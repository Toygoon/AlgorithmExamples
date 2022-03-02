package template;

/* Abstract class for each sort algorithms */
public abstract class AbstractSort {
    /* the method sort algorithms perform */
    public static void sort(Comparable[] a) {

    };

    /* Check w is less than v, for each comparable elements */
    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /* Exchange elements of index i and index j in array named a */
    protected static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /* Show all elements in a single array */
    protected static void show(Comparable[] a) {
        for(int i=0; i<a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    /* Check the array has sorted correctly */
    protected static boolean isSorted(Comparable[] a) {
        for(int i=1; i<a.length; i++)
            if(less(a[i], a[i-1]))
                return false;

        return true;
    }
}
