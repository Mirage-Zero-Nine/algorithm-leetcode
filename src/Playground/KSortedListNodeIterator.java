package Playground;

import Lib.ListNode.ListNode;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Iterate all nodes in K sorted linked list.
 *
 * @author BorisMirage
 * Time: 2019/08/21 16:01
 * Created with IntelliJ IDEA
 */

public class KSortedListNodeIterator {

    private PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    });

    /**
     * Constructor of iterator.
     *
     * @param list given K sorted list
     */
    public KSortedListNodeIterator(ListNode[] list) {
        Collections.addAll(heap, list);
    }

    /**
     * If there is a next node in iterator.
     *
     * @return true if there is a node in iterator
     */
    public boolean hasNext() {
        return !heap.isEmpty();
    }

    /**
     * Return next node in iterator.
     *
     * @return next node in iterator
     */
    public ListNode next() {

        if (!hasNext()) {
            return null;
        }

        ListNode n = heap.poll();

        if (n != null && n.next != null) {
            ListNode tmp = n.next;
            heap.add(tmp);
        }

        return n;
    }

    /**
     * Print part of the list.
     *
     * @param n n nodes
     * @param i iterator
     */
    private static void helper(int n, KSortedListNodeIterator i) {

        for (int j = 0; j < n; j++) {
            System.out.println(i.hasNext());
            ListNode node = i.next();
            if (node != null) {
                System.out.println(node.val);
            } else {
                System.out.println(node);
            }
        }
    }

    /**
     * Generate linked list from start to end - 1 (end is not included).
     *
     * @param start start value
     * @param end   end value (end in list is end - 1, end is not included)
     * @return linked list
     */
    private static ListNode genList(int start, int end) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        for (int i = start; i < end; i++) {
            head.next = new ListNode(i);
            head = head.next;
        }

        return dummy.next;
    }

    /**
     * Print all elements in linked list.
     *
     * @param head head of list
     */
    private static void printAll(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        KSortedListNodeIterator test = new KSortedListNodeIterator(new ListNode[]{genList(5, 10), genList(3, 8)});
        helper(11, test);
        test = new KSortedListNodeIterator(new ListNode[]{genList(1, 10), genList(3, 8), genList(81, 90)});
        helper(20, test);
    }
}
