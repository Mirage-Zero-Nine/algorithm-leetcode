package solution.linkedlist;

import library.listnode.ListNode;

/**
 * Reverse a singly linked list.
 *
 * @author BorisMirage
 * Time: 2019/07/13 10:19
 * Created with IntelliJ IDEA
 */

public class ReverseList_206 {
    /**
     * Iterative point current node to previous node, then move to next node.
     * Use a temp to save next start node during the iteration.
     *
     * @param head head of list
     * @return reversed list
     */
    public ListNode reverseList(ListNode head) {

        /* Corner case */
        if (head == null || head.next == null) {
            return head;
        }

        ListNode previous = null, current = head, next;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
    }

    /**
     * Recursion to solve the problem.
     *
     * @param head head node
     * @return new head of reversed list
     */
    public ListNode recursion(ListNode head) {

        /* Corner case */
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        ListNode previous = reverseList(next);
        next.next = head;
        head.next = null;

        return previous;
    }
}
