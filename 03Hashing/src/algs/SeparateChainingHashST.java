/* Separate Chaining Hash Symbol Tables
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package algs;

import algorithm.SequentialSearchST;

import java.util.ArrayList;

public class SeparateChainingHashST<K, V> {
    // The number of elements in the table
    private int N;

    // The size of the hash table
    private int M;

    // Struct with Sequential Search ST array
    private SequentialSearchST<K, V>[] st;

    public SeparateChainingHashST() {
        // Default : prime number
        this(997);
    }

    public SeparateChainingHashST(int M) {
        // The constructor for setting M
        this.M = M;
        st = (SequentialSearchST<K, V>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<>();
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    private V get(K key) {
        // Search the list
        return st[hash(key)].get(key);
    }

    private boolean isEmpty(K key) {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /* Hash function : Modular calculation with M */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /* Add to the list */
    public void put(K key, V value) {
        if (!contains(key))
            N++;
        st[hash(key)].put(key, value);
    }

    public void delete(K key) {
        if (!contains(key))
            return;

        st[hash(key)].delete(key);
        N--;
    }

    public Iterable<K> keys() {
        ArrayList<K> keyList = new ArrayList<>();
        for (int i = 0; i < M; i++)
            for (K key : st[i].keys())
                keyList.add(key);

        return keyList;
    }

}
