package Solution.LinkedList;

import Lib.ListNode.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * @author BorisMirage
 * Time: 2019/10/22 12:07
 * Created with IntelliJ IDEA
 */

public class RemoveElements_203 {
    /**
     * Link previous node of node to be removed to this remove node's next.
     *
     * @param head head of list
     * @param val  remove value
     * @return removed list head
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode current = head, previous = dummy;


        while (current != null) {
            if (current.val == val) {
                previous.next = current.next;
            } else {
                previous = current;
            }
            current = current.next;
        }

        return dummy.next;
    }
}
