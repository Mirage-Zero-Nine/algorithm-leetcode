package solution.linkedlist;

import library.listnode.ListNode;

/**
 * Given a singly linked list L: L0 -> L1 -> ... -> Ln-1 -> Ln.
 * Reorder it to: L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 *
 * @author BorisMirage
 * Time: 2019/06/29 14:34
 * Created with IntelliJ IDEA
 */

public class ReorderList_143 {
    /**
     * Reverse the second half of list, then insert every element into first half.
     *
     * @param head head node
     */
    public void reorderList(ListNode head) {

        /* Corner case */
        if (head == null) {
            return;
        }
        if (head.next == null || head.next.next == null) {
            return;
        }

        ListNode slow = head, fast = head;


        while (fast.next != null && fast.next.next != null) {       // find the middle of list
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode middle = slow;
        ListNode current = slow.next;

        /*
         * Reverse the second half of list in-place.
         * Each time, move pointer of current node into next node's next node.
         * This pointer will be the reference of next round.
         * Then move pointer of next node's next node to current node, as reverse operation.
         * Finally, move head node's next node to next node in this round. */
        while (current != null && current.next != null) {
            ListNode next = current.next;       // temporary store next node
            current.next = next.next;           // current node's next node is next of next node
            next.next = middle.next;            // next node's next now revert to middle's next node (reverse)
            middle.next = next;                 // middle node's next now move to next node, as previous node
        }

        slow = head;
        fast = middle.next;

        while (slow != middle) {        // insert each node in first half of list into second half
            middle.next = fast.next;
            fast.next = slow.next;
            slow.next = fast;
            slow = fast.next;
            fast = middle.next;
        }
    }
}
