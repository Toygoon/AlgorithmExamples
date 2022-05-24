package algorithm;

public class RedBlackBST<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // Root of the BST
    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(K key, V value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    private boolean isRed(Node x) {
        if (x == null)
            return false;

        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public V get(K key) {
        if (key == null)
            return null;

        return get(root, key);
    }

    private V get(Node x, K key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.value;
        }

        return null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public void put(K key, V value) {
        if (key == null)
            return;

        if (value == null) {
            delete(key);
            return;
        }

        root = put(root, key, value);
        root.color = BLACK;
    }

    private void delete(K key) {
        if (key == null || !contains(key))
            return;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty())
            root.color = BLACK;
    }

    private Node delete(Node h, K key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
        }
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }

        return h;
    }

    private Node put(Node h, K key, V value) {
        if (h == null)
            return new Node(key, value, RED, 1);

        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, value);
        else if (cmp > 0)
            h.right = put(h.right, key, value);
        else
            h.value = value;

        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;

        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;

        return x;
    }
}

