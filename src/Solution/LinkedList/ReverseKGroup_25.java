package Solution.LinkedList;

import Lib.ListNode.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * Note:
 * 1. Only constant extra memory is allowed.
 * 2. You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 * @author BorisMirage
 * Time: 2018/06/18 21:09
 * Created with IntelliJ IDEA
 */

public class ReverseKGroup_25 {
    /**
     * Use dummy -> 1 -> 2 -> 3 -> 4, k = 3 as example. The result is dummy -> 3 -> 2 -> 1 -> 4.
     * begin node is 1, end node is 3. Requires a previous node to link to.
     * Link 2 to previous, which is 1. And link 3 to 2.
     * Then link dummy to 3, link 1 to 4. These nodes (dummy, 1) should remain unchanged during reverse process.
     *
     * @param head head ListNode
     * @param k    reverse the nodes of a linked list k at a time
     * @return reversed head ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        /* Corner case */
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode begin = dummy;
        int i = 0;
        while (head != null) {
            while (++i % k != 0) {
                head = head.next;
            }
            begin = reverse(begin, head.next);
            head = begin.next;
        }

        return dummy.next;
    }

    /**
     * Reverse node in from dummy.next to end (end is not included).
     * Note that return value is the next reverse start dummy head node, instead of next start node.
     *
     * @param dummy dummy head node (not included in reverse)
     * @param end   end node (not included in reverse)
     * @return next reverse start node
     */
    public ListNode reverse(ListNode dummy, ListNode end) {
        ListNode current = dummy.next;
        ListNode first = current;     // first: first node to be reversed
        ListNode previous = dummy;
        ListNode next;

        while (current != end) {
            next = current.next;        // save next current position when current node reverse is completed
            current.next = previous;    // reverse: link current node to previous node
            previous = current;         // current reverse is done, move previous node to current node
            current = next;             // move current node to next node
        }

        /*
         * After iteration, current node is at end now.
         * Link beginning of list to last node reversed and link first reversed node to end of list.
         * Return last reversed node, as it will be the next reverse's beginning. (dummy head) */
        dummy.next = previous;      // link beginning node to last reversed node's next node
        first.next = end;           // link start to next start as next reverse's dummy
        return first;
    }

    /**
     * Reverse list in recursion.
     *
     * @param head head ListNode
     * @param k    reverse the nodes of a linked list k at a time
     * @return reversed head ListNode
     */
    public ListNode reverseKGroupRecursion(ListNode head, int k) {

        /* Corner case */
        if (k <= 1 || head == null) {
            return head;
        }

        ListNode current = head;
        int count = 0;

        while (current != null && count != k) {     // find current reverse part
            current = current.next;
            count++;
        }

        if (count == k) {       // if current part is not remaining part of linked list

            current = reverseKGroupRecursion(current, k);        // each recursion returns next recursion's start node

            /* Reverse example:
             * 1 -> 2 -> 3 -> 4 -> 5 ->
             * <- 1 <- 2 <- 3 <- 4 <- 5
             * 5 -> 4 -> 3 -> 2 -> 1 -> */
            while (count > 0) {
                ListNode cache = head.next;     // store next node that will be relinked
                head.next = current;        // link origin next pointer to previous node for reverse
                current = head;
                head = cache;       // set next reverse node
                count--;
            }
            head = current;
        }

        return head;
    }
}
