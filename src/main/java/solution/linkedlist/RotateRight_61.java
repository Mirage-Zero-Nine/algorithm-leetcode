package solution.linkedlist;

import library.listnode.ListNode;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * @author BorisMirage
 * Time: 2018/08/05 14:37
 * Created with IntelliJ IDEA
 */

public class RotateRight_61 {
    /**
     * Find i - k % ith node and do the rotation.
     *
     * @param head head ListNode
     * @param k    k times rotation
     * @return new head ListNode
     */
    public ListNode rotateRight(ListNode head, int k) {

        /* Corner case */
        if (head == null || head.next == null || k < 1) {
            return head;
        }

        int length = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode a = dummy;
        ListNode b = dummy;

        while (a.next != null) {
            length++;
            a = a.next;
        }

        for (int j = length - k % length; j > 0; j--) {
            b = b.next;
        }
        a.next = dummy.next;        // point end of list to head of list
        dummy.next = b.next;        // reset head to rotate start
        b.next = null;      // set rotate end to list end (null)

        return dummy.next;
    }
}
