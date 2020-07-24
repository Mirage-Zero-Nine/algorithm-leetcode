package Solution.LinkedList;

import Lib.ListNode.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * @author BorisMirage
 * Time: 2019/10/10 15:54
 * Created with IntelliJ IDEA
 */

public class SortList_148 {
    /**
     * Bottom-up merge sort (without recursion).
     * The normal merge sort with recursion is to split list into single element and combine them together.
     * Bottom-up merge sort complete this process by iteration.
     * Each loop takes length from 1 to 2^n, until 2^n is larger than length.
     * During each loop with length of 2^n, sort the sub list with that length.
     * In this way to complete the merge sort from bottom to top.
     *
     * @param head head node of list
     * @return sorted list
     */
    public ListNode sortList(ListNode head) {

        /* Corner case */
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1), previous, current, tmp;
        dummy.next = head;

        int length = 0;

        while (head != null) {      // count list length for later merge
            head = head.next;
            length++;
        }

        for (int i = 1; i < length; i <<= 1) {      // each time, merge sort list with length of 2^i from start
            previous = dummy;
            current = dummy.next;

            while (current != null) {
                tmp = previous;
                ListNode start = current;               // start point
                ListNode sub = split(start, i);         // split current and forwarding i nodes to sort
                current = split(sub, i);                // from current + i to current + 2i nodes
                previous = merge(start, sub, tmp);      // merge two lists and sort
            }
        }

        return dummy.next;
    }

    /**
     * Split list with given start point and length
     *
     * @param start start node
     * @param n     length of list to be cut
     * @return right part start node of split list
     */
    private ListNode split(ListNode start, int n) {
        if (start == null) {
            return null;
        }

        for (int i = 1; start.next != null && i < n; i++) {      // length starts at 1 (given start node itself)
            start = start.next;
        }

        ListNode right = start.next;
        start.next = null;

        return right;
    }

    /**
     * Merge two list with sorting.
     *
     * @param n1       first list
     * @param n2       second list
     * @param previous previous node to combine the result
     * @return sorted list
     */
    private ListNode merge(ListNode n1, ListNode n2, ListNode previous) {
        ListNode dummy = previous;      // dummy as moving pointer during merging

        while (n1 != null && n2 != null) {
            if (n1.val > n2.val) {
                dummy.next = n2;
                n2 = n2.next;
            } else {
                dummy.next = n1;
                n1 = n1.next;
            }

            dummy = dummy.next;
        }

        dummy.next = (n1 != null) ? n1 : n2;

        while (dummy.next != null) {
            dummy = dummy.next;
        }

        return previous;
    }
}
