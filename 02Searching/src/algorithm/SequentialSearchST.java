package algorithm;

import template.Node;

import java.util.ArrayList;
import java.util.List;

/* SequentialSearchST : Represents an unordered LinkedList of generic key-value pairs */
public class SequentialSearchST<K, V> {
    // Keep the reference of first node, initial value : null
    private Node<K, V> first;
    // Keep the count of nodes for LinkedList, initial value : 0
    int N;

    /* get() : Get the value from the key */
    public V get(K key) {
        // Scan the LinkedList, until the next node is null
        for (Node<K, V> x = first; x != null; x = x.next)
            // Search hit
            if (key.equals(x.key))
                return x.value;

        // Search miss
        return null;
    }

    /* put() : Put the key, and value to the LinkedList */
    public void put(K key, V value) {
        for (Node<K, V> x = first; x != null; x = x.next)
            // If the key given is exists, just replace the value of the key
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }

        // If the key given is not exists, add a node to the front of the LinkedList
        first = new Node<K, V>(key, value, first);
        N++;
    }

    /* delete() : Delete the specified key, and value from the LinkedList */
    public void delete(K key) {
        // The case when deleting the first node
        if (key.equals(first.key)) {
            first = first.next;
            N--;

            return;
        }

        // Find the node to delete
        for (Node<K, V> x = first; x.next != null; x = x.next)
            // x indicates the previous node to delete
            if (key.equals(x.next.key)) {
                // Now previous node will point the successor node of the deleting node
                x.next = x.next.next;
                N--;

                return;
            }
    }

    /* keys() : Return all the keys for Iterable type, such as ArrayList */
    public Iterable<K> keys() {
        // Make a new Iterable
        List<K> keyList = new ArrayList<K>(N);

        // Adding sequences, why don't this use for-each statements?
        for (Node<K, V> x = first; x != null; x = x.next)
            keyList.add(x.key);

        return keyList;
    }

    /* contains() : Returns true if the LinkedList contains the specified key */
    public boolean contains(K key) {
        return get(key) != null;
    }

    /* isEmpty() : Returns true if the LinkedList is empty */
    public boolean isEmpty() {
        return size() == 0;
    };

    /* size() : Returns the number of key-value pairs in the LinkedList */
    public int size() {
        return N;
    }
}
