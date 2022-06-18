package solution.linkedlist;

import library.listnode.ListNode;

/**
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * @author BorisMirage
 * Time: 2019/12/28 14:21
 * Created with IntelliJ IDEA
 */

public class PlusOne_369 {
    /**
     * Find the rightmost 9 in given list. Mark this node (and all consecutive 9s after this 9) to 0.
     * The previous node of rightmost 9 should add one.
     * One corner case: if all digits are 9, the there will be one more digit in result list and will always be 1.
     * Therefore, use a dummy head and set its value to 0. Then, find last 9 starts at dummy head.
     * If all digits are 9, then the left node of rightmost is the dummy node. Add one and mark all others to 0.
     *
     * @param head head of list
     * @return result of integer plus one in linked list format
     */
    public ListNode plusOne(ListNode head) {

        /* Corner case */
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode lastNotNine = dummy;       // start at dummy to avoid all digits are 9

        while (head != null) {
            if (head.val != 9) {
                lastNotNine = head;
            }
            head = head.next;
        }

        lastNotNine.val++;                  // if all nodes are 9, then it will stay at dummy
        lastNotNine = lastNotNine.next;
        while (lastNotNine != null) {
            lastNotNine.val = 0;
            lastNotNine = lastNotNine.next;
        }

        return dummy.next.val == 0 ? dummy : dummy.next;
    }
}
