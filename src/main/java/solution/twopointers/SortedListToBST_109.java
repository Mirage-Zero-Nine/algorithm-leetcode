package solution.twopointers;

import library.listnode.ListNode;
import library.tree.binarytree.TreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * Height-balanced binary tree is a binary tree that the depth of the two subtrees of node never differ by more than 1.
 *
 * @author BorisMirage
 * Time: 2019/06/11 11:01
 * Created with IntelliJ IDEA
 */

public class SortedListToBST_109 {
    /**
     * Linked list can not directly get item by index.
     * Therefore, use two pointers to traverse the linked list
     * When faster one reaches the end of list, slow one will be at root (middle of linked list).
     *
     * @param head head node
     * @return root of height balanced BST
     */
    public TreeNode sortedListToBST(ListNode head) {

        /* Corner case */
        if (head == null) {
            return null;
        }

        return builder(head, null);
    }

    /**
     * Use two pointers to find root node.
     * Fast node moves two time faster than slow node.
     * In this way, if fast node reaches the end, slow node will be at the middle of list, which is the root.
     *
     * @param head head node
     * @param tail end node
     * @return root of height balanced BST
     */
    public TreeNode builder(ListNode head, ListNode tail) {

        if (head == tail) {
            return null;
        }

        ListNode fast = head, slow = head;

        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }

        TreeNode root = new TreeNode(slow.val);

        root.left = builder(head, slow);
        root.right = builder(slow.next, tail);

        return root;
    }
}
