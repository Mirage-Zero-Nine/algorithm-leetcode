package library.randomnode;

/**
 * NodeCopy class is just a clone of Node class with the same attributes and constructors.
 *
 * @author BorisMirage
 * Time: 2020/06/19 17:17
 * Created with IntelliJ IDEA
 */

public class NodeCopy {
    public int val;
    public NodeCopy left;
    public NodeCopy right;
    public NodeCopy random;

    /**
     * Constructor.
     */
    public NodeCopy() {
    }

    /**
     * Constructor with node value.
     *
     * @param val value in node
     */
    public NodeCopy(int val) {
        this.val = val;
    }

    /**
     * Node constructor used in binary tree with random pointer.
     *
     * @param val    value in node
     * @param left   left child node
     * @param right  right child node
     * @param random random node
     */
    public NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
}
