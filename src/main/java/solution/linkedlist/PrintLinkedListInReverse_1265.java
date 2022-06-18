package solution.linkedlist;

import library.ImmutableListNode;

/**
 * You are given an immutable linked list, print out all values of each node in reverse with the help of the following interface:
 * ImmutableListNode: An interface of immutable linked list, you are given the head of the list.
 * You need to use the following functions to access the linked list (you can't access the ImmutableListNode directly):
 * 1. ImmutableListNode.printValue(): Print value of the current node.
 * 2. ImmutableListNode.getNext(): Return the next node.
 * The input is only given to initialize the linked list internally.
 * You must solve this problem without modifying the linked list.
 * In other words, you must operate the linked list using only the mentioned APIs.
 *
 * @author BorisMirage
 * Time: 2020/03/25 16:06
 * Created with IntelliJ IDEA
 */

public class PrintLinkedListInReverse_1265 {
    /**
     * Reverse linked list in recursive way.
     *
     * @param head head of list
     */
    public void printLinkedListInReverse(ImmutableListNode head) {

        /* Corner case */
        if (head == null) {
            return;
        }

        printLinkedListInReverse(head.getNext());
        head.printValue();
    }

    /**
     * Reach O(1) space.
     * Each time, print ith node starts from last node in list.
     *
     * @param head head of list
     */
    public void printLinkedListInReverseOptimizeSpace(ImmutableListNode head) {
        int n = countNodes(head);     // find total nodes in list
        for (int i = n; i >= 1; i--) {
            printNthNode(head, i);
        }
    }

    /**
     * Print ith node in list
     *
     * @param head  head of list
     * @param index ith node index
     */
    private void printNthNode(ImmutableListNode head, int index) {
        ImmutableListNode node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.getNext();
        }
        node.printValue();
    }

    /**
     * Count number of nodes in list.
     *
     * @param head head of list
     * @return number of nodes in list
     */
    private int countNodes(ImmutableListNode head) {
        int count = 0;
        ImmutableListNode node = head;
        while (node != null) {
            count++;
            node = node.getNext();
        }

        return count;
    }
}
