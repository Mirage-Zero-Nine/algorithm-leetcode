package Solution.TwoPointers;

import Lib.ListNode.ListNode;

/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * @author BorisMirage
 * Time: 2019/06/20 13:56
 * Created with IntelliJ IDEA
 */

public class HasCycle_141 {
    /**
     * Two pointers. Faster one moves twice as slow pointer.
     * If the given list has a cycle, two pointers will eventually meet.
     * Otherwise, if fast reaches null, then there is no cycle in list.
     *
     * @param head head of list
     * @return if it has a cycle in it
     */
    public boolean hasCycle(ListNode head) {

        /* Corner case */
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != null) {

            if (slow.val == fast.val) {
                return true;
            }

            slow = slow.next;

            if (fast.next != null && fast.next.next != null) {      // reaches the end of list if no cycle found
                fast = fast.next.next;
            } else {
                return false;
            }
        }
        return false;
    }
}
