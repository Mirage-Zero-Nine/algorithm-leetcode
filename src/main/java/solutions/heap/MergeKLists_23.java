package solutions.heap;

import library.listnode.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.
 *
 * @author BorisMirage
 * Time: 2018/06/13 12:48
 * Created with IntelliJ IDEA
 */

public class MergeKLists_23 {
    /**
     * Merges sorted lists with a min-heap.
     *
     * <p>The heap stores only the first not-yet-used node from each non-empty
     * input list. After the smallest node is removed, its next node becomes the
     * only new candidate from that same list. Therefore the heap has at most one
     * candidate per input list, and every removed node is the next node in the
     * final sorted result.</p>
     *
     * <p>This method relinks the input nodes and returns them in a new order; it
     * does not create replacement nodes. With {@code n} total nodes and {@code k}
     * input lists, the time complexity is {@code O(n log k)} and the auxiliary
     * space complexity is {@code O(k)}.</p>
     *
     * @param lists sorted linked lists, or {@code null}
     * @return the merged sorted list, or {@code null} when no list has a node
     */
    public ListNode mergeKListsHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(n -> n.val));
        ListNode dummy = new ListNode(-1), current = dummy;
        Arrays.stream(lists).filter(Objects::nonNull).forEach(pq::add);

        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll();
            current.next = smallest;
            current = current.next;
            if (smallest.next != null) {
                pq.add(smallest.next);
            }
        }

        return dummy.next;
    }

    /**
     * Merges sorted lists by repeatedly merging neighboring pairs.
     *
     * <p>At the start of a round, every entry in the working array is a sorted
     * list. The loop merges entries {@code 0} and {@code 1}, then entries
     * {@code 2} and {@code 3}, and so on. The next round works on those merged
     * results, so each round doubles the number of original lists represented by
     * one entry. When only one entry remains, it contains every input node in
     * sorted order. This is the bottom-up, iterative form of divide and conquer.</p>
     *
     * <p>The pairwise merge relinks the original nodes, so node objects and their
     * values are reused. The temporary working arrays hold only list heads and
     * require {@code O(k)} auxiliary space. Each round visits all {@code n}
     * nodes, and there are {@code O(log k)} rounds, giving {@code O(n log k)}
     * time. The method uses no recursion and therefore does not use a call stack
     * proportional to the length of a list.</p>
     *
     * @param lists sorted linked lists, or {@code null}
     * @return the merged sorted list, or {@code null} when no list has a node
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if (lists == null || lists.length == 0) {
            return null;
        }
        return divide(lists, 0, lists.length - 1);
    }

    /**
     * Performs the iterative pairwise rounds for a range of input lists.
     *
     * <p>A local array is used for each round so the caller's array of list heads
     * is not reordered. The linked-list nodes themselves are still relinked by
     * {@link #mergeTwoLists(ListNode, ListNode)}.</p>
     *
     * @param lists input array containing sorted lists
     * @param start inclusive index of the first list to merge
     * @param end   inclusive index of the last list to merge
     * @return the merged list for the requested range, or {@code null} for an
     * empty range
     */
    public ListNode divide(ListNode[] lists, int start, int end) {
        if (lists == null || lists.length == 0 || start > end) {
            return null;
        }

        ListNode[] current = Arrays.copyOfRange(lists, start, end + 1);
        while (current.length > 1) {
            ListNode[] next = new ListNode[(current.length + 1) / 2];

            for (int i = 0; i < current.length; i += 2) {
                int outputIndex = i / 2;
                if (i + 1 < current.length) {
                    // Merging adjacent pairs keeps each round balanced and
                    // reduces the number of independent lists by about half.
                    next[outputIndex] = mergeTwoLists(current[i], current[i + 1]);
                } else {
                    // An odd final list has no partner in this round; carry it
                    // forward so it participates in the next round.
                    next[outputIndex] = current[i];
                }
            }
            current = next;
        }
        return current[0];
    }

    /**
     * Merges two sorted linked lists by walking through both lists once.
     *
     * <p>The smaller current node is attached to the result, and that list's
     * pointer advances. Once one list is exhausted, the remaining suffix of the
     * other list is already sorted and can be attached directly. A dummy head
     * makes the first attachment follow the same rule as every later attachment.</p>
     *
     * <p>The method relinks the supplied nodes and does not allocate nodes for
     * the result. Its time complexity is {@code O(a + b)} for input lengths
     * {@code a} and {@code b}, and its auxiliary space complexity is {@code O(1)}.</p>
     *
     * @param l1 first sorted list
     * @param l2 second sorted list
     * @return both lists merged in sorted order
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        // The remaining suffix is already sorted, so no more comparisons are
        // needed after one input list reaches its end.
        tail.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}
