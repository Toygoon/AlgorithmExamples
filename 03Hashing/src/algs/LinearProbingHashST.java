/* Linear Probing Hash Symbol Tables
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package algs;

public class LinearProbingHashST<K, V> {
    // Always M > N
    private int N, M;
    private K[] keys;
    private V[] vals;

    public LinearProbingHashST() {
        this(31);
    }

    public LinearProbingHashST(int M) {
        this.M = M;
        keys = (K[]) new Object[M];
        vals = (V[]) new Object[M];
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) & M;
    }

    private V get(K key) {
        // Traversal of the table
        for(int i=hash(key); keys[i] != null; i = (i + 1) & M)
            // Search hit
            if (keys[i].equals(key))
                return vals[i];

        // Search miss
        return null;
    }

    public void put(K key, V value) {
        if (N >= M / 2)
            resize(2 * M + 1);
        int i;
        for(i=hash(key); keys[i] != null; i = (i + 1) & M) {
            if (keys[i].equals(key)) {
                vals[i] = value;
                return;
            }
        }

        // Add a new tuple
        keys[i] = key;
        vals[i] = value;
        N++;
    }

    private void resize(int i) {
    }
}
