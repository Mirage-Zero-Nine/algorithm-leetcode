package solutions.linkedlist;

import library.listnode.ListNode;

/**
 * Reverse a linked list from position m to n in one-pass.
 *
 * @author BorisMirage
 * Time: 2019/06/29 13:58
 * Created with IntelliJ IDEA
 */

public class ReverseBetween_92 {
    /**
     * Reverse a list in certain range. This is same as reverse list in k group.
     * Traverse list to start dummy head, and reverse list until reaches the end.
     *
     * @param head head node
     * @param m    start at mth (index m - 1)
     * @param n    end at nth (end index n - 1)
     * @return reversed list
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {

        /* Corner case */
        if (head == null || n == m) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode begin = dummy;
        for (int i = 0; i < m - 1; i++) {
            begin = begin.next;
        }

        ListNode current = begin.next;
        ListNode next, first = current;
        ListNode previous = current;
        while (m < n + 1) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            m++;
        }

        begin.next = previous;
        first.next = current;

        return dummy.next;
    }

}
