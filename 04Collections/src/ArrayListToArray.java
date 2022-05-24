import java.util.ArrayList;

public class ArrayListToArray {
    public static void main(String[] args) {
        // Create empty ArrayList
        ArrayList<Integer> al = new ArrayList<>();

        // Add elements to the ArrayList (autoboxing)
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);

        System.out.println("Contents of al : " + al);

        // Prepare new array
        Integer ia[] = new Integer[al.size()];
        // ArrayList to array
        ia = al.toArray(ia);

        int sum = 0;
        for(int i : ia)
            sum += i;

        System.out.println("Sum is " + sum);
    }
}
