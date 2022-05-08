import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        // Create empty ArrayList
        ArrayList<String> al = new ArrayList<>();

        System.out.println("Initial size of al : " + al.size());

        // Add elements to the ArrayList
        al.add("C");
        al.add("A");
        al.add("E");
        al.add("B");
        al.add("D");
        al.add("F");
        al.add(1, "A2");

        System.out.println("Size of after additions : " + al.size());
        // Print data of the ArrayList (toString())
        System.out.println("Contents of al : " + al);

        // Delete elements of the ArrayList
        al.remove("F");
        al.remove(2);

        System.out.println("Size of al after deletions : " + al.size());
        System.out.println("Contents of al : " + al);
    }
}
