package library.randomnode;

/**
 * Node with random pointer, point to a random node in list.
 *
 * @author BorisMirage
 * Time: 2019/08/27 18:54
 * Created with IntelliJ IDEA
 */

public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node left;          // part of random binary tree node
    public Node right;         // part of random binary tree node

    /**
     * Constructor of node.
     */
    public Node() {
    }

    /**
     * Constructor of node.
     *
     * @param val value in node
     */
    public Node(int val) {
        this.val = val;
    }

    /**
     * Overload of constructor, create a new node with value, next node, and a random linked node.
     *
     * @param val    value in node
     * @param next   next node
     * @param random next random node
     */
    public Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }

    /**
     * Node constructor used in binary tree with random pointer.
     *
     * @param val    value in node
     * @param left   left child node
     * @param right  right child node
     * @param random random node
     */
    public Node(int val, Node left, Node right, Node random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
}
