/* AVL Tree Symbol Table
 *
 * @author Lim Jung Min,
 * Department of Computer Engineering, Yeungnam University.
 */

package algorithm;

public class AVLTreeST<K extends Comparable<K>, V> extends BST<K, V>{
    // The root node
    private Node<K, V> root;

    /* relink() : Make a child to parents', and determine the location with makeLeft parameter */
    protected void relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) {
        // Make child to parents'
        if (child != null)
            child.parent = parent;

        // Determine the location
        if (makeLeft)
            parent.left = child;
        else
            parent.right = child;
    }

    /* rotate() : LL, and RR rotations */
    protected void rotate(Node<K, V> x) {
        Node<K, V> y = x.parent, z = y.parent;

        // If y has no parents
        if (z == null) {
            // Make x as root
            root = x;
            x.parent = null;
        } else {
            // Make x to z's child
            relink(z, x, y == z.left);
        }

        if (x == y.left) {
            // LL
            relink(y, x.right, true);
            relink(x, y, false);
        } else {
            // RR
            relink(y, x.left, false);
            relink(x, y, true);
        }
    }

    /* restructure() : Make a node structure again */
    protected Node<K, V> restructure(Node<K, V> x) {
        Node<K, V> y = x.parent, z = y.parent;

        if ((x == y.left) == (y == z.left)) {
            // LL or RR, y is the middle
            rotate(y);

            return y;
        }

        // x is the middle, change (LR or RL) to (LL or RR) first
        rotate(x);
        rotate(x);

        return x;
    }

    private int height(Node<K, V> x) {
        return (x == null) ? 0 : x.getAux();
    }

    private void setHeight(Node<K, V> x, int height) {
        x.setAux(height);
    }

    private void recomputeHeight(Node<K, V> x) {
        setHeight(x, 1 + Math.max(height(x.left), height(x.right)));
    }

    private boolean isBalanced(Node<K, V> x) {
        return Math.abs(height(x.left) - height(x.right)) <= 1;
    }

    private Node<K, V> tallerChild(Node<K, V> x) {
        if (height(x.left) > height(x.right))
            return x.left;
        if (height(x.left) < height(x.right))
            return x.right;
        if (x == root)
            return x.left;
        if (x == x.parent.left)
            return x.left;
        return x.right;
    }

    private void rebalance(Node<K, V> x) {
        do {
            if (!isBalanced(x)) {
                x = restructure(tallerChild(tallerChild(x)));
                recomputeHeight(x.left);
                recomputeHeight(x.right);

                for (Node<K, V> p = x; p != null; p = p.parent)
                    recomputeHeight(p);
            }

            x = x.parent;
        } while (x != null);
    }

    protected void rebalanceInsert(Node<K, V> x) {
        setHeight(x, 1);
        for (Node<K, V> p = x.parent; p != null; p = p.parent) {
            x.N += 1;
            recomputeHeight(p);
        }
        rebalance(x.parent);
    }

    protected void rebalanceDelete(Node<K, V> p, Node<K, V> deleted) {
        for (Node<K, V> x = p; x != null; x = x.parent) {
            x.N -= 1;
            recomputeHeight(x);
        }

        if (p != null)
            rebalance(p);
    }

    public int depth() {
        return height(root);
    }

    public String toString() {
         StringBuilder str = new StringBuilder();
         print(root, str);

         return str.toString();
    }

    private void print(Node<K,V> x, StringBuilder str) {
        if (x == null)
            return;
        str.append(x.key + "" + height(x) + "(");
        print(x.left, str);
        str.append(",");
        print(x.right, str);
        str.append(")");
    }
}
