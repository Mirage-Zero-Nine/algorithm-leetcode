package library.listnode;

/**
 * ListNode contains one single value. "next" works as link function.
 * However, it DOES NOT save node, just virtually "link" current node to next node.
 */
public class ListNode {

    public int val;             // value of current node
    public ListNode next;       // next node in linked list, or null if current node is last node

    /**
     * Constructor of linked list node.
     *
     * @param x value that related to this current ListNode
     */
    public ListNode(int x) {
        val = x;
    }
}
