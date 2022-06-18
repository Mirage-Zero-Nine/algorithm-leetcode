package solution.twopointers;

import library.listnode.LinkedListGenerator;
import library.listnode.ListNode;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers.
 * Leaving only distinct numbers from the original list.
 *
 * @author BorisMirage
 * Time: 2018/08/16 14:03
 * Created with IntelliJ IDEA
 */

public class DeleteDuplicates_82 {
    /**
     * Two pointers.
     * One point to previous non-duplicate element, the other one point to next non-duplicate element.
     *
     * @param head first ListNode
     * @return removed list head node
     */
    public ListNode deleteDuplicates(ListNode head) {

        ListNode dummy = new ListNode(0);       // cache node
        dummy.next = head;

        ListNode previous = dummy, current = head;     // two pointers

        while (current != null) {
            while (current.next != null && current.val == current.next.val) {     // move pass all duplicated nodes
                current = current.next;
            }

            if (previous.next == current) {        // no duplicated node found
                previous = previous.next;
            } else {
                previous.next = current.next;      // move previous non-duplicated value to next non-duplicated value
            }
            current = current.next;
        }

        return dummy.next;      // return head of list
    }

    public static void main(String[] args) {
        LinkedListGenerator.printAll(new DeleteDuplicates_82().deleteDuplicates(LinkedListGenerator.generate(new int[]{1, 2, 3, 3, 4, 5, 5, 5})));
    }
}
