package Solution.LinkedList;

import Lib.ListNode.ListNode;

/**
 * Sort a linked list using insertion sort.
 * Algorithm of Insertion Sort:
 * 1. Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * 2. At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * 3. It repeats until no input elements remain.
 *
 * @author BorisMirage
 * Time: 2019/07/06 12:50
 * Created with IntelliJ IDEA
 */

public class InsertionSortList_147 {
    /**
     * Implement the insertion sort.
     * Use two list nodes works as the smallest (head) and largest (tail) in list.
     * Then traverse the list. If element is smaller than head or larger than tail, insert the node.
     * Otherwise, use a temporary pointer and the pointer will pass all elements smaller than current element.
     * Finally, insert the node into correct position.
     *
     * @param head head of list
     * @return sorted list
     */
    public ListNode insertionSortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode sortedHead = head, sortedTail = head;      // initially, sorted list only contains first element
        head = head.next;
        sortedHead.next = null;

        while (head != null) {
            ListNode temp = head;
            head = head.next;       // move head to its next
            temp.next = null;       // new head

            if (temp.val <= sortedHead.val) {       // next val is less than the head, insert it in the front
                temp.next = sortedHead;
                sortedHead = temp;
            } else if (temp.val >= sortedTail.val) {      // next val is greater than the tail, insert it at the end
                sortedTail.next = temp;
                sortedTail = sortedTail.next;
            } else {        // new val is somewhere in the middle
                ListNode current = sortedHead;
                while (current.next.val < temp.val) {
                    current = current.next;
                }
                temp.next = current.next;
                current.next = temp;
            }
        }
        return sortedHead;
    }
}
