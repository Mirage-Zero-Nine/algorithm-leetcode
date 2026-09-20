package solutions.linkedlist;

import library.randomnode.Node;

/**
 * A linked list is given such that each node contains an additional random pointer.
 * Return a deep copy of the list.
 *
 * @author BorisMirage
 * Time: 2019/08/27 18:55
 * Created with IntelliJ IDEA
 */

public class CopyRandomList_138 {
    /**
     * Returns a deep copy of the list beginning at {@code head}.
     *
     * <p>The algorithm makes three linear passes. First, it inserts a newly allocated copy
     * after each original node. Second, because each copied node follows its original, it
     * assigns {@code copy.random} in constant time: a non-null original random target is
     * followed by {@code .next} to reach that target's copy. Third, it detaches alternating
     * nodes into the copied list and the original list. During that final pass, each original
     * node's {@code next} pointer is restored before advancing.</p>
     *
     * <p>Correctness follows from the interleaving invariant after the first pass: for every
     * original node {@code x}, {@code x.next} is exactly its copy, and the original next
     * node is {@code x.next.next}. Therefore the second pass gives every copy the random
     * edge corresponding to its original, including the null case. The final pass preserves
     * those copied edges while restoring every original next edge, so the result is a deep
     * structural copy and the input list is left unchanged.</p>
     *
     * <p>For a list of {@code n} nodes, the running time is O(n) and the auxiliary space is
     * O(1), excluding the O(n) nodes required for the returned copy. The input list is
     * temporarily modified while the method runs but is restored before returning.</p>
     *
     * @param head head of the original list, or {@code null}
     * @return the independent deep copy, or {@code null} when {@code head} is null
     */
    public Node copyRandomList(Node head) {
        // corner case
        if (head == null) {
            return null;
        }

        Node h1 = head, n;

        // Invariant: every processed original is immediately followed by its copy;
        // the saved next node lets the traversal continue through original nodes only.
        while (h1 != null) {
            n = h1.next;
            h1.next = new Node(h1.val);
            h1.next.next = n;
            h1 = n;
        }

        h1 = head;
        // Since each copy follows its original, random.next identifies the copied target.
        while (h1 != null) {
            h1.next.random = h1.random == null ? null : h1.random.next;
            h1 = h1.next.next;
        }

        Node dummy = new Node(-1), current = dummy;
        h1 = head;

        // Detach alternating original/copy nodes. Restoring h1.next before advancing
        // re-establishes the original list while current builds the independent copy.
        while (h1 != null) {
            n = h1.next.next;
            current.next = h1.next;
            current = current.next;
            h1.next = n;
            h1 = n;
        }

        return dummy.next;
    }
}
