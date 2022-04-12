/* Node Structure used for such as Sequential Search ST, or Binary Search ST
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package template;

/**
 * Single Node
 * A pair of key and value
 *
 * @param <K> : Key
 * @param <V> : Value
 */

public class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> next;

    public Node(K key, V value, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
