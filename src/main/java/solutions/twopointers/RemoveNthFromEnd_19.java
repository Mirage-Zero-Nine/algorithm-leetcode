package solutions.twopointers;

import library.listnode.ListNode;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * @author BorisMirage
 * Time: 2018/06/12 17:33
 * Created with IntelliJ IDEA
 */

public class RemoveNthFromEnd_19 {
    /**
     * Removes the requested node using a fixed-size gap between two pointers.
     *
     * <p>After {@code fast} advances {@code n} nodes ahead of {@code slow}, moving both
     * pointers until {@code fast} reaches the tail leaves {@code slow} immediately before
     * the n-th node from the end. Bypassing {@code slow.next} removes that node; the dummy
     * predecessor also makes removing the original head the same operation as any other
     * removal. The input list's links are mutated, but no new list nodes are created apart
     * from the temporary dummy node. The method runs in {@code O(L)} time and uses
     * {@code O(1)} auxiliary space, where {@code L} is the number of input nodes.</p>
     *
     * @param head head of the non-empty list to modify
     * @param n    one-based position from the end of the node to remove
     * @return the head of the modified list, or the next node when the original head was removed
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // corner case
        if (head == null) {
            return null;
        }

        ListNode tmp = new ListNode(0);
        tmp.next = head;
        ListNode fast = tmp, slow = tmp;

        // Establish an n-node gap; this ensures slow becomes the target's predecessor.
        while (n-- > 0) {
            fast = fast.next;
        }

        // Preserve the gap while fast advances to the tail's last node.
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // The temporary predecessor makes this link change valid even when slow is tmp itself.
        slow.next = slow.next.next;
        return tmp.next;
    }
}
