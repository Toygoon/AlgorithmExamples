import sort.*;

public class SortMain {
    public static void normalSort() {
        Integer[] a = { 10, 4, 5, 2, 1, 8, 3, 6 };

        Shell.sort(a);
        Shell.show(a);
    }

    public static void linearSort() {
        int[] a = { 10, 4, 5, 8, 1, 8, 3, 6 }, b;
        b = Counting.sort(a, 11);
        for (int i = 0; i < b.length; i++)
            System.out.print(b[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        linearSort();
    }
}
