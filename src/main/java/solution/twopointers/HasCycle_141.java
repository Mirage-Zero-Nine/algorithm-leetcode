package solution.twopointers;

import library.listnode.ListNode;

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

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }
}
