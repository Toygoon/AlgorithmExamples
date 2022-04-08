package algorithm;

/* Binary Search Tree */
public class BST<K extends Comparable<K>, V> {
    // Root of BST
    private Node<K, V> root;

    public static class Node<K, V> {
        // Key-Value pairs of this node
        K key;
        V value;

        // N : child node + 1 (ordered calculations)
        int N;
        // aux, parent : used for AVL tress or RB trees
        int aux;
        Node<K, V> left, right, parent;

        public Node(K key, V val) {
            this.key = key;
            this.value = val;
            this.N = 1;
        }

        public int getAux() {
            return aux;
        }

        public void setAux(int aux) {
            this.aux = aux;
        }
    }

    /* size() : Returns the number of key-value pairs */
    public int size() {
        return (root != null) ? root.N : 0;
    }

    /* get() : Returns the value of the key; if the key not exists, returns null */
    public V get(K key) {
        if (root == null)
            return null;
        // Find the proper location of the key
        Node<K, V> x = treeSearch(key);

        // The case that x has exact key
        if (key.equals(x.key))
            return x.value;

        // The case that x doesn't exist
        return null;
    }

    /* treeSearch() : Returns the node of the key, or the last node of traversal */
    protected Node<K, V> treeSearch(K key) {
        // All calculation for BST is started from the root
        Node<K, V> x = root;

        // Comparisons : The level of the nodes (The level of the root : 1)
        while (true) {
            int cmp = key.compareTo(x.key);

            // Exit the traversal if the key is founded
            if (cmp == 0)
                return x;
                // If the key is smaller than x.key, go to the left
            else if (cmp < 0) {
                // Exit if the key doesn't exist
                if (x.left == null)
                    return x;
                else
                    x = x.left;
                // If the key is bigger than x.key, go to the right
            } else {
                // Exit if the key doesn't exist
                if (x.right == null)
                    return x;
                else
                    x = x.right;
            }
        }
    }

    /* put() : Reset the node if the key exists after the search, or create the node if the key not exists */
    public void put(K key, V val) {
        if (root == null) {
            root = new Node<>(key, val);
            return;
        }

        Node<K, V> x = treeSearch(key);
        int cmp = key.compareTo(x.key);

        // Key is already existing, so reset the value
        if (cmp == 0)
            x.value = val;
        else {
            // New key is being created
            Node<K, V> newNode = new Node<>(key, val);

            // Insert the node into the proper location
            if (cmp < 0)
                x.left = newNode;
            else
                x.right = newNode;

            newNode.parent = x;
            // Make the tree balance
            rebalanceInsert(newNode);
        }
    }

    /* rebalanceInsert() : Increase the size for all parent nodes of the new node */
    protected void rebalanceInsert(Node<K, V> x) {
        resetSize(x.parent, 1);
    }

    /* rebalanceDelete() : Decrease the size for all parent nodes of the new node */
    protected void rebalanceDelete(Node<K, V> x) {
        resetSize(x.parent, -1);
    }

    private void resetSize(Node<K, V> x, int size) {
        for (; x != null; x = x.parent)
            x.N += size;
    }

    /* keys() : Returns an Iterable objects */
    public Iterable<K> keys()
}
