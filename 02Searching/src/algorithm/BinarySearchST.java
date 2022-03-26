package algorithm;

import template.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* BinarySearchST */
public class BinarySearchST<K extends Comparable<K>, V> {
    // Set first array capacity before increasing or decreasing the size of array
    private static final int INIT_CAPACITY = 10;

    // Arrays of keys, and values
    private K[] keys;
    private V[] vals;

    // The depth of key-value node trees
    private int N;

    /* Constructors for initiating each arrays */
    public BinarySearchST() {
        // Override the constructor below
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        // Keys are initiated with Comparable because keys must be able to compare with each others
        keys = (K[]) new Comparable[capacity];
        // Values are initiated with Object because values don't have to be compared with each others
        vals = (V[]) new Object[capacity];
    }

    /* contains() : Returns true if the LinkedList contains the specified key */
    public boolean contains(K key) {
        return get(key) != null;
    }

    /* isEmpty() : Returns true if the LinkedList is empty */
    public boolean isEmpty() {
        return size() == 0;
    }

    ;

    /* size() : Returns the number of key-value pairs in the LinkedList */
    public int size() {
        return N;
    }

    /* resize() : Resize the underlying arrays */
    private void resize(int capacity) {
        // Create new array
        K[] tempk = (K[]) new Comparable[capacity];
        V[] tempv = (V[]) new Object[capacity];

        // Copy elements to new array
        // Why don't this use System.arraycopy?
        /*
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        */

        System.arraycopy(keys, 0, tempk, N - 1, N);
        System.arraycopy(vals, 0, tempv, N - 1, N);

        // Change the pointer of current array, previous array will be garbage-collected
        vals = tempv;
        keys = tempk;
    }

    /* search() : Returns the position of given key
     If the key not exists, returns the proper location of given key */
    private int search(K key) {
        // Performs a binary search
        int lo = 0, hi = N - 1, mid, cmp;

        while (lo <= hi) {
            mid = (hi + lo) / 2;
            cmp = key.compareTo(keys[mid]);

            // Key is higher than mid
            if (cmp < 0)
                hi = mid - 1;
                // Key is lower than mid
            else if (cmp > 0)
                lo = mid + 1;
                // Key is identical with mid
            else
                return mid;
        }

        // If the key not exists, return the lo instead of -1
        // Because to find out the proper location when put the key
        return lo;
    }

    /* get() : Returns the value associated with the given key in this symbol table */
    public V get(K key) {
        if (isEmpty())
            return null;

        // Find out a proper location using binary search
        int i = search(key);

        // Searching hit
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
            // Searching miss, key not exists
        else
            return null;
    }

    /* put() : Inserts the specified key-value pair into the symbol table, overwriting the old
        value with the new value if the symbol table already contains the specified key */
    public void put(K key, V value) {
        // Find out the key first
        int i = search(key);

        // If the key exists, just change the value
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = value;
            return;
        }

        // If not exists, resize the array, capacity is double of current length
        if (N == keys.length)
            resize(2 * keys.length);

        /* Start with last index, because the existing values are overwritten
        if the loop is started with first index */
        for (int j = N; j > i; j--) {
            // Push elements to the next location
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }

        keys[i] = key;
        vals[i] = value;
        N++;
    }

    /* delete() : Removes the specified key and associated value from this symbol table */
    public void delete(K key) {
        if (isEmpty())
            return;

        // Find out the key with binary search
        int i = search(key);

        // Just return if key not exists, compare with result values
        if (i == N || keys[i].compareTo(key) != 0)
            return;

        // Start with first index, beside to putting sequences
        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }

        N--;
        keys[N] = null;
        vals[N] = null;

        /* Resize the array is too big, the reason of dividing with four is
        to use 50% of full capacity of its array, same reason for increasing array with double */
        if (N > INIT_CAPACITY && N == keys.length / 4)
            resize(keys.length / 2);
    }

    /* keys() : Return all the keys for Iterable type, such as ArrayList */
    public Iterable<K> keys() {
        // Make a new Iterable
        List<K> keyList = new ArrayList<>(N);

        // Adding sequences, why don't this use Collections?
        /*
        for (int i = 0; i < N; i++)
            keyList.add(keys[i]);
        */
        keyList.addAll(Arrays.asList(keys));

        return keyList;
    }

}
