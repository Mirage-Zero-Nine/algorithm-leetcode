package library.tree.binarytree;

/**
 * Variant of tree node, add a pointer to its parent and next node in current level.
 *
 * @author BorisMirage
 * Time: 2019/04/28 17:34
 * Created with IntelliJ IDEA
 */

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;       // point to next node in same level
    public Node parent;

    /**
     * Constructor of tree node.
     */
    public Node() {
    }

    /**
     * Overload of constructor, with value, children, parent and next node in same depth.
     *
     * @param val    value in current node
     * @param left   left child of current node
     * @param right  right child of current node
     * @param next   next node of current node in same depth
     * @param parent parent node of current node
     */
    public Node(int val, Node left, Node right, Node next, Node parent) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}