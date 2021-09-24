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
     * The first time they met each other is the start of the cycle.
     *
     * @param head head node of list
     * @return the node where the cycle begins, return null if there is no cycle
     */
    public ListNode detectCycle(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }

        ListNode node = findCycle(head);

        if (node == null) {
            return null;
        }

        return findCycleStart(head, node);
    }

    /**
     * Check if given list is a cycle.
     *
     * @param head head of list
     * @return if list is a cycle, return the node where two pointers meet; otherwise, return null
     */
    private ListNode findCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return fast;
            }
        }
        return null;
    }

    /**
     * Find the start point of list with cycle.
     *
     * @param head head of list
     * @param node node where 2 pointers meet
     * @return start node of cycle
     */
    private ListNode findCycleStart(ListNode head, ListNode node) {
        ListNode dummyHead = head;

        while (dummyHead != node) {
            dummyHead = dummyHead.next;
            node = node.next;
        }

        return dummyHead;
    }
}
