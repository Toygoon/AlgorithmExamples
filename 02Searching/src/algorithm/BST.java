/* Binary Search Tree
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package algorithm;

import java.util.ArrayList;
import java.util.List;

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

    private int size(Node<K, V> x) {
        return (x != null) ? x.N : 0;
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
    protected void rebalanceDelete(Node<K, V> p, Node<K, V> deleted) {
        resetSize(p, -1);
    }

    private void resetSize(Node<K, V> x, int size) {
        for (; x != null; x = x.parent)
            x.N += size;
    }

    /* keys() : Returns an Iterable objects */
    public Iterable<K> keys() {
        if (root == null)
            return null;

        List<K> keyList = new ArrayList<>(size());
        inorder(root, keyList);

        return keyList;
    }

    /* inorder() : Add keys to the keyList using inorder traversal methods */
    private void inorder(Node<K,V> x, List<K> keyList) {
        if (x != null) {
            inorder(x.left, keyList);
            keyList.add(x.key);
            inorder(x.right, keyList);
        }
    }

    /* delete() : Removes the specified key and its associated value */
    public void delete(K key) {
        if (root == null)
            return;

        Node<K, V> x, y, p;
        x = treeSearch(key);

        // The key given isn't inserted in a tree
        if (!key.equals(x.key))
            return;

        // The node is root node, or the node has two children nodes
        if (x == root || isTwoNode(x)) {
            if (isLeaf(x)) {
                // The case; root is leaf node
                root = null;

                return;
            } else if (!isTwoNode(x)) {
                // The case; for root node
                // Make a chijld node to root node
                root = (x.right == null) ? x.left : x.right;
                root.parent = null;

                return;
            } else {
                // The case; there are two children nodes (including root node)
                // inorder successor, find the node containing minimum value
                y = min(x.right);
                // copy y to x
                x.key = y.key;
                x.value = y.value;
                p = y.parent;

                // Make y's child to p's child (remove y)
                relink(p, y.right, y == p.left);
                // Decrease the size for parents'
                rebalanceDelete(p, y);
            }
        } else {
            // The case; less than one child, not root
            p = x.parent;

            if (x.right == null)
                relink(p, x.left, x == p.left);
            else if (x.left == null)
                relink(p, x.right, x == p.left);
            rebalanceDelete(p, x);
        }
    }

    protected void relink(Node<K,V> parent, Node<K,V> child, boolean makeLeft) {
        // Make a child is parents'
        if (child != null)
            child.parent = parent;

        if (makeLeft)
            parent.left = child;
        else
            parent.right = child;
    }

    protected Node<K,V> min(Node<K,V> x) {
        while (x.left != null)
            x = x.left;

        return x;
    }

    /* min() : Returns minimum key */
    public K min() {
        if (root == null)
            return null;

        Node<K, V> x = root;

        // Very left node
        while (x.left != null)
            x = x.left;

        return x.key;
    }

    /* max() : Returns minimum key */
    public K max() {
        if (root == null)
            return null;

        Node<K, V> x = root;

        // Very right node
        while (x.right != null)
            x = x.right;

        return x.key;
    }

    protected boolean isLeaf(Node<K,V> x) {
        return x.left == null && x.right == null;
    }

    protected boolean isTwoNode(Node<K,V> x) {
        return x.left != null && x.right != null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /* floor() : Returns the largest key less than or equal to key */
    public K floor(K key) {
        if (root == null || key == null)
            return null;

        Node<K, V> x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node<K, V> floor(Node<K, V> x, K key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        // Same node with key
        if (cmp == 0)
            return x;
        // Go to the left if bigger than given key
        if (cmp < 0)
            return floor(x.left, key);
        // Go to the right if bigger than given key
        Node<K, V> t = floor(x.right, key);


        if (t != null)
            // The case; key is located to the right
            return t;

        // The case; key isn't located to the right
        return x;
    }

    /* rank() : Return the number of keys strictly less than key */
    public int rank(K key) {
        if (root == null || key == null)
            return 0;

        Node<K, V> x = root;
        int num = 0, cmp;

        // Compare the keys from the root, add to sum of keys less than given key
        while (x != null) {
            cmp = key.compareTo(x.key);

            if (cmp < 0)
                x = x.left;
            else if(cmp > 0) {
                // Found the key less than given key
                // Get a total sum of left subtree nodes
                num += 1 + size(x.left);
                // Also check the right subtree nodes
                x = x.right;
            } else {
                // Only get a sum of left subtrees
                num += size(x.left);
                break;
            }
        }

        return num;
    }

    /* select() : Return the key of a given key */
    public K select(int rank) {
        if (root == null || rank < 0 || rank >= size())
            return null;

        Node<K, V> x = root;

        while (true) {
            int t = size(x.left);

            if (rank < t) {
                x = x.left;
            } else if (rank > t) {
                rank = rank - t - 1;
                x = x.right;
            } else {
                return x.key;
            }
        }
    }
}
