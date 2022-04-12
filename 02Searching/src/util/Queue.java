/* Queue class; represents a first-in-first-out (FIFO) queue of generic keys
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package util;

import java.util.Iterator;

public class Queue<K> implements Iterable<K> {
    /* Beginning, and end of queue */
    private Node<K> first, last;
    /* Number of elements on queue */
    private int N;

    /* Node class */
    public static class Node<K> {
        private K key;
        private Node<K> next;
    }

    /* Queue() : Initializes an empty queue */
    public Queue() {
        first = null;
        last = null;
        N = 0;
    }

    /* isEmpty() : Returns true if this queue is empty */
    public boolean isEmpty() {
        return first == null;
    }

    /* size() : Returns the number of keys in this queue */
    public int size() {
        return N;
    }

    /* enqueue() : Adds the key this queue */
    public void enqueue(K key) {
        Node<K> tmp = last;
        last = new Node<K>();
        last.key = key;
        last.next = null;

        if (isEmpty())
            first = last;
        else
            tmp.next = last;

        N++;
    }

    /* dequeue() : Removes and returns the key on this queue that was least recently added */
    public K dequeue() {
        if (isEmpty())
            return null;
        K key = first.key;
        first = first.next;
        N--;

        return key;
    }


    /* iterator() : Returns an iterator that iterates over the items in this queue in FIFO order */
    @Override
    public Iterator<K> iterator() {
        return new LinkedIterator(first);
    }

    /* An iterator, doesn't implement remove() since it's optional */
    private class LinkedIterator implements Iterator<K> {
        private Node<K> current;

        public LinkedIterator(Node<K> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public K next() {
            K key = current.key;
            current = current.next;

            return key;
        }
    }
}
