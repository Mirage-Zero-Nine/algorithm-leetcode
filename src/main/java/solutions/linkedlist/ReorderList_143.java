package solutions.linkedlist;

import library.listnode.ListNode;

/**
 * Given a singly linked list L: L0 -> L1 -> ... -> Ln-1 -> Ln.
 * Reorder it to: L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 *
 * @author BorisMirage
 * Time: 2019/06/29 14:34
 * Created with IntelliJ IDEA
 */
public class ReorderList_143 {
    /**
     * Reorders the list using the standard split, reverse, and merge algorithm.
     *
     * <p>The midpoint loop maintains the invariant that {@code n1} advances
     * one node for every two nodes advanced by {@code n2}. Consequently, when
     * {@code n2} cannot advance two more nodes, {@code n1} is the last node of
     * the first half: the left middle for an even-length list and the center
     * node for an odd-length list.</p>
     *
     * <p>The explicit {@code n1.next = null} split is important. It terminates
     * the first half before any reversal, so the old forward link cannot later
     * point back into the reversed half and create a cycle. During reversal,
     * {@code previous} is the head of the already-reversed prefix and
     * {@code secondHalf} is the first node not yet reversed. During merging,
     * both next pointers are saved before either link is changed; this keeps
     * the remainder of both halves reachable.</p>
     *
     * <p>The list is mutated in place in {@code O(n)} time and uses
     * {@code O(1)} auxiliary space.</p>
     *
     * @param head the first node of an acyclic singly linked list
     */
    public void reorderList(ListNode head) {

        // corner cases
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode n1 = head, n2 = head;

        // n1 moves one step while n2 moves two. At termination, n1 ends the
        // first half, which gives the desired split for both odd and even sizes.
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        // Detach the two halves before reversing so no old link can form a cycle.
        ListNode secondHalf = n1.next;
        n1.next = null;

        // Invariant: previous is the reversed prefix; secondHalf is the
        // unprocessed suffix. Save secondHalf.next before redirecting its link.
        ListNode previous = null;
        while (secondHalf != null) {
            ListNode next = secondHalf.next;
            secondHalf.next = previous;
            previous = secondHalf;
            secondHalf = next;
        }

        // Interleave one node from each half. Save both successors first;
        // otherwise changing either next link would lose the remaining nodes.
        n1 = head;
        n2 = previous;
        while (n2 != null) {
            ListNode n1Next = n1.next;
            ListNode n2Next = n2.next;

            n1.next = n2;
            n2.next = n1Next;

            n1 = n1Next;
            n2 = n2Next;
        }
    }

    /**
     * Reorders the list using head-insertion reversal of the second half.
     *
     * <p>After locating {@code middle}, the reversal keeps {@code current}
     * fixed. It is the tail of the reversed prefix, so the node to reverse is
     * always {@code current.next}. Removing that node changes
     * {@code current.next} to the next unprocessed node; this is why
     * {@code current} does not itself advance. The removed node is then
     * inserted immediately after {@code middle}, which grows the reversed
     * prefix from its head.</p>
     *
     * <p>During the merge, {@code middle.next} is used as the boundary of the
     * remaining reversed second half. Before inserting {@code fast}, the code
     * advances that boundary to {@code fast.next}. The inserted node is linked
     * after {@code slow}, and both pointers advance to their next portions.
     * The loop stops when {@code slow == middle}: for an odd-length list all
     * suffix nodes have been consumed and {@code middle.next} is null; for an
     * even-length list the final suffix node remains after {@code middle} and
     * is already the correct tail. Thus the compact approach also terminates
     * without a cycle.</p>
     *
     * <p>This approach also mutates the list in place in {@code O(n)} time and
     * uses {@code O(1)} auxiliary space.</p>
     *
     * @param head the first node of an acyclic singly linked list
     */
    public void reorderListWithHeadInsertion(ListNode head) {

        // corner cases
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode middle = head, fast = head;

        // As in the standard approach, middle becomes the end of the first
        // half while fast moves twice as quickly through the original list.
        while (fast.next != null && fast.next.next != null) {
            middle = middle.next;
            fast = fast.next.next;
        }

        // Head-insertion reversal of the suffix. current is deliberately fixed:
        // it is the tail of the reversed prefix, and current.next is therefore
        // always the next node to extract from the unprocessed suffix.
        ListNode current = middle.next;
        while (current != null && current.next != null) {
            ListNode next = current.next;

            // Skip next in the unprocessed chain. Although current does not
            // move, current.next now points farther forward, so the next loop
            // iteration extracts the following node.
            current.next = next.next;

            // Put the extracted node at the front of the reversed prefix.
            next.next = middle.next;
            middle.next = next;
        }

        // Insert each remaining reversed-half node after one first-half node.
        // middle.next is the remaining-second-half boundary and must advance
        // before fast is linked after slow.
        ListNode slow = head;
        fast = middle.next;
        while (slow != middle && fast != null) {
            ListNode firstNext = slow.next;
            ListNode secondNext = fast.next;

            // Remove fast from the remaining suffix boundary, then insert it
            // after slow while preserving the rest of both chains.
            middle.next = secondNext;
            slow.next = fast;
            fast.next = firstNext;

            slow = firstNext;
            fast = middle.next;
        }
    }
}
