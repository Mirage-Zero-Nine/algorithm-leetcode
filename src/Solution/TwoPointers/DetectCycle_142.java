package Solution.TwoPointers;

import Lib.ListNode.ListNode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Note:
 * 1. Do not modify the linked list.
 *
 * @author BorisMirage
 * Time: 2019/06/20 14:11
 * Created with IntelliJ IDEA
 */

public class DetectCycle_142 {
    /**
     * Two pointers. Fast pointer moves two nodes one time and slow pointer moves one node each time.
     * Suppose the first meet at step k, the length of the Cycle is r, then, 2k - k = n * r => k = n * r
     * Assume distance between the start node of list and the start node of cycle is s.
     * The distance between the start of list and the first meeting node is k.
     * The distance between the start node of cycle and the first meeting node is m
     * => s = k - m
     * => s = nr - m = (n - 1) * r + (r - m)
     * Take n = 1, using one pointer start at the beginning of list, another pointer start at meeting node.
     * All of them wake one step at a time.
     * The first time they meeting each other is the start of the cycle.
     *
     * @param head head node of list
     * @return the node where the cycle begins, return null if there is no cycle
     */
    public ListNode detectCycle(ListNode head) {

        /* Corner case */
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {     // found a cycle
                while (head != slow) {
                    head = head.next;       // move head (as a pointer) until slow and start meets
                    slow = slow.next;       // actually I think this is not safe (memory)
                }
                return head;        // two nodes will meet at cycle start
            }
        }
        return null;
    }
}
